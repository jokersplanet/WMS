import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  // {
  //   path:'/enter',
  //   name:'enter',
  //   component: () => import('../views/enter/index.vue')
  // },
  {
    path: '/login',
    name: 'login',
    component: () => import('../views/Login/login.vue')
  },
  {
    path: '/',
    name: '/',
    component: () => import('../layout/index.vue'),
    redirect: '/order',
    children: [
      {
        path: 'user',
        name: 'user',
        component: () => import('../views/user/index.vue')
      },
      {
        path: 'department',
        name: 'department',
        component: () => import('..//views/department/index.vue')
      },
      {
        path: 'goods',
        name: 'goods',
        component: () => import('../views/goods/index.vue')
      },
      {
        path: 'Homepage',
        name: 'Homepage',
        component: () => import('../views/Homepage/index.vue')
      },
      {
        path: 'inGoods',
        name: 'inGoods',
        component: () => import('../views/inGoods/index.vue')
      },
      {
        path: 'outGoods',
        name: 'outGoods',
        component: () => import('../views/outGoods/index.vue')
      },
      {
        path: 'scrap',
        name: 'scrap',
        component: () => import('../views/scrap/index.vue')
      },
      {
        path: 'income',
        name: 'income',
        component: () => import('../views/income/index.vue')
      },
      {
        path: 'expenditure',
        name: 'expenditure',
        component: () => import('../views/expenditure/index.vue')
      },
      {
        path: 'account',
        name: 'account',
        component: () => import('../views/account/index.vue')
      },
      {
        path: 'category',
        name: 'category',
        component: () => import('../views/category/index.vue')
      },
      {
        path: 'DepartmentUser',
        name: 'DepartmentUser',
        component: () => import('../views/department/user.vue')
      },
      {
        path: 'WarehouseGoods',
        name: 'WarehouseGoods',
        component: () => import('../views/goods/goods.vue')
      },
      {
        path: 'smallGroup',
        name: 'smallGroup',
        component: () => import('../views/category/smallGroup.vue')
      },
      {
        path: 'unit',
        name: 'unit',
        component: () => import('../views/unit/index.vue')
      },
      {
        path: 'goodAccount',
        name: 'goodAccount',
        component: () => import('../views/goods/account.vue')
      },
      {
        path: 'status',
        name: 'status',
        component: () => import('../views/status/index.vue')
      },
      {
        path: 'order',
        name: 'order',
        component: () => import('../views/order/index.vue')
      }

    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  mode: 'hash',
  routes
})

export default router