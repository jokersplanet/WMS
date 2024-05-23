//验证token
import { login as loginApi } from '/src/api/login'
import router from '../../router'
import { setTokenTime } from '../../utils/auth'

export default {
    namespaced: true,
    state: () => ({
        token: localStorage.getItem('token') || '',
    }),
    mutations: {
        setToken(state, data) {
            state.token = data
            localStorage.setItem('token', data)
        }
    },
    actions: {
        login({ commit }, userInfo) {
            return new Promise((resolve, reject) => {
                loginApi(userInfo)
                    .then(res => {
                        console.log(res)
                        commit('setToken', res.data);
                        setTokenTime()
                        router.replace('/')
                        resolve()
                    }).catch(err => {
                        reject(err)
                    })
            })
        },
        // 退出
        logout({ commit }) {
            commit('setToken', '')
            localStorage.clear()
            router.replace('/login')
        },

        enter() {
            router.replace('/login')
        }
        
    }

}