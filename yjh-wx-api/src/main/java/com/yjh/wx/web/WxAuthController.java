package com.yjh.wx.web;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.yjh.core.util.CharUtil;
import com.yjh.core.util.JacksonUtil;
import com.yjh.core.util.RegexUtil;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.util.bcrypt.BCryptPasswordEncoder;
import com.yjh.db.domain.HyHyjbxx;
import com.yjh.db.service.MemberBasicInfoService;
import com.yjh.wx.annotation.LoginUser;
import com.yjh.wx.dto.UserInfo;
import com.yjh.wx.service.UserTokenManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.yjh.wx.util.WxResponseCode.*;

/**
 * 鉴权服务
 */
@RestController
@RequestMapping("/wx/auth")
@Validated
public class WxAuthController {
    private final Log logger = LogFactory.getLog(WxAuthController.class);
    @Autowired
    MemberBasicInfoService memberBasicInfoService;


//    @Autowired
//    private LitemallUserService userService;
//
//    @Autowired
//    private WxMaService wxService;
//
//    @Autowired
//    private NotifyService notifyService;
//
//    @Autowired
//    private CouponAssignService couponAssignService;
//
    /**
     * 账号登录
     *
     * @param body    请求内容，{ username: xxx, password: xxx }
     * @param request 请求对象
     * @return 登录结果
     */
    @PostMapping("login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        if (username == null || password == null) {
            return ResponseUtil.badArgument();
        }

        HyHyjbxx user = memberBasicInfoService.getMemberInfoByUserName(username);
        if (user==null) {
            return ResponseUtil.fail(AUTH_INVALID_ACCOUNT, "账号不存在");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, user.getDlmm())) {
            return ResponseUtil.fail(AUTH_INVALID_ACCOUNT, "账号密码不对");
        }
//
//        // 更新登录情况
//        user.setLastLoginTime(LocalDateTime.now());
//        user.setLastLoginIp(IpUtil.getIpAddr(request));
//        if (userService.updateById(user) == 0) {
//            return ResponseUtil.updatedDataFailed();
//        }
//
        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        // token
        String token = UserTokenManager.generateToken(user.getHybh());
        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", token);
        result.put("userInfo", userInfo);
        return ResponseUtil.ok(result);
    }
//
//    /**
//     * 微信登录
//     *
//     * @param wxLoginInfo 请求内容，{ code: xxx, userInfo: xxx }
//     * @param request     请求对象
//     * @return 登录结果
//     */
//    @PostMapping("login_by_weixin")
//    public Object loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
//        String code = wxLoginInfo.getCode();
//        UserInfo userInfo = wxLoginInfo.getUserInfo();
//        if (code == null || userInfo == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        String sessionKey = null;
//        String openId = null;
//        try {
//            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
//            sessionKey = result.getSessionKey();
//            openId = result.getOpenid();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        if (sessionKey == null || openId == null) {
//            return ResponseUtil.fail();
//        }
//
//        LitemallUser user = userService.queryByOid(openId);
//        if (user == null) {
//            user = new LitemallUser();
//            user.setUsername(openId);
//            user.setPassword(openId);
//            user.setWeixinOpenid(openId);
//            user.setAvatar(userInfo.getAvatarUrl());
//            user.setNickname(userInfo.getNickName());
//            user.setGender(userInfo.getGender());
//            user.setUserLevel((byte) 0);
//            user.setStatus((byte) 0);
//            user.setLastLoginTime(LocalDateTime.now());
//            user.setLastLoginIp(IpUtil.getIpAddr(request));
//            user.setSessionKey(sessionKey);
//
//            userService.add(user);
//
//            // 新用户发送注册优惠券
//            couponAssignService.assignForRegister(user.getId());
//        } else {
//            user.setLastLoginTime(LocalDateTime.now());
//            user.setLastLoginIp(IpUtil.getIpAddr(request));
//            user.setSessionKey(sessionKey);
//            if (userService.updateById(user) == 0) {
//                return ResponseUtil.updatedDataFailed();
//            }
//        }
//
//        // token
//        String token = UserTokenManager.generateToken(user.getId());
//
//        Map<Object, Object> result = new HashMap<Object, Object>();
//        result.put("token", token);
//        result.put("userInfo", userInfo);
//        return ResponseUtil.ok(result);
//    }
//
//
    /**
     * 请求注册验证码
     *
     * TODO
     * 这里需要一定机制防止短信验证码被滥用
     *
     * @param body 手机号码 { mobile }
     * @return
     */
    @PostMapping("regCaptcha")
    public Object registerCaptcha(@RequestBody String body) {
        String phoneNumber = JacksonUtil.parseString(body, "mobile");
        if (StringUtils.isEmpty(phoneNumber)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isMobileExact(phoneNumber)) {
            return ResponseUtil.badArgumentValue();
        }

//        if (!notifyService.isSmsEnable()) {
//            return ResponseUtil.fail(AUTH_CAPTCHA_UNSUPPORT, "小程序后台验证码服务不支持");
//        }
//        String code = CharUtil.getRandomNum(6);
//        notifyService.notifySmsTemplate(phoneNumber, NotifyType.CAPTCHA, new String[]{code});
//
//        boolean successful = CaptchaCodeManager.addToCache(phoneNumber, code);
//        if (!successful) {
//            return ResponseUtil.fail(AUTH_CAPTCHA_FREQUENCY, "验证码未超时1分钟，不能发送");
//        }

        return ResponseUtil.ok();
    }
