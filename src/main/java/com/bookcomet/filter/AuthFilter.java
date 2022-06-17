package com.bookcomet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.bookcomet.exception.BusinessException;

@Component
@Order(2)
public class AuthFilter implements Filter {

	
	@Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        
        boolean auth = Boolean.valueOf(req.getParameter("auth"));
        
        if(!auth) {
        	((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "The auth value is not valid.");
        	return;
        }
        
        chain.doFilter(request, response);
        
        
        
 
    }
}
