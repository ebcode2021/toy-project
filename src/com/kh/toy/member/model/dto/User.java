package com.kh.toy.member.model.dto;

//Builder 패턴 공부용 . 클래스 사용 x
public class User {
	//객체 생성 패턴
	//1. 점층적 생성자 패턴
	// 생성자의 매개변수를 통해 객체의 속성을 초기화하고 생성하는 패턴
	// 단점 : 코드만 보고 생성자의 매개변수가 어떤 객체의 속성을 초기화하는 값인지 알기 어렵다. 가독성이 안좋다.
	
	//2. 자바빈 패턴 (모든 필드변수는 private로 처리하며, 게터세터. 스트링. 이퀄즈 가질수 있고. 해쉬도 오버라이드 가능하고.)
	// getter/setter
	// 장점 :한눈에 잘 보임
	// 단점 : public 메서드인 setter를 사용해 속성을 초기화하기 때문에, 변경불가능한 객체(immutable)를 만들 수 없다. 
	// *** string은 immutable하다. String 결합이 많을수록 메모리 엄청 차지한다. 
	//dto는 객체의 일종. 그래서 불변 객체가 되면 안됨. 다른 아이디나 패스워드를 넣을 수 있어야지.
	
	//3. 빌더패턴
	// 가독성과 변경불가능한 객체를 만들 수 없다는 단점을 극복한 디자인패턴
	// Effective Java에서 제시된 Builder패턴
	private String userId;
	private String password;
	private String grade;
	private String email;
	private String tell;
	
	//생성자는 private 처리하여 외부에서 직접 객체를 생성하는 것을 차단
	//객체의 생성은 오직 Factory class인 Builder를 통해서만 생성
	private User(UserBuilder builder) {
		this.userId = builder.userId;
		this.password = builder.password;
		this.email = builder.email;
		this.tell = builder.tell;
	}
	//UserBuilder를 반환
	public static UserBuilder builder() {
		return new UserBuilder();
	}
	
	
	//생성될 User 객체의 속성을 초기화하기 위한 값을 전달받고, User의 인스턴스를 생성해줄 inner class
	public static class UserBuilder {
		private String userId;
		private String password;
		private String grade;
		private String email;
		private String tell; //위의 자바빈 패턴처럼 생성자에 동일한 속성을 지니고 있어야함
		
		public UserBuilder userId(String userId) {
			this.userId = userId;
			return this; //userId 속성이 초기화된 userBuilder
		}
		
		public UserBuilder password(String password) {
			this.password = password;
			return this; //userId 속성이 초기화된 userBuilder
		}
		
		public UserBuilder email(String email) {
			this.email = email;
			return this; //userId 속성이 초기화된 userBuilder
		}
		
		public UserBuilder tell(String tell) {
			this.tell = tell;
			return this; //userId 속성이 초기화된 userBuilder
		}
		
		public User build() {
			return new User(this);
		}
		
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", grade=" + grade + ", email=" + email + ", tell="
				+ tell + "]";
	}
	
	
	
	
	
	
	
	
}
