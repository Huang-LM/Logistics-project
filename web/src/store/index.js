import Vue from "vue";
import Vuex from "vuex";
import account from "@/store/account";
import activePath from "@/store/activePath";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: { account, activePath }
});
