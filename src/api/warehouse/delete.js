import request from '../request'

export const deleteWarehouse =(params)=>{
    return  request({
        url: '/warehouse/deleteWarehouseById',
        method: 'GET',
        params
    })

}