import request from '../request'


export const searchUid = (params) => {
    return request({
        url:'/user/getUserDetailByUid',
        method: 'GET',
        params
    })
}