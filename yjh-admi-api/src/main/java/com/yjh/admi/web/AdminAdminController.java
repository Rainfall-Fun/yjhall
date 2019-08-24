package com.yjh.admi.web;

import com.yjh.admi.annotation.RequiresPermissionsDesc;
import com.yjh.admi.util.Permission;
import com.yjh.admi.util.PermissionUtil;
import com.yjh.admi.vo.PermVo;
import com.yjh.core.util.JacksonUtil;
import com.yjh.core.util.RegexUtil;
import com.yjh.core.util.ResponseUtil;
import com.yjh.core.util.bcrypt.BCryptPasswordEncoder;
import com.yjh.core.validator.Order;
import com.yjh.core.validator.Sort;
import com.yjh.db.domain.*;
import com.yjh.db.service.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.*;

import com.yjh.admi.util.AdminResponseCode.*;

import static com.yjh.admi.util.AdminResponseCode.*;

@RestController
@RequestMapping("/admin/admin")
@Validated
public class AdminAdminController {
    private final Log logger = LogFactory.getLog(AdminAdminController.class);
    @Value("${static.adminId}")
    String adminId;
    @Value("${static.roleId}")
    String roleId;
    @Autowired
    SupplierService supplierService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserCategoryService userCategoryService;
    @Autowired
    UserService userService;
    @Autowired
    RolePermissionService rolePermissionService;
    @Autowired
    UserPermissionService userPermissionService;
    @Autowired
    PermissionService permissionService;

    @RequiresPermissions("admin:admin:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "查询")
    @GetMapping("/list")
    public Object list(String username,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer limit,
                       @Sort @RequestParam(defaultValue = "jsbm") String sort,
                       @Order @RequestParam(defaultValue = "desc") String order) {
        List<SysYh> adminList = userService.querySelective(username, page, limit, sort, order);
        return ResponseUtil.okList(adminList);
    }

    private Object validate(SysYh admin) {
        String name = admin.getYhm();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        if (!RegexUtil.isUsername(name)) {
            return ResponseUtil.fail(ADMIN_INVALID_NAME, "管理员名称不符合规定");
        }
        String password = admin.getMm();
        if (StringUtils.isEmpty(password) || password.length() < 6) {
            return ResponseUtil.fail(ADMIN_INVALID_PASSWORD, "管理员密码长度不能小于6");
        }
        return null;
    }

    @RequiresPermissions("admin:admin:create")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "添加")
    @PostMapping("/create")
    public Object create(@RequestBody SysYh admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }

        String username = admin.getYhm();
        boolean userNameExist = userService.checkExistByName(username);
        if (userNameExist) {
            return ResponseUtil.fail(ADMIN_NAME_EXIST, "管理员已经存在");
        }

        //密码加密
        String rawPassword = admin.getMm();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(rawPassword);
        admin.setMm(encodedPassword);

        SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date = new Date();
        String id = d.format(date);
        admin.setYhbm(id);
        userService.addUser(admin);
        admin.setYhbm(id);
        //将该角色对应的权限添加到用户权限表
        Integer roleId = admin.getJsbm();
        List<Integer> permissionIds = rolePermissionService.getAllPermissionCodeByRoleCode(roleId);
        for (Integer permissionId :
                permissionIds) {
            SysYhqxKey userPermission = new SysYhqxKey();
            userPermission.setQxbm(permissionId);
            userPermission.setYhbm(admin.getYhbm());
            userPermissionService.add(userPermission);
        }
        return ResponseUtil.ok(admin);
    }

