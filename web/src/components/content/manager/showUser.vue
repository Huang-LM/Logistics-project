<template>
  <div class="showUser">
    <!-- 搜索 -->
    <el-form :model="queryInfo" ref="queryInfo">
      <el-form-item>
        <!-- <el-input
          v-model="queryInfo.id"
          style="width: 80px"
          auto-complete="new-password"
          placeholder="id"
          clearable
          class="search-input"
          @clear="getUserList"
        ></el-input> -->
        <el-input
          v-model="queryInfo.username"
          style="width: 180px"
          auto-complete="new-password"
          placeholder="请输入用户名"
          clearable
          class="search-input"
          @clear="getUserList"
        ></el-input>
        <el-input
          v-model="queryInfo.phone"
          style="width: 200px"
          placeholder="请输入手机号"
          clearable
          class="search-input"
          @clear="getUserList"
        ></el-input>
        <el-select
          v-model="queryInfo.role"
          placeholder="请选择"
          style="width: 120px"
          clearable
          @clear="getUserList"
        >
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
        <el-button
          type="primary"
          v-waves
          @click="this.selectUserList"
          icon="el-icon-search"
          class="search-btn"
        >
          查询
        </el-button>
        <el-button
          type="primary"
          v-waves
          @click="dialogFormVisible = true"
          icon="el-icon-edit"
        >
          新增
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 搜索结果 -->
    <el-table :data="userList" style="width: 100%;" stripe>
      <!-- <el-table-column prop="id" label="ID" sortable width="70">
      </el-table-column> -->
      <el-table-column prop="username" label="用户名" width="100">
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="130">
      </el-table-column>
      <el-table-column label="性别" width="70">
        <template v-slot="scope">
          <p v-if="scope.row.gender === 0">女</p>
          <p v-else>男</p>
        </template>
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="130"> </el-table-column>
      <el-table-column prop="role" label="角色" width="100"> </el-table-column>
      <el-table-column label="审核" width="80">
        <template v-slot="scope">
          <el-button v-if="scope.row.verify === 0" type="info" size="mini"
            >未通过</el-button
          >
          <el-button v-if="scope.row.verify === 1" type="success" size="mini"
            >通过</el-button
          >
        </template>
      </el-table-column>
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
      <el-table-column label="操作" min-width="180" fixed="right">
        <template slot-scope="scope">
          <el-button
            type="primary"
            plain
            v-waves
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          >
          <el-button
            type="danger"
            v-waves
            size="mini"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      layout="sizes, prev, pager, next, total"
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
      :current-page="queryInfo.page + 1"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="queryInfo.pageSize"
      :total="total"
      class="pagination"
    >
    </el-pagination>

    <!-- 新增用户对话框 -->
    <el-dialog
      title="新增用户"
      class="update-dialog"
      :visible.sync="dialogFormVisible"
      :append-to-body="true"
    >
      <el-form
        :model="addUserForm"
        :rules="rules"
        ref="addUserForm"
        :label-position="labelPosition"
        label-width="80px"
        class="update-Form"
      >
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="addUserForm.username"
            auto-complete="new-password"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="用户角色" prop="role">
          <el-select
            v-model="addUserForm.role"
            placeholder="请选择"
            style="width: 120px"
          >
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.name"
              :value="item.name"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="addUserForm.gender">
            <el-radio :label="1" name="male">男</el-radio>
            <el-radio :label="0" name="female">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input
            v-model="addUserForm.phone"
            placeholder="请输入手机号"
          ></el-input>
        </el-form-item>
        <el-form-item prop="email" label="邮箱">
          <el-input v-model="addUserForm.email" placeholder="请输入邮箱">
          </el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="addUserForm.password"
            type="password"
            auto-complete="new-password"
            placeholder="请输入密码"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="check_password">
          <el-input
            type="password"
            v-model="addUserForm.check_password"
            placeholder="请再次输入密码"
            auto-complete="new-password"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item label="审核" prop="verify">
          <el-switch
            v-model="addUserForm.verify"
            active-text="通过"
            inactive-text="未通过"
          >
          </el-switch>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          class="btn-reset"
          plain
          @click="resetForm('addUserForm')"
          v-waves
          >取消</el-button
        >
        <el-button type="primary" @click="submitForm('addUserForm')" v-waves
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
    >
      <el-form
        :model="updateUserForm"
        :rules="rules"
        ref="updateUserForm"
        :label-position="labelPosition"
        label-width="80px"
        class="update-Form"
      >
        <el-form-item label="用户名" prop="username">
          <el-input
            v-model="updateUserForm.username"
            auto-complete="new-password"
            placeholder="请输入用户名"
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="updateUserForm.gender">
            <el-radio :label="1" name="male">男</el-radio>
            <el-radio :label="0" name="female">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input
            v-model="updateUserForm.phone"
            placeholder="请输入手机号"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          class="btn-reset"
          plain
          @click="resetForm('updateUserForm')"
          v-waves
          >取消</el-button
        >
        <el-button type="primary" @click="updateForm('updateUserForm')" v-waves
          >确认</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import service from "@/utils/request";
import waves from "@/directive/waves";
// import Waves from "@/components/common/directives/waves";

