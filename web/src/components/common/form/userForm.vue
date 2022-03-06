<template>
  <div>
    <el-form
      :model="addUserForm"
      :rules="rules"
      ref="addUserForm"
      class="update-Form"
    >
      <el-form-item prop="username">
        <el-input
          v-model="addUserForm.username"
          auto-complete="new-password"
          prefix-icon="el-icon-user-solid"
          placeholder="请输入用户名"
        ></el-input>
      </el-form-item>
      <el-form-item prop="gender" label="性别">
        <el-radio-group v-model="addUserForm.gender">
          <el-radio :label="1" name="male">男</el-radio>
          <el-radio :label="0" name="female">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item prop="phone">
        <el-input
          v-model="addUserForm.phone"
          placeholder="请输入手机号"
          prefix-icon="el-icon-mobile"
        ></el-input>
      </el-form-item>
      <el-form-item prop="email">
        <el-tooltip
          class="item"
          effect="dark"
          content="获取验证码后等待1分钟可重新获取"
          placement="top-start"
        >
          <el-input
            v-model="addUserForm.email"
            placeholder="请输入邮箱"
            prefix-icon="el-icon-message"
          >
            <el-button
              type="primary"
              slot="append"
              class="email-btn"
              v-loading="loading"
              :disabled="btnDisabled"
              @click="getEmail()"
              style="width: 120px"
              >获取验证码</el-button
            >
          </el-input>
        </el-tooltip>
      </el-form-item>
      <el-form-item prop="code">
        <el-input
          v-model="addUserForm.code"
          placeholder="请输入验证码"
          prefix-icon="el-icon-chat-line-round"
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input
          v-model="addUserForm.password"
          type="password"
          auto-complete="new-password"
          prefix-icon="el-icon-key"
          placeholder="请输入密码"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item prop="check_password">
        <el-input
          type="password"
          v-model="addUserForm.check_password"
          prefix-icon="el-icon-key"
          placeholder="请再次输入密码"
          auto-complete="new-password"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item class="add-btn">
        <el-button
          type="primary"
          @click="submitForm('addUserForm')"
          style="width: 360px"
          >注册</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import service from "@/utils/request";

export default {
  name: "userForm",
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
      labelPosition: "left",
      loading: false,
      addUserForm: {
        username: "",
        password: "",
        check_password: "",
        phone: "",
        code: "",
        gender: "",
        email: "",
        role: "货主",
        createTime: ""
      },

      // time: 10,
      // btnName: "获取验证码",
      btnDisabled: false,
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
        phone: [{ required: true, message: "请输入手机号", trigger: "blur" }],
        gender: [{ required: true, message: "请选择性别", trigger: "blur" }],
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
        code: [{ required: true, message: "请输入验证码", trigger: "blur" }]
      }
    };
  },

  methods: {
    submitForm(formName) {
      this.getTime();
      this.$refs[formName].validate(valid => {
        if (valid) {
          service
            .post("/user/register", this.addUserForm)
            .then(res => {
              // console.log(res);
              if (res.data.code === 1) {
                this.$message({
                  message: "注册成功，请等待审核",
                  type: "success"
                });
                this.$refs[formName].resetFields();
                this.$router.push("/login");
              } else if (res.data.code === 102) {
                this.$message({
                  message: "用户名已存在",
                  type: "warning"
                });
              } else if (res.data.code === 108) {
                this.$message({
                  message: "邮箱已被使用",
                  type: "warning"
                });
              } else {
                this.$message.error(res.data.message);
              }
            })
            .catch(err => {
              console.log(err);
              this.$message.error("注册失败，请重试！");
            });
        } else {
          this.$message.error("注册失败，请重试！");
          return false;
        }
      });
    },
    // 申请验证码
    getEmail() {
      this.loading = true;
      service
        .post("/user/code", { emailNo: this.addUserForm.email })
        .then(res => {
          // console.log(res);
          if (res.data.code === 1) {
            this.$message.success(res.data.message);
            this.btnDisabled = true;
            this.loading = false;
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error(err.data.message);
        });
      setTimeout((this.btnDisabled = false), 60000);
      // this.btnDisabled = true;
      // let interval = window.setInterval(function() {
      //   this.btnName = "（" + this.time + "秒）后重新发送";
      //   --this.time;
      //   if (this.time < 0) {
      //     this.btnName = "重新发送";
      //     this.time = 10;
      //     this.btnDisabled = false;
      //     window.clearInterval(interval);
      //   }
      // }, 1000);
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
    }
  }
};
</script>
<style scoped>
.add-btn {
  text-align: center;
  margin: 0 auto;
}
.email-btn {
  text-align: center;
}
</style>
