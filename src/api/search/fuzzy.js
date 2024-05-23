import request from '../request'


export const searchTelephone = (params) => {
    return request({
        url:'/user/getUsersByTelephone',
        method: 'GET',
        params
    })
}

export const searchNumber = (params) => {
    return request({
        url:'/user/getUsersByNumber',
        method: 'GET',
        params
    })
}
export const searchUid = (params) => {
    return request({
        url:'/user/getUsersByUid',
        method: 'GET',
        params
    })
}