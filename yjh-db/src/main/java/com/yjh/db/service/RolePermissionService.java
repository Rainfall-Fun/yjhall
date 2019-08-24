package com.yjh.db.service;

import com.yjh.db.dao.SysJsqxMapper;
import com.yjh.db.domain.SysJsqxExample;
import com.yjh.db.domain.SysJsqxKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/1 13:39
 */
@Service
public class RolePermissionService {
    @Autowired
    SysJsqxMapper rolePermissionMapper;

    /**
     * 根据角色编码获得其所有的权限编码
     * @param roleCode
     * @return
     */
    public List<Integer> getAllPermissionCodeByRoleCode(int roleCode){
        SysJsqxExample rolePermissionExample=new SysJsqxExample();
        SysJsqxExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andJsbmEqualTo(roleCode);
        List<SysJsqxKey> rolePermissions = rolePermissionMapper.selectByExample(rolePermissionExample);
        List<Integer> permissionIds=new ArrayList<>(rolePermissions.size());
        for (SysJsqxKey rp :
             rolePermissions) {
            permissionIds.add(rp.getQxbm());
        }
        return permissionIds;
    }

    /**
     * 根据角色编码删除其所有权限
     * @param roleId
     * @return
     */
    public int deleteByRoleId(int roleId){
        SysJsqxExample rolePermissionExample=new SysJsqxExample();
        SysJsqxExample.Criteria criteria = rolePermissionExample.createCriteria();
        criteria.andJsbmEqualTo(roleId);
        return rolePermissionMapper.deleteByExample(rolePermissionExample);
    }

    public int addRolePermission(SysJsqxKey rolePermission){
        try{
            return rolePermissionMapper.insertSelective(rolePermission);
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
