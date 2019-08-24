<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-cascader
        placeholder="选择所属分类，可搜索"
        :options="categoryList"
        filterable
        change-on-select
        clearable
        expand-trigger="hover"
        @change="handleCategoryChange"/>
      <!--<el-input v-model="listQuery.id" clearable class="filter-item" style="width: 200px;" placeholder="请输扩展属性ID"/>-->
      <!--<el-input v-model="listQuery.name" clearable class="filter-item" style="width: 200px;" placeholder="请输入扩展属性名称"/>-->
      <!--<el-button v-permission="['GET /admin/extensionAttribute/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>-->
      <el-button v-permission="['POST /admin/extensionAttribute/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>

      <el-table-column align="center" label="扩展属性ID" prop="extensionAttributeId"/>

      <el-table-column align="center" label="扩展属性名称" prop="extensionAttributeName"/>

      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /admin/extensionAttribute/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" >
      <template>
        <el-transfer
          :titles="title"
          filterable
          v-model="value"
          :props="{
          key: 'kzsxbm',
          label: 'sxmc'
          }"
          :data="data">
        </el-transfer>
      </template>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
  .el-transfer-panel {
    border: 1px solid #EBEEF5;
    border-radius: 4px;
    overflow: hidden;
    background: #FFF;
    display: inline-block;
    vertical-align: middle;
    width: 300px;
    max-height: 100%;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    position: relative;
  }
</style>

<script>
  import { listCat} from '@/api/extensionAttribute'
  import {createCategoryExtension,getAllExtensionAttributes,getGoodsAttribute,deleteCategoryExtension} from '@/api/categoryExtension'
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
        data:[],
        listLoading: false,
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
        title:['所有扩展属性值','该分类下的扩展属性值'],
        categoryList:[],
        dialogFormVisible: false,
        dialogStatus: '',
        categoryId:undefined,
        textMap: {
          create: '修改该分类下的商品扩展属性'
        },
        rules: {
          sxmc: [
            { required: true, message: '品牌商名称不能为空', trigger: 'blur' }
          ]
        },
        downloadLoading: false,
        value: []
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
      //   listExtensionAttribute(this.listQuery)
      //     .then(response => {
      //     this.list = response.data.data.list
      //   this.total = response.data.data.total
      //   this.listLoading = false
      // })
      // .catch(() => {
      //     this.list = []
      //   this.total = 0
      //   this.listLoading = false
      // })
        listCat().then(response=>{
          this.categoryList=response.data.data.categoryList
        })
        getAllExtensionAttributes().then(response=>{
          this.data=response.data.data
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
      },
      createData() {
        const add={
          value:this.value,
          categoryId:this.categoryId
        }
        createCategoryExtension(add).then(response => {
          this.list=response.data.data.data
        this.$notify.success({
          title: '成功',
          message: '添加成功'
        })
        this.dialogFormVisible=false
      })
      .catch(response => {
          this.$notify.error({
          title: '失败',
          message: response.data.errmsg
        })
      })
      },
      handleCategoryChange(value) {
        this.value=[]
        this.categoryId=value[value.length-1]
        this.listLoading=true
        getGoodsAttribute(this.categoryId).then(response=>{
          this.list=response.data.data
          for(var i=0;i<response.data.data.length;i++)
            this.value.push(this.list[i].extensionAttributeId)
          this.listLoading=false
        })
      },
      handleDelete(row) {
        const categoryExtension={
          extensionAttributeVo:row,
          categoryId:this.categoryId
        }
        deleteCategoryExtension(categoryExtension)
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
