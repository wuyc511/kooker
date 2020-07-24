package com.example.demo.service;

import com.example.demo.entity.Role;

public interface RolePermissionService {
	    public void setPermissions(Role role, long[] permissionIds);
	    public void deleteByRole(long roleId);
	    public void deleteByPermission(long permissionId);
}
