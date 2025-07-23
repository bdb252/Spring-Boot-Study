package com.edu.springboot.bean1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//데이터 저장 기능만 있는 일반적인 DTO 클래스(VO 라고도 함)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	//멤버변수
	private String name;
	private int age;
	private Notebook notebook;
	
	/*
	toString() 메서드 오버라이딩
	: 인스턴스 변수를 직접 print해서 클래스의 멤버변수를 출력할 수 있다.
	 */
	@Override
	public String toString() {
		return "Person [name = " + name + "age = " + age+ "notebook = " + notebook + "]";
	}
}