//
//    /**
//     * 账号注册
//     *
//     * @param body    请求内容
//     *                {
//     *                username: xxx,
//     *                password: xxx,
//     *                mobile: xxx
//     *                code: xxx
//     *                }
//     *                其中code是手机验证码，目前还不支持手机短信验证码
//     * @param request 请求对象
//     * @return 登录结果
//     * 成功则
//     * {
//     * errno: 0,
//     * errmsg: '成功',
//     * data:
//     * {
//     * token: xxx,
//     * tokenExpire: xxx,
//     * userInfo: xxx
//     * }
//     * }
//     * 失败则 { errno: XXX, errmsg: XXX }
//     */
    @PostMapping("register")
    public Object register(@RequestBody String body, HttpServletRequest request) {
        String password = JacksonUtil.parseString(body, "password");
        String mobile = JacksonUtil.parseString(body, "mobile");
        String code = JacksonUtil.parseString(body, "code");
        String userName=JacksonUtil.parseString(body,"userName");
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(mobile) || StringUtils.isEmpty(code)) {
            return ResponseUtil.badArgument();
        }
        //未对验证码进行验证，实现发送验证码功能之后，需要对此功能进行完善
        HyHyjbxx memberBasicInfo=new HyHyjbxx();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        LocalDateTime ldt = LocalDateTime.now();
        memberBasicInfo.setShztbm(1);//1未审核，2审核中，3已审核
        memberBasicInfo.setYhm(userName);//用户名
        memberBasicInfo.setLxsj1(mobile);//联系手机1
        memberBasicInfo.setYqr("xxx");//邀请人
        memberBasicInfo.setSjrz(1);//手机认证，1：已认证，2：未认证
        memberBasicInfo.setSjrzsj(ldt);//手机认证时间
        memberBasicInfo.setSmrz(2);//实名认证，1：已认证，2：未认证
        memberBasicInfo.setDlmm(encodedPassword);//密码
        memberBasicInfo.setQqsj(ldt);//请求时间
        memberBasicInfo.setHydjbm(1);//设置等级为最低等级（1对应最低等级的主键，要和数据库中的最低等级的主键想对应）
        memberBasicInfo.setHyztbm(1);//设置会员状态为可用（1对应了数据库中会员状态的可用的主键，若数据库中修改了之后，这里的状态编码要和可用的记录对应的主键相同）

        if (memberBasicInfoService.add(memberBasicInfo))
            return ResponseUtil.ok("success");
        else
            return ResponseUtil.fail(1,"注册失败，请重试或联系客服");
    }


    /**
     * 请求验证码
     *
     * TODO
     * 这里需要一定机制防止短信验证码被滥用
     *
     * @param body 手机号码 { mobile: xxx, type: xxx }
     * @return
     */
