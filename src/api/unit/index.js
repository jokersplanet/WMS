import request from '../request'

//删除单位
export const deleteUnitById =(params)=>{
    return  request({
        url: '/unit/deleteUnitById',
        method: 'GET',
        params
    })

}

//查询单位
export const getUnit =(params)=>{
    return  request({
        url: '/unit/getUnit',
        method: 'GET',
        params
    })

}

//增加单位
export const insertUnit =(data)=>{
    return  request({
        url: '/unit/insertUnit',
        method: 'POST',
        data
    })

}
//修改单位
export const updateUnit =(data)=>{
    return  request({
        url: '/unit/updateUnit',
        method: 'POST',
        data
    })

}