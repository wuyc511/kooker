package com.example.demo.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.IPermissionService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserService;


public class MyShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private IUserService  userService ;
	@Autowired
	private IRoleService roleService ;
	
	@Autowired
	private IPermissionService permissionService ;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
	    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
	    String username  = (String)principals.getPrimaryPrincipal();
	   System.out.println("=========   " + username);
//	    for(Role role:roleService.listRoles(username)){
//	        authorizationInfo.addRole(role.getName());
//	        for(Permission p:permissionService.list(role)){
//	            authorizationInfo.addStringPermission(p.getName());
//	        }
//	    }
	  
	   authorizationInfo.setRoles(roleService.listRoleNames(username));
	   authorizationInfo.setStringPermissions(permissionService.listPermissions(username));
	   System.out.println("===============r   p ==================");
	   System.out.println(roleService.listRoleNames(username));
	   System.out.println(permissionService.listPermissions(username));
	    return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
	    //获取用户的输入的账号.
		    UsernamePasswordToken t = (UsernamePasswordToken) token;
	        String userName= token.getPrincipal().toString();
	        String password= new String( t.getPassword());
	       
	        String passwordInDB = userService.getPassword(userName);
	    System.out.println("----->>userInfo="+passwordInDB);
	    if(null==passwordInDB || !passwordInDB.equals(password)) {
            return null ;
	    }
	    SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
	    		userName, 
	    		password, 
	    		getName());
	    Subject subject = SecurityUtils.getSubject(); 
	    Session session=subject.getSession();
	    session.setAttribute("subject", subject);
	    return authenticationInfo;
	
	}

}
