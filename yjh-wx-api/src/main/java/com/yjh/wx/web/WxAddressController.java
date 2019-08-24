package com.yjh.wx.web;

import com.yjh.core.util.RegexUtil;
import com.yjh.core.util.ResponseUtil;
import com.yjh.db.domain.AllShdz;
import com.yjh.db.service.AddressService;
import com.yjh.wx.annotation.LoginUser;
import com.yjh.wx.dto.AddressInfo;
import com.yjh.wx.service.GetRegionService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户收货地址服务
 */
@RestController
@RequestMapping("/wx/address")
@Validated
public class WxAddressController extends GetRegionService {
	private final Log logger = LogFactory.getLog(WxAddressController.class);

	@Autowired
	private AddressService addressService;

//	@Autowired
//	private LitemallRegionService regionService;


	/**
	 * 用户收货地址列表
	 *
	 * @param userId 用户ID
	 * @return 收货地址列表
	 */
	@GetMapping("list")
	public Object list(@LoginUser Integer userId) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		List<AllShdz> addressList = addressService.getAddressByUserId(userId);
		return ResponseUtil.okList(addressList);
	}

	/**
	 * 收货地址详情
	 *
	 * @param userId 用户ID
	 * @param id     收货地址ID
	 * @return 收货地址详情
	 */
	@GetMapping("detail")
	public Object detail(@LoginUser Integer userId, @NotNull Integer id) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		AllShdz address = addressService.getAddressByAddressId(id);
		if (address == null) {
			return ResponseUtil.badArgumentValue();
		}
		AddressInfo addressInfo=new AddressInfo();
		addressInfo.setUserId(address.getYhbm());
		addressInfo.setId(address.getDzbm());
		addressInfo.setAddressDetail(address.getShdz());
		addressInfo.setProvince(address.getSheng());
		addressInfo.setCity(address.getShi());
		addressInfo.setCounty(address.getXian());
		addressInfo.setCity(address.getShi());
		addressInfo.setCounty(address.getXian());
		addressInfo.setIsDefault(address.getSfsmrdz()==1);
		addressInfo.setName(address.getShdr());
		addressInfo.setTel(address.getDhhm());
		addressInfo.setAreaCode(address.getQybm());
		return ResponseUtil.ok(addressInfo);
	}

	private Object validate(AddressInfo address) {
		String name = address.getName();
		if (StringUtils.isEmpty(name)) {
			return ResponseUtil.badArgument();
		}

		// 测试收货手机号码是否正确
		String mobile = address.getTel();
		if (StringUtils.isEmpty(mobile)) {
			return ResponseUtil.badArgument();
		}
		if (!RegexUtil.isMobileExact(mobile)) {
			return ResponseUtil.badArgument();
		}

		String province = address.getProvince();
		if (StringUtils.isEmpty(province)) {
			return ResponseUtil.badArgument();
		}

		String city = address.getCity();
		if (StringUtils.isEmpty(city)) {
			return ResponseUtil.badArgument();
		}

		String county = address.getCounty();
		if (StringUtils.isEmpty(county)) {
			return ResponseUtil.badArgument();
		}

//
//		String areaCode = address.getAreaCode();
//		if (StringUtils.isEmpty(areaCode)) {
//			return ResponseUtil.badArgument();
//		}
//
		String detailedAddress = address.getAddressDetail();
		if (StringUtils.isEmpty(detailedAddress)) {
			return ResponseUtil.badArgument();
		}

		Boolean isDefault = address.getIsDefault();
		if (isDefault == null) {
			return ResponseUtil.badArgument();
		}
		return null;
	}

	/**
	 * 添加或更新收货地址
	 *
	 * @param userId  用户ID
	 * @param address 用户收货地址
	 * @return 添加或更新操作结果
	 */
	@PostMapping("save")
	public Object save(@LoginUser Integer userId, @RequestBody AddressInfo address) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Object error = validate(address);
		if (error != null) {
			return error;
		}

		if (address.getIsDefault()) {
			// 重置其他收获地址的默认选项
			addressService.resetDefault(userId);
		}

		if (address.getId() == null || address.getId().equals(0)) {
			address.setId(null);
			address.setUserId(userId);
			AllShdz address1=new AllShdz();
			address1.setSfsmrdz(address.getIsDefault()?1:0);//是否是默认地址，1：是，0：否
			address1.setDhhm(address.getTel());//电话
			address1.setSheng(address.getProvince());//省
			address1.setShi(address.getCity());//市
			address1.setXian(address.getCounty());//县
			address1.setShdz(address.getAddressDetail());//详细地址
			address1.setShdr(address.getName());//收货人
			address1.setYhbm(userId);//用户编码
			address1.setQybm(address.getAreaCode());//区域编码
			addressService.add(address1);
		} else {
			address.setUserId(userId);
			AllShdz address1=new AllShdz();
			address1.setSfsmrdz(address.getIsDefault()?1:0);//是否市默认地址
			address1.setDhhm(address.getTel());//电话
			address1.setSheng(address.getProvince());//省
			address1.setShi(address.getCity());//市
			address1.setXian(address.getCounty());//县
			address1.setShdz(address.getAddressDetail());//详细地址
			address1.setShdr(address.getName());//收货人
			address1.setYhbm(userId);//用户编码
			address1.setDzbm(address.getId());//待更新的地址编码
			address1.setQybm(address.getAreaCode());//区域编码
			if (!addressService.update(address1)) {
				return ResponseUtil.updatedDataFailed();
			}
		}
		return ResponseUtil.ok(address.getId());
	}

	/**
	 * 删除收货地址
	 *
	 * @param userId  用户ID
	 * @param address 用户收货地址，{ id: xxx }
	 * @return 删除操作结果
	 */
	@PostMapping("delete")
	public Object delete(@LoginUser Integer userId, @RequestBody AddressInfo address) {
		if (userId == null) {
			return ResponseUtil.unlogin();
		}
		Integer id = address.getId();
		if (id == null) {
			return ResponseUtil.badArgument();
		}

		addressService.delete(id);
		return ResponseUtil.ok();
	}
}