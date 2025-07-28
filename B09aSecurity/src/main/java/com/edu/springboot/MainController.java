package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	//root경로 : 인증 없이 누구든 접속 가능
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	//누구든 접속 가능
	@RequestMapping("/guest/index.do")
	public String welcome1() {
		return "guest";
	}
	//ADMIN, USER 중 하나의 권한만 있으면 접속 가능
	@RequestMapping("/member/index.do")
	public String welcome2() {
		return "member";
	}
	//ADMIN 권한만 접속 가능
	@RequestMapping("/admin/index.do")
	public String welcome3() {
		return "admin";
	}
}
