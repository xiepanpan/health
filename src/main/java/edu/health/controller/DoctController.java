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
@RequestMapping("/doct")
public class DoctController {
	final static org.slf4j.Logger log = LoggerFactory.getLogger(AdminController.class);
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
		return "doct/index";
	}

	@RequestMapping("welcome")
	public String welcome() {
		return "doct/welcome";
	}
	
	@RequestMapping("topic/lst")
	public String topicLst(String title,  Integer p, Model model) {
		if(p == null) {
			p = 1;
		}
		List<TopicWithUser> lst = topicService.queryPage(title, null, null, null, p, 10);
		Integer count = topicService.count(title, null);
		String page = new PageUtil(10, count, p, "/doct/topic/lst?").page();
		model.addAttribute("list", lst);
		model.addAttribute("page", page);
		return "doct/topic/lst";
	}
	
	@RequestMapping("exam/lst")
	public String examList(Integer type, Integer p,Model model) {
		List<Exam> lst = examService.query(type, p, 50);
		
		model.addAttribute("lst", lst);
		model.addAttribute("type", type);
		return "doct/exam/lst";
	}
	
	@RequestMapping("exam/stu_lst")
	public String examStuList(Integer id, Integer p,String tel, Model model, HttpSession session) {
		if(p==null) {
			p=1;
		}
		List<UserExamJoinUsers> list = examService.userExamQuery(id, null, (int)session.getAttribute("user_id"), tel,p, 10);
		int count = examService.userExamCount(id, null, (int)session.getAttribute("user_id"), tel);
		String page = new PageUtil(10, count, p, "/doct/exam/stu_lst?id=" + id +"&").page();
		List<Users> doctLst = usersService.query(2, null, 1, 1000);
		model.addAttribute("doctLst", doctLst);
		model.addAttribute("lst", list);
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		model.addAttribute("tel",tel);
		return "doct/exam/stu_lst";
	}
	@RequestMapping("pyexam/lst")
	public String physicalStuLst(Integer p,String tel,Model model, HttpSession session) {
		Integer size = 10;
		p = (p==null)?1:p;
		Integer doctId = (Integer) session.getAttribute("user_id");
		List<PhysicalExamJoinUser> list = physicalExamService.query(null, doctId, tel, p, size);
		int count = physicalExamService.count(null, null, tel);
		String page = new PageUtil(size, count, p, "/doct/pyexam/lst").page();
		List<Users> doctLst = usersService.query(2, null, 1, 1000);
		model.addAttribute("doctLst", doctLst);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		model.addAttribute("tel", tel);
		return "doct/exam/physical_stu_lst";
	}
	
	@RequestMapping("pyexam/detail")
	public String pyexamDetail(Integer id, Model model, HttpSession session) {
		
		PhysicalExam item = physicalExamService.findById(id);
		Users user = usersService.findByKey(item.getStuId());
		log.error("体测信息" + item);
		model.addAttribute("name", user.getLoginName());
		model.addAttribute("item", item);
		return "doct/exam/pydetail";
	}
	
	@RequestMapping("pyexam/update")
	public String pyexamUpdate(PhysicalExam item) {
		Integer id = item.getTestId();
		physicalExamService.update(item);
		
		return "redirect:/doct/pyexam/lst";
	}
	
	
	@RequestMapping("exam/detail")
	public String examDetail(Integer id,Integer stuId, Model model) {
		Exam exam = examService.findById(id);
		List<UserExamJoinUsers> list = examService.userExamQuery(id, stuId, null, null, 1, 10);
		UserExamJoinUsers userExam = list.get(0);
		if(userExam.getAnswer() == null){
			userExam.setAnswer(exam.getExamContent());
		}
		model.addAttribute("userExam", userExam);
		model.addAttribute("exam", exam);
		return "doct/exam/detail";
	}
	
	@RequestMapping("exam/detailUpdate")
	public String examDetailUpdate(Integer id, Integer stuId, String advice) {
		UserExamWithBLOBs e = new UserExamWithBLOBs();
		e.setExamId(id);
		e.setUserId(stuId);
		e.setAdvice(advice);
		examService.userExamUpdate(e);
		return "redirect:/doct/exam/stu_lst?id=" + id;
	}
	
	
	@RequestMapping("topic/detail")
	public String topicEdit(Integer id,Model model) {
		model.addAttribute("item", topicService.findByKey(id));
		List<CmtJoinUser> lst = cmtService.topicCmt(id);
		model.addAttribute("cmt_lst", lst);
		return "doct/topic/detail";
	}
	
	@RequestMapping("cmt/cmt")
	public String cmtSubmit(Integer topicId,Integer cmtId,String content,HttpSession session) {
		CmtWithBLOBs item = new CmtWithBLOBs();
		item.setCmtId(cmtId);
		item.setRepeat(content);
		cmtService.update(item);
		return "redirect:/doct/topic/detail?id=" + topicId;
	}
	
}
