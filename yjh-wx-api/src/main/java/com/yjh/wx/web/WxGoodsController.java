package com.yjh.wx.web;


import com.yjh.core.util.ResponseUtil;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.*;
import com.yjh.db.service.*;
import com.yjh.wx.annotation.LoginUser;
import com.yjh.wx.dto.GoodsInfo;
import com.yjh.wx.vo.GoodsAttributeVo;
import com.yjh.wx.vo.GoodsSpecificationVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 商品服务
 */
@RestController
@RequestMapping("/wx/goods")
@Validated
public class WxGoodsController {
	private final Log logger = LogFactory.getLog(WxGoodsController.class);
	@Value("${static.server.address}")
	private String uploadAddress;
	@Autowired
	GoodsService goodsService;
	@Autowired
	ExpressRuleService expressRuleService;
	@Autowired
    GoodsExtensionAttributeService goodsExtensionAttributeService;
	@Autowired
    ExtensionAttributeService extensionAttributeService;
	@Autowired
	GoodsSpecificationService goodsSpecificationService;
	@Autowired
	GoodsSpecificationAttributeService goodsSpecificationAttributeService;
	@Autowired
	GoodsAndProductPictureService goodsAndProductPictureService;
	@Autowired
	GoodsProductService goodsProductService;
	@Autowired
	GoodsCategoryService categoryService;


	private final static ArrayBlockingQueue<Runnable> WORK_QUEUE = new ArrayBlockingQueue<>(9);

	private final static RejectedExecutionHandler HANDLER = new ThreadPoolExecutor.CallerRunsPolicy();

	private static ThreadPoolExecutor executorService = new ThreadPoolExecutor(16, 16, 1000, TimeUnit.MILLISECONDS, WORK_QUEUE, HANDLER);

