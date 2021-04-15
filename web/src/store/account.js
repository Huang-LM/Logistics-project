import db from "@/utils/localstorage";

export default {
  namespaced: true,
  state: {
    token: db.get("USER_TOKEN"),
    userID: db.get("USERID"),
    userName: db.get("USERNAME"),
    permissions: db.get("PERMISSIONS"),
    roles: db.get("ROLES")
    // code: db.get("CODE")
  },
  mutations: {
    setToken(state, val) {
      db.save("USER_TOKEN", val);
      state.token = val;
    },
    setUserID(state, val) {
      db.save("USERID", val);
      state.userID = val;
    },
    setUserName(state, val) {
      db.save("USERNAME", val);
      state.userName = val;
    },
    setPermissions(state, val) {
      db.save("PERMISSIONS", val);
      state.permissions = val;
    },
    setRoles(state, val) {
      db.save("ROLES", val);
      state.roles = val;
    },
    reLogin(state) {
      db.clear();
      state.userID = "";
      state.userName = "";
      state.permissions = "";
      state.roles = "";
      state.token = "";
    }
  }
};
