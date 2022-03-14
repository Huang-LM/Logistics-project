<template>
  <div class="Login">
    <vue-particles
      class="v-background"
      color="#dedede"
      :particleOpacity="0.8"
      :particlesNumber="80"
      shapeType="circle"
      :particleSize="4"
      linesColor="#dedede"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.6"
      :linesDistance="150"
      :moveSpeed="3"
      :hoverEffect="true"
      hoverMode="repulse"
      :clickEffect="true"
      clickMode="push"
    >
    </vue-particles>
    <el-container class="el-container">
      <el-main class="login-main">
        <el-row>
          <el-col :xs="0" :sm="4" :md="8" :lg="8" :xl="8"
            ><div class="placeholder"></div
          ></el-col>
          <el-col :xs="24" :sm="16" :md="8" :lg="8" :xl="8">
            <!-- <p class="login-title">海上物流管理平台</p> -->
            <transition name="el-fade-in">
              <el-card class="L-card" v-show="this.cardShow">
                <find-password
                  v-show="this.findShow"
                  @pushLogin="pushLogin"
                ></find-password>
                <el-tabs
                  v-model="activeName"
                  :stretch="true"
                  v-show="this.loginShow"
                >
                  <el-tab-pane label="登录" name="login"
                    ><el-form
                      :model="loginForm"
                      :rules="rules"
                      ref="loginForm"
                      class="loginForm"
                    >
                      <el-form-item prop="username">
                        <el-input
                          v-model="loginForm.username"
                          prefix-icon="el-icon-user-solid"
                          placeholder="请输入登录账号"
                        ></el-input>
                      </el-form-item>
                      <el-form-item prop="password">
                        <el-input
                          type="password"
                          prefix-icon="el-icon-key"
                          v-model="loginForm.password"
                          placeholder="请输入密码"
                        ></el-input>
                      </el-form-item>

                      <el-form-item class="btn">
                        <el-button
                          type="primary"
                          @click="submitForm('loginForm')"
                          v-waves
                          style="width: 250px"
                          >登录</el-button
                        >
                      </el-form-item>
                    </el-form></el-tab-pane
                  >
                  <el-tab-pane label="货代公司注册" name="register">
                    <user-form></user-form>
                  </el-tab-pane>
                </el-tabs>
              </el-card>
            </transition>
            <div class="findText">
              <el-link type="primary" :underline="false" @click="pushFind()"
                >忘记密码</el-link
              >
              <el-link
                type="primary"
                :underline="false"
                @click="pushLogin()"
                class="returnLoginText"
                v-show="findShow"
              >
                返回登陆页面</el-link
              >
            </div>
          </el-col>
          <el-col :xs="0" :sm="4" :md="8" :lg="8" :xl="8"
            ><div class="placeholder"></div>
          </el-col>
        </el-row>
      </el-main>
      <!-- <el-footer class="login-footer"></el-footer> -->
    </el-container>
  </div>
</template>

<script>
import service from "@/utils/request";
import waves from "@/directive/waves";
import UserForm from "../components/common/form/userForm.vue";
import FindPassword from "../components/common/findPassword.vue";

export default {
  name: "Login",
  directives: { waves },
  components: { UserForm, FindPassword },
  created() {
    this.showCard();
    // service.get("/list").then(res => {
    //   console.log(res);
    // });
  },
  data() {
    return {
      loginForm: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入登录账号", trigger: "blur" }
        ],
        password: [{ required: true, message: "请选择密码", trigger: "blur" }]
      },
      userId: "",
      account: {
        token: "",
        username: "",
        permissions: "",
        role: ""
      },
      activeName: "login",
      cardShow: false,
      loginShow: true,
      findShow: false
    };
  },
  methods: {
    async submitForm(formName) {
      // this.$router.push("/home");
      // await service
      //   .post("/user/id", this.loginForm)
      //   .then(res => {
      //     // console.log(res.data);
      //     this.$store.commit("account/setUserID", res.data);
      //   })
      //   .catch(err => {
      //     console.log(err);
      //   });
      this.$refs[formName].validate(valid => {
        if (valid) {
          service
            .post("/user/login", this.loginForm)
            .then(res => {
              // console.log(res.data);
              // this.getId();

              // 新
              const resData = res.data;
              console.log(resData.code);
              if (resData.code == 1) {
                this.$store.commit("account/setToken", resData.data.token);
                this.$store.commit(
                  "account/setUserName",
                  this.loginForm.username
                );
                this.$store.commit("account/setUserID", resData.data.userId);
                // this.$store.commit("account/setRoles", resData.data.roles);
                this.$message.success(resData.data.message);
                this.$router.push("/home");
              } else if (resData.code == 0) {
                // console.log(123123);
                this.$message.error(resData.data.message);
              }

              // 老
              // if (res.data.code === 1) {
              //   // console.log(res.data.token);
              //   this.$store.commit("account/setToken", res.data.token);
              //   this.$store.commit(
              //     "account/setUserName",
              //     this.loginForm.username
              //   );
              //   // setTimeout(this.getId);
              //   // this.$store.commit("account/setRoles", resDate.authz.roles);
              //   this.$message.success("登录成功");
              //   this.$router.push("/home");
              // } else {
              //   this.$message.error(res.data.message);
              // }
              //
            })
            .catch(err => {
              console.log(err);
              this.$message.error("账号或密码错误，请重试！");
            });
        } else {
          this.$message.error("请检查信息完整性");
          return false;
        }
      });
    },
    getId() {
      service
        .post("/user/id", this.loginForm)
        .then(res => {
          // console.log(res.data);
          this.$store.commit("account/setUserID", res.data);
        })
        .catch(err => {
          console.log(err);
        });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    showCard() {
      setTimeout(() => {
        this.cardShow = true;
      }, 200);
    },
    pushFind() {
      // this.$router.push("/findPassword");
      this.loginShow = false;
      this.findShow = true;
    },
    pushLogin() {
      this.loginShow = true;
      this.findShow = false;
    }
  }
};
</script>

<style scoped>
.Login {
  height: 100vh;
}
.bar {
  width: 100%;
  height: 1px;
}
.topBar {
  width: 99%;
}
.topBar-nav {
  margin-top: 20px;
}
.placeholder {
  width: 1px;
  height: 1px;
}
.el-container {
  height: 100vh;
}
.v-background {
  width: 100%;
  height: 100vh;
  position: absolute;
  z-index: -1;
  /* background-image: linear-gradient(50deg, #a1c4fd 0%, #c2e9fb 100%); */
  /* background-image: linear-gradient(to top, #e6e9f0 0%, #eef1f5 100%); */
  /* background-image: linear-gradient(to top, #dfe9f3 0%, white 100%); */
  background-image: linear-gradient(to top, #accbee 0%, #e7f0fd 100%);
}
/* .login-main {
  background-image: linear-gradient(to top, #e6e9f0 0%, #eef1f5 100%);
  text-align: center;
} */
.loginForm {
  margin: 6% 10%;
  width: 80%;
}
.login-title {
  color: #565656;
  font-size: 40px;
  padding-bottom: 10%;
}
.findText {
  margin: 20px;
  text-align: center;
}
.returnLoginText {
  margin-left: 60px;
}
.L-card {
  margin-top: 35%;
  /* height: 50vh; */
  padding: 20px 10px 0px 10px;
  border-radius: 20px;
  background-color: #ffffffcc;
}
.btn {
  display: flex;
  justify-content: center;
}
.el-radio__label {
  font-size: 50px !important;
}
</style>
