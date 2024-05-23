import request from '../request'

//删除状态
export const deleteStatusById =(params)=>{
    return  request({
        url: '/status/deleteStatusById',
        method: 'GET',
        params
    })

}

//查询状态
export const getStatus =(params)=>{
    return  request({
        url: '/status/getStatus',
        method: 'GET',
        params
    })

}

//增加状态
export const insertStatus =(data)=>{
    return  request({
        url: '/status/insertStatus',
        method: 'POST',
        data
    })

}
//修改状态
export const updateStatus =(data)=>{
    return  request({
        url: '/status/updateStatus',
        method: 'POST',
        data
    })

}