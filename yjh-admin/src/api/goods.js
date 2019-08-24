import request from '@/utils/request'

export function listGoods(query) {
  return request({
    url: '/goods/list',
    method: 'get',
    params: query
  })
}

export function deleteGoods(data) {
  return request({
    url: '/goods/delete',
    method: 'post',
    data
  })
}

export function publishGoods(data) {
  return request({
    url: '/goods/create',
    method: 'post',
    data
  })
}

export function detailGoods(id) {
  return request({
    url: '/goods/detail',
    method: 'get',
    params: { id }
  })
}

export function editGoods(data) {
  return request({
    url: '/goods/update',
    method: 'post',
    data
  })
}

export function listCatAndBrandAndSupplier() {
  return request({
    url: '/goods/catAndBrandAndSupplier',
    method: 'get'
  })
}

export function getGoodsAttribute(categoryId) {
  return request({
    url: '/goods/getGoodsAttribute',
    method: 'get',
    params: { categoryId }
  })
}

export function getGoodsAttributeValue(goodsId) {
  return request({
    url: '/goods/getGoodsAttributeValue',
    method: 'get',
    params: { goodsId }
  })
}
