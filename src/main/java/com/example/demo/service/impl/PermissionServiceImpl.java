package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Permission;
import com.example.demo.entity.PermissionExample;
import com.example.demo.entity.Role;
import com.example.demo.entity.RolePermission;
import com.example.demo.entity.RolePermissionExample;
import com.example.demo.mapper.PermissionMapper;
import com.example.demo.mapper.RolePermissionMapper;
import com.example.demo.service.IPermissionService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xy
 * @since 2019-08-21
 */
@Service
public class PermissionServiceImpl  implements IPermissionService{
 
    @Autowired 
    PermissionMapper permissionMapper;
    @Autowired 
    IUserService userService;
    @Autowired 
    IRoleService roleService;
    @Autowired 
    RolePermissionMapper rolePermissionMapper;
 
    @Override
    public Set<String> listPermissions(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = roleService.listRoles(userName);
         
        List<RolePermission> rolePermissions = new ArrayList<>();
         
        for (Role role : roles) {
            RolePermissionExample example = new RolePermissionExample();
            example.createCriteria().andRidEqualTo(role.getId());
            List<RolePermission> rps= rolePermissionMapper.selectByExample(example);
            rolePermissions.addAll(rps);
        }
         
        for (RolePermission rolePermission : rolePermissions) {
            Permission p = permissionMapper.selectByPrimaryKey(rolePermission.getPid());
            result.add(p.getName());
        }
         
        return result;
    }
   @Override
    public void add(Permission u) {
        permissionMapper.insert(u);
    }
  
    @Override
    public void delete(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
    }
  
    @Override
    public void update(Permission u) {
        permissionMapper.updateByPrimaryKeySelective(u);
    }
  
    @Override
    public Permission get(Long id) {
        return permissionMapper.selectByPrimaryKey(id);
    }
  
    @Override
    public List<Permission> list(){
        PermissionExample example =new PermissionExample();
        example.setOrderByClause("id desc");
        return permissionMapper.selectByExample(example);
  
    }
    @Override
    public List<Permission> list(Role role) {
        List<Permission> result = new ArrayList<>();
        RolePermissionExample example = new RolePermissionExample();
        example.createCriteria().andRidEqualTo(role.getId());
        List<RolePermission> rps = rolePermissionMapper.selectByExample(example);
        for (RolePermission rolePermission : rps) {
            result.add(permissionMapper.selectByPrimaryKey(rolePermission.getPid()));
        }
         
        return result;
    }
 
}
