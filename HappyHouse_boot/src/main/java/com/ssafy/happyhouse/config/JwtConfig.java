package com.ssafy.happyhouse.config;

import java.util.Arrays;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.interceptor.JwtInterceptor;

@Configuration
@MapperScan(basePackages = "com.ssafy.happyhouse.model.mapper")
@EnableWebMvc
public class JwtConfig implements WebMvcConfigurer {
	
	@Autowired
	private JwtInterceptor jwtInterCeptor;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
//		System.out.println("CORS Setting");
//		default 설정.
//		Allow all origins.
//		Allow "simple" methods GET, HEAD and POST.
//		Allow all headers.
//		Set max age to 1800 seconds (30 minutes).
		registry.addMapping("/**")
			.allowedOrigins("*")
			.allowedMethods("*")
			.maxAge(6000);
	}
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterCeptor).addPathPatterns("/user/**").addPathPatterns("/apt/**").addPathPatterns("/notice/**").addPathPatterns("/news/**") //기본 적용 경로
		.excludePathPatterns(Arrays.asList("/**/*.do/**"));
	}


}
