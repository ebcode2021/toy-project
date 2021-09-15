package com.kh.toy.common.http;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RequestParams {
	private Map<String,String> params = new HashMap<String,String>();
	
	private RequestParams(RequestParamsBuilder builder) {
		this.params = builder.params; //초기화해주는 역할
	}
	
	public static RequestParamsBuilder builder() {
		return new RequestParamsBuilder();
	}
	
	public static class RequestParamsBuilder { //해당 인스턴스의 클래스들을 만들어주는게 팩토리 메서드. 빌더를 통해서만 생성할 수 있게끔.
		private Map<String,String> params = new LinkedHashMap<String,String>(); //순서 보장할려고
		
		public RequestParamsBuilder param(String name, String value) {
			params.put(name,value);
			return this;
		}
		
		public RequestParams build() {
			return new RequestParams(this);
		}
		
	}

	public Map<String, String> getParams() {
		return params;
	}
	
	
}
