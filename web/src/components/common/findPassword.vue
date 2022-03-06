<template>
  <div>
    <el-form :model="findUserForm" :rules="rules" ref="findUserForm">
      <el-form-item prop="email">
        <el-tooltip
          class="item"
          effect="dark"
          content="获取验证码后等待1分钟可重新获取"
          placement="top-start"
        >
          <el-input
            v-model="findUserForm.email"
            placeholder="请输入邮箱"
            prefix-icon="el-icon-message"
          >
            <el-button
              type="primary"
              slot="append"
              class="email-btn"
              :disabled="btnDisabled"
              v-loading="loading"
              @click="findEmail()"
              style="width: 120px"
              >获取验证码</el-button
            >
          </el-input>
        </el-tooltip>
      </el-form-item>
      <el-form-item prop="code">
        <el-input
          v-model="findUserForm.code"
          placeholder="请输入验证码"
          prefix-icon="el-icon-chat-line-round"
        ></el-input>
      </el-form-item>
      <el-form-item class="add-btn" v-show="checkShow">
        <el-button type="primary" @click="checkForm()" style="width: 360px"
          >验证邮箱</el-button
        >
      </el-form-item>
      <el-form-item prop="password" v-show="passShow">
        <el-input
          v-model="findUserForm.password"
          type="password"
          auto-complete="new-password"
          prefix-icon="el-icon-key"
          placeholder="请输入密码"
          show-password
        ></el-input>
      </el-form-item>
      <el-form-item prop="check_password" v-show="passShow">
        <el-input
          type="password"
          v-model="findUserForm.check_password"
          prefix-icon="el-icon-key"
          placeholder="请再次输入密码"
          auto-complete="new-password"
          show-password
        ></el-input>
      </el-form-item>

      <el-form-item class="add-btn" v-show="passShow">
        <el-button
          type="primary"
          @click="findForm('findUserForm')"
          style="width: 360px"
          >修改密码</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import service from "@/utils/request";

export default {
  name: "findPassword",
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.findUserForm.check_password !== "") {
          this.$refs.findUserForm.validateField("check_password");
        }
        callback();
      }
    };

    let validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.findUserForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      labelPosition: "left",
      findUserForm: {
        password: "",
        check_password: "",
        code: "",
        email: ""
      },
      loading: false,
      passShow: false,
      checkShow: true,
      // time: 10,
      // btnName: "获取验证码",
      btnDisabled: false,
      rules: {
        password: [
          { required: true, validator: validatePass, trigger: "blur" }
        ],
        check_password: [
          { required: true, validator: validatePass2, trigger: "blur" }
        ],
        email: [{ required: true, message: "请输入邮箱", trigger: "blur" }],
        code: [{ required: true, message: "请输入验证码", trigger: "blur" }]
      }
    };
  },

  methods: {
    findForm(formName) {
      // this.$parent.pushLogin();
      this.$refs[formName].validate(valid => {
        if (valid) {
          service
            .post("/user/findPassword", this.findUserForm)
            .then(res => {
              // console.log(res);
              if (res.data.code === 1) {
                this.$message({
                  message: "找回成功，请跳转登陆页面",
                  type: "success"
                });
                this.$refs[formName].resetFields();
                this.$emit("pushLogin");
                // this.$parent.pushLogin();
                // this.$parent.pushLogin;
                // this.$router.push("/login");
              } else if (res.data.code === 0) {
                this.$message({
                  message: "找回失败",
                  type: "warning"
                });
              } else {
                this.$message.error("找回失败,请重试！");
              }
            })
            .catch(err => {
              console.log(err);
              this.$message.error("找回失败");
            });
        } else {
          this.$message.error("找回失败");
          return false;
        }
      });
    },
    // 申请验证码
    findEmail() {
      this.loading = true;
      service
        .post("/user/code2", { emailNo: this.findUserForm.email })
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
    },
    // 验证码
    checkForm() {
      service
        .post("/user/checkCode", this.findUserForm)
        .then(res => {
          // console.log(res);
          if (res.data.code === 1) {
            this.$message({
              message: "验证成功",
              type: "success"
            });
            this.passShow = true;
            this.checkShow = false;
            // this.$refs[formName].resetFields();
            // this.$router.push("/login");
          } else if (res.data.code === 0) {
            this.$message({
              message: "验证码输入错误",
              type: "warning"
            });
          } else {
            this.$message.error("请重新填写！");
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络错误");
        });
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
