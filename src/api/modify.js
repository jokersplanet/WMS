import request from './request'

export const modify = (data) => {
  return request({
    url: '/user/updateUser',
    method: 'POST',
    data
  })
}
