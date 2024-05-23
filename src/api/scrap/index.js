import request from '../request'

//删除报废记录
export const deleteScrapRecords =(params)=>{
    return  request({
        url: '/scrap/deleteScrapRecords',
        method: 'GET',
        params
    })

}
//查询所有报废记录
export const getScrapRecords =(params)=>{
    return  request({
        url: '/scrap/getScrapRecords',
        method: 'GET',
        params
    })

}

//增加报废记录
export const insertScrapRecords =(data)=>{
    return  request({
        url: '/scrap/insertScrapRecords',
        method: 'POST',
        data
    })

}

//用时间查询报废
export const getScrapRecordsByTime =(params)=>{
    return  request({
        url: '/scrap/getScrapRecordsByTime',
        method: 'GET',
        params
    })

}
//修改报废记录
export const updateScrapRecords =(data)=>{
    return  request({
        url: '/scrap/updateScrapRecords',
        method: 'POST',
        data
    })

}