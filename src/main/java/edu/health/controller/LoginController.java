package edu.health.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.health.config.CmsConfig;
import edu.health.model.Users;
import edu.health.service.UsersService;
import edu.health.util.JsonResults;

@RequestMapping("/login")
@Controller
public class LoginController {
	
	final static org.slf4j.Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	UsersService usersService;
	
	/**
     * 用户注销
     * @param session
     * @return
     */
    @RequestMapping("admin_logout")
    public String logout(HttpSession session){
        session.invalidate();
        log.info("# 用户注销成功 #");
        return "redirect:/login/admin_login";
    }


    /**
     * 用户登录
     * @return
     */
    @RequestMapping("admin_login")
    public String admin_login(String error, Model model, HttpSession session) throws UnsupportedEncodingException {
        if(error != null){
            log.info("# 用户登录，错误信息：" + URLDecoder.decode(error, "utf-8"));
            model.addAttribute("error",URLDecoder.decode(error, "utf-8"));
        }
        int random_int = (int)(Math.random()*888+1111);
        ModelAndView mv = new ModelAndView();
        model.addAttribute("yzm", random_int);
        session.setAttribute("yzm", random_int);
        return "/login/admin_login";
    }



    /**
     * BLOG用户登录表单处理
     * @param login_name
     * @param login_pwd
     * @param session
     * @return
     */
    @RequestMapping("do_admin_login")
    public String do_blog_login(Integer yzm,String login_name, String login_pwd, HttpSession session, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException {

        log.info("# 用户登录 #" + login_name);
        Users user = usersService.findByLoginName(login_name);
        log.info("# 查找到用户 #" + user);
        Integer validate_yzm = (Integer) session.getAttribute("yzm");
        if(yzm == null) {
        	redirectAttributes.addAttribute("error", URLEncoder.encode("验证码不能为空", "utf-8"));
        	return "redirect:/login/admin_login";
        }else if(!yzm.equals(validate_yzm) ){
        	redirectAttributes.addAttribute("error", URLEncoder.encode("验证码不正确" + validate_yzm + "," + yzm, "utf-8"));
        	return "redirect:/login/admin_login";
        }
        if(user == null){
            return "redirect:/login/admin_login";
        }else if(!user.getLoginPwd().equals(login_pwd)){
            log.debug("# 密码错误 #");
            redirectAttributes.addAttribute("error", URLEncoder.encode("用户名或密码错误","utf-8"));
            return "redirect:/login/admin_login";
        }else{
            session.setAttribute("user",user);
            session.setAttribute("user_name", user.getLoginName());
            session.setAttribute("user_id",user.getUserId());
            session.setAttribute("user_type", user.getUserType());
            log.debug("# 登录成功# " + user.toString()); 
            if(user.getUserType() == 0) {
            	return "redirect:/admin/index";
            }else if(user.getUserType() == 1) {
            	return "redirect:/stu/index";
            }else{
            	return "redirect:/doct/index";
            }
        }

    }
    
    @RequestMapping("reg")
    public String reg(String error, Model model) throws UnsupportedEncodingException {
    	if(error != null) {
            model.addAttribute("error",URLDecoder.decode(error, "utf-8"));
    	}
    	return "/login/bbs_reg";
    }
    
    @RequestMapping("do_reg")
    public String do_reg(String login_name, String login_pwd, MultipartFile avatar, RedirectAttributes redirectAttributes) throws IllegalStateException, IOException {
    	
    	Users u1 = usersService.findByLoginName(login_name);
    	log.info("用户注册，检查是否已有用户名：" + u1);
    	if(u1 != null) {
            redirectAttributes.addAttribute("error", URLEncoder.encode("用户名已经存在！","utf-8"));
    		return "redirect:/login/reg";
    	}
    	
    	if(avatar.isEmpty()) {
            redirectAttributes.addAttribute("error", URLEncoder.encode("头像不能位空","utf-8"));
    		return "redirect:/login/reg";
    	}
    	String savePath = CmsConfig.getUploadPath();
    	String fileName = avatar.getOriginalFilename();
    	String ext = fileName.substring(fileName.lastIndexOf("."), fileName.length());
    	String saveName = System.currentTimeMillis() + ext;
    	File saveFile = new File(savePath + saveName);
    	log.info("save_name:" + saveFile.getAbsolutePath());
    	avatar.transferTo(saveFile);
    	Users user = new Users();
    	user.setLoginName(login_name);
    	user.setLoginPwd(login_pwd);
    	
    	user.setAvatar("/images/" + saveName);
    	usersService.insert(user);
    	return "redirect:/login/admin_login";
    }
    
    @RequestMapping("check_login")
    @ResponseBody
    public JsonResults checkLogin(HttpSession session) {
    	JsonResults res = new JsonResults();
    	Integer userId = (Integer) session.getAttribute("user_id");
    	log.info("用户是否登陆检查：" + userId);
    	return res; 
    }
    
    public String info(HttpSession session) {
    	Users user = (Users) session.getAttribute("user");
    	return user.toString();
    }
    
    @RequestMapping("demo")
    @ResponseBody
    public Users demo() {
    	Users user = new Users();
    	user.setUserId(1);
    	
    	return user;
    }
	
}
