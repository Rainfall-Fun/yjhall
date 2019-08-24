package com.yjh.db.service;

import com.yjh.db.dao.SysQxMapper;
import com.yjh.db.domain.SysQx;
import com.yjh.db.domain.SysQxExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by 彭椿悦 on 2019/8/1 13:50
 */
@Service
public class PermissionService {
    @Autowired
    SysQxMapper permissionMapper;

    /**
     * 得到所有的权限
     * @param permissionIds 权限id
     * @return 权限值
     */
    public Set<String> getPermissionByPermissionIds(List<Integer> permissionIds){
        Set<String> permissions = new HashSet<String>();
        if (permissionIds.size()==0)
            return permissions;
        SysQxExample permissionExample=new SysQxExample();
        SysQxExample.Criteria criteria = permissionExample.createCriteria();
        criteria.andQxbmIn(permissionIds);
        List<SysQx> permissions1 = permissionMapper.selectByExample(permissionExample);
        for (SysQx permission :
                permissions1) {
            permissions.add(permission.getQxdz());
        }
        return permissions;
    }

    /**
     * 得到所有含有list里面的权限地址的权限编码
     * @param permissionAddress
     * @return
     */
    public List<Integer> getPermissionIdByPermissionAddress(List<String> permissionAddress){
        try{
            SysQxExample permissionExample=new SysQxExample();
            SysQxExample.Criteria criteria = permissionExample.createCriteria();
            criteria.andQxdzIn(permissionAddress);
            List<SysQx> sysQxes = permissionMapper.selectByExample(permissionExample);
            List<Integer> permissionId=new ArrayList<>(sysQxes.size());
            for (SysQx permission :
                    sysQxes) {
                permissionId.add(permission.getQxbm());
            }
            return permissionId;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
