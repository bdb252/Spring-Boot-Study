package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//이 클래스를 컨트롤러로 설정
@Controller
public class AuthController {
	//GET 방식으로 요청명 매핑
	@GetMapping("/memberRegist.do")
	public String memberRegist() {
		//view의 경로를 반환하여 JSP로 포워드
		return "member/regist";
	}
	@PostMapping("/registProcess.do")
	public String registProcess(AuthDTO authDTO) {
		//view의 경로를 반환하여 JSP로 포워드
		return "member/registProcess";
	}
	
	//로그인 페이지
	@GetMapping("/memberLogin.do")
	public String memberLogin() {
		return "member/login";
	}
	//로그인 처리
	@PostMapping("/loginProcess.do")
	public String loginProcess(AuthDTO authDTO) {
		//모든 폼값은 커맨드객체인 DTO를 통해 한번에 받는다.
		return "member/loginProcess";
	}
}
