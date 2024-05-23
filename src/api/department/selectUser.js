import request from '../request'

export const selectUserByDepartment =(params)=>{
    return  request({
        url: '/user/getUsersByDepartment',
        method: 'GET',
        params
    })

}