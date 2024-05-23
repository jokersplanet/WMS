import request from '../request'

//删除货物
export const deleteGoods =(params)=>{
    return  request({
        url: '/goods/deleteGoods',
        method: 'GET',
        params  
    })

}
//根据条件查询所有
export const getGoods =(params)=>{
    return  request({
        url: '/goods/getGoods',
        method: 'GET',
        params
    })

}

//增加货物
export const insertGoods =(data)=>{
    return  request({
        url: '/goods/insertGoods',
        method: 'POST',
        data
    })

}

//根据入库时间查询货物
export const getGoodsByInboundTime =(params)=>{
    return  request({
        url: '/goods/getGoodsByInboundTime',
        method: 'GET',
        params
    })

}
//根据出库时间查询货物
export const getGoodsByOutboundTime =(params)=>{
    return  request({
        url: '/goods/getGoodsByOutboundTime',
        method: 'GET',
        params
    })

}



//修改货物
export const updateGoods =(data)=>{
    return  request({
        url: '/goods/updateGoods',
        method: 'POST',
        data
    })

}