package edu.health.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.health.dao.CmtMapper;
import edu.health.model.CmtWithBLOBs;
import edu.health.model.CmtJoinUser;

@Service
public class CmtService {

	@Autowired
	CmtMapper cmtMapper;
	
	public Integer insert(CmtWithBLOBs item) {
		return cmtMapper.insertSelective(item);
	}
	
	public void del(Integer id) {
		cmtMapper.deleteByPrimaryKey(id);
	}
	
	public void update(CmtWithBLOBs item) {
		cmtMapper.updateByPrimaryKeySelective(item);
	}
	
	
	public List<CmtJoinUser> topicCmt(Integer topicId){
		List<CmtJoinUser> lst = new ArrayList<CmtJoinUser>();
		lst = cmtMapper.finByTopic(topicId);
		return lst;
	}
	
}
