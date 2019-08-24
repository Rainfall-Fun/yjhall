package com.yjh.admi.shiro;


import com.yjh.core.util.bcrypt.BCryptPasswordEncoder;
import com.yjh.db.domain.SysYh;
import com.yjh.db.service.PermissionService;
import com.yjh.db.service.RolePermissionService;
import com.yjh.db.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;

public class AdminAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    RolePermissionService rolePermissionService;
    @Autowired
    PermissionService permissionService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        }

        SysYh admin = (SysYh) getAvailablePrincipal(principals);
        Integer roleIds = admin.getJsbm();
        List<Integer> permissionIds = rolePermissionService.getAllPermissionCodeByRoleCode(roleIds);
        Set<String> permissions = permissionService.getPermissionByPermissionIds(permissionIds);
//        Set<String> roles = roleService.queryByIds(roleIds);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.setRoles(roles);
        info.setStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = new String(upToken.getPassword());

        if (StringUtils.isEmpty(username)) {
            throw new AccountException("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new AccountException("密码不能为空");
        }

        List<SysYh> adminList = userService.getUsers(username);
        Assert.state(adminList.size() < 2, "同一个用户名存在两个账户");
        if (adminList.size() == 0) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }
        SysYh admin = adminList.get(0);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(password, admin.getMm())) {
            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
        }

//        //需要加密
//        if (!password.equals(admin.getMm())) {
//            throw new UnknownAccountException("找不到用户（" + username + "）的帐号信息");
//        }

        return new SimpleAuthenticationInfo(admin, password, getName());
    }

}
