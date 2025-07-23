package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	//root경로에 대한 매핑
	@RequestMapping("/")
	public String main() {
		return "main";
	}

	//글쓰기 페이지에 대한 매핑
	@RequestMapping("/write.do")
	public String insert1() {
		return "write";
	}
	
	/*
	   Validator 인터페이스를 통한 폼값의 유효성 검증
	   Spring에서 사용하는 커맨드객체를 전송된 폼값을 한꺼번에 받아 Model에
	   저장해준다. 만약 저장되는 속성명을 변경하고 싶다면 @ModelAttribute를
	   사용하면된다. 
	   */
	@RequestMapping("/writeAction1.do")
	public String writeActoin1(@ModelAttribute("dto") BoardDTO boardDTO, 
			BindingResult result) {
		//폼값 검증에 성공한 경우 포워드할 View의 경로 설정
		String page = "result";
		System.out.println(boardDTO);
	
		//폼값 검증을 위한 인스턴스 생성
		BoardValidator validator = new BoardValidator();
		/*
		폼값 전체를 저장한 DTO 및 검증결과 전달을 위한 객체를 인수로 전달한다.
		여기서 validate()를 호출하여 검증을 진행한다.
		 */
		validator.validate(boardDTO, result);
		
		//폼값 검증에 실패한 경우 if문의 블럭이 실행됨
		if(result.hasErrors()) {
			//실패한 경우 재입력을 받기 위해 쓰기페이지로 포워드
			page = "write";
			System.out.println("검증 실패 반환값1 : "+ result.toString());
			System.out.println("========================================");
			
			//제목 검증에 실패한 경우 개별 메시지 출력
			if(result.getFieldError("title")!=null) {
				//getCode() : 우리가 지정한 에러객체명을 반환
				System.out.println("제목 검증1(에러코드): "
						+result.getFieldError("title").getCode());
			}
			//내용 검증에 실패한 경우 개별 메시지 출력
			if(result.getFieldError("content")!=null) {
				//getDefaultMessage() : 우리가 지정한 디폴트메시지 반환
				System.out.println("내용 검증1(디폴트메시지): "
						+result.getFieldError("content").getDefaultMessage());
			}
		}
		
		return page;
	}
	
	/*
	어노테이션을 통한 검증이므로 폼값 저장을 위한 VO 객체에 @Validation을 추가해야 한다.
	 */
	@RequestMapping("/writeAction2.do")
	public String writeActoin2(@ModelAttribute("dto") @Validated BoardVO boardVO, 
			BindingResult result) {
		String page = "result";
		System.out.println(boardVO);
	
		//검증을 위한 클래스를 별도로 정의할 필요가 없으므로 주석처리
//		BoardValidator validator = new BoardValidator();
//		validator.validate(boardDTO, result);
		
		//폼값 검증에 문제가 생긴 경우 if문의 블럭이 실행된다.
		if(result.hasErrors()) {
			page = "write";
			System.out.println("검증 실패 반환값2 : "+ result.toString());
			System.out.println("========================================");
			
			if(result.getFieldError("title")!=null) {
				System.out.println("제목 검증2(에러코드): "
						+result.getFieldError("title").getCode());
			}
			if(result.getFieldError("content")!=null) {
				System.out.println("내용 검증2(디폴트메시지): "
						+result.getFieldError("content").getDefaultMessage());
			}
		}
		
		return page;
	}
}