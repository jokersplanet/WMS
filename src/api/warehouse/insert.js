import request from '../request'

export const insertWarehouse =(data)=>{
    return  request({
        url: '/warehouse/insertWarehouse',
        method: 'POST',
        data
    })

}
