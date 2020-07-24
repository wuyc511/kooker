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

	    public String getPassword(String name);
	    public User getByName(String name);
	     
	    public List<User> list();
	    public void add(User user);
	    public void delete(Long id);
	    public User get(Long id);
	    public void update(User user);
}
