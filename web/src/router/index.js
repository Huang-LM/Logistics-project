import Vue from "vue";
import VueRouter from "vue-router";
const Home = () => import("@/views/Home");
const Login = () => import("@/views/Login");
const showUser = () => import("@/components/content/manager/showUser");
const checkUser = () => import("@/components/content/check/checkUser");
const settingUser = () => import("@/components/common/settingUser");
const rolePower = () => import("@/components/content/role/rolePower");
const roleUser = () => import("@/components/content/role/roleUser");
const shipApplication = () =>
  import("@/components/content/ship/shipApplication");
const shipInquiry = () => import("@/components/content/ship/shipInquiry");
const reviewGoods = () =>
  import("@/components/content/shipManager/reviewGoods");
const recordedInfo = () =>
  import("@/components/content/shipMessage/recordedInfo");
const goodsPeck = () => import("@/components/content/echarts/goodsPeck");
const goodsStatistics = () =>
  import("@/components/content/echarts/goodsStatistics");
const dictionaryManager = () =>
  import("@/components/content/dictionary/dictionaryManager");
const findPassword = () => import("@/components/common/findPassword");
const announceShow = () =>
  import("@/components/common/announcement/announceShow");
const announceList = () =>
  import("@/components/common/announcement/announceList");
const boatTrip = () => import("@/components/content/shipOwner/boatTrip");

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    meta: { index: 0 },
    redirect: "login"
  },
  {
    path: "/login",
    name: "login",
    meta: { index: 0 },
    component: Login
  },
  {
    path: "/findPassword",
    name: "findPassword",
    meta: { index: 0 },
    component: findPassword
  },
  {
    path: "/home",
    name: "home",
    meta: { index: 2 },
    component: Home,
    children: [
      // 管理员
      {
        path: "/showUser",
        name: "showUser",
        meta: { index: 2 },
        component: showUser
      },
      {
        path: "/checkUser",
        name: "checkUser",
        meta: { index: 2 },
        component: checkUser
      },
      {
        path: "/rolePower",
        name: "rolePower",
        meta: { index: 2 },
        component: rolePower
      },
      {
        path: "/roleUser",
        name: "roleUser",
        meta: { index: 2 },
        component: roleUser
      },
      {
        path: "/dictionaryManager",
        name: "dictionaryManager",
        meta: { index: 2 },
        component: dictionaryManager
      },
      {
        path: "/goodsPeck",
        name: "goodsPeck",
        meta: { index: 2 },
        component: goodsPeck
      },
      {
        path: "/goodsStatistics",
        name: "goodsStatistics",
        meta: { index: 2 },
        component: goodsStatistics
      },
      {
        path: "/announceList",
        name: "announceList",
        meta: { index: 2 },
        component: announceList
      },
      // 货主
      {
        path: "/shipApplication",
        name: "shipApplication",
        meta: { index: 2 },
        component: shipApplication
      },
      {
        path: "/shipInquiry",
        name: "shipInquiry",
        meta: { index: 2 },
        component: shipInquiry
      },
      // 物流服务员
      {
        path: "/reviewGoods",
        name: "reviewGoods",
        meta: { index: 2 },
        component: reviewGoods
      },
      // 信息服务员
      {
        path: "/recordedInfo",
        name: "recordedInfo",
        meta: { index: 2 },
        component: recordedInfo
      },
      // 船主
      {
        path: "/announceShow",
        name: "announceShow",
        meta: { index: 2 },
        component: announceShow
      },
      {
        path: "/boatTrip",
        name: "boatTrip",
        meta: { index: 2 },
        component: boatTrip
      },
      // 通用
      {
        path: "/settingUser",
        name: "settingUser",
        meta: { index: 2 },
        component: settingUser
      }
    ]
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

// 跳转拦截
router.beforeEach((to, from, next) => {
  if (to.path === "/login") {
    next();
  } else {
    let token = localStorage.getItem("USER_TOKEN");

    if (token === null || token === "") {
      alert("请先登录");
      next("/login");
    } else {
      next();
    }
  }
});

// 重复路由警告
const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err);
};

export default router;
