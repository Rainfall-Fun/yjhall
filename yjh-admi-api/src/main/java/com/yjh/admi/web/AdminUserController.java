package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.admi.vo.MemberInfoVo;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.AllHydj;
import com.yjh.db.domain.AllShzt;
import com.yjh.db.domain.HyHyjbxx;
import com.yjh.db.domain.HyHyzt;
import com.yjh.db.service.MemberAuditService;
import com.yjh.db.service.MemberBasicInfoService;
import com.yjh.db.service.MemberLevelService;
import com.yjh.db.service.MemberStatusService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
@Validated
public class AdminUserController {
    private final Log logger = LogFactory.getLog(AdminUserController.class);

    @Autowired
    private MemberBasicInfoService userService;
    @Autowired
    private MemberAuditService memberAuditService;
    @Autowired
    private MemberStatusService memberStatusService;
    @Autowired
    private MemberLevelService memberLevelService;

    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "会员管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username, String mobile,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "qqsj") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        //后面可以对此进行优化，将下面三个list放入缓存中，减少对数据库的操作
        List<AllShzt> allMemberAudits = memberAuditService.getAllMemberAudits();
        List<HyHyzt> allMemberStatus = memberStatusService.getAllMemberStatus();
        List<AllHydj> allMemberLevel = memberLevelService.getAllMemberLevel();
        Map auditsMap=new HashMap();
        Map statusMap=new HashMap();
        Map levelMap=new HashMap();
        for (AllShzt audits :
                allMemberAudits) {
            auditsMap.put(audits.getShztbm(), audits.getShzt());
        }
        for (HyHyzt status :
                allMemberStatus) {
            statusMap.put(status.getHyztbm(), status.getHyzt());
        }
        for (AllHydj level:
             allMemberLevel) {
            levelMap.put(level.getHydjbm(),level.getHydj());
        }
        List<HyHyjbxx> userList = userService.querySelective(username, mobile, page, limit, sort, order);
        List<MemberInfoVo> memberInfoList=new ArrayList<>(userList.size());
        for (HyHyjbxx info :
                userList) {
            MemberInfoVo member = new MemberInfoVo();
            member.setId(info.getHybh());
            member.setMobile(info.getLxsj1());
            member.setUsername(info.getYhm());
            if(!StringUtils.isEmpty(info.getHyztbm()))
                member.setStatus(statusMap.get(info.getHyztbm()).toString());
            if(!StringUtils.isEmpty(info.getHydjbm()))
                member.setUserLevel(levelMap.get(info.getHydjbm()).toString());
            if(!StringUtils.isEmpty(info.getShztbm()))
                member.setAudits(auditsMap.get(info.getShztbm()).toString());
            System.out.println(member);
            memberInfoList.add(member);
        }
        return ResponseUtil.okList(memberInfoList);
    }
}
