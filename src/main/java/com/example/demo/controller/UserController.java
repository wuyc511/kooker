package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.IRoleService;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xy
 * @since 2019-08-21
 */
@Controller
@RequiresRoles("admin")
@RequestMapping("/config")
public class UserController {

	    @Autowired IUserRoleService userRoleService;
	    @Autowired IUserService userService;
	    @Autowired IRoleService roleService;
	     
	    @RequestMapping("listUser")
	    public String list(Model model){
	        List<User> us= userService.list();
	        model.addAttribute("us", us);
	        Map<User,List<Role>> user_roles = new HashMap<>();
	        for (User user : us) {
	            List<Role> roles=roleService.listRoles(user);
	            user_roles.put(user, roles);
	        }
	        model.addAttribute("user_roles", user_roles);
	 
	        return "listUser";
	    }
	    @RequestMapping("editUser")
	    public String edit(Model model,long id){
	        List<Role> rs = roleService.list();
	        model.addAttribute("rs", rs);      
	        User user =userService.get(id);
	        model.addAttribute("user", user);
	         
	        List<Role> roles =roleService.listRoles(user);
	        model.addAttribute("currentRoles", roles);
	         
	        return "editUser";
	    }
	    @RequestMapping("deleteUser")
	    public String delete(Model model,long id){
	        userService.delete(id);
	        return "redirect:listUser";
	    }
	    @RequestMapping("updateUser")
	    public String update(User user,long[] roleIds){
	        userRoleService.setRoles(user,roleIds);
	         
	        String password=user.getPassword();
	        //如果在修改的时候没有设置密码，就表示不改动密码
	        if(user.getPassword().length()!=0) {
	           // String salt = new SecureRandomNumberGenerator().nextBytes().toString();
	            int times = 2;
	            String algorithmName = "md5";
	           // String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();
	           // user.setSalt(salt);
	           // user.setPassword(encodedPassword);
	        }
	        else
	            user.setPassword(null);
	         
	        userService.update(user);
	 
	        return "redirect:listUser";
	 
	    }
	 
	    @RequestMapping("addUser")
	    public String add(Model model,String name, String password){
	         
	        //String salt = new SecureRandomNumberGenerator().nextBytes().toString();
	        int times = 2;
	        String algorithmName = "md5";
	          
	        //String encodedPassword = new SimpleHash(algorithmName,password,salt,times).toString();
	         
	        User u = new User();
	        u.setUsername(name);
	       // u.setPassword(encodedPassword);
	        u.setPassword(password);
	        u.setSalt("abc");
	        userService.add(u);
	         
	        return "redirect:listUser";
	    }

	  
}

