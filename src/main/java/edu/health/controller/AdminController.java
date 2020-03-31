package edu.health.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.health.model.CmtJoinUser;
import edu.health.model.Exam;
import edu.health.model.PhysicalExam;
import edu.health.model.PhysicalExamJoinUser;
import edu.health.model.Topic;
import edu.health.model.TopicWithUser;
import edu.health.model.UserExamJoinUsers;
import edu.health.model.UserExamWithBLOBs;
import edu.health.model.Users;
import edu.health.service.CmtService;
import edu.health.service.ExamService;
import edu.health.service.PhysicalExamService;
import edu.health.service.TopicService;
import edu.health.service.UsersService;
import edu.health.util.JsonResults;
import edu.health.util.PageUtil;

@Controller
@RequestMapping("/admin")
public class AdminController {
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
		return "/admin/index";
	}

	@RequestMapping("welcome")
	public String welcome() {
		return "/admin/welcome";
	}

	@RequestMapping(value = "user/lst")
	public String userLst(Integer r,String name, Integer p, Model model) {

		if (p == null) {
			p = 1;
		}
		if (name != null && name.trim().length() == 0) {
			name = null;
		}
		List<Users> lst = usersService.query(r, name, p, 10);
		model.addAttribute("list", lst);
		model.addAttribute("name", name);
		model.addAttribute("r", r);
		return "admin/user/lst";
	}

	/**
	 * 个人信息列表
	 * @param r
	 * @param name
	 * @param p
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "user/lstPerson")
	public String userlstPerson(Integer r,String name, Integer p, Model model,Integer id) {

		if (p == null) {
			p = 1;
		}
		if (name != null && name.trim().length() == 0) {
			name = null;
		}
		List<Users> lst = usersService.queryById(r, name, p, 10,id);
		model.addAttribute("list", lst);
		model.addAttribute("name", name);
		model.addAttribute("r", r);
		model.addAttribute("", r);
		return "admin/user/lst_person";
	}

	/**
	 * 用户新新增
	 * 
	 * @param error
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "user/add")
	public String userAdd(String error, Integer r, Model model) throws UnsupportedEncodingException {
		if (error != null) {
			model.addAttribute("error", URLDecoder.decode(error, "utf-8"));
		}
		model.addAttribute("r", r);
		return "admin/user/add";
	}

	/**
	 * 用户添加
	 * 
	 * @param user
	 * @param r
	 * @param cls
	 * @param redirectAttributes
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "user/do_add")
	public String userDoAdd(Users user, Integer r, Integer cls, RedirectAttributes redirectAttributes)
			throws UnsupportedEncodingException {
		log.info("新增会员:" + user.toString());
		// 密码加密
		// user.setPwd(new BCryptPasswordEncoder().encode(user.getPwd()));
		// todo Table has set the login_name uq, catch insert error exception return
		// error
		
		if (usersService.findByLoginName(user.getLoginName()) != null) {
			log.info("用户名已经存在");
			redirectAttributes.addAttribute("error", URLEncoder.encode("用户已存在", "utf-8"));
			return "redirect:/admin/user/add";
		}
		usersService.insert(user);

		return "redirect:/admin/user/lst?r="+user.getUserType();
	}
	/**
	 * 用户密码修改
	 * @param id
	 * @param model
	 * @param error
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "user/edit")
	public String userEdit(Integer id,String flag, Model model, String error) throws UnsupportedEncodingException {
		if (error != null) {
			model.addAttribute("error", URLDecoder.decode(error, "utf-8"));
		}
		Users user = usersService.findByKey(id);
		model.addAttribute("item", user);
		model.addAttribute("flag", flag);
		return "admin/user/edit";
	}
	/**
	 * 用户修改表单处理
	 * @param id
	 * @param loginPwd
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "user/update")
	public String userUpdate(Users users,String flag) {

//		if (id == 1) {
//			log.error("权限不足，修改会员信息失败");
//			return "redirect:/admin/user/edit";
//		}

		// todo 限定参数

		log.info("修改信息：" + users.toString());
		usersService.update(users);
		if ("person".equals(flag)) {
			return "redirect:/admin/user/lstPerson?r=" + users.getUserType();
		}
		return "redirect:/admin/user/lst?r=" + users.getUserType();
	}
	
	
	@RequestMapping(value = "user/edit_info")
	public String userEditInfo(Integer id,String flag, Model model, String error) throws UnsupportedEncodingException {
		if (error != null) {
			model.addAttribute("error", URLDecoder.decode(error, "utf-8"));
		}
		
		Users user = usersService.findByKey(id);
		model.addAttribute("item", user);
		model.addAttribute("flag", flag);
		return "admin/user/edit_info";
	}
	
	@RequestMapping(value="user/del")
	@ResponseBody
	public JsonResults userDel(Integer id, Model model) {
		JsonResults res = new JsonResults();
		if(id<2) {
			res.setResult_msg("超级管理员不能删除");
		}else {
			res.setResult_msg("删除成功");
			usersService.del(id);
		}
		return res;
	}

	/**
	 * 更新用户信息
	 * @param user
	 * @param flag
	 * @param model
	 * @param error
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "user/update_info")
	public String userUpdateInfo(Users user,String flag, Model model, String error) throws UnsupportedEncodingException {
//		if (user.getUserId() == 1) {
//			log.error("权限不足，修改会员信息失败");
//			return "redirect:/admin/user/edit";
//		}
		
		log.info("修改信息：" + user.toString());
		usersService.update(user);
		if ("person".equals(flag)) {
			return "redirect:/admin/user/lstPerson?r=" + user.getUserType();
		}
		return "redirect:/admin/user/lst?r=" + user.getUserType();
	}
	 
	/**
	 * 帖子列表
	 * @param title
	 * @param catId
	 * @param p
	 * @param model
	 * @return
	 */
	@RequestMapping("topic/lst")
	public String topicLst(String title,  Integer p, Model model) {
		if(p == null) {
			p = 1;
		}
		List<TopicWithUser> lst = topicService.queryPage(title, null, null, null, p, 10);
		Integer count = topicService.count(title, null);
		String page = new PageUtil(10, count, p, "/admin/topic/lst?").page();
		model.addAttribute("list", lst);
		model.addAttribute("page", page);
		return "admin/topic/lst";
	}
	
	@RequestMapping("topic/add")
	public String topicAdd() {
		return "admin/topic/add";
	}
	
	@RequestMapping("topic/do_add")
	public String topicDoAdd(Topic topic,String topicContent, HttpSession session) {
		topic.setUserId((int)session.getAttribute("user_id"));
		topic.setContent(topicContent);
		topic.setCreateDate(new Date());
		topicService.insert(topic);
		return "redirect:/admin/topic/lst";
	}
	
	@RequestMapping("topic/edit")
	public String topicEdit(Integer id,Model model) {
		model.addAttribute("item", topicService.findByKey(id));
		return "admin/topic/edit";
	}
	
	@RequestMapping("toipc/update")
	public String topicUpdate(Topic topic, String topicContent) {
		log.info("======================");
		log.info("update topic");
		topic.setContent(topicContent);
		topicService.update(topic);
		return "redirect:/admin/topic/lst";
	}
	
	
	/**
	 * 删除帖子
	 * @param id
	 * @return
	 */
	@RequestMapping("topic/del")
	public String topicDel(Integer id) {
		topicService.del(id);
		return "redirect:/admin/topic/lst";
	}
	
	/**
	 * 帖子回复
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("topic/cmtlst")
	public String cmtLst(Integer id, Model model) {
		List<CmtJoinUser> lst = cmtService.topicCmt(id);
		model.addAttribute("list", lst);
		return "admin/topic/cmt_lst";
	}
	
	/**
	 * h回复删除
	 * @param cmtId
	 * @param topicId
	 * @return
	 */
	@RequestMapping("topic/cmtdel")
	public String cmtDel(Integer cmtId, Integer topicId) {
		cmtService.del(cmtId);
		return "redirect:/admin/topic/cmtlst?id=" + topicId;
	}
	
	@RequestMapping("exam/lst")
	public String examList(Integer type, Integer p,Model model) {
		List<Exam> lst = examService.query(type, p, 50);
		
		model.addAttribute("lst", lst);
		model.addAttribute("type", type);
		return "admin/exam/lst";
	}
	
	@RequestMapping("exam/add")
	public String examAdd(Integer type, Model model) {

		model.addAttribute("type", type);
		return "admin/exam/add";
	}
	
	@RequestMapping("exam/do_add")
	public String examDoAdd(Exam exam, String topicContent) {
		exam.setExamContent(topicContent);
		examService.insert(exam);
		
		return "redirect:/admin/exam/lst";
	}
	
	@RequestMapping("exam/edit")
	public String examEdit(Integer id, Model model) {
		Exam item = examService.findById(id);
		model.addAttribute("item", item);
		return "admin/exam/edit";
	}
	
	@RequestMapping("exam/update")
	public String examUpdate(Exam exam, String topicContent) {
		exam.setExamContent(topicContent);
		examService.update(exam);
		return "redirect:/admin/exam/lst?type=" + exam.getExamType();
	}
	
	@RequestMapping("exam/del")
	public String examDel(Integer id) {
		examService.del(id);
		return "redirect:/admin/exam/lst";
	}
	
	@RequestMapping("exam/generate")
	public String examGenerate(Integer id) {
		Exam exam = examService.findById(id);
		//清除当前测试学生记录，
		examService.delByExamID(id);
		//检索所有学生
		List<Users> users = usersService.query(1, null, 1, 1000);
		//遍历增加学生-测试
		for(int x = 0;x < users.size(); x++) {
			UserExamWithBLOBs item = new UserExamWithBLOBs();
			item.setUserId(users.get(x).getUserId());
			item.setExamId(id);
			examService.userExamAdd(item);
		}
		return "redirect:/admin/exam/stu_lst?id=" + id;
	}
	
	@RequestMapping("exam/stu_lst")
	public String examStuList(Integer id, Integer p, Model model) {
		if(p==null) {
			p=1;
		}
		List<UserExamJoinUsers> list = examService.userExamQuery(id, null, null,null, p, 10);
		int count = examService.userExamCount(id, null, null,null);
		String page = new PageUtil(10, count, p, "/admin/exam/stu_lst?id=" + id +"&").page();
		List<Users> doctLst = usersService.query(2, null, 1, 1000);
		model.addAttribute("doctLst", doctLst);
		model.addAttribute("lst", list);
		model.addAttribute("page", page);
		model.addAttribute("id", id);
		return "admin/exam/stu_lst";
	}
	
	@RequestMapping("exam/assign")
	@ResponseBody
	public JsonResults examAssign(Integer exam_id,Integer doct, String stu) {
		
		String[] stu_arr = stu.split(",");
		for(int x =0;x<stu_arr.length;x++) {
			UserExamWithBLOBs item = new UserExamWithBLOBs();
			item.setUserId(Integer.valueOf(stu_arr[x]));
			item.setDoctorId(doct);
			item.setExamId(exam_id);
			examService.userExamUpdate(item);
		}
		
		JsonResults res = new JsonResults();
		return res;
	}
	
	@RequestMapping("pyexam/lst")
	public String physicalStuLst(Integer p,Model model) {
		Integer size = 10;
		p = (p==null)?1:p;
		List<PhysicalExamJoinUser> list = physicalExamService.query(null, null,null, p, size);
		int count = physicalExamService.count(null, null,null);
		String page = new PageUtil(size, count, p, "/admin/pyexam/lst?").page();
		List<Users> doctLst = usersService.query(2, null, 1, 1000);
		model.addAttribute("doctLst", doctLst);
		model.addAttribute("list",list);
		model.addAttribute("page",page);
		return "admin/exam/physical_stu_lst";
	}
	
	@RequestMapping("exam/physical_exam_generate")
	public String phyGenerate() {
		physicalExamService.delAll();
		
		List<Users> users = usersService.query(1, null, 1, 1000);
		//遍历增加学生-测试
		for(int x = 0;x < users.size(); x++) {
			PhysicalExam item = new PhysicalExam();
			item.setStuId(users.get(x).getUserId());
			physicalExamService.insert(item);
		}
		return "redirect:/admin/pyexam/lst";
	}
	
	
	@RequestMapping("exam/py_assign")
	@ResponseBody
	public JsonResults examAssign(Integer doct, String stu) {
		
		String[] stu_arr = stu.split(",");
		for(int x =0;x<stu_arr.length;x++) {
			physicalExamService.updateAssign(Integer.valueOf(stu_arr[x]),doct);
		}
		JsonResults res = new JsonResults();
		return res;
	}
	
	
	
}
