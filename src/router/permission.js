import router from './index'
import store from '../store'

const whiteList = ['/login']

router.beforeEach((to, from, next) => {
  if (store.getters.token !== '') {
    if (to.path === '/login') {
      next()
    } else {
      next()
    }
  } else {
    if (whiteList.includes(to.path)) {
      next()
    } else if (to.path === '/') { // 如果访问的是默认路由
      next('/enter') // 重定向到 enter 页面
    } else {
      next('/login')
    }
  }
})
