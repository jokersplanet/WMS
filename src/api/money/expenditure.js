import request from '../request'

//查询支出时间
export const getExpenditureByTime =(params)=>{
    return  request({
        url: '/expenditure/getExpenditureByTime',
        method: 'GET',
        params
    })

}

export const deleteExpenditure =(params)=>{
    return  request({
        url: '/expenditure/deleteExpenditure',
        method: 'GET',
        params
    })

}

export const insertExpenditure =(data)=>{
    return  request({
        url: '/expenditure/insertExpenditure',
        method: 'POST',
        data    
    })

}

export const getExpenditure =(params)=>{
    return  request({
        url: 'expenditure/getExpenditure',
        method: 'GET',
        params
    })

}

export const updateExpenditure =(data)=>{
    return  request({
        url: '/expenditure/updateExpenditure',
        method: 'POST',
        data
    })

}

