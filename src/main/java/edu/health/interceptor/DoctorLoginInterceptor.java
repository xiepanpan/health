package edu.health.interceptor;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import edu.health.model.Users;

public class DoctorLoginInterceptor implements HandlerInterceptor {
	
	
	final static org.slf4j.Logger log = LoggerFactory.getLogger(DoctorLoginInterceptor.class);

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        log.warn("################ BBS 登陆 拦截器触发 ###################");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user != null && user.getUserId() != null && user.getUserType() == 2) {
            log.info("#医生已登录#" + user.toString());
            return true;
        }else {
            log.info("#医生未登录#");
            response.sendRedirect("/login/admin_login");
            return false;
        }
    }

}
