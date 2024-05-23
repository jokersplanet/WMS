import request from '../request'

//删除状态
export const deleteOrder =(params)=>{
    return  request({
        url: '/order/deleteOrder',
        method: 'GET',
        params
    })

}

//查询状态
export const getOrderByTime =(params)=>{
    return  request({
        url: '/order/getOrderByTime',
        method: 'GET',
        params
    })

}

//通过时间查询
export const getOrder =(params)=>{
    return  request({
        url: '/order/getOrder',
        method: 'GET',
        params
    })

}

//增加状态
export const insertOrder =(data)=>{
    return  request({
        url: '/order/insertOrder',
        method: 'POST',
        data
    })

}
//修改状态
export const updateOrder =(data)=>{
    return  request({
        url: '/order/updateOrder',
        method: 'POST',
        data
    })

}