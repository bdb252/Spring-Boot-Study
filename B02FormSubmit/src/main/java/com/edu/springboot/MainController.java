package com.edu.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

/*
JSP에서는 컨트롤러로 정의하기 위해 Servlet클래스를 정의한 후 매핑정보를 입력했다.
Spring Boot에서는 아래와 같이 @Controller 어노테이션을 부착하면 자동으로
컨트롤러 클래스로 정의된다.
또한 사용을 위해 별도의 인스턴스를 생성할 필요 없이 스프링 컨테이너가 시작시 자동으로 
자바빈(인스턴스)를 생성해준다.
 */
@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	/*
	컨트롤러에 매핑된 각 메서드에서 String을 반환하면 
	application.properties의 JSP 설정에 의해 View의 경로가 조립된다.
	"Prefix경로" + 메서드의반환값 + "Suffix경로"에 해당하는 JSP 파일을 찾아 포워드한다.
	 */
	@RequestMapping("/index.do")
	public String index() {
		//index.jsp 파일을 찾아 포워드한다.
		return "index";
	}
	
	@RequestMapping("/sub.do")
	public String sub() {
		/*
		만약 views 하위에 별도의 폴더가 필요하다면 이와 같이 슬러쉬로
		구분하여 작성하면 된다. sub폴더 하위의 sub.jsp를 찾아 포워드한다. */
		return "sub/sub";
	}
	
	
	/*
	1. request 내장객체를 통해 폼값 받기
	: JSP에서 사용하던 방식으로 request 내장객체의 getParameter() 메서드를 실행한다.
	 */
	@RequestMapping("/form1.do")
	public String form1(HttpServletRequest httpservletRequest, Model model) {
		//폼값을 받아 변수에 저장
		String name = httpservletRequest.getParameter("name");
		String age = httpservletRequest.getParameter("age");
		//Model 인스턴스를 통해 영역에 변수를 저장
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		//뷰의 경로를 String형으로 반환하면 포워드 됨.
		return "form/submit1";
	}
	
	/*
	2. @RequestParam 어노테이션을 통해 파라미터를 받아서 변수에 저장
	 */
	@RequestMapping("/form2.do")
	public String form2(@RequestParam("name") String name, @RequestParam("age") String age,
			Model model) {
		
		//Model객체에 데이터 저장
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		//View의 경로 반환
		return "form/submit2";
	}
	
	/*
	3. 커맨드 객체를 통해 파라미터를 한번에 받아 DTO에 저장한다.
	: 사용시 매개변수명은 클래스명의 첫글자를 소문자로 바꾼 이름을 사용해야 한다.
	  만약 매개변수명을 변경하고 싶다면 @ModelAttribute 어노테이션을 사용해야 한다. */
	@RequestMapping("/form3.do")
	public String form3(PersonDTO personDTO) {
		/*
		요청시 사용되는 파라미터명과 DTO의 멤버변수명을 동일하게 정의하면
		개수에 상관없이 setter를 통해 한꺼번에 폼값을 받아 저장할 수 있다.
		또한 model 인스턴스에 자동으로 저장되므로 View로 전달하기 위해 
		별도로 저장 로직을 작성할 필요가 없다. */
		return "form/submit3";
	}
	
	/*
	4. PathVariable 어노테이션으로 경로 형태로 전달되는 파라미터를 순서대로 변수에 저장한다.
	단 이 경우 파라미터를 경로로 인식하므로 정적리소스 사용시 경로 설정에 주의해야 한다.
	 */
	@RequestMapping("/form4/{name}/{age}")
	public String form4(@PathVariable("name") String name, @PathVariable("age") String age,
			Model model) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		return "form/submit4";
	}
	
	@RequestMapping("/memberRegist.do")
	public String memberRegist(PersonDTO personDTO) {
		return "member/regist";
	}
	@RequestMapping("/memberLogin.do")
	public String memberLogin(PersonDTO personDTO) {
		return "member/login";
	}
}

