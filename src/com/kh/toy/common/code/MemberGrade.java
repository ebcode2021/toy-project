package com.kh.toy.common.code;

// enum(enumerated type) : 열거형
// 서로 연관된 상수들의 집합
// 서로 연관된 상수들을 하나의 묶음으로 다루기 위한 enum만의 문법적 형식과 메서드를 제공

public enum MemberGrade {
	// 회원 등급 코드 ME00 은 코드 info가 '일반'이고 연장가능횟수가 1회
	// enum은 내부적으로 class
	// ME00("일반",1) -> public static final MemberGrade ME00 = new MemberGrade("일반",1);
	ME00("일반", "user", 1),
	ME01("성실", "user", 2),
	ME02("우수", "user", 3),
	ME03("VIP", "user", 4),
	
	AD00("super", "admin", 9999), //슈퍼 관리자
	AD01("member", "admin", 9999), //회원 관리자 등급 코드
	AD02("board", "admin", 9999); //게시판 관리자 등급 코드
	
	
	public final String DESC; // 혹은 public final String desc 식으로 부를 수도 있음.
	public final String ROLE;
	public final int EXTENDABLE_CNT;
	
	private MemberGrade(String desc, String role,int extendableCnt) {
		this.DESC = desc;
		this.ROLE = role;
		this.EXTENDABLE_CNT = extendableCnt;
	}
	
}
