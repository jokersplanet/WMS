//axios全局配置
import axios from "axios";
//axios实例
const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API,
  timeout: 5000
})

// axios.defaults.baseURL = "http://localhost:8081"
// 响应拦截器，用来简化拿取data 
service.interceptors.response.use(
  (response) => {
    console.log(response)
    if (response.data.status === 200 || response.data.status === 201) {
      return response.data
    } else {  
      ElMessage.error( response.data.msg)
      return Promise.reject(new Error(response.data.msg))
    }
  },
  (error) => {
    console.log(error)
    error.response && ElMessage.error(error.data.msg)
    return Promise.reject(new Error(error.data.msg))
  }
)

// 请求拦截器，为每一个请求添加token
service.interceptors.request.use(config => {
  config.headers.Authorization = `Bearer ${localStorage.getItem('token')}`
  return config
}),error => {
  return Promise.reject(new Error(error)) 
}


export default service