export default {
  name: "showUser",
  directives: { waves },
  created() {
    this.getUserList();
    this.getRoleOptions();
  },
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.addUserForm.check_password !== "") {
          this.$refs.addUserForm.validateField("check_password");
        }
        callback();
      }
    };

    let validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.addUserForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      labelPosition: "right",
      userList: [],
      queryInfo: {
        // 查询参数
        id: "",
        username: "",
        phone: "",
        role: "",
        gender: "",
        // 当前页数
        page: 0,
        // 当前显示多少行
        pageSize: 5
      },
      total: 0,
      btnDisabled: false,
      delId: {
        id: 0
      },
      dialogFormVisible: false,
      dialogFormVisible2: false,
      formLabelWidth: "150px",
      addUserForm: {
        username: "",
        password: "",
        check_password: "",
        createTime: "",
        phone: "",
        code: "",
        email: "",
        role: "",
        gender: "",
        verify: true
      },
      updateUserForm: {
        id: "",
        username: "",
        phone: "",
        gender: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入用户姓名", trigger: "blur" }
        ],
        password: [
          { required: true, validator: validatePass, trigger: "blur" }
        ],
        check_password: [
          { required: true, validator: validatePass2, trigger: "blur" }
        ],
        gender: [{ required: true, message: "请选择性别", trigger: "blur" }],
        phone: [{ required: true, message: "请输入手机号", trigger: "blur" }],
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
        role: [{ required: true, message: "请选择角色", trigger: "blur" }]
      },
      options: [
        {
          value: "1",
          label: "管理员"
        },
        {
          value: "2",
          label: "货主"
        },
        {
          value: "3",
          label: "物流管理员"
        },
        {
          value: "4",
          label: "信息服务员"
        }
      ]
    };
  },
  methods: {
    //获取用户列表
    getUserList() {
      service
        .post("/user/list", this.queryInfo)
        .then(res => {
          // console.log(res);
          const resData = res.data;
          // console.log(resData.list);
          this.userList = resData.list;
          this.total = resData.total;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    // 获取角色菜单
    getRoleOptions() {
      service
        .get("/role/listRole")
        .then(res => {
          // console.log(res.data.data);
          this.options = res.data.data;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    // 查找用户
    selectUserList() {
      // console.log(this.queryInfo);
      if (
        this.queryInfo.phone ||
        this.queryInfo.id ||
        this.queryInfo.username ||
        this.queryInfo.role
      ) {
        this.queryInfo.page = 0;
      }
      service
        .post("/user/select", this.queryInfo)
        .then(res => {
          // console.log(res);
          const resData = res.data;
          this.userList = resData.list;
          this.total = resData.total;
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

      this.addUserForm.createTime = currentdate;
    },
    //跳转至编辑用户
    handleEdit(index, row) {
      this.updateUserForm = row;
      this.dialogFormVisible2 = true;
    },
    //删除用户信息
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.delId.id = row.id;
          service
            .post("/user/delete", this.delId)
            .then(res => {
              if (res.data.code === 1) {
                this.$message({
                  type: "success",
                  message: "该用户信息已删除!"
                });
                this.getUserList();
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
    // 换页
    handleCurrentChange(val) {
      this.queryInfo.page = val - 1;
      this.getUserList();
    },
    handleSizeChange(val) {
      this.queryInfo.pageSize = val;
      this.getUserList();
    },
    // 新增用户
    submitForm(formName) {
      this.getTime();
      this.$refs[formName].validate(valid => {
        if (valid) {
          // console.log(this.addUserForm);
          service
            .post("/user/add", this.addUserForm)
            .then(res => {
              // console.log(res);
              if (res.data.code === 1) {
                this.$message({
                  message: "添加成功",
                  type: "success"
                });
                this.dialogFormVisible = false;
                this.$refs[formName].resetFields();
                this.getUserList();
              } else if (res.data.code === 0) {
                this.$message({
                  message: "用户名已存在",
                  type: "warning"
                });
              } else {
                // console.log(res);
                this.$message.error("添加失败,请重试！");
              }
            })
            .catch(err => {
              console.log(err);
              this.$message.error(
                err.response.data.data.message + "，请重试！"
              );
            });
        } else {
          this.$message.error("添加失败，请重试！");
          return false;
        }
      });
    },
    // 更新用户
    updateForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //console.log(this.addUserForm);
          service
            .post("/user/update", this.updateUserForm)
            .then(res => {
              // console.log(res);
              if (res.data.code === 1) {
                this.$message({
                  message: "更新成功",
                  type: "success"
                });

                this.dialogFormVisible2 = false;
                this.$refs[formName].resetFields();
                this.getUserList();
              } else if (res.data.code === 0) {
                this.$message({
                  message: "更新用户失败，用户名已占用，请重试！",
                  type: "warning"
                });
              } else {
                // console.log(res);
                this.$message.error("更新失败,请重试！");
              }
            })
            .catch(err => {
              console.log(err);
              this.$message.error("更新失败，请重试！");
            });
        } else {
          this.$message.error("更新失败，请重试！");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.dialogFormVisible = false;
      this.dialogFormVisible2 = false;
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style scoped>
.pagination {
  margin-top: 40px;
  text-align: center;
}
.search-btn {
  margin-left: 10px;
}
/* .update-dialog {
  position: absolute;
  z-index: 999;
} */
</style>
