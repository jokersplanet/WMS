import request from './request'


export const getUser = (params) => {
    return request({
        url:'/user/listUsers',
        method: 'GET',
        params
    })
}

export const deleteAccount = (params) => {
    return request({
        url:'/user/deleteUserByUid',
        method:'GET',
        params
    })
}