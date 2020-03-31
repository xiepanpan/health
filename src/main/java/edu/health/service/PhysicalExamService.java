package edu.health.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.health.dao.PhysicalExamMapper;
import edu.health.model.PhysicalExam;
import edu.health.model.PhysicalExamJoinUser;

@Service
public class PhysicalExamService {

	
	@Autowired
	PhysicalExamMapper mapper;
	
	public Integer insert(PhysicalExam item) {
		return mapper.insertSelective(item);
	}
	
	public void del(Integer id) {
		mapper.deleteByPrimaryKey(id);
	}
	
	public void update(PhysicalExam item) {
		mapper.updateByPrimaryKeySelective(item);
	}
	
	public PhysicalExam findById(Integer id) {
		return mapper.selectByPrimaryKey(id);
	}
	
	public void updateAssign(Integer stuId,Integer doctorId) {
		
		mapper.updateAssign(stuId,doctorId);
	}
	
	public Integer count(Integer stuId,Integer doctId, String tel) {
		if(tel != null && tel.length() > 0) {
			tel = '%' + tel + '%';
		}
		return mapper.count(stuId, doctId, tel);
	}
	
	public List<PhysicalExamJoinUser> query(Integer stuId,Integer doctId,String tel,Integer p,Integer size){
		if(p==null) {
			p=1;
		}
		if(tel != null && tel.length() > 0) {
			tel = '%' + tel + '%';
		}
		return mapper.query(stuId, doctId,tel, (p-1)*size, size);
	}
	
	public void delAll() {
		mapper.delAll();
	}
	
}
