package com.yjh.admi.service;

import com.yjh.admi.dto.GoodsAllinone;
import com.yjh.admi.dto_helper.GoodsAttributeInfo;
import com.yjh.admi.dto_helper.GoodsInfo;
import com.yjh.admi.dto_helper.GoodsProductInfo;
import com.yjh.admi.dto_helper.GoodsSpecificationInfo;
import com.yjh.admi.vo.CatVo;
import com.yjh.admi.vo.ExtensionAttributeVo;
import com.yjh.core.util.ResponseUtil;
import com.yjh.db.domain.*;
import com.yjh.db.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yjh.admi.util.AdminResponseCode.GOODS_NAME_EXIST;

/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class AdminGoodsService {
    private final Log logger = LogFactory.getLog(AdminGoodsService.class);

    @Value("${static.upload.path}")
    private String uploadPath;
    @Value("${static.server.address}")
    private String uploadAddress;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    GoodsBrandService goodsBrandService;
    @Autowired
    GoodsModulesService goodsModulesService;
    @Autowired
    GoodsCategoryService goodsCategoryService;
    @Autowired
    SupplierService supplierService;
    @Autowired
    GoodsSpecificationService goodsSpecificationService;
    @Autowired
    GoodsSpecificationAttributeService goodsSpecificationAttributeService;
    @Autowired
    GoodsAndSpecificationPictureService goodsAndSpecificationPictureService;
    @Autowired
    PaymentWayService paymentWayService;
    @Autowired
    FreightRuleService freightRuleService;

    @Autowired
    GoodsCategoryExtensionService goodsCategoryExtensionService;
    @Autowired
    GoodsExtensionAttributeService goodsExtensionAttributeService;
    @Autowired
    ExtensionAttributeService extensionAttributeService;
    @Autowired
    GoodsProductService goodsProductService;
    @Autowired
    GoodsAndProductPictureService goodsAndProductPictureService;


//    @Autowired
//    private QCodeService qCodeService;

    public Object list(String goodsSn, String name,
                       Integer page, Integer limit, String sort, String order) {
        List<AllSpjbxx> goodsList = goodsService.querySelective(goodsSn, name, page, limit, sort, order);
        List<GoodsInfo> goodsInfos=new ArrayList<>(goodsList.size());
        for (AllSpjbxx goods :
             goodsList) {
            GoodsInfo goodsInfo=new GoodsInfo();
            String goodsPicture = goodsAndProductPictureService.getGoodsPicture(goods.getSpbm());
            goodsInfo.setGoodsId(goods.getSpbm());
            goodsInfo.setName(goods.getSpmc());
            goodsInfo.setCounterPrice(goods.getScj().doubleValue());
            goodsInfo.setRetailPrice(goods.getSpsj().doubleValue());
            goodsInfo.setCostPrice(goods.getCbj().doubleValue());
            goodsInfo.setTaxCost(goods.getSf().doubleValue());
            goodsInfo.setIsRecommendation(goods.getSfstjsp());
            goodsInfo.setIsOnSale(goods.getSfsj());
            goodsInfo.setPicUrl(uploadAddress+goodsPicture);
            goodsInfo.setUnit(goods.getJldw());
            goodsInfo.setBrandId(goods.getSpppbm());
            goodsInfo.setSupplierId(goods.getGysbm());
            goodsInfo.setBelongModule(goods.getSsbkbm());
            goodsInfo.setDetail(goods.getCpms());
            goodsInfo.setCategoryId(goods.getSpflbm());
            goodsInfo.setPaymentWay(goods.getZffsbm());
            goodsInfo.setFreightRule(goods.getYfgzbm());
            goodsInfos.add(goodsInfo);
        }
        return ResponseUtil.okList(goodsInfos);
    }

    private Object validate(GoodsAllinone goodsAllinone) {
        GoodsInfo goods = goodsAllinone.getGoods();
        String name = goods.getName();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }

    /**
     * 编辑商品
     * <p>
     * TODO
     * 目前商品修改的逻辑是
     * 1. 更新litemall_goods表
     * 2. 逻辑删除litemall_goods_specification、litemall_goods_attribute、litemall_goods_product
     * 3. 添加litemall_goods_specification、litemall_goods_attribute、litemall_goods_product
     * <p>
     * 这里商品三个表的数据采用删除再添加的策略是因为
     * 商品编辑页面，支持管理员添加删除商品规格、添加删除商品属性，因此这里仅仅更新是不可能的，
     * 只能删除三个表旧的数据，然后添加新的数据。
     * 但是这里又会引入新的问题，就是存在订单商品货品ID指向了失效的商品货品表。
     * 因此这里会拒绝管理员编辑商品，如果订单或购物车中存在商品。
     * 所以这里可能需要重新设计。
     */
    @Transactional
    public Object update(GoodsAllinone goodsAllinone,String userName) {
        Object error = validate(goodsAllinone);
        if (error != null) {
            return error;
        }
        GoodsInfo goods = goodsAllinone.getGoods();
        GoodsSpecificationInfo[] specifications = goodsAllinone.getSpecifications();
        GoodsProductInfo[] products = goodsAllinone.getProducts();
        LocalDateTime ldt = LocalDateTime.now();
        AllSpjbxx goodsEntity=new AllSpjbxx();
        goodsEntity.setSpmc(goods.getName());//设置商品名称
        goodsEntity.setZffsbm(goods.getPaymentWay());//设置支付方式编码
        String categoryIds = goods.getCategoryId();
        //设置商品分类编码

        goodsEntity.setSpflbm(categoryIds);
        goodsEntity.setYfgzbm(goods.getFreightRule());//设置运费规则编码
        goodsEntity.setSsbkbm(Integer.valueOf(goods.getBelongModule()));//商品所属板块
        goodsEntity.setScj(BigDecimal.valueOf(goods.getCounterPrice()));//市场价
        goodsEntity.setSpsj(BigDecimal.valueOf(goods.getRetailPrice()));//商品售价
        goodsEntity.setCbj(BigDecimal.valueOf(goods.getCostPrice()));//成本价
        goodsEntity.setSf(BigDecimal.valueOf(goods.getTaxCost()));//税费
        goodsEntity.setSfstjsp(goods.getIsRecommendation());//是否是推荐商品
        goodsEntity.setSfsj(goods.getIsOnSale());//是否上架
        goodsEntity.setJldw(goods.getUnit());//计量单位
        goodsEntity.setSpppbm(Integer.valueOf(goods.getBrandId()));//商品品牌编码
        goodsEntity.setGysbm(String.valueOf(goods.getSupplierId()));//供应商编码，不能为null
        goodsEntity.setCpms(goods.getDetail());//产品描述
        goodsEntity.setZhxgr(userName);//最后修改人,不能为null
        goodsEntity.setZhxgsj(ldt);//最后修改时间,不能为null
        goodsEntity.setZhszsxjsj(ldt);//最后设置上下架时间,不能为null
        goodsEntity.setZhsztjr(userName);//最后设置推荐人,不能为null
        goodsEntity.setZhsztjsj(ldt);//最后设置推荐时间,不能为null
        goodsEntity.setSzsxjr(userName);
        int goodsId;//商品id
        goodsEntity.setSpbm(goods.getGoodsId());
        goodsService.updateGoodsInfo(goodsEntity);//更新商品信息
        goodsId=goods.getGoodsId();

        //将生成的分享图片地址写入数据库
//        String url = qCodeService.createGoodShareImage(goods.getId().toString(), goods.getPicUrl(), goods.getName());
//        if (!StringUtils.isEmpty(url)) {
//            goods.setShareUrl(url);
//            if (goodsService.updateById(goods) == 0) {
//                throw new RuntimeException("更新数据失败");
//            }
//        }
        //删除该商品的所有图片
        goodsAndProductPictureService.deleteSpecificationPictureByGoodsId(goodsId);
        //将封面图写入数据库
        String[] gallery = goodsAllinone.getGoods().getGallery();
        for (int i = 0; i < gallery.length; i++) {
            AllSpjkctp pic=new AllSpjkctp();
            pic.setTpljjmc(gallery[i]);
            pic.setSpbm(goodsId);
            goodsAndProductPictureService.add(pic);
        }

        //先删除原先的扩展信息，再商品扩展信息写入数据库
        goodsExtensionAttributeService.deleteByGoodsId(goodsId);
        ExtensionAttributeVo[] extensionAttributes = goodsAllinone.getExtensionAttributes();
        for (ExtensionAttributeVo e :
                extensionAttributes) {
            AllSpkzsxz extensionAttribute=new AllSpkzsxz();
            extensionAttribute.setSpbm(goodsId);
            extensionAttribute.setKzsxbm(e.getExtensionAttributeId());
            if(!StringUtils.isEmpty(e.getExtensionAttributeValue()))
                extensionAttribute.setKzsxz(e.getExtensionAttributeValue());
            goodsExtensionAttributeService.add(extensionAttribute);
        }
        //删除原先的商品规格
        goodsSpecificationService.deleteSpecificationByGoodsId(goodsId);
        // 将商品规格写入数据库
        for (GoodsSpecificationInfo specification : specifications) {
            AllSpgg goodsSpecification=new AllSpgg();
            goodsSpecification.setGgmc(specification.getSpecification());//商品规格名称
            goodsSpecification.setSpbm(goodsId);//商品编码
            goodsSpecification.setGgz(specification.getValue());
            goodsSpecificationService.addGoodsSpecification(goodsSpecification);
        }
        //删除商品库存信息
        goodsProductService.deleteByGoodsId(goodsId);
        //将商品库存写入数据库
        for (GoodsProductInfo product :
                products) {
            AllSpkc goodsProduct=new AllSpkc();
            goodsProduct.setCbj(BigDecimal.valueOf(product.getCostPrice()));
            goodsProduct.setSpsj(BigDecimal.valueOf(product.getPrice()));
            goodsProduct.setJfzddked(BigDecimal.valueOf(product.getBiggestDeductionAmount()));
            goodsProduct.setKc(product.getNumber());
            goodsProduct.setTjr(userName);
            goodsProduct.setTjsj(ldt);
            goodsProduct.setZhxgr(userName);
            goodsProduct.setSpbm(goodsId);
            goodsProduct.setZhxgsj(ldt);
            goodsProduct.setXlggz(product.getValue());
            int goodsProductId = goodsProductService.add(goodsProduct);
            //将规格图片写入数据库
            AllSpjkctp pic=new AllSpjkctp();
            pic.setSpkcbm(goodsProductId);
            pic.setSpbm(goodsId);
            if (StringUtils.isEmpty(product.getUrl())||product.getUrl().equals(""))
                pic.setTpljjmc("默认图片");
            pic.setTpljjmc(product.getUrl());
            goodsAndProductPictureService.add(pic);
        }





        return ResponseUtil.ok();
    }

    @Transactional
    public Object delete(GoodsInfo goods) {
        Integer goodsId = goods.getGoodsId();//商品编码
        //删除商品库存
        goodsSpecificationService.deleteSpecificationByGoodsId(goodsId);
        //删除商品规格
        goodsProductService.deleteByGoodsId(goodsId);
        //删除商品规格的图片路径及名称
        List<String> pictures = goodsAndProductPictureService.getAllPicturebyGoodsId(goodsId);
        if(pictures!=null){
            for (String picture :
                    pictures) {
                String[] split = picture.split("/");
                String pictureFileName=uploadPath+split[split.length-1];
                deleteFile(pictureFileName);
            }
            //删除规格图片记录
            goodsAndProductPictureService.deleteSpecificationPictureByGoodsId(goodsId);
        }
        //删除商品信息
        goodsService.deleteGoods(goodsId);
        return ResponseUtil.ok();
    }

    @Transactional
    public Object create(GoodsAllinone goodsAllinone,String userName) {

        Object error = validate(goodsAllinone);
        if (error != null) {
            return error;
        }
        GoodsInfo goods = goodsAllinone.getGoods();
        //GoodsAttributeInfo[] attributes = goodsAllinone.getAttributes();
        GoodsSpecificationInfo[] specifications = goodsAllinone.getSpecifications();
        GoodsProductInfo[] products = goodsAllinone.getProducts();
        String name = goods.getName();
        if (goodsService.checkExistByName(name)) {
            return ResponseUtil.fail(GOODS_NAME_EXIST, "商品名已经存在");
        }
        LocalDateTime ldt = LocalDateTime.now();
        AllSpjbxx goodsEntity=new AllSpjbxx();
        goodsEntity.setSpmc(goods.getName());//设置商品名称
        goodsEntity.setZffsbm(goods.getPaymentWay());//设置支付方式编码
        String categoryIds = goods.getCategoryId();
        //设置商品分类编码
        goodsEntity.setSpflbm(categoryIds);
        goodsEntity.setYfgzbm(goods.getFreightRule());//设置运费规则编码
        goodsEntity.setSsbkbm(Integer.valueOf(goods.getBelongModule()));//商品所属板块
        goodsEntity.setScj(BigDecimal.valueOf(goods.getCounterPrice()));//市场价
        goodsEntity.setSpsj(BigDecimal.valueOf(goods.getRetailPrice()));//商品售价
        goodsEntity.setCbj(BigDecimal.valueOf(goods.getCostPrice()));//成本价
        goodsEntity.setSf(BigDecimal.valueOf(goods.getTaxCost()));//税费
        goodsEntity.setSfstjsp(goods.getIsRecommendation());//是否是推荐商品
        goodsEntity.setSfsj(goods.getIsOnSale());//是否上架
        goodsEntity.setJldw(goods.getUnit());//计量单位
        goodsEntity.setSpppbm(Integer.valueOf(goods.getBrandId()));//商品平拍编码
        goodsEntity.setGysbm(String.valueOf(goods.getSupplierId()));//供应商编码，不能为null
        goodsEntity.setCpms(goods.getDetail());//产品描述
        goodsEntity.setTjr(userName);//不能为null
        goodsEntity.setTjsj(ldt);//添加时间,不能为null
        goodsEntity.setZhxgr(userName);//最后修改人,不能为null
        goodsEntity.setZhxgsj(ldt);//最后修改时间,不能为null
        goodsEntity.setZhszsxjsj(ldt);//最后设置上下架时间,不能为null
        goodsEntity.setZhsztjr(userName);//最后设置推荐人,不能为null
        goodsEntity.setZhsztjsj(ldt);//最后设置推荐时间,不能为null
        goodsEntity.setSzsxjr(userName);
        int goodsId = goodsService.addGoods(goodsEntity);

        //将商品扩展信息写入数据库
        ExtensionAttributeVo[] extensionAttributes = goodsAllinone.getExtensionAttributes();
        if(extensionAttributes!=null){
            for (ExtensionAttributeVo e :
                    extensionAttributes) {
                AllSpkzsxz extensionAttribute=new AllSpkzsxz();
                extensionAttribute.setSpbm(goodsId);
                extensionAttribute.setKzsxbm(e.getExtensionAttributeId());
                if(!StringUtils.isEmpty(e.getExtensionAttributeValue()))
                    extensionAttribute.setKzsxz(e.getExtensionAttributeValue());
                goodsExtensionAttributeService.add(extensionAttribute);
            }
        }
        //将封面图写入数据库
        String[] gallery = goodsAllinone.getGoods().getGallery();
        for (int i = 0; i < gallery.length; i++) {
            AllSpjkctp pic=new AllSpjkctp();
            String[] split = gallery[i].split("/");
//            System.out.println(split[split.length-1]);
            pic.setTpljjmc(split[split.length-1]);
            pic.setSpbm(goodsId);
            goodsAndProductPictureService.add(pic);
        }
        //将生成的分享图片地址写入数据库
//        String url = qCodeService.createGoodShareImage(goods.getId().toString(), goods.getPicUrl(), goods.getName());
//        if (!StringUtils.isEmpty(url)) {
//            goods.setShareUrl(url);
//            if (goodsService.updateById(goods) == 0) {
//                throw new RuntimeException("更新数据失败");
//            }
//        }
        // 将商品规格写入数据库
        for (GoodsSpecificationInfo specification : specifications) {
            AllSpgg goodsSpecification=new AllSpgg();
            goodsSpecification.setGgmc(specification.getSpecification());//商品规格名称
            goodsSpecification.setSpbm(goodsId);//商品编码
            goodsSpecification.setGgz(specification.getValue());
            goodsSpecificationService.addGoodsSpecification(goodsSpecification);
        }

        //将商品库存写入数据库
        for (GoodsProductInfo product :
                products) {
            AllSpkc goodsProduct=new AllSpkc();
            goodsProduct.setCbj(BigDecimal.valueOf(product.getCostPrice()));
            goodsProduct.setSpsj(BigDecimal.valueOf(product.getPrice()));
            goodsProduct.setJfzddked(BigDecimal.valueOf(product.getBiggestDeductionAmount()));
            goodsProduct.setKc(product.getNumber());
            goodsProduct.setTjr(userName);
            goodsProduct.setTjsj(ldt);
            goodsProduct.setZhxgr(userName);
            goodsProduct.setSpbm(goodsId);
            goodsProduct.setZhxgsj(ldt);
            goodsProduct.setXlggz(product.getValue());
            int goodsProductId = goodsProductService.add(goodsProduct);
            //将规格图片写入数据库
            AllSpjkctp pic=new AllSpjkctp();
            pic.setSpkcbm(goodsProductId);
            pic.setSpbm(goodsId);
            if (StringUtils.isEmpty(product.getUrl())||product.getUrl().equals(""))
                pic.setTpljjmc("默认图片");
            pic.setTpljjmc(product.getUrl());
            goodsAndProductPictureService.add(pic);
        }
        return ResponseUtil.ok();
    }

    public Object list2() {
        //商品所属板块
        List<AllSpssbk> allGoodsModules = goodsModulesService.getAllGoodsModules();
        List<CatVo> catVoListGoodsModules=new ArrayList<>(allGoodsModules.size());
        for (AllSpssbk gm :
                allGoodsModules) {
            CatVo goodsModule=new CatVo();
            goodsModule.setLabel(gm.getSsbk());
            goodsModule.setValue(gm.getSsbkbm());
            catVoListGoodsModules.add(goodsModule);
        }

        //管理员设置“所属分类”
        List<AllSpfl> categoryL1 = goodsCategoryService.getGoodsL1Categories();
        List<CatVo> catVoListL1=new ArrayList<>(categoryL1.size());

        //添加一级分类
        for (AllSpfl goodsCategoryL1 :
                categoryL1) {
            CatVo l1CatVo = new CatVo();
            l1CatVo.setValue(goodsCategoryL1.getSpflbm());
            l1CatVo.setLabel(goodsCategoryL1.getSpfl());
            //添加二级分类
            List<AllSpfl> categoryL2 = goodsCategoryService.getGoodsL2CategoriesBySuperiorCategoryId(goodsCategoryL1.getSpflbm());
            List<CatVo> catVoListL2=new ArrayList<>(categoryL2.size());
            for (AllSpfl goodsCategoryL2 :
                    categoryL2) {
                CatVo l2CatVo=new CatVo();
                l2CatVo.setValue(goodsCategoryL2.getSpflbm());
                l2CatVo.setLabel(goodsCategoryL2.getSpfl());
                //添加三级分类
                List<AllSpfl> categoryL3 = goodsCategoryService.getGoodsL3CategoriesBySuperiorCategoryId(goodsCategoryL2.getSpflbm());
                List<CatVo> catVoListL3=new ArrayList<>(categoryL3.size());
                for (AllSpfl goodsCategoryL3 :
                        categoryL3) {
                    CatVo l3CateVo = new CatVo();
                    l3CateVo.setValue(goodsCategoryL3.getSpflbm());
                    l3CateVo.setLabel(goodsCategoryL3.getSpfl());
                    catVoListL3.add(l3CateVo);
                }
                l2CatVo.setChildren(catVoListL3);
                catVoListL2.add(l2CatVo);
            }
            l1CatVo.setChildren(catVoListL2);
            catVoListL1.add(l1CatVo);
        }

        // 管理员设置“所属品牌商”
        List<AllSppp> list = goodsBrandService.getAllSppp();
        List<Map<String, Object>> brandList = new ArrayList<>(list.size());
        for (AllSppp brand : list) {
            Map<String, Object> b = new HashMap<>(2);
            b.put("value", brand.getSpppbm());//商品品牌编码
            b.put("label", brand.getPpmc());//品牌名称
            brandList.add(b);
        }

        //所属供应商
        List<AllGys> allSupplier = supplierService.getAllSupplier();
        List<Map<String,Object>> supplierList=new ArrayList<>(allSupplier.size());
        for (AllGys supplier :
                allSupplier) {
            Map<String,Object> s=new HashMap<>(2);
            s.put("value",supplier.getGysbm());
            s.put("label",supplier.getGysmc());
            supplierList.add(s);
        }
        //支付方式
        List<AllZffs> allPaymentWays = paymentWayService.getAllPaymentWays();
        List<Map<String,Object>> paymentWayList=new ArrayList<>(allPaymentWays.size());
        for (AllZffs paymentWay :
             allPaymentWays) {
            Map<String,Object> s=new HashMap<>(2);
            s.put("value",paymentWay.getZffsbm());
            s.put("label",paymentWay.getZffs());
            paymentWayList.add(s);
        }
        //运费规则
        List<AllYfgz> freightRules=freightRuleService.getAllFreightRule();
        List<Map<String,Object>> freightRuleList=new ArrayList<>(freightRules.size());
        for (AllYfgz frieightRule :
                freightRules) {
            Map<String, Object> fr=new HashMap<>(2);
            fr.put("value",frieightRule.getYfgzbm());
            fr.put("label",frieightRule.getYfgz());
            freightRuleList.add(fr);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", catVoListL1);//分类list
        data.put("brandList", brandList);//品牌list
        data.put("supplierList",supplierList);//所属供应商list
        data.put("moduleList",catVoListGoodsModules);//所属板块list
        data.put("paymentWayList",paymentWayList);//支付方式List
        data.put("freightRuleList",freightRuleList);//运费规则list
        return ResponseUtil.ok(data);
    }

    public Object detail(Integer id) {
        //商品信息
        AllSpjbxx good = goodsService.getGoodsByGoodsId(id);
        String []goodsPicture = goodsAndProductPictureService.getAllGoodsPictures(good.getSpbm());
        for (int i = 0; i < goodsPicture.length; i++) {
            goodsPicture[i]=uploadAddress+goodsPicture[i];
        }
        GoodsInfo goods=new GoodsInfo();
        goods.setName(good.getSpmc());
        goods.setGallery(goodsPicture);
        goods.setCounterPrice(good.getScj().doubleValue());
        goods.setCostPrice(good.getCbj().doubleValue());
        goods.setDetail(good.getCpms());
        goods.setIsOnSale(good.getSfsj());
        goods.setIsRecommendation(good.getSfstjsp());
        goods.setUnit(good.getJldw());
        goods.setPaymentWay(good.getZffsbm());
        goods.setBrandId(good.getSpppbm());
        goods.setBelongModule(good.getSsbkbm());
        goods.setSupplierId(good.getGysbm());
        goods.setFreightRule(good.getYfgzbm());
        goods.setRetailPrice(good.getSpsj().doubleValue());
        goods.setTaxCost(good.getSf().doubleValue());
        goods.setCategoryId(good.getSpflbm());//商品分类编码
        goods.setGoodsId(good.getSpbm());//商品编码
        List<GoodsSpecificationInfo> specifications=new ArrayList<>();
        List<GoodsProductInfo> products=new ArrayList<>();
        //商品规格及商品库存
        List<AllSpgg> goodsSpecifications = goodsSpecificationService.getGoodsSpecificationsByGoodsId(good.getSpbm());
        for (AllSpgg s :
                goodsSpecifications) {
            GoodsSpecificationInfo gsi = new GoodsSpecificationInfo();
            gsi.setSpecification(s.getGgmc());//设置规格名称
            gsi.setSpecificationId(s.getSpggbm());//商品规格id
            gsi.setValue(s.getGgz());
            specifications.add(gsi);
        }
        List<AllSpkc> goodsProducts = goodsProductService.getByGoodsId(goods.getGoodsId());
        for (AllSpkc goodsProduct :
                goodsProducts) {
            GoodsProductInfo gp = new GoodsProductInfo();
            gp.setUrl("");
            gp.setPrice(goodsProduct.getSpsj().doubleValue());
            gp.setCostPrice(goodsProduct.getCbj().doubleValue());
            gp.setNumber(goodsProduct.getKc());
            gp.setValue(goodsProduct.getXlggz());
            gp.setBiggestDeductionAmount(goodsProduct.getJfzddked().doubleValue());
            products.add(gp);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("goods", goods);
        data.put("specifications", specifications);
        data.put("products", products);
        return ResponseUtil.ok(data);
    }

    public boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {// 路径为文件且不为空则进行删除
            file.delete();// 文件删除
        }
        return true;
    }


    /**
     * 得到商品的属性值
     * @return
     */
    public Object getGoodsAttribute(int categoryId){
        List<Integer> extensionAttributeIds = goodsCategoryExtensionService.getAllExtensionAttributeByCategoryId(categoryId);
        List<AllKzsx> allExtensionAttributes = extensionAttributeService.getAllExtensionAttributeBy(extensionAttributeIds);
        if(allExtensionAttributes==null)
            return ResponseUtil.ok();
        List<ExtensionAttributeVo> extensionAttributeVos=new ArrayList<>(allExtensionAttributes.size());
        for (AllKzsx allKzsx:
             allExtensionAttributes) {
            ExtensionAttributeVo extensionAttributeVo=new ExtensionAttributeVo();
            extensionAttributeVo.setExtensionAttributeId(allKzsx.getKzsxbm());
            extensionAttributeVo.setExtensionAttributeName(allKzsx.getSxmc());
            extensionAttributeVos.add(extensionAttributeVo);
        }
        return ResponseUtil.ok(extensionAttributeVos);
    }

    /**
     * 根据商品id得到其扩展属性的值
     * @param goodsId
     * @return
     */
    public Object getGoodsAttributeValue(int goodsId){
        List<AllSpkzsxz> list = goodsExtensionAttributeService.getExtensionAttributeValue(goodsId);
        return ResponseUtil.ok(list);
    }

}
