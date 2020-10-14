package com.example.demo.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.service.IPermissionService;
import com.example.demo.service.IRoleService;
import com.example.demo.service.RolePermissionService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xy
 * @since 2019-08-21
 */
@Controller
@RequestMapping("/config")
public class RoleController {
	@Autowired IRoleService roleService;
	@Autowired RolePermissionService rolePermissionService;
    @Autowired IPermissionService permissionService;
     
    @RequestMapping("listRole")
    public String list(Model model){
        List<Role> rs= roleService.list();
        model.addAttribute("rs", rs);
         
        Map<Role,List<Permission>> role_permissions = new HashMap<>();
         
        for (Role role : rs) {
            List<Permission> ps = permissionService.list(role);
            role_permissions.put(role, ps);
        }
        model.addAttribute("role_permissions", role_permissions);
 
        return "listRole";
    }
    @RequestMapping("editRole")
    public String list(Model model,long id){
        Role role =roleService.get(id);
        model.addAttribute("role", role);
         
        List<Permission> ps = permissionService.list();
        model.addAttribute("ps", ps);
 
        List<Permission> currentPermissions = permissionService.list(role);
        model.addAttribute("currentPermissions", currentPermissions);
         
        return "editRole";
    }
    @RequestMapping("updateRole")
    public String update(Role role,long[] permissionIds){
        rolePermissionService.setPermissions(role, permissionIds);
        roleService.update(role);
        return "redirect:listRole";
    }
 
    @RequestMapping("addRole")
    public String list(Model model,Role role){
        System.out.println(role.getName());
        System.out.println(role.getDesc());
        roleService.add(role);
        return "redirect:listRole";
    }
    @RequestMapping("deleteRole")
    public String delete(Model model,long id){
        roleService.delete(id);
        return "redirect:listRole";
    }   
}

