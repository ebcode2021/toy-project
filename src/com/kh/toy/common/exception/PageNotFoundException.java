package com.kh.toy.common.exception;

public class PageNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public PageNotFoundException() {
		//stackTrace를 비워준다. 콘솔창에 안찍히도록.
		this.setStackTrace(new StackTraceElement[0]);
	}
}