//    @PostMapping("captcha")
//    public Object captcha(@LoginUser Integer userId, @RequestBody String body) {
//        if(userId == null){
//            return ResponseUtil.unlogin();
//        }
//        String phoneNumber = JacksonUtil.parseString(body, "mobile");
//        String captchaType = JacksonUtil.parseString(body, "type");
//        if (StringUtils.isEmpty(phoneNumber)) {
//            return ResponseUtil.badArgument();
//        }
//        if (!RegexUtil.isMobileExact(phoneNumber)) {
//            return ResponseUtil.badArgumentValue();
//        }
//        if (StringUtils.isEmpty(captchaType)) {
//            return ResponseUtil.badArgument();
//        }
//
//        if (!notifyService.isSmsEnable()) {
//            return ResponseUtil.fail(AUTH_CAPTCHA_UNSUPPORT, "小程序后台验证码服务不支持");
//        }
//        String code = CharUtil.getRandomNum(6);
//        // TODO
//        // 根据type发送不同的验证码
//        notifyService.notifySmsTemplate(phoneNumber, NotifyType.CAPTCHA, new String[]{code});
//
//        boolean successful = CaptchaCodeManager.addToCache(phoneNumber, code);
//        if (!successful) {
//            return ResponseUtil.fail(AUTH_CAPTCHA_FREQUENCY, "验证码未超时1分钟，不能发送");
//        }
//
//        return ResponseUtil.ok();
//    }
//
//    /**
//     * 账号密码重置
//     *
//     * @param body    请求内容
//     *                {
//     *                password: xxx,
//     *                mobile: xxx
//     *                code: xxx
//     *                }
//     *                其中code是手机验证码，目前还不支持手机短信验证码
//     * @param request 请求对象
//     * @return 登录结果
//     * 成功则 { errno: 0, errmsg: '成功' }
//     * 失败则 { errno: XXX, errmsg: XXX }
//     */
//    @PostMapping("reset")
//    public Object reset(@RequestBody String body, HttpServletRequest request) {
//        String password = JacksonUtil.parseString(body, "password");
//        String mobile = JacksonUtil.parseString(body, "mobile");
//        String code = JacksonUtil.parseString(body, "code");
//
//        if (mobile == null || code == null || password == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        //判断验证码是否正确
//        String cacheCode = CaptchaCodeManager.getCachedCaptcha(mobile);
//        if (cacheCode == null || cacheCode.isEmpty() || !cacheCode.equals(code))
//            return ResponseUtil.fail(AUTH_CAPTCHA_UNMATCH, "验证码错误");
//
//        List<LitemallUser> userList = userService.queryByMobile(mobile);
//        LitemallUser user = null;
//        if (userList.size() > 1) {
//            return ResponseUtil.serious();
//        } else if (userList.size() == 0) {
//            return ResponseUtil.fail(AUTH_MOBILE_UNREGISTERED, "手机号未注册");
//        } else {
//            user = userList.get(0);
//        }
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        String encodedPassword = encoder.encode(password);
//        user.setPassword(encodedPassword);
//
//        if (userService.updateById(user) == 0) {
//            return ResponseUtil.updatedDataFailed();
//        }
//
//        return ResponseUtil.ok();
//    }
//
//    /**
//     * 账号手机号码重置
//     *
//     * @param body    请求内容
//     *                {
//     *                password: xxx,
//     *                mobile: xxx
//     *                code: xxx
//     *                }
//     *                其中code是手机验证码，目前还不支持手机短信验证码
//     * @param request 请求对象
//     * @return 登录结果
//     * 成功则 { errno: 0, errmsg: '成功' }
//     * 失败则 { errno: XXX, errmsg: XXX }
//     */
//    @PostMapping("resetPhone")
//    public Object resetPhone(@LoginUser Integer userId, @RequestBody String body, HttpServletRequest request) {
//        if(userId == null){
//            return ResponseUtil.unlogin();
//        }
//        String password = JacksonUtil.parseString(body, "password");
//        String mobile = JacksonUtil.parseString(body, "mobile");
//        String code = JacksonUtil.parseString(body, "code");
//
//        if (mobile == null || code == null || password == null) {
//            return ResponseUtil.badArgument();
//        }
//
//        //判断验证码是否正确
//        String cacheCode = CaptchaCodeManager.getCachedCaptcha(mobile);
//        if (cacheCode == null || cacheCode.isEmpty() || !cacheCode.equals(code))
//            return ResponseUtil.fail(AUTH_CAPTCHA_UNMATCH, "验证码错误");
//
//        List<LitemallUser> userList = userService.queryByMobile(mobile);
//        LitemallUser user = null;
//        if (userList.size() > 1) {
//            return ResponseUtil.fail(AUTH_MOBILE_REGISTERED, "手机号已注册");
//        }
//        user = userService.findById(userId);
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        if (!encoder.matches(password, user.getPassword())) {
//            return ResponseUtil.fail(AUTH_INVALID_ACCOUNT, "账号密码不对");
//        }
//
//        user.setMobile(mobile);
//        if (userService.updateById(user) == 0) {
//            return ResponseUtil.updatedDataFailed();
//        }
//
//        return ResponseUtil.ok();
//    }
//
//    /**
//     * 账号信息更新
//     *
//     * @param body    请求内容
//     *                {
//     *                password: xxx,
//     *                mobile: xxx
//     *                code: xxx
//     *                }
//     *                其中code是手机验证码，目前还不支持手机短信验证码
//     * @param request 请求对象
//     * @return 登录结果
//     * 成功则 { errno: 0, errmsg: '成功' }
//     * 失败则 { errno: XXX, errmsg: XXX }
//     */
//    @PostMapping("profile")
//    public Object profile(@LoginUser Integer userId, @RequestBody String body, HttpServletRequest request) {
//        if(userId == null){
//            return ResponseUtil.unlogin();
//        }
//        String avatar = JacksonUtil.parseString(body, "avatar");
//        Byte gender = JacksonUtil.parseByte(body, "gender");
//        String nickname = JacksonUtil.parseString(body, "nickname");
//
//        LitemallUser user = userService.findById(userId);
//        if(!StringUtils.isEmpty(avatar)){
//            user.setAvatar(avatar);
//        }
//        if(gender != null){
//            user.setGender(gender);
//        }
//        if(!StringUtils.isEmpty(nickname)){
//            user.setNickname(nickname);
//        }
//
//        if (userService.updateById(user) == 0) {
//            return ResponseUtil.updatedDataFailed();
//        }
//
//        return ResponseUtil.ok();
//    }
//
//    /**
//     * 微信手机号码绑定
//     *
//     * @param userId
//     * @param body
//     * @return
//     */
//    @PostMapping("bindPhone")
//    public Object bindPhone(@LoginUser Integer userId, @RequestBody String body) {
//    	if (userId == null) {
//            return ResponseUtil.unlogin();
//        }
//    	LitemallUser user = userService.findById(userId);
//        String encryptedData = JacksonUtil.parseString(body, "encryptedData");
//        String iv = JacksonUtil.parseString(body, "iv");
//        WxMaPhoneNumberInfo phoneNumberInfo = this.wxService.getUserService().getPhoneNoInfo(user.getSessionKey(), encryptedData, iv);
//        String phone = phoneNumberInfo.getPhoneNumber();
//        user.setMobile(phone);
//        if (userService.updateById(user) == 0) {
//            return ResponseUtil.updatedDataFailed();
//        }
//        return ResponseUtil.ok();
//    }
//
    @PostMapping("logout")
    public Object logout(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }
        return ResponseUtil.ok();
    }

    @GetMapping("info")
    public Object info(@LoginUser Integer userId) {
        if (userId == null) {
            return ResponseUtil.unlogin();
        }

        HyHyjbxx user = memberBasicInfoService.getMemberInfoById(userId);
        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("nickName", user.getYhm());
//        data.put("avatar", user.getAvatar());
//        data.put("gender", user.getGender());
        data.put("mobile", user.getLxsj1());

        return ResponseUtil.ok(data);
    }
    
}
