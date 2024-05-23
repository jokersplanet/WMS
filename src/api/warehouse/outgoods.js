import request from '../request'

//删除入库
export const deleteOutboundRecords =(params)=>{
    return  request({
        url: '/outbound/deleteOutboundRecords',
        method: 'GET',
        params
    })

}

//增加出库记录
export const insertOutboundRecords =(data)=>{
    return  request({
        url: '/outbound/insertOutboundRecords',
        method: 'POST',
        data
    })

}

//查询所有出库记录
export const getOutboundRecords =(params)=>{
    return  request({
        url: '/outbound/getOutboundRecords',
        method: 'GET',
        params
    })

}
//用时间查询出库
export const getOutboundRecordsByTime =(params)=>{
    return  request({
        url: '/outbound/getOutboundRecordsByTime',
        method: 'GET',
        params
    })

}
//修改出库记录
export const updateOutboundRecords =(data)=>{
    return  request({
        url: '/outbound/updateOutboundRecords',
        method: 'POST',
        data
    })

}