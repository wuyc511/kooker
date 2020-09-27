package com.example.demo.service;

import java.util.List;
import java.util.Set;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xy
 * @since 2019-08-21
 */
public interface IPermissionService  {
	/**
	 * 权限列表
	 * @param userName
	 * @return
	 */
    public Set<String> listPermissions(String userName);
 
    /**
     * 查询列表
     * @return
     */
    public List<Permission> list();
    
    /**
     * 添加
     * @param permission
     */
    public void add(Permission permission);
    
    /**
     * 删除--根据ID
     * @param id
     */
    public void delete(Long id);
    
    /**
     * 查询-根据id 
     * @param id
     * @return
     */
    public Permission get(Long id);
    
    /**
     * 修改
     * @param permission
     */
    public void update(Permission permission);
 
    /**
     * 角色查询
     * @param role
     * @return
     */
    public List<Permission> list(Role role);
	     
}
