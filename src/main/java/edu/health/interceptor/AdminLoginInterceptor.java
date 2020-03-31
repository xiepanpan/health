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

public class AdminLoginInterceptor implements HandlerInterceptor {
	
	
	final static org.slf4j.Logger log = LoggerFactory.getLogger(AdminLoginInterceptor.class);

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {

        log.warn("################ Admin 拦截器触发 ###################");
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        if (user != null && user.getUserId() != null) {
            log.info("#用户已登录#" + user.toString());

            if(user.getUserType() != 0){
                log.info("# 非管理员用户无法登陆 #");
                response.sendRedirect("/login/admin_login?error=" + URLEncoder.encode("非管理员用户无法登陆","utf-8"));
                return false;
            }
            log.info("#用户拥有管理员权限#");
            return true;
        }else {
            log.info("#用户未登录#");
            response.sendRedirect("/login/admin_login");
            return false;
        }
    }

}
