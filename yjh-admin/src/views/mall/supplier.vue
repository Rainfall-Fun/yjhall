<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.id" clearable class="filter-item" style="width: 200px;" placeholder="请输入供应商ID"/>
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入供应商名称"/>
      <el-button v-permission="['GET /admin/supplier/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /admin/supplier/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>


      <el-table-column align="center" label="供应商名称" prop="gysmc"/>
      <el-table-column align="center" label="供应商会员编号" prop="gyshybh"/>
      <el-table-column align="center" label="联系人" prop="lxr"/>
      <el-table-column align="center" label="联系电话" prop="lxdh"/>

      <el-table-column align="center" min-width="400px" label="供应商介绍" prop="gysjj"/>
      <el-table-column align="center" label="所属业务员" prop="ssywy"/>
      <el-table-column align="center" label="添加入" prop="tjr"/>
      <el-table-column align="center" label="添加时间" prop="tjsj"/>
      <el-table-column align="center" label="最后修改人" prop="zhxgr"/>
      <el-table-column align="center" label="最后修改时间" prop="zhxgsj"/>


      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /admin/supplier/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /admin/supplier/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="供应商名称" prop="gysmc">
          <el-input v-model="dataForm.gysmc"/>
        </el-form-item>
        <el-form-item label="所属业务区域">
          <el-cascader
            placeholder="选择所属区域，可搜索"
            :options="areaList"
            filterable
            change-on-select
            clearable
            expand-trigger="hover"
            @change="handleAreaChange"/>
        </el-form-item>
        <!--<el-form-item label="供应商会员编号" prop="gyshybh">-->
          <!--<el-select v-model="dataForm.gyshybh">-->
            <!--<el-option v-for="item in supplierCodeList" :key="item.value" :label="item.label" :value="item.value"/>-->
          <!--</el-select>-->
          <!--&lt;!&ndash;<el-input v-model="dataForm.gyshybh"/>&ndash;&gt;-->
        <!--</el-form-item>-->
        <el-form-item label="联系人" prop="lxr">
          <el-input v-model="dataForm.lxr"/>
        </el-form-item>
        <el-form-item label="联系电话" prop="lxdh">
          <el-input v-model="dataForm.lxdh"/>
        </el-form-item>
        <el-form-item label="供应商简介" prop="gysjj">
          <el-input v-model="dataForm.gysjj"/>
        </el-form-item>
        <el-form-item label="所属业务员" prop="ssywy">
          <el-input v-model="dataForm.ssywy"/>
        </el-form-item>
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
  import { getAreaList,listSupplier, createSupplier, updateSupplier, deleteSupplier } from '@/api/supplier'
  import { uploadPath } from '@/api/storage'
  import { getToken } from '@/utils/auth'
  import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

  export default {
    name: 'Supplier',
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
          sort: 'tjsj',
          order: 'desc'
        },
        dataForm: {
          gysmc: '',
          hybh:'',
          lxr:'',
          lxdh:'',
          gysjj:'',
          ssywy:''


        },
        areaList:[],
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '创建'
        },
        rules: {
          name: [
            { required: true, message: '供应商商名称不能为空', trigger: 'blur' }
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
        this.listLoading = true
        listSupplier(this.listQuery).then(response => {
        this.list = response.data.data.list
        this.total = response.data.data.total
        this.listLoading = false
      })
      .catch(() => {
        this.list = []
        this.total = 0
        this.listLoading = false
      });
        if(this.areaList.length==0){
          getAreaList().then(response => {
            this.areaList = response.data.data.areaList;
        });
        }

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
      uploadPicUrl: function(response) {
        this.dataForm.picUrl = response.data.url
      },
      createData() {
        this.$refs['dataForm'].validate(valid => {
          if (valid) {
            createSupplier(this.dataForm)
              .then(response => {
              console.log(response.data.data)
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
            updateSupplier(this.dataForm)
              .then(() => {
              for (const v of this.list) {
              if (v.id === this.dataForm.id) {
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
        deleteSupplier(row)
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
      handleAreaChange(value) {
        // var result='';
        // for(var i=0;i<value.length;i++){
        //   result=i<value.length-1?result+value[i]+',':result+value[i];
        // }
        this.dataForm.ywqybm =  value[value.length - 1]
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
