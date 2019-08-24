<template>
  <div class="app-container">

    <div class="filter-container">
      <el-input v-model="listQuery.id" clearable class="filter-item" style="width: 200px;" placeholder="请输扩展属性ID"/>
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入扩展属性名称"/>
      <el-button v-permission="['GET /admin/extensionAttribute/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /admin/extensionAttribute/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="扩展属性ID" prop="kzsxbm"/>

      <el-table-column align="center" label="扩展属性名称" prop="sxmc"/>

      <!--<el-table-column align="center" label="属性数据类型" prop="sxsjlx"/>-->
      <!--<el-table-column align="center" label="宽度" prop="kd"/>-->
      <!--<el-table-column align="center" label="小数位数" prop="xsws"/>-->
      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /admin/extensionAttribute/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /admin/extensionAttribute/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="扩展属性名称" prop="sxmc">
          <el-input v-model="dataForm.sxmc"/>
        </el-form-item>
        <!--<el-form-item label="扩展属性数据类型" prop="sxsjlx">-->
          <!--<el-input v-model="dataForm.sxsjlx"/>-->
        <!--</el-form-item>-->
        <!--<el-form-item label="宽度" prop="kd">-->
          <!--<el-input v-model="dataForm.kd"/>-->
        <!--</el-form-item>-->
        <!--<el-form-item label="小数位数" prop="xsws">-->
          <!--<el-input v-model="dataForm.xsws"/>-->
        <!--</el-form-item>-->

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #20a0ff;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 120px;
    height: 120px;
    line-height: 120px;
    text-align: center;
  }
  .avatar {
    width: 145px;
    height: 145px;
    display: block;
  }
</style>

<script>
  import { listCat,listExtensionAttribute, createExtensionAttribute, editExtensionAttribute, deleteExtensionAttribute } from '@/api/extensionAttribute'
  import { uploadPath } from '@/api/storage'
  import { getToken } from '@/utils/auth'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  export default {
    name: 'Brand',
    components: { Pagination },
    data() {
      return {
        list: [],
        total: 0,
        listLoading: true,
        listQuery: {
          page: 1,
          limit: 20,
          id: undefined,
          name: undefined,
          sort: 'kzsxbm',
          order: 'desc'
        },
        dataForm: {
          kzsxbm: undefined,
          sxmc: '',
          sxsjlx: '',
          kd: undefined,
          sxws: undefined,
        },
        categoryList:[],
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '创建'
        },
        rules: {
          sxmc: [
            { required: true, message: '品牌商名称不能为空', trigger: 'blur' }
          ]
        },
        downloadLoading: false
      }
    },
    computed: {
      headers() {
        return {
          'X-Litemall-Admin-Token': getToken()
        }
      }
    },
    created() {
      this.getList()
    },
    methods: {
      getList() {
        listExtensionAttribute(this.listQuery)
          .then(response => {
          this.list = response.data.data.list
        this.total = response.data.data.total
        this.listLoading = false
      })
      .catch(() => {
          this.list = []
        this.total = 0
        this.listLoading = false
      })
        listCat().then(response=>{
          this.categoryList=response.data.data.categoryList
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },
      resetForm() {
        this.dataForm = {
          id: undefined,
          name: '',
          desc: '',
          floorPrice: undefined,
          picUrl: undefined
        }
      },
      handleCreate() {
        this.resetForm()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
      })
      },
      createData() {
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            createExtensionAttribute(this.dataForm).then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
              title: '成功',
              message: '创建成功'
            })
          })
          .catch(response => {
              this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
          }
        })
      },
      handleCategoryChange(value) {
        var result='';
        for(var i=0;i<value.length;i++){
          result=i<value.length-1?result+value[i]+',':result+value[i];
        }
        this.goods.categoryId = result
      },
      handleUpdate(row) {
        this.dataForm = Object.assign({}, row)
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
      })
      },
      updateData() {
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            editExtensionAttribute(this.dataForm)
              .then(() => {
              for (const v of this.list) {
              if (v.kzsxbm === this.dataForm.kzsxbm) {
                const index = this.list.indexOf(v)
                this.list.splice(index, 1, this.dataForm)
                break
              }
            }
            this.dialogFormVisible = false
            this.$notify.success({
              title: '成功',
              message: '更新成功'
            })
          })
          .catch(response => {
              this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
          }
        })
      },
      handleDelete(row) {
        deleteExtensionAttribute(row)
          .then(response => {
          this.$notify.success({
          title: '成功',
          message: '删除成功'
        })
        const index = this.list.indexOf(row)
        this.list.splice(index, 1)
      })
      .catch(response => {
          this.$notify.error({
          title: '失败',
          message: response.data.errmsg
        })
      })
      },
      handleDownload() {
        this.downloadLoading = true
        import('@/vendor/Export2Excel').then(excel => {
          const tHeader = [
            '品牌商ID',
            '品牌商名称',
            '介绍',
            '低价',
            '品牌商图片'
          ]
          const filterVal = ['id', 'name', 'desc', 'floorPrice', 'picUrl']
          excel.export_json_to_excel2(
          tHeader,
          this.list,
          filterVal,
          '品牌商信息'
        )
        this.downloadLoading = false
      })
      }
    }
  }
</script>
