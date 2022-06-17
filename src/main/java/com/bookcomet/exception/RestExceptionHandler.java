package com.bookcomet.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
      
   @ExceptionHandler(BusinessException.class)
   public ResponseEntity<Object> handleNodataFoundException2(
		   BusinessException ex, WebRequest request) {

       Map<String, Object> body = new LinkedHashMap<>();
       body.put("timestamp", LocalDateTime.now());
       body.put("message", ex.getMsgError());

       return new ResponseEntity<>(body, ex.getHttpStatus());
   }
   
}