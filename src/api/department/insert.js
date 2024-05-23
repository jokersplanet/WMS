import request from '../request'

export const insertDepartment =(data)=>{
    return  request({
        url: '/department/insertDepartment',
        method: 'POST',
        data
    })

}
