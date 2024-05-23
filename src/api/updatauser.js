import request from './request'


export const updateUser = (data) => {
    return request({
        url:'/user/updateUser',
        method: 'POST',
        data
    })
}