package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.RoleExample;
import com.example.demo.entity.User;
import com.example.demo.entity.UserExample;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserRoleService;
import com.example.demo.service.IUserService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xy
 * @since 2019-08-21
 */
@Service
public class UserServiceImpl  implements IUserService {

	@Autowired
	private UserMapper userMapper;

	 @Autowired 
	 IUserRoleService userRoleService;
     
	    @Override
	    public String getPassword(String name) {
	        User user = getByName(name);
	        if(null == user)
	            return null;
	        return user.getPassword();
	    }
	 
	    @Override
	    public User getByName(String name) {
	        UserExample example = new UserExample();
	        example.createCriteria().andUsernameEqualTo(name);
	        List<User> users = userMapper.selectByExample(example);
	        if(users.isEmpty())
	            return null;
	        return users.get(0);
	    }
	     
	    @Override
	    public void add(User u) {
	        userMapper.insert(u);
	    }
	  
	    @Override
	    public void delete(Long id) {
	        userMapper.deleteByPrimaryKey(id);
	        userRoleService.deleteByUser(id);
	    }
	  
	    @Override
	    public void update(User u) {
	        userMapper.updateByPrimaryKeySelective(u);
	    }
	  
	    @Override
	    public User get(Long id) {
	        return userMapper.selectByPrimaryKey(id);
	    }
	  
	    @Override
	    public List<User> list(){
	        UserExample example =new UserExample();
	        example.setOrderByClause("id desc");
	        return userMapper.selectByExample(example);
	  
	    }
}
