import request from '../request'


export const searchNumber = (params) => {
    return request({
        url:'/user/getUserDetailByNumber',
        method: 'GET',
        params
    })
}