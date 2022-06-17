package com.bookcomet.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

	private String msgError;
	private HttpStatus httpStatus;
	
	public BusinessException(String msgError, HttpStatus httpStatus) {
		super();
		this.msgError = msgError;
		this.httpStatus = httpStatus;
	}

	public String getMsgError() {
		return msgError;
	}

	public void setMsgError(String msgError) {
		this.msgError = msgError;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	

}
