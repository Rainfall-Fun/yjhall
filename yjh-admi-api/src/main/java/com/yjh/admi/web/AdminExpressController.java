package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.AllYfgz;
import com.yjh.db.domain.AllYfgzgc;
import com.yjh.db.service.ExpressRuleConstitutionService;
import com.yjh.db.service.ExpressRuleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/express")
@Validated
public class AdminExpressController {
    private final Log logger = LogFactory.getLog(AdminExpressController.class);



    @Autowired
    ExpressRuleConstitutionService expressRuleConstitutionService;
    @Autowired
    ExpressRuleService expressRuleService;
    @RequiresPermissions("admin:express:list")
    @RequiresPermissionsDesc(menu = {"配置管理", "运费配置"}, button = "查询")
    @GetMapping("/list")
    public Object listExpress(String id,String name,
                              @RequestParam(defaultValue = "1") Integer page,
                              @RequestParam(defaultValue = "10") Integer limit,
                              @Sort @RequestParam(defaultValue = "szsj") String sort,
                              @Order @RequestParam(defaultValue = "desc") String order) {
        List<AllYfgzgc> list = expressRuleConstitutionService.querySelective(id, name, page, limit, sort, order);
        return ResponseUtil.okList(list);
    }

    @RequiresPermissions("admin:express:create")
    @RequiresPermissionsDesc(menu = {"配置管理", "运费配置"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody AllYfgzgc expressRuleConstitution) {
        Object validate = validate(expressRuleConstitution);
        if (validate!=null)
            return ResponseUtil.fail(1,"请将参数填写完整");
        LocalDateTime ldt = LocalDateTime.now();
        expressRuleConstitution.setSzsj(ldt);
        int id = expressRuleConstitutionService.add(expressRuleConstitution);
        if (id==0)
            return ResponseUtil.fail(1,"添加失败，请重试");
        else
            expressRuleConstitution.setFygzbm(id);
        return ResponseUtil.ok(expressRuleConstitution);
    }

    @RequiresPermissions("admin:express:update")
    @RequiresPermissionsDesc(menu = {"配置管理", "运费配置"}, button = "修改")
    @PostMapping("/update")
    public Object update(@RequestBody AllYfgzgc expressRuleConstitution) {
        Object validate = validate(expressRuleConstitution);
        if (validate!=null)
            return ResponseUtil.fail(1,"请将参数填写完整");
        boolean update = expressRuleConstitutionService.update(expressRuleConstitution);
        if (update)
            return ResponseUtil.ok(expressRuleConstitution);
        else
            return ResponseUtil.fail(1,"修改运费费用规则失败");
    }

    @RequiresPermissions("admin:express:delete")
    @RequiresPermissionsDesc(menu = {"配置管理", "运费配置"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody AllYfgzgc expressRuleConstitution) {
        if (expressRuleConstitution.getFygzbm()==null)
            return ResponseUtil.fail(1,"删除运费规则失败");
        boolean delete = expressRuleConstitutionService.delete(expressRuleConstitution.getFygzbm());
        if (delete)
            return ResponseUtil.ok();
        else
            return ResponseUtil.fail(1,"删除运费规则失败");
    }

    private Object validate(AllYfgzgc expressRule) {
        String name = expressRule.getFygz();
        if (StringUtils.isEmpty(name)||StringUtils.isEmpty(expressRule.getFy())||StringUtils.isEmpty(expressRule.getYfgzbm())||StringUtils.isEmpty(expressRule.getSfyx())) {
            return ResponseUtil.badArgument();
        }
        return null;
    }
    @GetMapping("/expressRuleList")
    public Object expressRuleList(){
        List<AllYfgz> allExpressRules = expressRuleService.getAllExpressRules();
        List<Map<String,Object>> expressRuleList=new ArrayList<>(allExpressRules.size());
        for (AllYfgz er :
                allExpressRules) {
            Map<String,Object> e=new HashMap<>(2);
            e.put("value",er.getYfgzbm());
            e.put("label",er.getYfgz());
            expressRuleList.add(e);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("expressRuleList", expressRuleList);//运费规则list
        return ResponseUtil.ok(data);
    }
}
