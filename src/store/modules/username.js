export default {
  state: {
    uid: null, // 初始值为 null，表示未登录

  },
  mutations: {
    setUid(state, uid) {
      state.uid = uid;
      localStorage.setItem('uid', uid)
    },

    clearUid(state) {
      state.uid = null;
    },
    // 其他 mutations...
  },
  actions: {

  },
};
