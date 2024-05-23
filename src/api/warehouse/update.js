import request from '../request'

export const updateWarehouse =(data)=>{
    return  request({
        url: '/warehouse/updateWarehouse',
        method: 'POST',
        data
    })

}