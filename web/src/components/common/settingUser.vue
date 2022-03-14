<template>
  <div class="settingUser">
    <el-row :gutter="20">
      <el-col :span="2"><div class="grid-content"></div></el-col>
      <el-col :span="8"
        ><el-form
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
          <el-form-item prop="phone">
            <el-button type="primary" @click="updateForm('updateUserForm')"
              >更新信息</el-button
            >
            <el-button type="primary" @click="dialogFormVisible = true" plain
              >重置密码</el-button
            >
          </el-form-item>
        </el-form>
      </el-col>
      <el-col :span="14"><div class="grid-content"></div></el-col>
    </el-row>

    <!-- 重置密码对话框 -->
    <el-dialog
      title="重置密码"
      class="update-dialog"
      :visible.sync="dialogFormVisible"
      :append-to-body="true"
    >
      <el-form
        :model="updatePasswordForm"
        :rules="rules"
        ref="updatePasswordForm"
        :label-position="labelPosition"
        label-width="80px"
        class="update-Form"
      >
        <el-form-item label="旧密码" prop="password">
          <el-input
            v-model="updatePasswordForm.password"
            type="password"
            auto-complete="new-password"
            placeholder="请输入旧密码"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="password">
          <el-input
            v-model="updatePasswordForm.new_password"
            type="newPassword"
            auto-complete="new-password"
            placeholder="请输入新密码"
            show-password
          ></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="check_password">
          <el-input
            type="password"
            v-model="updatePasswordForm.check_password"
            placeholder="请再次输入新密码"
            auto-complete="new-password"
            show-password
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          class="btn-reset"
          plain
          @click="resetForm('updatePasswordForm')"
          >取消</el-button
        >
        <el-button type="primary" @click="updatePassword">确认</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import service from "@/utils/request";

export default {
  name: "settingUser",
  created() {
    this.getId();
  },
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.updatePasswordForm.check_password !== "") {
          this.$refs.updatePasswordForm.validateField("check_password");
        }
        callback();
      }
    };

    let validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.updatePasswordForm.new_password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      userId: "",
      labelPosition: "right",
      formLabelWidth: "150px",
      dialogFormVisible: false,
      updateUserForm: {
        id: "",
        username: "",
        phone: "",
        gender: ""
      },
      updatePasswordForm: {
        id: "",
        password: "",
        new_password: "",
        check_password: ""
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
        phone: [{ required: true, message: "请输入手机号", trigger: "blur" }]
      }
    };
  },

  methods: {
    getId() {
      this.userId = this.$store.state.account.userID;
      this.updatePasswordForm.id = this.userId;
      this.getUser();
    },
    getUser() {
      service.post("user/get", { id: this.userId }).then(res => {
        // console.log(res.data);
        this.updateUserForm = res.data.data;
      });
    },
    // 更新用户
    updateForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          console.log(this.updateUserForm);
          service
            .post("/user/update", this.updateUserForm)
            .then(res => {
              // console.log(res);
              if (res.data.code === 1) {
                this.$message({
                  message: "更新成功",
                  type: "success"
                });
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
    updatePassword() {
      service
        .post("user/updatepw", this.updatePasswordForm)
        .then(res => {
          console.log(res.data.code);
          if (res.data.code === 1) {
            this.$message.success(res.data.message);
            this.dialogFormVisible = false;
          } else {
            this.$message.error(res.data.message + "，请检查旧密码是否正确");
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("更新失败，请重试！");
        });
    },
    resetForm(formName) {
      this.dialogFormVisible = false;
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style scoped>
.settingUser {
  padding-top: 50px;
}
.grid-content {
  width: 1px;
  height: 1px;
}
</style>