//    @RequiresPermissions("admin:admin:read")
//    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "详情")
//    @GetMapping("/read")
//    public Object read(@NotNull Integer id) {
//        LitemallAdmin admin = adminService.findById(id);
//        return ResponseUtil.ok(admin);
//    }
//
    @RequiresPermissions("admin:admin:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "编辑")
    @PostMapping("/update")
    public Object update(@RequestBody SysYh admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }

        String anotherAdminId = admin.getYhbm();
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }
        if (anotherAdminId.equals(adminId))
            return ResponseUtil.fail(ADMIN_DELETE_NOT_ALLOWED, "不能修改超级管理员账号");
        // 不允许管理员通过编辑接口修改密码
        admin.setMm(null);
        if (!userService.modifyUser(admin)) {
            return ResponseUtil.updatedDataFailed();
        }
        return ResponseUtil.ok(admin);
    }

    @RequiresPermissions("admin:admin:delete")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(@RequestBody SysYh admin) {
        String anotherAdminId = admin.getYhbm();
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }
        // 管理员不能删除自身账号
        Subject currentUser = SecurityUtils.getSubject();
        SysYh currentAdmin = (SysYh) currentUser.getPrincipal();
        if (currentAdmin.getYhbm().equals(anotherAdminId)) {
            return ResponseUtil.fail(ADMIN_DELETE_NOT_ALLOWED, "管理员不能删除自己账号");
        }
        if (admin.getYhbm().equals(adminId))
            return ResponseUtil.fail(ADMIN_DELETE_NOT_ALLOWED, "不能删除超级管理员账号");
        userPermissionService.deleteByUserId(anotherAdminId);
        userService.deleteUser(anotherAdminId);
        return ResponseUtil.ok();
    }

    @GetMapping("/listSupplierAndRoleAndCategory")
    public Object listSupplierAndRoleAndCategory() {
        //供应商
        List<AllGys> allSupplier = supplierService.getAllSupplier();
        List<Map<String,Object>> supplierList=new ArrayList<>(allSupplier.size());
        for (AllGys supplier :
                allSupplier) {
            Map<String,Object> s=new HashMap<>(2);
            s.put("value",supplier.getGysbm());
            s.put("label",supplier.getGysmc());
            supplierList.add(s);
        }
        //角色
        List<SysJs> allRoles = roleService.getAllRoles();
        List<Map<String,Object>> roleList=new ArrayList<>(allRoles.size());
        for (SysJs role :
                allRoles) {
            Map<String, Object> r = new HashMap<>(2);
            r.put("value", role.getJsbm());
            r.put("label", role.getJsmc());
            roleList.add(r);
        }
        //类别
        List<SysYhlb> allUserCategories = userCategoryService.getAllUserCategories();
        List<Map<String,Object>> categoryList=new ArrayList<>(allUserCategories.size());
        for (SysYhlb uc:
             allUserCategories) {
            Map<String, Object> u = new HashMap<>(2);
            u.put("value", uc.getYhlbbm());
            u.put("label", uc.getYhlb());
            categoryList.add(u);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("categoryList", categoryList);//分类list
        data.put("roleList",roleList);//角色list
        data.put("supplierList",supplierList);//所属板块list
        return ResponseUtil.ok(data);
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
    private Set<String> getAssignedPermissions(String userId) {
        Set<String> assignedPermissions = null;
        assignedPermissions=permissionService.getPermissionByPermissionIds(userPermissionService.getPermissionsByUserId(userId));
        return assignedPermissions;
    }

    /**
     * 管理员的权限情况
     *
     * @return 系统所有权限列表和管理员已分配权限
     */
    @RequiresPermissions("admin:admin:permission:get")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "权限详情")
    @GetMapping("/permissions")
    public Object getPermissions(String userId) {
        System.out.println("进入了");
        List<PermVo> systemPermissions = getSystemPermissions();
        Set<String> assignedPermissions = getAssignedPermissions(userId);
        Map<String, Object> data = new HashMap<>();
        data.put("systemPermissions", systemPermissions);
        data.put("assignedPermissions", assignedPermissions);
        return ResponseUtil.ok(data);
    }

    /**
     * 更新管理员的权限
     *
     * @param body
     * @return
     */
    @RequiresPermissions("admin:admin:permission:update")
    @RequiresPermissionsDesc(menu = {"系统管理", "管理员管理"}, button = "权限变更")
    @PostMapping("/permissions")
    public Object updatePermissions(@RequestBody String body) {
        String userId = JacksonUtil.parseString(body, "yhbm");
        List<String> permissions = JacksonUtil.parseStringList(body, "permissions");
        if (userId == null || permissions == null) {
            return ResponseUtil.badArgument();
        }
        if(userId.equals(adminId))
            return ResponseUtil.fail(1,"不允许对超级管理员进行授权");
        // 先删除旧的权限，再更新新的权限
        userPermissionService.deleteByUserId(userId);
        //如果没有选择任何权限，则删除原先权限不再进行增加新权限操作，直接返回成功
        if(permissions.size()==0)
            return ResponseUtil.ok();
        List<Integer> permissionIds = permissionService.getPermissionIdByPermissionAddress(permissions);
        for (Integer permissionId : permissionIds) {
            SysYhqxKey usrePermission=new SysYhqxKey();
            usrePermission.setYhbm(userId);
            usrePermission.setQxbm(permissionId);
            userPermissionService.add(usrePermission);
        }
        return ResponseUtil.ok();
    }
}
