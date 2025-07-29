package com.edu.springboot;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}

	/*
	컨트롤러에서 매핑을 위한 모든 메서드에 @ResponseBody 어노테이션을 추가하면
	반환되는 값을 웹브라우저에 출력한다. 만약 이 어노테이션이 없다면 View의 경로가 되므로
	포워드된다. */
	@RequestMapping("/json.do")
	@ResponseBody /* 이 어노테이션이 없으면 json문자열이 view의 경로가 되는것.	*/
	public String json() {
		/*
		외부 라이브러리인 simple-json을 통해 사용할 수 있는 클래스로
		JSON배열과 JSON객체를 만들어준다. 사용법은 List, Map 컬렉션과
		완전히 동일하다. */
		//배열은 List와 동일함
		JSONArray arr = new JSONArray();
		//객체는 Map과 동일함
		JSONObject obj = new JSONObject();
		
		//배열에 데이터 추가
		arr.add("손오공");
		arr.add("저팔계");
		arr.add("사오정");
		
		//객체에 데이터 추가
		obj.put("서유기", arr);
		obj.put("result", 1);
		
		//JSON을 String 형식으로 웹브라우저에 출력
		return obj.toJSONString();
	}

	@RequestMapping("/jsonQuiz.do")
	@ResponseBody
	public String jsonQuiz() {
		JSONObject obj1 = new JSONObject();
		JSONObject obj2 = new JSONObject();
		JSONObject obj3 = new JSONObject();

		//고딩친구
		JSONArray arr_fhcc = new JSONArray();
		arr_fhcc.add("유비");
		arr_fhcc.add("관우");
		arr_fhcc.add("장비");		
		JSONArray arr_fhcl = new JSONArray();
		arr_fhcl.add("이몽룡");
		arr_fhcl.add("성춘향");

		obj3.put("circle", arr_fhcc);
		obj3.put("class", arr_fhcl);
		
		//중딩친구
		JSONArray arr_fm = new JSONArray();
		arr_fm.add("손오공");
		arr_fm.add("저팔계");
		arr_fm.add("사오정");

		//중딩, 고딩 통합
		obj2.put("mid", arr_fm);
		obj2.put("high", obj3);
		
		//취미
		JSONArray arr_h = new JSONArray();
		arr_h.add("자전거");
		arr_h.add("수영");
		arr_h.add("축구");
		obj1.put("hobby", arr_h);

		//결과
		obj1.put("name", "홍길동");
		obj1.put("age", 99);
		obj1.put("friend", obj2);		
		
		return obj1.toJSONString();
	}
}

