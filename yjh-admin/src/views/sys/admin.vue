<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.username" clearable class="filter-item" style="width: 200px;" placeholder="请输入管理员名称"/>
      <el-button v-permission="['GET /admin/admin/list']" class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button v-permission="['POST /admin/admin/create']" class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="管理员ID" prop="yhbm" sortable/>

      <el-table-column align="center" label="管理员用户名" prop="yhm"/>


      <el-table-column align="center" label="管理员角色" prop="jsbm">

      </el-table-column>
      <el-table-column align="center" label="是否是本单位" prop="sfsbdw">
        <template slot-scope="scope">
          <el-tag :type="scope.row.sfsbdw ? '1' : '0' ">{{ scope.row.sfsbdw ? '是' : '否' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否有效" prop="sfyx">
        <template slot-scope="scope">
          <el-tag :type="scope.row.sfyx ? '1' : '0' ">{{ scope.row.sfyx ? '是' : '否' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button v-permission="['POST /admin/admin/update']" type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button v-permission="['POST /admin/admin/delete']" type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
          <el-button v-permission="['GET /admin/role/permissions']" type="primary" size="mini" @click="handlePermission(scope.row)">授权</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="管理员用户名" prop="yhm">
          <el-input v-model="dataForm.yhm"/>
        </el-form-item>
        <el-form-item label="管理员密码" prop="mm">
          <el-input v-model="dataForm.mm" type="password" auto-complete="off"/>
        </el-form-item>
        <!--<el-form-item label="管理员头像" prop="avatar">-->
          <!--<el-upload-->
            <!--:headers="headers"-->
            <!--:action="uploadPath"-->
            <!--:show-file-list="false"-->
            <!--:on-success="uploadAvatar"-->
            <!--class="avatar-uploader"-->
            <!--accept=".jpg,.jpeg,.png,.gif">-->
            <!--<img v-if="dataForm.avatar" :src="dataForm.avatar" class="avatar">-->
            <!--<i v-else class="el-icon-plus avatar-uploader-icon"/>-->
          <!--</el-upload>-->
        <!--</el-form-item>-->
        <el-form-item label="供应商" prop="gysbm">
          <el-select v-model="dataForm.gysbm"  placeholder="请选择">
            <el-option
              v-for="item in supplierList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="用户类别" prop="yhlbbm">
          <el-select v-model="dataForm.yhlbbm"  placeholder="请选择">
            <el-option
              v-for="item in categoryList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="管理员角色" prop="jsbm">
          <el-select v-model="dataForm.jsbm"  placeholder="请选择">
            <el-option
              v-for="item in roleList"
              :key="item.value"
              :label="item.label"
              :value="item.value"/>
          </el-select>
        </el-form-item>
        <el-form-item label="是否是本单位" prop="sfsbdw">
          <el-select v-model="dataForm.sfsbdw" @change="onLevelChange">
            <el-option label="是" value="1"/>
            <el-option label="否" value="0"/>
          </el-select>
        </el-form-item>
        <el-form-item label="是否有效" prop="sfyx">
          <el-select v-model="dataForm.sfyx" @change="onLevelChange">
            <el-option label="是" value="1"/>
            <el-option label="否" value="0"/>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

    <!-- 权限配置对话框 -->
    <el-dialog :visible.sync="permissionDialogFormVisible" title="权限配置">
      <el-tree
        ref="tree"
        :data="systemPermissions"
        :default-checked-keys="assignedPermissions"
        show-checkbox
        node-key="id"
        highlight-current>
        <span slot-scope="{ node, data }" class="custom-tree-node">
          <span>{{ data.label }}</span>
          <el-tag v-if="data.api" type="success" size="mini">{{ data.api }}</el-tag>
        </span>
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <el-button @click="permissionDialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="updatePermission">确定</el-button>
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
import { updatePermission,listSupplierAndRoleAndCategory,listAdmin, createAdmin, updateAdmin, deleteAdmin ,getPermission} from '@/api/admin'
import { roleOptions } from '@/api/role'
import { getToken } from '@/utils/auth'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'Admin',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      roleOptions: null,
      listLoading: true,
      categoryList:[],
      supplierList:[],
      roleList:[],
      listQuery: {
        page: 1,
        limit: 20,
        username: undefined,
        sort: 'jsbm',
        order: 'desc'
      },
      dataForm: {
        yhbm: undefined,
        yhm: undefined,
        mm: undefined,
        gysbm:undefined,
        yhlbbm:undefined,
        jsbm:undefined,
        sfsbdw:undefined,
        sfyx:undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      rules: {
        yhm: [{ required: true, message: '管理员名称不能为空', trigger: 'blur' }],
        mm: [{ required: true, message: '密码不能为空', trigger: 'blur' }],
        yhlbbm:[{ required: true, message: '用户类别必选', trigger: 'blur' }],
        sfsbdw:[{ required: true, message: '是否是本单位必选', trigger: 'blur' }],
        sfyx:[{ required: true, message: '是否有效必选', trigger: 'blur' }]
      },
      downloadLoading: false,
      permissionDialogFormVisible: false,
      systemPermissions: null,
      assignedPermissions: null,
      permissionForm: {
        roleId: undefined,
        permissions: []
      }
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

    roleOptions()
      .then(response => {
        this.roleOptions = response.data.data.list
      })
  },
  methods: {
    formatRole(roleId) {
      for (let i = 0; i < this.roleOptions.length; i++) {
        if (roleId === this.roleOptions[i].value) {
          return this.roleOptions[i].label
        }
      }
      return ''
    },
    getList() {
      this.listLoading = true
      listAdmin(this.listQuery)
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
      listSupplierAndRoleAndCategory().then(response => {
        this.categoryList = response.data.data.categoryList
        this.supplierList=response.data.data.supplierList
        this.roleList=response.data.data.roleList
    })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetForm() {
      this.dataForm = {
        id: undefined,
        username: undefined,
        password: undefined,
        avatar: undefined,
        roleIds: []
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
          createAdmin(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '添加管理员成功'
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
      row.sfsbdw=String(row.sfsbdw)
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
          updateAdmin(this.dataForm)
            .then(() => {
              for (const v of this.list) {
                if (v.yhbm === this.dataForm.yhbm) {
                  const index = this.list.indexOf(v)
                  this.dataForm.sfyx=Number(this.dataForm.sfyx)
                  this.dataForm.sfsbdw=Number(this.dataForm.sfsbdw)
                  this.list.splice(index, 1, this.dataForm)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '更新管理员成功'
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
      deleteAdmin(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除管理员成功'
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
        const tHeader = ['管理员ID', '管理员名称', '管理员头像']
        const filterVal = ['id', 'username', 'avatar']
        excel.export_json_to_excel2(
          tHeader,
          this.list,
          filterVal,
          '管理员信息'
        )
        this.downloadLoading = false
      })
    },
    handlePermission(row) {
      this.permissionDialogFormVisible = true
      this.permissionForm.yhbm = row.yhbm
      getPermission({ userId: row.yhbm })
        .then(response => {
        this.systemPermissions = response.data.data.systemPermissions
      this.assignedPermissions = response.data.data.assignedPermissions
      console.log(this.systemPermissions)
      console.log(this.assignedPermissions)
    })
    },
    updatePermission() {
      this.permissionForm.permissions = this.$refs.tree.getCheckedKeys(true)
      updatePermission(this.permissionForm)
        .then(response => {
        this.permissionDialogFormVisible = false
      this.$notify.success({
        title: '成功',
        message: '授权成功'
      })
    })
    .catch(response => {
        this.$notify.error({
        title: '失败',
        message: response.data.errmsg
      })
    })
    }
  }
}
</script>
