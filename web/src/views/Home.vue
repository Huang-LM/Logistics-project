<template>
  <div class="home">
    <vue-particles
      class="v-background"
      color="#dedede"
      :particleOpacity="0.6"
      :particlesNumber="80"
      shapeType="circle"
      :particleSize="4"
      linesColor="#dedede"
      :linesWidth="1"
      :lineLinked="true"
      :lineOpacity="0.4"
      :linesDistance="150"
      :moveSpeed="3"
      :hoverEffect="true"
      hoverMode="repulse"
      :clickEffect="true"
      clickMode="push"
    >
    </vue-particles>
    <el-container class="home-container-all">
      <el-aside width="217px" class="home-aside">
        <sideBar ref="sideBar"></sideBar>
      </el-aside>
      <el-container>
        <el-header class="home-header">
          <topBar></topBar>
          <!-- <el-divider></el-divider> -->
        </el-header>
        <el-main class="home-main">
          <el-row>
            <el-col :span="0.1">
              <div class="breadcrumb-box"></div>
            </el-col>
            <el-col :span="6">
              <el-breadcrumb separator="/" class="breadcrumb">
                <el-breadcrumb-item :to="{ path: '/home' }"
                  >主页</el-breadcrumb-item
                >

                <el-breadcrumb-item v-if="this.subName">{{
                  subName
                }}</el-breadcrumb-item>
              </el-breadcrumb>
            </el-col>
          </el-row>
          <div class="welcomeText" v-show="welcomeShow">
            欢迎来到物流管理平台!
          </div>
          <transition :name="transitionName">
            <router-view> </router-view>
          </transition>
        </el-main>
        <!-- <el-footer class="home-footer"></el-footer> -->
      </el-container>
    </el-container>
  </div>
</template>

<script>
import sideBar from "../components/common/sideBar";
import topBar from "@/components/common/topBar";

export default {
  name: "Home",
  components: {
    sideBar,
    topBar
  },
  created() {
    this.getState();
  },
  data() {
    return {
      transitionName: "",
      subName: "",
      welcomeShow: true
    };
  },
  methods: {
    getState() {
      this.subName = window.sessionStorage.getItem("subName");
      if (this.subName !== "/home") this.welcomeShow = false;
      // console.log(this.subName);
    }
  },
  watch: {
    // 使用watch 监听$router的变化
    $route(to, from) {
      if (to.path === "/home") {
        this.welcomeShow = true;
      } else {
        this.welcomeShow = false;
      }
      this.subName = window.sessionStorage.getItem("subName");
      // 如果to索引大于from索引,判断为前进状态,反之则为后退状态
      if (to.meta.index > from.meta.index) {
        // 设置动画名称
        this.transitionName = "slide-left";
      } else {
        // window.sessionStorage.setItem("activePath", "/home");
        window.sessionStorage.setItem("subName", "");
        // window.sessionStorage.clear();
        this.transitionName = "slide-right";
      }
    }
  }
};
</script>

<style>
.home {
  font-size: 1.1rem;
}
.home-container-all {
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
}
.welcomeText {
  color: #565656;
  font-size: 40px;
  /* padding-bottom: 10%; */
}
.home-main {
  margin-top: 10px;
  z-index: 1;
}
.breadcrumb-box {
  height: 14px;
  width: 5px;
  background-color: #88b9f9;
}
.breadcrumb {
  margin-bottom: 24px;
  margin-left: 5px;
}
.home-aside {
  /* background-color: #545c64; */
  z-index: 2;
}
.home-header {
  z-index: 3;
  box-shadow: 0 1px #59596755;
}

/* 页面切换动画 */
.slide-right-enter-active,
.slide-right-leave-active,
.slide-left-enter-active,
.slide-left-leave-active {
  /* will-change属性可以提前通知浏览器我们要对元素做什么动画，这样浏览器可以提前准备合适的优化设置 */
  will-change: transform;
  transition: all ease 0.25s;
  -webkit-transition: all ease 0.25s;
  position: absolute;
  width: 79%;
  left: 201px;
}
.slide-right-enter {
  transform: translateX(-100%);
  -webkit-transform: translateX(-100%);
}
.slide-right-leave-active {
  transform: translateX(100%);
  -webkit-transform: translateX(100%);
}
.slide-left-enter {
  transform: translateX(100%);
  -webkit-transform: translateX(100%);
}
.slide-left-leave-active {
  transform: translateX(-100%);
  -webkit-transform: translateX(-100%);
}
/* 页面切换动画 */

.box-row-center {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
.box-row-between {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
}
.box-row-around {
  display: flex;
  flex-direction: column;
  justify-content: space-around;
  align-items: center;
}
.box-col-center {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.box-col-between {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.box-col-around {
  display: flex;
  flex-direction: row;
  justify-content: space-around;
  align-items: center;
}
.box-col-end {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: flex-end;
  align-items: center;
}
.box-col-start {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  align-items: center;
}
.swiper {
  width: 100%;
  height: 100%;
}
</style>
