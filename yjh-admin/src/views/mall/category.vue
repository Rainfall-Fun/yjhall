<template>
  <div class="app-container">
    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-button
        v-permission="['POST /admin/category/create']"
        class="filter-item"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate"
      >添加</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="正在查询中。。。"
      border
      fit
      highlight-current-row
      row-key="id"
    >
      <el-table-column align="center" min-width="90" label="类目ID" prop="id" />

      <el-table-column align="center" label="类目名" prop="name" />

      <el-table-column align="center" label="所述板块" prop="block" />

      <el-table-column align="center" property="picUrl" label="类目图片">
        <template slot-scope="scope">
          <img v-if="scope.row.picUrl" :src="scope.row.picUrl" width="80" />
        </template>
      </el-table-column>

      <el-table-column align="center" label="是否失效" prop="available">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.available === '1' ? 'primary' : 'info' "
          >{{ scope.row.available === '1' ? '未失效' : '失效' }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" min-width="100" label="简介" prop="desc" />

      <el-table-column align="center" label="级别" prop="level">
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.level === '1' ? 'primary' : 'info' "
          >{{ scope.row.level === '1' ? '一级类目' : (scope.row.level==='2'?'二级类目':(scope.row.level==='3'?'三级类目':'四级类目')) }}</el-tag>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            v-permission="['POST /admin/category/update']"
            type="primary"
            size="mini"
            @click="handleUpdate(scope.row)"
          >编辑</el-button>
          <el-button
            v-permission="['POST /admin/category/delete']"
            type="danger"
            size="mini"
            @click="handleDelete(scope.row)"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form
        ref="dataForm"
        :rules="rules"
        :model="dataForm"
        status-icon
        label-position="left"
        label-width="100px"
        style="width: 400px; margin-left:50px;"
      >
        <el-form-item label="类目名称" prop="name">
          <el-input v-model="dataForm.name" />
        </el-form-item>

        <el-form-item label="类目ID" prop="id" v-show="false">
          <el-label v-model="dataForm.id" v-text="dataForm.id" />
        </el-form-item>

        <el-form-item label="所述板块" prop="block">
          <el-cascader
            :options="block"
            v-model="dataForm.blockOptions"
            :show-all-levels="true"
            :change-on-select="true"
            placeholder="请选择所属板块"
            filterable
            value="{this.value}"
          ></el-cascader>
        </el-form-item>

        <el-form-item label="是否失效" prop="available">
          <el-select v-model="dataForm.available" @change="onAvailableChange">
            <el-option label="未失效" value="1" />
            <el-option label="失效" value="0" /> 
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="级别" prop="level">
          <el-select v-model="dataForm.level" @change="onLevelChange">
            <el-option label="一级类目" value="1" />
            <el-option label="二级类目" value="2" />
            <el-option label="三级类目" value="3" />
            <el-option label="四级类目" value="4" />
          </el-select>
        </el-form-item>-->
        <!-- <el-form-item v-if="dataForm.level !== '1'" label="父类目" prop="pid">
          <el-select v-model="dataForm.pid">
            <el-option
              v-for="item in catL1"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>-->

        <el-form-item label="所属类别" prop="selectedOptions">
          <el-cascader
            :options="catL1"
            v-model="dataForm.selectedOptions"
            :show-all-levels="true"
            :change-on-select="true"
            @change="onchange"
            placeholder="请选择分类"
            filterable
            clearable
          ></el-cascader>
        </el-form-item>
        <el-form-item label="类目图片" prop="picUrl">
          <el-upload
            :headers="headers"
            :action="uploadPath"
            :show-file-list="false"
            :on-success="uploadPicUrl"
            class="avatar-uploader"
            accept=".jpg, .jpeg, .png, .gif"
          >
            <img v-if="dataForm.picUrl" :src="dataForm.picUrl" class="avatar" />
            <i v-else class="el-icon-plus avatar-uploader-icon" />
          </el-upload>
        </el-form-item>
        <el-form-item label="类目简介" prop="desc">
          <el-input v-model="dataForm.desc" />
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

<style scoped>
.filter-item {
  margin-left: 100px;
}
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
import {
  listCategory,
  listCatL1,
  listBlock,
  getCategoryList,
  createCategory,
  updateCategory,
  deleteCategory
} from "@/api/category";
import { uploadPath } from "@/api/storage";
import { getToken } from "@/utils/auth";

export default {
  name: "Category",
  data() {
    return {
      uploadPath,
      list: [],
      listLoading: true,
      catL1: {},
      dataForm: {
        id: undefined,
        name: "",
        // level: "2",
        available: "1",
        blockOptions: [],
        selectedOptions: [],
        // pid: "0",
        desc: "",
        picUrl: ""
      },
      dialogFormVisible: false,
      dialogStatus: "",
      textMap: {
        update: "编辑",
        create: "创建"
      },
      rules: {
        name: [{ required: true, message: "类目名不能为空", trigger: "blur" }]
      }
    };
  },
  computed: {
    headers() {
      return {
        "X-Litemall-Admin-Token": getToken()
      };
    }
  },
  created() {
    this.getList();
    this.getCatL1();
    this.getBlock();
  },
  methods: {
    getList() {
      this.listLoading = true;
      listCategory()
        .then(response => {
          this.list = response.data.data.list;
          this.listLoading = false;
        })
        .catch(() => {
          this.list = [];
          this.listLoading = false;
        });
    },
    onchange(value) {
      console.log(value);
    },
    getCatL1() {
      listCatL1().then(response => {
        this.catL1 = response.data.data.list;
        console.log(this.catL1);
      });
    },
    getBlock() {
      listBlock().then(response => {
        this.block = response.data.data.list;
        console.log(this.block);
      });
    },
    resetForm() {
      this.dataForm = {
        id: undefined,
        name: "",
        // level: "2",
        available: "1",
        // pid: "0",
        selectedOptions: [],
        blockOptions: [],
        desc: "",
        picUrl: ""
      };
    },
    onAvailableChange: function(value) {
      if (value === "1") {
        this.dataForm.available = "1";
      } else if (value === "0") {
        this.dataForm.available = "0";
      }
    },
    handleCreate() {
      this.resetForm();
      this.dialogStatus = "create";
      this.dialogFormVisible = true;
      // this.selectedOptions = [];
      this.$nextTick(() => {
        this.$refs["dataForm"].clearValidate();
      });
    },
    uploadPicUrl: function(response) {
      this.dataForm.picUrl = response.data.data;
    },
    createData() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          console.log(this.dataForm);
          createCategory(this.dataForm)
            .then(response => {
              this.getList();
              // // 更新L1目录
              // this.getCatL1();
              this.dialogFormVisible = false;
              this.$notify.success({
                title: "成功",
                message: "创建成功"
              });
            })
            .catch(response => {
              this.$notify.error({
                title: "失败",
                message: response.data.errmsg
              });
            });
        }
      });
    },
    handleUpdate(row) {
      this.dataForm = Object.assign({}, row);
      for (var i = 0; i < this.block.length; i++) {
        if (this.block[i].label == row.block) {
          var temp = [];
          temp[0] = String(this.block[i].value);
          this.dataForm.blockOptions = temp;
          break;
        }
      }

      this.dataForm.selectedOptions = row.selectedOptions;

      if (this.dataForm.selectedOptions.length >= 2) {
        this.dataForm.selectedOptions[0] = this.dataForm.selectedOptions[
          this.dataForm.selectedOptions.length - 2
        ];
      } else if (this.dataForm.selectedOptions.length == 1) {
        this.dataForm.selectedOptions[0] = "0";
      }

      this.dialogStatus = "update";
      this.dialogFormVisible = true;
      this.$nextTick(() => {
        this.$refs["dataForm"].clearValidate();
      });
    },
    updateData() {
      this.$refs["dataForm"].validate(valid => {
        if (valid) {
          console.log(this.dataForm);
          updateCategory(this.dataForm)
            .then(() => {
              this.getList();
              // 更新L1目录
              this.getCatL1();
              this.dialogFormVisible = false;
              this.$notify.success({
                title: "成功",
                message: "更新成功"
              });
            })
            .catch(response => {
              this.$notify.error({
                title: "失败",
                message: response.data.errmsg
              });
            });
        }
      });
    },
    handleDelete(row) {
      deleteCategory(row)
        .then(response => {
          this.getList();
          // 更新L1目录
          this.getCatL1();
          this.$notify.success({
            title: "成功",
            message: "删除成功"
          });
        })
        .catch(response => {
          this.$notify.error({
            title: "失败",
            message: response.data.errmsg
          });
        });
    }
  }
};
</script>
