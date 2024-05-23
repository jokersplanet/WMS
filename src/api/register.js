import request from './request'


export const register = (data) => {
    return request({
        url:'/register',
        method: 'POST',
        data
    })
}