	/**
	 * 商品详情
	 * <p>
	 * 用户可以不登录。
	 * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
	 *
	 * @param userId 用户ID
	 * @param id     商品ID
	 * @return 商品详情
	 */
	@GetMapping("detail")
	public Object detail(@LoginUser Integer userId, @NotNull Integer id) {
		// 商品信息
		Callable<Object> info=()->{
			AllSpjbxx goodsByGoodsId = goodsService.getGoodsByGoodsId(id);
			String []goodsPicture = goodsAndProductPictureService.getAllGoodsPictures(id);
			for (int i = 0; i < goodsPicture.length; i++) {
				goodsPicture[i]=uploadAddress+goodsPicture[i];
			}
			GoodsInfo info1=new GoodsInfo();
			info1.setGallery(goodsPicture);
			info1.setPicUrl(goodsPicture[0]);
			info1.setRetailPrice(goodsByGoodsId.getSpsj().doubleValue());
			info1.setCounterPrice(goodsByGoodsId.getScj().doubleValue());
			info1.setName(goodsByGoodsId.getSpmc());
			info1.setGoodsId(goodsByGoodsId.getSpbm());
			info1.setDetail(goodsByGoodsId.getCpms());
			//得到运费规则
			AllYfgz expressRuleById = expressRuleService.getExpressRuleById(goodsByGoodsId.getYfgzbm());
			if(expressRuleById!=null)
				info1.setFreightRule(expressRuleById.getYfgz());
			return info1;
		};

		// 商品属性
		Callable<List> attributeCallable=()->{
			List<AllSpkzsxz> extensionAttributeValue = goodsExtensionAttributeService.getExtensionAttributeValue(id);
			List<GoodsAttributeVo> attributeList=new ArrayList<>(extensionAttributeValue.size());
			for (AllSpkzsxz e :
					extensionAttributeValue) {
				AllKzsx extensionAttributeByid = extensionAttributeService.getExtensionAttributeByid(e.getKzsxbm());
				GoodsAttributeVo goodsAttributeVo=new GoodsAttributeVo();
				goodsAttributeVo.setExtensionAttribute(extensionAttributeByid.getSxmc());
				goodsAttributeVo.setExtensionAttributeValue(e.getKzsxz());
				attributeList.add(goodsAttributeVo);
			}
			return attributeList;
		};


		// 商品规格 返回的是定制的GoodsSpecificationVo
//		Callable<Object> objectCallable = () -> goodsSpecificationService.getSpecificationVoList(id);

//		 商品规格对应的数量和价格
		Callable<Object> productListCallable = () -> {
			List<AllSpgg> specifications = goodsSpecificationService.getGoodsSpecificationsByGoodsId(id);
			List<AllSpkc> goodsProducts = goodsProductService.getByGoodsId(id);
			Map<String, Object> data = new HashMap<>();
			data.put("specifications",specifications);
			data.put("products",goodsProducts);
//			Map<String, VO> map = new HashMap<>();
//			List<VO> specificationVoList = new ArrayList<>();
//			for (AllSpgg s :
//					specifications) {
//				List<AllSpggsxz> specificationAttributes = goodsSpecificationAttributeService.getSpecificationAttributesBySpecificationId(s.getSpggbm());
//				for (AllSpggsxz sa :
//						specificationAttributes) {
//					String specification = s.getGgmc();
//					VO goodsSpecificationVo = map.get(specification);
//					if (goodsSpecificationVo == null) {
//						goodsSpecificationVo = new VO();
//						goodsSpecificationVo.setName(specification);
//						List<AllSpggsxz> valueList = new ArrayList<>();
//						valueList.add(sa);
//						goodsSpecificationVo.setValueList(valueList);
//						map.put(specification, goodsSpecificationVo);
//						specificationVoList.add(goodsSpecificationVo);
//					} else {
//						List<AllSpggsxz> valueList = goodsSpecificationVo.getValueList();
//						valueList.add(sa);
//					}
//
//				}
//			}
			return data;
		};



//
//		// 商品问题，这里是一些通用问题
//		Callable<List> issueCallable = () -> goodsIssueService.querySelective("", 1, 4, "", "");
//
//		// 商品品牌商
//		Callable<LitemallBrand> brandCallable = ()->{
//			Integer brandId = info.getBrandId();
//			LitemallBrand brand;
//			if (brandId == 0) {
//				brand = new LitemallBrand();
//			} else {
//				brand = brandService.findById(info.getBrandId());
//			}
//			return brand;
//		};
//
//		// 评论
//		Callable<Map> commentsCallable = () -> {
//			List<LitemallComment> comments = commentService.queryGoodsByGid(id, 0, 2);
//			List<Map<String, Object>> commentsVo = new ArrayList<>(comments.size());
//			long commentCount = PageInfo.of(comments).getTotal();
//			for (LitemallComment comment : comments) {
//				Map<String, Object> c = new HashMap<>();
//				c.put("id", comment.getId());
//				c.put("addTime", comment.getAddTime());
//				c.put("content", comment.getContent());
//				LitemallUser user = userService.findById(comment.getUserId());
//				c.put("nickname", user == null ? "" : user.getNickname());
//				c.put("avatar", user == null ? "" : user.getAvatar());
//				c.put("picList", comment.getPicUrls());
//				commentsVo.add(c);
//			}
//			Map<String, Object> commentList = new HashMap<>();
//			commentList.put("count", commentCount);
//			commentList.put("data", commentsVo);
//			return commentList;
//		};
//
//		//团购信息
//		Callable<List> grouponRulesCallable = () ->rulesService.queryByGoodsId(id);
//
//		// 用户收藏
//		int userHasCollect = 0;
//		if (userId != null) {
//			userHasCollect = collectService.count(userId, id);
//		}
//
//		// 记录用户的足迹 异步处理
//		if (userId != null) {
//			executorService.execute(()->{
//				LitemallFootprint footprint = new LitemallFootprint();
//				footprint.setUserId(userId);
//				footprint.setGoodsId(id);
//				footprintService.add(footprint);
//			});
//		}
		FutureTask<List> getAttributeListTask = new FutureTask<>(attributeCallable);
		FutureTask<Object> getGoodsInfoTask=new FutureTask<>(info);
//		FutureTask<List> objectCallableTask = new FutureTask<>(productListCallable);
		FutureTask<Object> productListCallableTask = new FutureTask<>(productListCallable);
//		FutureTask<List> issueCallableTask = new FutureTask<>(issueCallable);
//		FutureTask<Map> commentsCallableTsk = new FutureTask<>(commentsCallable);
//		FutureTask<LitemallBrand> brandCallableTask = new FutureTask<>(brandCallable);
//        FutureTask<List> grouponRulesCallableTask = new FutureTask<>(grouponRulesCallable);
//
		executorService.submit(getAttributeListTask);
//		executorService.submit(objectCallableTask);
		executorService.submit(getGoodsInfoTask);
		executorService.submit(productListCallableTask);
//		executorService.submit(issueCallableTask);
//		executorService.submit(commentsCallableTsk);
//		executorService.submit(brandCallableTask);
//		executorService.submit(grouponRulesCallableTask);

		Map<String, Object> data = new HashMap<>();

		try {
			data.put("info", getGoodsInfoTask.get());
//			data.put("userHasCollect", userHasCollect);
//			data.put("issue", issueCallableTask.get());
//			data.put("comment", commentsCallableTsk.get());
//			data.put("specificationList", objectCallableTask.get());
			data.put("productList", productListCallableTask.get());
			data.put("attribute", getAttributeListTask.get());
//			data.put("brand", brandCallableTask.get());
//			data.put("groupon", grouponRulesCallableTask.get());
//			//SystemConfig.isAutoCreateShareImage()
//			data.put("share", SystemConfig.isAutoCreateShareImage());

		}
		catch (Exception e) {
			e.printStackTrace();
		}

		//商品分享图片地址
//		data.put("shareImage", info.getShareUrl());
		return ResponseUtil.ok(data);
	}

