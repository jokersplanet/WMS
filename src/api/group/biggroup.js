import request from '../request'


export const deleteGroup =(params)=>{
    return  request({
        url: '/group/deleteGroupById',
        method: 'GET',
        params
    })

}


export const insertGroup =(data)=>{
    return  request({
        url: '/group/insertGroup',
        method: 'POST',
        data
    })

}


export const selectGroup =(params)=>{
    return  request({
        url: '/group/getGroup',
        method: 'GET',
        params
    })

}

export const updateGroup =(data)=>{
    return  request({
        url: '/group/updateGroup',
        method: 'POST',
        data
    })

}