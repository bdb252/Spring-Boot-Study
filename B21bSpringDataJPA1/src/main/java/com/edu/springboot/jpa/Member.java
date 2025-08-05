package com.edu.springboot.jpa;

import lombok.Data;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

//게터, 세터, toString 등을 추가
@Data
//인수생성자
@AllArgsConstructor
//기본생성자
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/*
빌더패턴을 적용하여 메서드 체이닝을 통해 멤버변수를 초기화할 수 있는 기능을 제공한다. */
@Builder
//엔티티 설정
@Entity(name = "JPAMEMBER01")
public class Member {
	//PK, 시퀀스 설정
	@Id
	@GeneratedValue
	private Long id;
	private String username;
	//name 속성에 지정한 이름으로 컬럼 생성
	@Column(name="create_date")
	private LocalDate createDate;
}
