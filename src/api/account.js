import request from './request'

export const getAccount =(params)=>{
    return  request({
        url: '/user/getUsersByUid',
        method: 'GET',
        params
    })

}