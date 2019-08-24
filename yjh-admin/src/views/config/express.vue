<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.id" clearable class="filter-item" style="width: 200px;" placeholder="请输入运费规则ID"/>
      <el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入运费规则名称"/>
      <el-button v-permission="['GET /admin/express/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /admin/express/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="费用规则ID" prop="fygzbm"/>

      <el-table-column align="center" label="费用规则" prop="fygz"/>

      <el-table-column align="center" label="费用"  prop="fy"/>
      <el-table-column align="center" label="是否有效"  prop="sfyx">
        <template slot-scope="scope">
          <el-tag :type="scope.row.sfyx ? '1' : '0' ">{{ scope.row.sfyx ? '是' : '否' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="设置时间"  prop="szsj"/>
      <el-table-column align="center" label="备注"  prop="bz"/>
      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /admin/express/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /admin/express/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="运费规则" prop="yfgzbm">
          <el-select v-model="dataForm.yfgzbm"  placeholder="请选择">
            <el-option
              v-for="item in expressRuleList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="费用规则" prop="fygz">
          <el-input v-model="dataForm.fygz"/>
        </el-form-item>
        <el-form-item label="费用" prop="fy">
          <el-input v-model="dataForm.fy"/>
        </el-form-item>
        <el-form-item label="是否有效" prop="sfyx">
          <el-select v-model="dataForm.sfyx">
            <el-option label="是" value="1"/>
            <el-option label="否" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="bz">
          <el-input v-model="dataForm.bz"/>
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
  import { expressRuleList,listExpress, createExpress, updateExpress, deleteExpress } from '@/api/express'
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
          sort: 'szsj',
          order: 'desc'
        },
        dataForm: {
          fygzbm: undefined,
          sfyx: undefined,
          bz: undefined,
          fygz:undefined,
          fy:undefined
        },
        expressRuleList:[],
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '创建'
        },
        rules: {
          fygz: [{ required: true, message: '费用规则不能为空', trigger: 'blur' }],
          yfgzbm: [{ required: true, message: '运费规则规则不能为空', trigger: 'blur' }],
          sfyx: [{ required: true, message: '是否有效不能为空', trigger: 'blur' }],
          fy: [{ required: true, message: '请填写正确的金额,保留两位小数',pattern: /(^[\d]|^[1-9][\d]*)($|[\.][\d]{0,2}$)/,trigger:'blur' }]
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
        listExpress(this.listQuery)
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

      expressRuleList().then(response=> {
          this.expressRuleList=response.data.data.expressRuleList
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
            createExpress(this.dataForm)
              .then(response => {
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
        row.sfyx=String(row.sfyx)
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
            updateExpress(this.dataForm)
              .then(() => {
              for (const v of this.list) {
              if (v.fygzbm == this.dataForm.fygzbm) {
                this.dataForm.sfyx=Number(this.dataForm.sfyx)
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
        deleteExpress(row)
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
