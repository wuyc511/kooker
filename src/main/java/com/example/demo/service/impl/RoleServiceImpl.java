package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.entity.RoleExample;
import com.example.demo.entity.User;
import com.example.demo.entity.UserRole;
import com.example.demo.entity.UserRoleExample;
import com.example.demo.mapper.RoleMapper;
import com.example.demo.mapper.UserRoleMapper;
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
public class RoleServiceImpl  implements IRoleService{
    @Autowired 
    RoleMapper roleMapper;
    @Autowired 
    UserRoleMapper userRoleMapper;
    @Autowired 
    IUserService userService;
 
    @Override
    public Set<String> listRoleNames(String userName) {
        Set<String> result = new HashSet<>();
        List<Role> roles = listRoles(userName);
        for (Role role : roles) {
            result.add(role.getName());
        }
        return result;
    }
 
    @Override
    public List<Role> listRoles(String userName) {
        List<Role> roles = new ArrayList<>();
        User user = userService.getByName(userName);
        if(null==user)
            return roles;
         
        roles = listRoles(user);
        return roles;
    }
     
    @Override
    public void add(Role u) {
        roleMapper.insert(u);
    }
  
    @Override
    public void delete(Long id) {
        roleMapper.deleteByPrimaryKey(id);
    }
  
    @Override
    public void update(Role u) {
        roleMapper.updateByPrimaryKeySelective(u);
    }
  
    @Override
    public Role get(Long id) {
        return roleMapper.selectByPrimaryKey(id);
    }
  
    @Override
    public List<Role> list(){
        RoleExample example =new RoleExample();
        example.setOrderByClause("id desc");
        return roleMapper.selectByExample(example);
  
    }
 
    @Override
    public List<Role> listRoles(User user) {
        List<Role> roles = new ArrayList<>();
 
        UserRoleExample example = new UserRoleExample();
         
        example.createCriteria().andUidEqualTo(user.getId());
        List<UserRole> userRoles= userRoleMapper.selectByExample(example);
         
        for (UserRole userRole : userRoles) {
            Role role=roleMapper.selectByPrimaryKey(userRole.getRid());
            roles.add(role);
        }
        return roles;
    }  
     
}
