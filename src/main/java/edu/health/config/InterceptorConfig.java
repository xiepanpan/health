package edu.health.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import edu.health.interceptor.*;
import edu.health.util.PropertiesUtil;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminLoginInterceptor()).addPathPatterns("/admin/**");
        registry.addInterceptor(new StudentLoginInterceptor()).addPathPatterns("/stu/**");
        registry.addInterceptor(new DoctorLoginInterceptor()).addPathPatterns("/doct/**");
	}
	
	/**
	 * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("=======================================================");
        System.out.println("===================资源句柄=========================");
        PropertiesUtil.readProperties("application.properties");
        String uploadPath = PropertiesUtil.getProperty("upload.uploadPath");
        System.out.println("===================="+ uploadPath+ "============================");
        registry.addResourceHandler("/images/**").addResourceLocations("file:"+ uploadPath);
    }

}
