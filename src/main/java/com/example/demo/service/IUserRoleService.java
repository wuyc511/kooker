package com.example.demo.service;

import com.example.demo.entity.User;

public interface IUserRoleService {

	    public void setRoles(User user, long[] roleIds);
	    public void deleteByUser(long userId);
	    public void deleteByRole(long roleId);
}
