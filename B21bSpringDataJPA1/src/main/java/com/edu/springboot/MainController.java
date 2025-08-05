package com.edu.springboot;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.springboot.jpa.Member;
import com.edu.springboot.jpa.MemberService;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}

	@Autowired
	MemberService memberService;
	
	//입력
	@GetMapping("/insert.do")
	public String insert(@RequestParam("username") String name, Model model) {
		//빌더패턴으로 인스턴스를 생성한 후 멤버변수를 초기화한다.
		Member member = Member.builder()
				.username(name)
				.createDate(LocalDate.now())
				.build();
		//입력처리
		Member result = memberService.insert(member);
		model.addAttribute("member", result);
		return "insert";
	}
	
	//개별조회
	@GetMapping("/select.do")
	public String select(@RequestParam("id") Long p_id, Model model) {
		Optional<Member> result = memberService.select(p_id);
		if(result.isPresent()) {
			model.addAttribute("member", result.get());
		}
		else {
			model.addAttribute("member", null);
		}
		return "select";
	}
	
	//전체조회
	@GetMapping("/selectAll.do")
	public String selectAll(Model model) {
		List<Member> result = memberService.selectAll();
		model.addAttribute("members", result);
		return "selectAll";
	}
	
	//삭제
	@GetMapping("/delete.do")
	public String delete(@RequestParam("id")Long p_id) {
		memberService.delete(p_id);
		return "delete";
	}
	//수정
	@GetMapping("/update.do")
	public String update(Member member, Model model) {
		member.setCreateDate(LocalDate.now());
		Member result = memberService.update(member);
		model.addAttribute("members", result);
		return "update";
	}
}

