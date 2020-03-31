package edu.health.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.health.dao.ExamMapper;
import edu.health.dao.UserExamMapper;
import edu.health.model.Exam;
import edu.health.model.UserExamJoinUsers;
import edu.health.model.UserExamWithBLOBs; 

@Service
public class ExamService {
	
	@Autowired
	ExamMapper examMapper;
	@Autowired
	UserExamMapper userExamMapper;
	
	public Integer insert(Exam item) {
		return examMapper.insertSelective(item);
	}
	
	public void del(Integer id) {
		examMapper.deleteByPrimaryKey(id);
	}
	
	public void update(Exam item) {
		examMapper.updateByPrimaryKeySelective(item);
	}
	
	public Exam findById(Integer id) {
		return examMapper.selectByPrimaryKey(id);
	}
	
	public List<Exam> query(Integer type,Integer p, Integer size){
		if(p==null) {
			p = 1;
		}
		int start = (p-1)*size;
		List<Exam> lst = new ArrayList<Exam>();
		lst = examMapper.query(type, start, size);
		return lst;
	}
	
	
	public List<UserExamJoinUsers> userExamQuery(Integer examId, Integer userId, Integer doctorId,String tel, Integer p, Integer size){
		if(p==null) {
			p = 1;
		}
		if(size == null) {
			size = 10;
		}
		if(tel != null && tel.length() > 0) {
			tel = '%' + tel + '%';
		}
		return userExamMapper.query(examId, userId, doctorId,tel,(p-1)*size, size);
	}
	
	public Integer userExamCount(Integer examId,Integer userId, Integer doctorId, String tel) {
		if(tel != null && tel.length() > 0) {
			tel = '%' + tel + '%';
		}
		return userExamMapper.count(examId, userId, doctorId, tel);
	}
	
	public void delByExamID(Integer examID) {
		userExamMapper.delByExamId(examID);
	}
	
	public void userExamAdd(UserExamWithBLOBs item) {
		userExamMapper.insertSelective(item);
	}
	
	public void userExamUpdate(UserExamWithBLOBs item) {
		userExamMapper.updateByPrimaryKeySelective(item);
	}
	
	
}
