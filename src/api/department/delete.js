import request from '../request'

export const deleteDepartment =(params)=>{
    return  request({
        url: '/department/deleteDepartment',
        method: 'GET',
        params
    })

}