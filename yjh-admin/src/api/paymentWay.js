import request from '@/utils/request'

export function listPaymentWay(query) {
  return request({
    url: '/paymentWay/list',
    method: 'get',
    params: query
  })
}

export function createPaymentWay(data) {
  return request({
    url: '/paymentWay/create',
    method: 'post',
    data
  })
}

export function readPaymentWay(data) {
  return request({
    url: '/paymentWay/read',
    method: 'get',
    data
  })
}

export function updatePaymentWay(data) {
  return request({
    url: '/paymentWay/update',
    method: 'post',
    data
  })
}

export function deletePaymentWay(data) {
  return request({
    url: '/paymentWay/delete',
    method: 'post',
    data
  })
}
