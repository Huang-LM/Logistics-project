<template>
  <div class="topBar">
    <el-row class="topBar-nav">
      <el-col :span="18">
        <div class="bar">码头船只出行系统</div>
      </el-col>
      <el-col :span="4">
        <!-- 当前天气 -->
        <weather-simple class="weatherSimple"></weather-simple>
      </el-col>
      <!-- <el-col :span="1"> </el-col> -->
      <!-- <el-col :span="2">
        <el-link type="info" :underline="false" @click="setting"
          ><i class="el-icon-s-custom"></i>用户设置</el-link
        >
      </el-col> -->
      <el-col :span="2">
        <el-button
          type="info"
          @click="logout"
          plain
          class="logout-btn"
          size="mini"
          >退出登录</el-button
        >
      </el-col>
    </el-row>
  </div>
</template>

<script>
import weatherSimple from "./weather/weatherSimple.vue";
// import service from "@/utils/request";
export default {
  components: { weatherSimple },
  name: "topBar",

  data() {
    return {};
  },
  methods: {
    setting() {
      window.sessionStorage.setItem("subName", "用户设置");
      this.$router.push("/settingUser").then(() => {
        this.$message.success("跳转");
      });
    },
    logout() {
      this.$store.commit("account/reLogin");
      // 注销时将token列入黑名单
      // service.get("/user/logout", () => {
      // });
      // window.sessionStorage.setItem("activePath", "/home");
      window.sessionStorage.clear();

      this.$router.push("/login").then(() => {
        this.$message.success("已注销");
      });
    }
  }
};
</script>

<style scoped>
.bar {
  font-size: 25px;
  color: #409eff;
}
.topBar {
  width: 99%;
}
.topBar-nav {
  margin-top: 20px;
}
.weatherSimple {
  position: fixed;
  width: 100px;
  top: 10px;
}
.logout-btn {
  position: absolute;
  right: 60px;
  bottom: 10%;
}
</style>
