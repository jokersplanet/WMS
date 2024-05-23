import request from '../request'

export const selectDepartment =(params)=>{
    return  request({
        url: '/department/selectDepartment',
        method: 'GET',
        params
    })

}