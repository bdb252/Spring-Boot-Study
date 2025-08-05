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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.edu.springboot.jpaboard.BoardEntity;
import com.edu.springboot.jpaboard.BoardService;

import jakarta.servlet.http.HttpServletRequest;



@Controller
public class MainController {
	
	@Autowired
	BoardService boardService;

	@GetMapping("/")
	public String main() {
		return "main";
	}
	
    @GetMapping("/list.do")
    public String list(Model model) {

    	List<BoardEntity> rows = boardService.selectList();
    	
    	model.addAttribute("rows", rows);
    	return "boardList";
    }
    
    //작성(진입)
    @GetMapping("/write.do")
    public String write() {
    	return "boardWrite";
    }
    //작성(DB처리)
    @PostMapping("/writeProc.do")
    public String writeProc(BoardEntity boardTable) {
    	boardService.insertPost(boardTable);
    	return "redirect:list.do";
    }
    //열람
    @GetMapping("/view.do")
    public String view(Model model, HttpServletRequest req) {
    	//파라미터로 전달된 일련번호 저장
    	String idx = req.getParameter("idx");
    	//일련번호를 Long타입으로 변환 후 메서드 호출
    	Optional<BoardEntity> result = boardService.selectPost(Long.parseLong(idx));
    	//조회한 결과가 있는 경우
    	if(result.isPresent()) {
    		//실제 BoardEntity 객체를 인출
    		BoardEntity be = result.get();
    		//내용에 따라 줄바꿈 처리
    		be.setContents(be.getContents().replace("\r\n", "<br>"));
    		//Model에 저장
    		model.addAttribute("row", be);
    	}
    	else {
    		//만약 결과가 없다면 null 저장
    		model.addAttribute("row", null);
    	}
    	return "boardView";
    }
    //수정(진입) : 열람과 거의 비슷한 코드로 구성됨
    @GetMapping("/edit.do")
    public String edit(Model model, HttpServletRequest req) {
    	String idx = req.getParameter("idx");
    	Optional<BoardEntity> result = boardService.selectPost(Long.parseLong(idx));
    	if(result.isPresent()) {
    		BoardEntity be = result.get();
    		be.setContents(be.getContents().replace("\r\n", "<br>"));
    		model.addAttribute("row", be);
    	}
    	else {
    		model.addAttribute("row", null);
    	}
    	return "boardEdit";
    }
    //수정(DB처리)
    @PostMapping("/editProc.do")
    public String editProc(BoardEntity boardTable) {
    	boardService.updatePost(boardTable);
    	return "redirect:view.do?idx="+boardTable.getIdx();
    }
    //삭제
    @GetMapping("/delete.do")
    public String delete(BoardEntity boardTable) {
    	boardService.deletePost(boardTable.getIdx());
    	return "redirect:list.do";
    }
}   

