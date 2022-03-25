import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "./plugins/element.js";

import qs from "qs";
Vue.prototype.$qs = qs;

import * as echarts from "echarts";
Vue.prototype.$echarts = echarts;

import VueParticles from "vue-particles";
Vue.use(VueParticles);

import LazyRender from "vue-lazy-render";
Vue.use(LazyRender);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount("#app");
