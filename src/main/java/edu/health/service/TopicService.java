package edu.health.service;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.health.dao.TopicMapper;
import edu.health.model.Topic;
import edu.health.model.TopicWithUser;

@Service
public class TopicService {
	final static org.slf4j.Logger log = LoggerFactory.getLogger(TopicService.class);
	@Autowired
	TopicMapper topicMapper;

	public Integer insert(Topic item) {
		return topicMapper.insert(item);
	}

	public void del(Integer id) {
		topicMapper.deleteByPrimaryKey(id);
	}

	public void update(Topic item) {
		topicMapper.updateByPrimaryKeySelective(item);
	}

	public Topic findByKey(Integer key) {
		return topicMapper.selectByPrimaryKey(key);
	}
	
	public Integer count(String title,Integer userId) {
		return topicMapper.count(title, userId);
	}

	public List<TopicWithUser> queryPage(String title,Integer userId, String order,
			String orderType, Integer pageIndex, Integer size) {
		if(pageIndex == null) {
			pageIndex = 1;
		}
		int start = (pageIndex - 1) * size;
		if (title != null) {
			title = "%" + title + "%";
		}
		return topicMapper.queryPage(title, userId, order, orderType, start, size);
	}
	
	
	
}
