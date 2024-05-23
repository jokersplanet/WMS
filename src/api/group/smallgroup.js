import request from '../request'

export const deleteClass =(params)=>{
    return  request({
        url: '/class/deleteClassById',
        method: 'GET',
        params
    })

}

export const deleteAllClass =(params)=>{
    return  request({
        url: '/class/deleteClassByGroupId',
        method: 'GET',
        params
    })

}

export const insertClass =(data)=>{
    return  request({
        url: '/class/insertClass',
        method: 'POST',
        data
    })

}

export const selectClass =(params)=>{
    return  request({
        url: '/class/getClassByGroupId',
        method: 'GET',
        params
    })

}

export const updateClass =(data)=>{
    return  request({
        url: '/class/updateClass',
        method: 'POST',
        data
    })

}

