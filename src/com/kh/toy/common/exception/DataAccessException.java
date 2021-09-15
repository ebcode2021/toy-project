package com.kh.toy.common.exception;

import com.kh.toy.common.code.ErrorCode;

// 예외처리가 강제되지않는 UnCheckedException

public class DataAccessException extends HandlableException { //이거 상속해서 web.xml에서도 잡힘

	private static final long serialVersionUID = 1L;

	public DataAccessException(Exception e) {
		super(ErrorCode.DATABASE_ACCESS_ERROR,e);
	}
	
}
