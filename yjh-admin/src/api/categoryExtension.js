import request from '@/utils/request'

export function deleteCategoryExtension(data) {
  return request({
    url: '/categoryExtension/delete',
    method: 'post',
    data
  })
}
export function addCategoryExtension(data) {
  return request({
    url: '/categoryExtension/update',
    method: 'post',
    data
  })
}

export function getGoodsAttribute(categoryId) {
  return request({
    url: '/categoryExtension/getGoodsAttribute',
    method: 'get',
    params: { categoryId }
  })
}

export function getAllExtensionAttributes() {
  return request({
    url: '/categoryExtension/getAllExtensionAttributes',
    method: 'get',
  })
}

export function createCategoryExtension(data) {
  return request({
    url: '/categoryExtension/create',
    method: 'post',
    data
  })
}

