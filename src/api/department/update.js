import request from '../request'

export const updateDepartment =(data)=>{
    return  request({
        url: '/department/updateDepartment',
        method: 'POST',
        data
    })

}