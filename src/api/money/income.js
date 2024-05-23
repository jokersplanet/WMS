import request from '../request'

export const getIncome =(params)=>{
    return  request({
        url: '/income/getIncome',
        method: 'GET',
        params
    })

}

export const insertIncome =(data)=>{
    return  request({
        url: '/income/insertIncome',
        method: 'POST',
        data
    })

}

export const deleteIncome =(params)=>{
    return  request({
        url: '/income/deleteIncome',
        method: 'GET',
        params
    })

}

//查询收入时间
export const getIncomeByTime =(params)=>{
    return  request({
        url: '/income/getIncomeByTime',
        method: 'GET',
        params
    })

}

export const updateIncome =(data)=>{
    return  request({
        url: '/income/updateIncome',
        method: 'POST',
        data
    })

}

