import request from '../request'

export const selectWarehouse =(params)=>{
    return  request({
        url: '/warehouse/selectWarehouse',
        method: 'GET',
        params
    })

}