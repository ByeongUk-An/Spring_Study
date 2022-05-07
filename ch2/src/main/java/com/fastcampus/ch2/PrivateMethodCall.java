package com.fastcampus.ch2;

import java.lang.reflect.Method;

public class PrivateMethodCall {
	public static void main(String[] args) throws Exception {
//		Hello hello = new Hello();
//		hello.main(); // private이라서 외부 호출 불가
		
		// Reflection API를 사용 - 클래스 정보를 얻고 다룰 수 있는 강력한 기능제공
		// java.lang.reflect패키지를 제공
		
		// Hello클래스의 Class객체(클래스의 정보를 담고 있는 객체)를 얻어온다.
		Class helloClass = Class.forName("com.fastcampus.ch2.Hello");
		
		// Class객체가 가진 정보로 객체 생성
		Hello hello = (Hello)helloClass.newInstance();
		
		// main이라는 메서드 정보 가져오기
		Method main = helloClass.getDeclaredMethod("main");
		
		// private인 main()을 호출 가능하게 한다.
		main.setAccessible(true);  
		
		main.invoke(hello); //hello.main(); 
	}

}
