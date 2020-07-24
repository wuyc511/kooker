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
	    public Set<String> listPermissions(String userName);
	 
	    public List<Permission> list();
	    public void add(Permission permission);
	    public void delete(Long id);
	    public Permission get(Long id);
	    public void update(Permission permission);
	 
	    public List<Permission> list(Role role);
	     
}
