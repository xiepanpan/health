package edu.health.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.health.model.CmtJoinUser;
import edu.health.model.CmtWithBLOBs;
import edu.health.model.Exam;
import edu.health.model.PhysicalExam;
import edu.health.model.PhysicalExamJoinUser;
import edu.health.model.TopicWithUser;
import edu.health.model.UserExamJoinUsers;
import edu.health.model.UserExamWithBLOBs;
import edu.health.model.Users;
import edu.health.service.CmtService;
import edu.health.service.ExamService;
import edu.health.service.PhysicalExamService;
import edu.health.service.TopicService;
import edu.health.service.UsersService;
import edu.health.util.PageUtil;

@Controller
@RequestMapping("/stu")
public class StuController {

	final static org.slf4j.Logger log = LoggerFactory.getLogger(StuController.class);
	@Autowired
	UsersService usersService; 
	@Autowired
	TopicService topicService;
	@Autowired
	CmtService cmtService;
	@Autowired
	ExamService examService;
	@Autowired
	PhysicalExamService physicalExamService;
	
	@RequestMapping("index")
	public String index() {
		return "stu/index";
	}

	@RequestMapping("welcome")
	public String welcome() {
		return "stu/welcome";
	}
	
	@RequestMapping("topic/lst")
	public String topicLst(String title,  Integer p, Model model) {
		if(p == null) {
			p = 1;
		}
		List<TopicWithUser> lst = topicService.queryPage(title, null, null, null, p, 10);
		Integer count = topicService.count(title, null);
		String page = new PageUtil(10, count, p, "/stu/topic/lst?").page();
		model.addAttribute("list", lst);
		model.addAttribute("page", page);
		return "stu/topic/lst";
	}
	
	@RequestMapping("exam/lst")
	public String examList(Integer type, Integer p,Model model) {
		List<Exam> lst = examService.query(type, p, 50);
		
		model.addAttribute("lst", lst);
		model.addAttribute("type", type);
		return "stu/exam/lst";
	}
	
	
	@RequestMapping("pyexam/lst")
	public String physicalStuLst(Integer p,Model model, HttpSession session) {
		Integer size = 10;
		p = (p==null)?1:p;
		List<PhysicalExamJoinUser> list = physicalExamService.query((int)session.getAttribute("user_id"), null, null, p, size);
		int count = physicalExamService.count((int)session.getAttribute("user_id"), null, null);
		String page = new PageUtil(size, count, p, "/stu/pyexam/lst?").page();
		List<Users> doctLst = usersService.query(2, null, 1, 1000);
//		log.error(list.get(0).toString() + list.get(0).getTestId());
		model.addAttribute("doctLst", doctLst);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		return "stu/exam/physical_stu_lst";
	}
	
	@RequestMapping("answer/edit")
	public String answerEdit(Integer id, Model model, HttpSession session) {
		log.error("学生问卷填写");
		int p = 1;
		int size = 10;
		List<UserExamJoinUsers> lst = examService.userExamQuery(id, (int)session.getAttribute("user_id"), null, null,p, size);
		UserExamJoinUsers answer = lst.get(0);
		Exam exam = examService.findById(id);
		if(answer.getAnswer() == null) {
			answer.setAnswer(exam.getExamContent());
		}
		model.addAttribute("exam", exam);
		model.addAttribute("answer", answer);
		log.error("考试名称" + exam.getExamName());
		return "stu/exam/edit";
	}
	
	@RequestMapping("answer/doEdit")
	public String doAnswerEdit(Integer id, HttpSession session, String topicContent) {
		
		UserExamWithBLOBs item = new UserExamWithBLOBs();
		item.setExamId(id);
		item.setUserId((int)session.getAttribute("user_id"));
		item.setAnswer(topicContent);
		
		examService.userExamUpdate(item);
		return "redirect:/stu/answer/edit?id=" + id;
	}
	
	@RequestMapping("pyexam/detail")
	public String pyexamDetail(Integer id, Model model, HttpSession session) {
		
		PhysicalExam item = physicalExamService.findById(id);
		Users user = (Users) session.getAttribute("user");
		log.error("体测信息" + item);
		model.addAttribute("name", user.getLoginName());
		model.addAttribute("item", item);
		return "stu/exam/pydetail";
	}
	
	
	@RequestMapping("topic/detail")
	public String topicEdit(Integer id,Model model) {
		model.addAttribute("item", topicService.findByKey(id));
		List<CmtJoinUser> lst = cmtService.topicCmt(id);
		model.addAttribute("cmt_lst", lst);
		return "stu/topic/detail";
	}
	
	
	@RequestMapping("cmt/cmt")
	public String cmtSubmit(Integer topicId,String content,HttpSession session) {
		CmtWithBLOBs item = new CmtWithBLOBs();
		item.setTopicId(topicId);
		item.setContent(content);
		item.setUserId((int)session.getAttribute("user_id"));
		cmtService.insert(item);
		return "redirect:/stu/topic/detail?id=" + topicId;
	}
}