	/**
	 * 商品分类类目
	 *
	 * @param id 分类类目ID
	 * @return 商品分类类目
	 */
	@GetMapping("category")
	public Object category(@NotNull Integer id) {
		AllSpfl cur = categoryService.getGoodsCategoryById(id);
		AllSpfl parent = null;
		List<AllSpfl> children = null;

		if (cur.getSjspflbm() == 0) {
			parent = cur;
			children = categoryService.getGoodsL2CategoriesBySuperiorCategoryId(cur.getSpflbm());
			cur = children.size() > 0 ? children.get(0) : cur;
		} else {
			parent = categoryService.getGoodsCategoryById(cur.getSjspflbm());
			children = categoryService.getGoodsL2CategoriesBySuperiorCategoryId(cur.getSjspflbm());
		}
		Map<String, Object> data = new HashMap<>();
		data.put("currentCategory", cur);
		data.put("parentCategory", parent);
		data.put("brotherCategory", children);
		return ResponseUtil.ok(data);
		//return ResponseUtil.ok(data);
	}

	/**
	 * 根据条件搜素商品
	 * <p>
	 * 1. 这里的前五个参数都是可选的，甚至都是空
	 * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
	 *
	 * @param categoryId 分类类目ID，可选
	 * @param brandId    品牌商ID，可选
	 * @param keyword    关键字，可选
	 * @param isHot      是否热买，可选
	 * @param userId     用户ID
	 * @param page       分页页数
	 * @param limit       分页大小
	 * @param sort       排序方式，支持"add_time", "retail_price"或"name"
	 * @param order      排序类型，顺序或者降序
	 * @return 根据条件搜素的商品详情
	 */
	@GetMapping("list")
	public Object list(
		Integer categoryId,
		Integer brandId,
		String keyword,
		Boolean isRecomm,
		Boolean isHot,
		@LoginUser Integer userId,
		@RequestParam(defaultValue = "1") Integer page,
		@RequestParam(defaultValue = "10") Integer limit,
		@Sort @RequestParam(defaultValue = "tjsj") String sort,
		@Order @RequestParam(defaultValue = "desc") String order) {

		//添加到搜索历史
//		if (userId != null && !StringUtils.isNullOrEmpty(keyword)) {
//			LitemallSearchHistory searchHistoryVo = new LitemallSearchHistory();
//			searchHistoryVo.setKeyword(keyword);
//			searchHistoryVo.setUserId(userId);
//			searchHistoryVo.setFrom("wx");
//			searchHistoryService.save(searchHistoryVo);
//		}
		List<AllSpjbxx> goodsList = goodsService.querySelectiveByBrandId(categoryId,brandId, page, limit, sort, order);
		List<GoodsInfo> goodsInfos=new ArrayList<>(goodsList.size());
		for (AllSpjbxx goods :
				goodsList) {
			String goodsPicture = goodsAndProductPictureService.getGoodsPicture(goods.getSpbm());
			GoodsInfo g=new GoodsInfo();
			g.setGoodsId(goods.getSpbm());
			g.setRetailPrice(goods.getSpsj().doubleValue());
			g.setCounterPrice(goods.getScj().doubleValue());
			g.setName(goods.getSpmc());
			g.setPicUrl(uploadAddress+goodsPicture);
			goodsInfos.add(g);
		}
//		//查询列表数据
//		List<LitemallGoods> goodsList = goodsService.querySelective(categoryId, brandId, keyword, isHot, isNew, page, limit, sort, order);
//
//		// 查询商品所属类目列表。
//		List<Integer> goodsCatIds = goodsService.getCatIds(brandId, keyword, isHot, isNew);
//		List<LitemallCategory> categoryList = null;
//		if (goodsCatIds.size() != 0) {
//			categoryList = categoryService.queryL2ByIds(goodsCatIds);
//		} else {
//			categoryList = new ArrayList<>(0);
//		}
//
//		PageInfo<LitemallGoods> pagedList = PageInfo.of(goodsList);

		Map<String, Object> entity = new HashMap<>();
		entity.put("list", goodsInfos);
//		entity.put("total", pagedList.getTotal());
//		entity.put("page", pagedList.getPageNum());
//		entity.put("limit", pagedList.getPageSize());
//		entity.put("pages", pagedList.getPages());
//		entity.put("filterCategoryList", categoryList);

		// 因为这里需要返回额外的filterCategoryList参数，因此不能方便使用ResponseUtil.okList
		return ResponseUtil.ok(entity);
	}

	/**
	 * 商品详情页面“大家都在看”推荐商品
	 *
//	 * @param id, 商品ID
	 * @return 商品详情页面推荐商品
	 */
//	@GetMapping("related")
//	public Object related(@NotNull Integer id) {
//		LitemallGoods goods = goodsService.findById(id);
//		if (goods == null) {
//			return ResponseUtil.badArgumentValue();
//		}
//
//		// 目前的商品推荐算法仅仅是推荐同类目的其他商品
//		int cid = goods.getCategoryId();
//
//		// 查找六个相关商品
//		int related = 6;
//		List<LitemallGoods> goodsList = goodsService.queryByCategory(cid, 0, related);
//		return ResponseUtil.okList(goodsList);
//	}
//
//	/**
//	 * 在售的商品总数
//	 *
//	 * @return 在售的商品总数
//	 */
//	@GetMapping("count")
//	public Object count() {
//		Integer goodsCount = goodsService.queryOnSale();
//		return ResponseUtil.ok(goodsCount);
//	}

	private class VO {
		private String name;
		private List<AllSpggsxz> valueList;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<AllSpggsxz> getValueList() {
			return valueList;
		}

		public void setValueList(List<AllSpggsxz> valueList) {
			this.valueList = valueList;
		}
	}

}