package com.edu.springboot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
	//로그인폼
	private String id;
	private String passwd;
	//회원가입폼
	private String pass1, pass2;
	private String name;
	private String sex;	
	private String email1, email2;
	private String mailing;
	private String zipcode;
	private String addr1, addr2;
	private String phone1, phone2, phone3;
	private String sms;
	private String etc_no1, etc_no2;
}
