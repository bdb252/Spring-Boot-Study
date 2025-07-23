package com.edu.springboot;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.edu.springboot.jdbc.IMemberService;
import com.edu.springboot.jdbc.MemberDTO;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@Autowired
	IMemberService dao;
	
	//회원 목록
	@RequestMapping("/list.do")
	public String member2(Model model) {
		model.addAttribute("memberList", dao.select());
		return "list";
	}
	
	//회원 등록
	@GetMapping("/regist.do")
	public String member1() {
		return "regist";
	}
	//글쓰기 처리하기
	@PostMapping("/regist.do")
	public String member6(HttpServletRequest req) {
		//전송된 폼값은 request 내장객체를 통해 개별적으로 전달받는다.		
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");
		
		//함수 호출시에도 개별적으로 전달해야 한다.
		int result = dao.insert(id, pass, name);
		if(result == 1) System.out.println("입력되었습니다.");
		return "redirect:list.do";
	}
	
	//회원 수정
	@RequestMapping(value="/edit.do", method=RequestMethod.GET)
	public String member3(HttpServletRequest req, MemberDTO memberDTO, Model model) {
		memberDTO = dao.selectOne(req.getParameter("id"));
		model.addAttribute("dto", memberDTO);
		return "edit";
	}
	
	@RequestMapping(value="/edit.do", method=RequestMethod.POST)
	public String member7(HttpServletRequest req) {
		//request 내장객체로 id를 받는다.
		String id = req.getParameter("id");
		String pass = req.getParameter("pass");
		String name = req.getParameter("name");

		//받은 폼값은 Map에 저장
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("m_id", id);
		paramMap.put("m_pass", pass);
		paramMap.put("m_name", name);

		//함수 호출시 Map을 인수로 전달
		int result = dao.update(paramMap);
		if(result == 1) System.out.println("수정되었습니다.");
		return "redirect:list.do";
	}
	
	//회원 삭제
	@RequestMapping("/delete.do")
	public String member4(HttpServletRequest req) {
		//request 내장객체로 받은 후 전달
		String id = req.getParameter("id");
		int result = dao.delete(id);
		if(result == 1) System.out.println("삭제되었습니다.");
		return "redirect:list.do";
	}
}
