package com.example.demo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	
	@RequestMapping("index")
	public String index() {
		return "index";
	}

	@RequestMapping("listProduct")
	@RequiresPermissions("addProduct")
	public String listProduct() {
		return "listProduct";
	}
	
	
	@RequestMapping("login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("deleteProduct")
	@RequiresRoles("productManager")
	public String deleteProduct() {
		
		return "deleteProduct";
	}
	
	@RequestMapping("deleteOrder")
	public String deleteOrder() {
		return "deleteOrder";
	}
	
	 @RequestMapping("unauthorized")
	   public String noPerms(){
	       return "unauthorized";
	   }
}
