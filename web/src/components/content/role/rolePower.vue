<template>
  <div class="rolePower">
    <el-button
      type="primary"
      @click="dialogFormVisible = true"
      icon="el-icon-edit"
    >
      新增角色
    </el-button>
    <!-- 角色列表 -->
    <el-table :data="roleList" style="width: 100%;" stripe>
      <!-- <el-table-column
        prop="id"
        label="ID"
        sortable
        width="70"
      ></el-table-column> -->
      <el-table-column prop="name" label="角色" width="120"> </el-table-column>
      <el-table-column prop="note" label="简介" width="200"> </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
        sortable
        min-width="170"
      >
      </el-table-column>
      <el-table-column
        prop="updateTime"
        label="修改时间"
        sortable
        min-width="170"
      >
      </el-table-column>
      <el-table-column label="操作" min-width="220" fixed="right">
        <template slot-scope="scope">
          <el-button
            type="primary"
            plain
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            >更新菜单列表</el-button
          >
          <el-button
            type="danger"
            size="mini"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增用户对话框 -->
    <el-dialog
      title="新增用户"
      class="update-dialog"
      :visible.sync="dialogFormVisible"
      :append-to-body="true"
    >
      <el-form
        :model="addRoleForm"
        :rules="rules"
        ref="addRoleForm"
        label-width="80px"
        class="update-Form"
      >
        <el-form-item label="角色名" prop="name">
          <el-input
            v-model="addRoleForm.name"
            auto-complete="new-password"
            placeholder="请输入角色名"
          ></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="note">
          <el-input
            v-model="addRoleForm.note"
            placeholder="请输入简介"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button class="btn-reset" plain @click="resetForm('addRoleForm')"
          >取消</el-button
        >
        <el-button type="primary" @click="submitForm('addRoleForm')"
          >确认</el-button
        >
      </div>
    </el-dialog>

    <!-- 更新用户对话框 -->
    <el-dialog
      title="更新用户"
      class="update-dialog"
      :visible.sync="dialogFormVisible2"
      :append-to-body="true"
      :close="closeDialog"
    >
      <el-tree
        :props="props"
        :data="roleTree"
        show-checkbox
        node-key="id"
        ref="treeRef"
        :default-expand-all="true"
        :default-checked-keys="defaultRoleTree"
      >
      </el-tree>
      <div slot="footer" class="dialog-footer">
        <!-- <el-button class="btn-reset" type="primary" plain @click="refresh"
          >刷新</el-button
        > -->
        <el-button class="btn-reset" plain @click="resetUpdateForm"
          >取消</el-button
        >
        <el-button type="primary" @click="updateRoleForm()">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import service from "@/utils/request";

export default {
  name: "rolePower",
  created() {
    this.getRoleList();
  },
  data() {
    return {
      // 角色列表
      roleList: [],
      dialogFormVisible: false,
      dialogFormVisible2: false,
      addRoleForm: {
        name: "",
        note: "",
        createTime: ""
      },
      roleId: "",
      // 所有菜单数组
      roleTree: [],
      // 没处理前的菜单数组
      roleTreeOlder: [],
      // 用户所拥有的菜单数组
      defaultRoleTree: [],
      props: {
        label: "name",
        children: "children"
      },
      rules: {
        name: [{ required: true, message: "请输入角色名", trigger: "blur" }],
        note: [{ required: true, message: "请输入简介", trigger: "blur" }]
      }
    };
  },

  methods: {
    // 获取角色列表
    getRoleList() {
      service
        .get("role/listRole")
        .then(res => {
          // console.log(res);
          this.roleList = res.data.data;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    // 获取当前时间
    getTime() {
      var date = new Date();
      var seperator1 = "-";
      var seperator2 = ":";
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate =
        date.getFullYear() +
        seperator1 +
        month +
        seperator1 +
        strDate +
        " " +
        date.getHours() +
        seperator2 +
        date.getMinutes() +
        seperator2 +
        date.getSeconds();

      this.addRoleForm.createTime = currentdate;
    },
    // 跳转至编辑角色
    handleEdit(index, row) {
      this.roleId = row.id;
      this.defaultRoleTree = [];
      // 暂时的获取全部侧边栏
      service.get("role/menus").then(res => {
        // console.log(res.data);
        this.roleTree = res.data.data;
      });

      // 获取该角色的总菜单
      service.post("role/menu", { role_id: row.id }).then(res => {
        this.$nextTick(() => {
          this.getdefaultKey(res.data.data);
        });
      });

      this.dialogFormVisible2 = true;
    },
    // 获取该角色的默认key
    getdefaultKey(node) {
      let arr = [];
      for (let i in node) {
        for (let j in node[i].children) {
          arr.push(node[i].children[j].id);
        }
      }
      this.defaultRoleTree = arr;
    },
    // 关闭对话框清空
    closeDialog() {
      // console.log("清除");
      this.defaultRoleTree = [];
    },
    // 新增角色
    submitForm(formName) {
      this.getTime();
      this.$refs[formName].validate(valid => {
        if (valid) {
          // console.log(this.addUserForm);
          service
            .post("/role/add", this.addRoleForm)
            .then(res => {
              // console.log(res);
              if (res.data.code === 1) {
                this.$message({
                  message: "添加成功",
                  type: "success"
                });
                this.dialogFormVisible = false;
                this.$refs[formName].resetFields();
                this.getRoleList();
              } else if (res.data.code === 0) {
                this.$message({
                  message: "角色名已存在",
                  type: "warning"
                });
              } else {
                // console.log(res);
                this.$message.error("添加失败,请重试！");
              }
            })
            .catch(err => {
              console.log(err);
              this.$message.error("添加失败，请重试！");
            });
        } else {
          this.$message.error("添加失败，请重试！");
          return false;
        }
      });
    },
    // 删除用户信息
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该角色, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          service
            .post("/role/delete", { id: row.id })
            .then(res => {
              if (res.data.code === 1) {
                this.$message({
                  type: "success",
                  message: "该用户信息已删除!"
                });
                this.getRoleList();
              } else {
                this.$message({
                  type: "info",
                  message: "删除失败"
                });
              }
            })
            .catch(err => {
              console.log(err);
              this.$message({
                type: "info",
                message: "删除失败"
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
    },

    // 更新角色权限
    updateRoleForm() {
      const keys = [
        ...this.$refs.treeRef.getCheckedKeys(),
        ...this.$refs.treeRef.getHalfCheckedKeys()
      ];

      // console.log(keys);
      service
        .post("role/updateRoleMenu", { menu_id: keys, role_id: this.roleId })
        .then(res => {
          if (res.data.code === 1) {
            this.$message.success(res.data.message);
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.err("网络错误！");
        });

      this.getRoleList();
      this.dialogFormVisible2 = false;
    },
    resetUpdateForm() {
      this.dialogFormVisible2 = false;
      this.defaultRoleTree = [];
    },
    resetForm(formName) {
      this.dialogFormVisible = false;
      this.dialogFormVisible2 = false;
      this.$refs[formName].resetFields();
    }
  },
  watch: {
    // roleTreeOlder() {
    //   console.log(this.defaultRoleTree);
    //   this.$nextTick(() => {
    //     this.dialogFormVisible2 = true;
    //   });
    //   // if (newVal === oldVal) {
    //   //   console.log(this.defaultRoleTree);
    //   //   this.defaultRoleTree = oldVal;
    //   // }
    // }
  }
};
</script>
<style scoped></style>
