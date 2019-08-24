package com.yjh.db.service;

import com.github.pagehelper.PageHelper;
import com.yjh.db.dao.SysJsMapper;
import com.yjh.db.domain.SysJs;
import com.yjh.db.domain.SysJsExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by 彭椿悦 on 2019/7/29 11:45
 */
@Service
public class RoleService {
    @Autowired
    SysJsMapper roleMapper;

    /**
     * 查询符合要求的角色
     * @param name
     * @param page
     * @param size
     * @param sort
     * @param order
     * @return
     */
    public List<SysJs> querySelective(String name, Integer page, Integer size, String sort, String order) {
        SysJsExample roleExample=new SysJsExample();
        SysJsExample.Criteria criteria = roleExample.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andJsmcLike("%" + name + "%");
        }
        if (!StringUtils.isEmpty(sort) && !StringUtils.isEmpty(order)) {
            roleExample.setOrderByClause(sort + " " + order);
        }
        PageHelper.startPage(page, size);
        return roleMapper.selectByExample(roleExample);
    }

    /**
     * 根据角色名称判断角色是否存在
     * @param roleName
     * @return
     */
    public boolean checkExist(String roleName){
        try {
            SysJsExample roleExample=new SysJsExample();
            SysJsExample.Criteria criteria = roleExample.createCriteria();
            criteria.andJsmcEqualTo(roleName);
            List<SysJs> sysJs = roleMapper.selectByExample(roleExample);
            if (sysJs.size()!=0)
                return true;
            else
                return false;
        }catch (Exception e){
            return true;
        }
    }

    /**
     * 添加角色信息到数据库
     * @param role
     * @return
     */
    public int addRole(SysJs role){
        int i = roleMapper.insertSelective(role);
        if(i==1)
            return role.getJsbm();
        else
            return 0;
    }

    /**
     * 更新角色信息
     * @param role
     * @return
     */
    public int updateRole(SysJs role){
        return roleMapper.updateByPrimaryKey(role);
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    public boolean deleteRoleByRoleId(int roleId){
        int i = roleMapper.deleteByPrimaryKey(roleId);
        if (i==1)
            return true;
        else
            return false;
    }

    /**
     * 得到所有的角色信息
     * @return
     */
    public List<SysJs> getAllRoles(){
        try {
            return roleMapper.selectByExample(new SysJsExample());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
