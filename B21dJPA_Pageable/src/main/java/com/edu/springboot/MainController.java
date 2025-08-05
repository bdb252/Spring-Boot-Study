package com.edu.springboot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.springboot.jpa.Member;
import com.edu.springboot.jpa.MemberRepository;
import com.edu.springboot.jpa.MemberService;


@Controller
public class MainController {
	
	@Autowired
	MemberService memberService;

	@GetMapping("/")
	public String main() {
		return "main";
	}
	
    //이름으로 like 검색
    @GetMapping("/selectByNameLike.do")
    public String selectByNameLike(@RequestParam("name") String pname, 
    		@RequestParam("page") String page, Model model) {
    	//파라미터로 전달된 검색어와 페이지 번호 확인
    	System.out.println("(검색어) : "+pname);
    	System.out.println("(페이지) : "+page);
    	
    	//와일드카드 %가 뒤에만 있으므로 pname으로 시작하는 문자열 검색
    	String name = pname + "%";
    	//Sort 빈을 사용해서 id를 내림차순 정렬
    	Sort sort = Sort.by(Sort.Order.desc("id"));
    	//Pageable에서는 페이지번호가 0부터 시작이므로 -1해서 넘겨줘야 한다.
    	int pageNum = Integer.parseInt(page) - 1;
    	//현재 페이지에 출력할 레코드를 5개로 인출하고 정렬을 적용
    	Pageable pageable = PageRequest.ofSize(5).withPage(pageNum).withSort(sort);
    	//페이지에 대한 설정과 검색어를 인수로 전달해서 쿼리문 실행
    	Page<Member> result = memberService.selectNameLike(name, pageable);
    	//출력할 레코드를 list로 인출
    	List<Member> content = result.getContent();
    	//레코드 개수
    	long totalElements = result.getTotalElements();
    	//전체페이지수
    	int totalPages = result.getTotalPages();
    	//페이지 당 출력수
    	int size = result.getSize();
    	//현재 페이지번호. 0부터 시작이므로 +1해줌
    	int pageNumber = result.getNumber()	+ 1;
    	//컨텐츠 개수
    	int numberOfElements = result.getNumberOfElements();
    	
    	model.addAttribute("members", content);
    	model.addAttribute("totalElements", totalElements);
    	model.addAttribute("totalPages", totalPages);
    	model.addAttribute("size", size);
    	model.addAttribute("pageNumber", pageNumber);
    	model.addAttribute("numberOfElements", numberOfElements);

    	return "member_list";
    }
}   

