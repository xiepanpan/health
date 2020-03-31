package edu.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.health.dao.UsersMapper;
import edu.health.model.Users;

@Service
public class UsersService {

	@Autowired
	UsersMapper usersMapper;
	
	
	public Integer insert(Users item) {
		return usersMapper.insertSelective(item);
	}
	
	public void del(Integer id) {
		usersMapper.deleteByPrimaryKey(id);
	}
	
	public void update(Users item) {
		usersMapper.updateByPrimaryKeySelective(item);
	}
	
	public Users findByKey(Integer key) {
		return usersMapper.selectByPrimaryKey(key);
	}
	
	public Users findByLoginName(String name) {
		return usersMapper.findByLoginName(name);
	}
	
	public List<Users> query(Integer type, String name, Integer p, Integer size){
		if(p==null) {
			p = 1;
		}
		return usersMapper.query(type, name, (p-1)*size, size,null);
	}

	public List<Users> queryById(Integer type, String name, Integer p, Integer size,Integer id){
		if(p==null) {
			p = 1;
		}
		return usersMapper.query(type, name, (p-1)*size, size,id);
	}
	
}
