import { createApp } from 'vue'
import App from './App.vue'
import Elementplus from 'element-plus'
// import './styles/index.scss'
// import './styles/variables.scss'
import './router/permission'
import i18n from './i18n'
import store from './store'
import router from './router'

const app = createApp(App)
app.use(Elementplus)
app.use(i18n)
app.use(store)
app.use(router) 
app.mount('#app')
