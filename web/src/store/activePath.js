export default {
  namespaced: true,
  state: {
    sideBarPath: "1"
  },
  mutations: {
    changePath(state, path) {
      state.sideBarPath = path;
    }
  }
};
