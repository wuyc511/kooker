package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Permission;
import com.example.demo.service.IPermissionService;

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
public class PermissionController {

	@Autowired IPermissionService permissionService;
    
    @RequestMapping("listPermission")
    public String list(Model model){
        List<Permission> ps= permissionService.list();
        model.addAttribute("ps", ps);
        return "listPermission";
    }
    @RequestMapping("editPermission")
    public String list(Model model,long id){
        Permission permission =permissionService.get(id);
        model.addAttribute("permission", permission);
        return "editPermission";
    }
    @RequestMapping("updatePermission")
    public String update(Permission permission){
 
        permissionService.update(permission);
        return "redirect:listPermission";
    }
 
    @RequestMapping("addPermission")
    public String list(Model model,Permission permission){
        System.out.println(permission.getName());
        System.out.println(permission.getDescName());
        permissionService.add(permission);
        return "redirect:listPermission";
    }
    @RequestMapping("deletePermission")
    public String delete(Model model,long id){
        permissionService.delete(id);
        return "redirect:listPermission";
    }   
 
}

