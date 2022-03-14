<template>
  <div class="sidebar">
    <div class="sidebar-main">
      <!-- 头像框 -->
      <!-- <div class="sidebar-avatar">
        <el-avatar :size="80" :src="circleUrl"></el-avatar>
      </div> -->
      <div>
        <div class="sidebar-name">
          {{ this.userName }}
        </div>
      </div>

      <!-- 菜单 -->
      <el-menu
        class="sidebar-menu"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#409eff"
        :default-active="activePath"
        :unique-opened="true"
        router
      >
        <el-submenu
          class="sidebar-menu"
          :index="item.url + ''"
          v-for="item in menuList"
          :key="item.id"
        >
          <template slot="title">
            <!-- <i class="el-icon-user"></i> -->
            <i :class="'el-icon-' + item.icon"></i>
            <span>{{ item.name }}</span>
          </template>
          <el-menu-item
            class="siderbar-menu-item"
            v-for="subItem in item.children"
            :index="subItem.url + ''"
            :key="subItem.id"
            @click="saveState(subItem.url, subItem.name)"
            ><span>{{ subItem.name }}</span></el-menu-item
          >
        </el-submenu>
        <el-menu-item
          index="settingUser"
          @click="saveState('settingUser', '用户设置')"
          class="menu-setting"
        >
          <i class="el-icon-setting"></i>
          <span slot="title">用户设置</span>
        </el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
// import activePath from "@/store/activePath";

import service from "@/utils/request";
import bus from "@/utils/eventBus.js";

export default {
  name: "sideBar",
  created() {
    this.getState();
  },
  data() {
    return {
      circleUrl:
        "https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png",
      activePath: "",
      subName: "",
      userName: "",
      userId: "",
      roles: "",
      queryMenu: [],
      menuList: [],
      isCollapse: false
    };
  },
  methods: {
    saveState(path, subName) {
      window.sessionStorage.setItem("activePath", path);
      window.sessionStorage.setItem("subName", subName);
      this.activePath = path;
    },
    getState() {
      // this.activePath = activePath.state.sideBarPath;
      this.activePath = window.sessionStorage.getItem("activePath");
      this.subName = window.sessionStorage.getItem("subName");
      this.userName = this.$store.state.account.userName;
      this.userId = this.$store.state.account.userID;
      // setTimeout(this.showBanner(), 5000);
      this.showBanner();
      this.roles = this.$store.state.account.roles;
    },
    async showBanner() {
      // console.log(this.userId);
      await service.post("user/menu", { id: this.userId }).then(res => {
        // console.log(res.data.data);
        this.menuList = res.data.data;

        // this.menuList = res.data;
      });
    }
  },
  mounted() {
    bus.$on("getSide", () => {
      service.post("user/menu", { id: this.userId }).then(res => {
        this.menuList = res.data.data;
      });
    });
  },
  watch: {
    $route(to) {
      if (to.path) this.activePath = to.path;
    }
  }
};
</script>

<style scoped>
/* .sidebar {
  margin: 0 40px;
  padding: 0 40px;
} */
.sidebar-main {
  height: 100vh;
  width: 200px;
  background-color: #545c64;
}
.sidebar-avatar {
  padding-top: 20px;
  display: flex;
  justify-content: center;
}
.sidebar-name {
  text-align: center;
  color: white;
  padding-top: 15px;
}
.sidebar-menu {
  margin-top: 10px;
  width: 200px;
}
.siderbar-menu-item {
  /* padding: 0 !important; */
  width: 200px;
}
/* .sidebar-submenu {
  width: 200px;
} */
</style>
