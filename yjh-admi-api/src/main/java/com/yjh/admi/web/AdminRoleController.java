package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.admi.util.Permission;
import com.yjh.admi.util.PermissionUtil;
import com.yjh.admi.vo.PermVo;
import com.yjh.core.util.JacksonUtil;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.SysJs;
import com.yjh.db.domain.SysJsqxKey;
import com.yjh.db.service.PermissionService;
import com.yjh.db.service.RolePermissionService;
import com.yjh.db.service.RoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;

import static com.yjh.admi.util.AdminResponseCode.ROLE_NAME_EXIST;
import static com.yjh.admi.util.AdminResponseCode.ROLE_USER_EXIST;

@RestController
@RequestMapping("/admin/role")
@Validated
public class AdminRoleController {
    private final Log logger = LogFactory.getLog(AdminRoleController.class);
    @Value("${static.roleId}")
    String roleId;
    @Autowired
    RoleService roleService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    RolePermissionService rolePermissionService;


    @RequiresPermissions("admin:role:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色查询")
    @GetMapping("/list")
    public Object list(String name,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "jsbm") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<SysJs> roleList = roleService.querySelective(name, page, limit, sort, order);
        return ResponseUtil.okList(roleList);
    }

    @GetMapping("/options")
    public Object options() {
//        List<LitemallRole> roleList = roleService.queryAll();
//
//        List<Map<String, Object>> options = new ArrayList<>(roleList.size());
//        for (LitemallRole role : roleList) {
//            Map<String, Object> option = new HashMap<>(2);
//            option.put("value", role.getId());
//            option.put("label", role.getName());
//            options.add(option);
//        }

        return ResponseUtil.okList(new ArrayList());
    }
//
//    @RequiresPermissions("admin:role:read")
//    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色详情")
//    @GetMapping("/read")
//    public Object read(@NotNull Integer id) {
//        LitemallRole role = roleService.findById(id);
//        return ResponseUtil.ok(role);
//    }
//
//
    private Object validate(SysJs role) {
        String name = role.getJsmc();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }

        return null;
    }

    @RequiresPermissions("admin:role:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色添加")
    @PostMapping("/create")
    public Object create(@RequestBody SysJs role) {
        Object error = validate(role);
        if (error != null) {
            return error;
        }

        if (roleService.checkExist(role.getJsmc())) {
            return ResponseUtil.fail(ROLE_NAME_EXIST, "角色已经存在");
        }
        int roleId = roleService.addRole(role);
        role.setJsbm(roleId);
        return ResponseUtil.ok(role);
    }

    @RequiresPermissions("admin:role:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色编辑")
    @PostMapping("/update")
    public Object update(@RequestBody SysJs role) {
        Object error = validate(role);
        if (error != null) {
            return error;
        }
        if(role.getJsbm().toString()==roleId)
            return ResponseUtil.fail(1,"不允许修改超级管理员角色");
        roleService.updateRole(role);
        return ResponseUtil.ok(role);
    }

    @RequiresPermissions("admin:role:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "角色删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody SysJs role) {
        Integer id = role.getJsbm();
        if (id == null) {
            return ResponseUtil.badArgument();
        }
        if(role.getJsbm().toString()==roleId)
            return ResponseUtil.fail(1,"不允许删除超级管理员角色");
        roleService.deleteRoleByRoleId(id);
        return ResponseUtil.ok();
    }


    @Autowired
    private ApplicationContext context;
    private List<PermVo> systemPermissions = null;
    private Set<String> systemPermissionsString = null;

    //得到系统的所有权限
    private List<PermVo> getSystemPermissions() {
        final String basicPackage = "com.yjh.admin.web";
        if (systemPermissions == null) {
            List<Permission> permissions = PermissionUtil.listPermission(context, basicPackage);
            systemPermissions = PermissionUtil.listPermVo(permissions);
            systemPermissionsString = PermissionUtil.listPermissionString(permissions);
        }
        return systemPermissions;
    }

    //得到已授权的权限
    private Set<String> getAssignedPermissions(Integer roleId) {
        Set<String> assignedPermissions = null;
        assignedPermissions=permissionService.getPermissionByPermissionIds(rolePermissionService.getAllPermissionCodeByRoleCode(roleId));
        return assignedPermissions;
    }

    /**
     * 角色的权限情况
     *
     * @return 系统所有权限列表和管理员已分配权限
     */
    @RequiresPermissions("admin:role:permission:get")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "权限详情")
    @GetMapping("/permissions")
    public Object getPermissions(Integer roleId) {
        List<PermVo> systemPermissions = getSystemPermissions();
        Set<String> assignedPermissions = getAssignedPermissions(roleId);
        Map<String, Object> data = new HashMap<>();
        data.put("systemPermissions", systemPermissions);
        data.put("assignedPermissions", assignedPermissions);
        return ResponseUtil.ok(data);
    }


    /**
     * 更新角色的权限
     *
     * @param body
     * @return
     */
    @RequiresPermissions("admin:admin:permission:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "角色管理"}, button = "权限变更")
    @PostMapping("/permissions")
    public Object updatePermissions(@RequestBody String body) {
        Integer roleid = JacksonUtil.parseInteger(body, "jsbm");
        List<String> permissions = JacksonUtil.parseStringList(body, "permissions");
        if (roleid == null || permissions == null) {
            return ResponseUtil.badArgument();
        }
        if(roleid.toString()==roleId)
            return ResponseUtil.fail(1,"不允许对超级管理员角色进行授权");
        // 先删除旧的权限，再更新新的权限
        rolePermissionService.deleteByRoleId(roleid);
        //如果没有选择任何权限，则删除原先权限不再进行郑家新权限操作，直接返回成功
        if(permissions.size()==0)
            return ResponseUtil.ok();
        List<Integer> permissionIds = permissionService.getPermissionIdByPermissionAddress(permissions);
        for (Integer permissionId : permissionIds) {
            SysJsqxKey rolePermission=new SysJsqxKey();
            rolePermission.setJsbm(roleid);
            rolePermission.setQxbm(permissionId);
            rolePermissionService.addRolePermission(rolePermission);
        }
        return ResponseUtil.ok();
    }

}
