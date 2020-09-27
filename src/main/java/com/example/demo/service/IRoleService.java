package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xy
 * @since 2019-08-21
 */
public interface IRoleService  {
	
	public Set<String> listRoleNames(String userName);
	
    public List<Role> listRoles(String userName);
    
    public List<Role> listRoles(User user);
    
    public List<Role> list();
    
    public void add(Role role);
    
    public void delete(Long id);
    
    /**
     * 角色查询--根据id 
     * @param id
     * @return
     */
    public Role get(Long id);
    
    /**
     * 修改
     * @param role
     */
    public void update(Role role);
}
