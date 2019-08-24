import request from '@/utils/request'

export function listExtensionAttribute(query) {
  return request({
    url: '/extensionAttribute/list',
    method: 'get',
    params: query
  })
}

export function deleteExtensionAttribute(data) {
  return request({
    url: '/extensionAttribute/delete',
    method: 'post',
    data
  })
}



export function createExtensionAttribute(data) {
  return request({
    url: '/extensionAttribute/create',
    method: 'post',
    data
  })
}

export function detailExtensionAttribute(id) {
  return request({
    url: '/extensionAttribute/detail',
    method: 'get',
    params: { id }
  })
}

export function editExtensionAttribute(data) {
  return request({
    url: '/extensionAttribute/update',
    method: 'post',
    data
  })
}

export function listCat() {
  return request({
    url: '/extensionAttribute/cat',
    method: 'get'
  })
}

