import request from '../request'


export const searchTelephone = (params) => {
    return request({
        url:'/user/getUserDetailByTelephone',
        method: 'GET',
        params
    })
}