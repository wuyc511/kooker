package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xy
 * @since 2019-08-21
 */
public interface IUserService  {
	
	// 获取密码
	public String getPassword(String name);
	
	// 获取用户名
	public User getByName(String name);
	 
	// 查询
	public List<User> list();
	
	// 添加用户
	public void add(User user);
	
	// 删除
	public void delete(Long id);
	
	// 查询用户
	public User get(Long id);
	
	//	更新
	public void update(User user);
}
