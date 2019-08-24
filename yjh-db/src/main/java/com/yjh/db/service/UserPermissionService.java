package com.yjh.db.service;

import com.yjh.db.dao.SysYhqxMapper;
import com.yjh.db.domain.SysYhqxExample;
import com.yjh.db.domain.SysYhqxKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 彭椿悦 on 2019/8/8 13:14
 */
@Service
public class UserPermissionService {
    @Autowired
    SysYhqxMapper userPermissionMapper;

    /**
     * 添加用户权限到数据库
     * @param userPermission
     * @return
     */
    public boolean add(SysYhqxKey userPermission){
        try {
            int insert = userPermissionMapper.insert(userPermission);
            return insert==1?true:false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据用户编码删除用户权限
     * @param userId
     * @return
     */
    public boolean deleteByUserId(String userId){
        try {
            SysYhqxExample userPermissionExample=new SysYhqxExample();
            SysYhqxExample.Criteria criteria = userPermissionExample.createCriteria();
            criteria.andYhbmEqualTo(userId);
            userPermissionMapper.deleteByExample(userPermissionExample);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据用户id的到其所有的权限id
     * @param userId
     * @return
     */
    public List<Integer> getPermissionsByUserId(String userId){
        try {
            SysYhqxExample userPermissionExample=new SysYhqxExample();
            SysYhqxExample.Criteria criteria = userPermissionExample.createCriteria();
            criteria.andYhbmEqualTo(userId);
            List<SysYhqxKey> userPermissions = userPermissionMapper.selectByExample(userPermissionExample);
            List<Integer> permissionIds=new ArrayList<>(userPermissions.size());
            for (SysYhqxKey userPermission :
                    userPermissions) {
                permissionIds.add(userPermission.getQxbm());
            }
            return permissionIds;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
