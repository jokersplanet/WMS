import request from '../request'

//删除出库
export const deleteInboundRecords =(params)=>{
    return  request({
        url: '/inbound/deleteInboundRecords',
        method: 'GET',
        params
    })

}
//用时间查询入库
export const getInBoundRecordsByTime =(params)=>{
    return  request({
        url: '/inbound/getInBoundRecordsByTime',
        method: 'GET',
        params
    })

}

//增加入库记录
export const insertInboundRecords =(data)=>{
    return  request({
        url: '/inbound/insertInboundRecords',
        method: 'POST',
        data
    })

}

//查询所有入库记录
export const getInboundRecords =(params)=>{
    return  request({
        url: '/inbound/getInboundRecords',
        method: 'GET',
        params
    })

}
//修改入库记录
export const updateInboundRecords =(data)=>{
    return  request({
        url: '/inbound/updateInboundRecords',
        method: 'POST',
        data
    })

}