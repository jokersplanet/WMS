import { createStore } from 'vuex'
import app from './modules/app'
import getters from './getters'
import username from './modules/username'

export default createStore({
  modules: {
    app,
    username
  },
  getters
})
