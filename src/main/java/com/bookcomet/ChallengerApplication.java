package com.bookcomet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.bookcomet.filter.AuthFilter;

@SpringBootApplication
public class ChallengerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengerApplication.class, args);
	}

	
	@Bean
	public FilterRegistrationBean<AuthFilter> loggingFilter(){
	    FilterRegistrationBean<AuthFilter> registrationBean 
	      = new FilterRegistrationBean<>();
	        
	    registrationBean.setFilter(new AuthFilter());
	    registrationBean.addUrlPatterns("/*");
	    registrationBean.setOrder(2);
	        
	    return registrationBean;    
	}
}
