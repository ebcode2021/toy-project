package com.kh.toy.common.code;

public enum Config {
	//DOMAIN("https://pclass.ga"), // 이건 배포용 도메인
	DOMAIN("http://localhost:9090"),
	COMPANY_EMAIL("eb2021@gmail.com"),
	SMTP_AUTHENTICATION_ID("ebcode2021@gmail.com"),
	SMTP_AUTHENTICATION_PASSWORD("eunbi0914"),
	//UPLOAD_PATH("C:\\CODE\\upload\\"); //이건 배포 경로
	UPLOAD_PATH("C:\\CODE\\upload\\");
	public final String DESC;
	
	Config(String desc){
		this.DESC = desc;
	}
}
