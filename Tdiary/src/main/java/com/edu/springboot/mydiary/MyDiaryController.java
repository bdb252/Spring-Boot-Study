package com.edu.springboot.mydiary;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import utils.MyFunctions;
import utils.PagingUtil;

@Controller
public class MyDiaryController {

	@Autowired
	IMyDiary dao;

	@RequestMapping("/mydiary/list.do")
	public String mydiaryList(Model model, HttpServletRequest req, ParameterDTO parameterDTO) {

		// 게시물의 갯수 카운트(검색어가 있는 경우 파라미터는 DTO에 자동으로 저장됨)
		int totalCount = dao.getTotalCount(parameterDTO);

		// 페이징을 위한 설정값(하드코딩)
		int pageSize = 7; // 페이지 당 출력할 게시물의 갯수
		int blockPage = 5; // 한 블럭당 출력할 페이지번호의 갯수

		/*
		 * 목록에 처음 진입할때는 페이지번호가 없으므로 1로 설정하고, 파라미터를 통해 전달된 페이지번호가 있다면 받은 후 정수로 변환한다.
		 */
		int pageNum = (req.getParameter("pageNum") == null || req.getParameter("pageNum").equals("")) ? 1
				: Integer.parseInt(req.getParameter("pageNum"));

		// 현재 페이지에 출력할 게시물의 구간을 계산한다.
		// pageNum이 1이면 1,2가 나옴.
		int start = (pageNum - 1) * pageSize + 1;
		int end = pageNum * pageSize;

		// 계산의 결과는 DTO에 저장
		parameterDTO.setStart(start);
		parameterDTO.setEnd(end);

		// View에서 게시물의 가상번호 계산을 위한 값을 Map에 저장
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("totalCount", totalCount);
		maps.put("pageSize", pageSize);
		maps.put("pageNum", pageNum);
		model.addAttribute("maps", maps);

		// DB에서 인출한 게시물의 목록을 영역에 저장
		ArrayList<MyDiaryDTO> lists = dao.listPage(parameterDTO);
		model.addAttribute("lists", lists);

		// 게시판 하단에 출력할 페이지번호를 String으로 저장한 후 영역에 저장
		String pagingImg = PagingUtil.pagingImg(totalCount, pageSize, blockPage, pageNum,
				req.getContextPath() + "/list.do?");
		model.addAttribute("pagingImg", pagingImg);

		// View로 포워드
		return "/mydiary/list";
	}

	// 입력1 : 작성페이지 매핑
	@GetMapping("/mydiary/write.do")
	public String boardList(Model model) {
		return "mydiary/write";
	}

	// 입력2 : 사용자가 작성한 값으로 입력 처리
	@PostMapping("/mydiary/write.do")
	public String boardList(Model model, HttpServletRequest req) {
		// request 내장객체를 통해 폼값 받음
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		// 매퍼 인터페이스 호출
		int result = dao.write(name, title, content);
		System.out.println("글쓰기결과:" + result);

		// 페이징 확인을 위한 더미데이터 100개 입력하기(테스트용)
//			for(int i=1 ; i<=100 ; i++) {
//				dao.write(name+"["+i+"]", title+"["+i+"]", content);
//			}

		// 글쓰기 완료 후 목록이로 이동
		return "redirect:list.do";
	}

//	// 열람 : 클릭시 전달되는 일련번호는 DTO를 통해 받은 후 사용
//	@GetMapping("/view.do")
//	public String boardView(Model model, BoardDTO boardDTO) {
//		// DTO를 view함수 호출시 전달
//		boardDTO = dao.view(boardDTO);
//		// 레코드 중 내용은 줄바꿈 처리 후 다시 저장
//		boardDTO.setContent(boardDTO.getContent().replace("\r\n", "<br/"));
//		model.addAttribute("boardDTO", boardDTO);
//		return "view";
//	}
//
//	// 수정1 : 기존 내용을 읽어와서 수정폼에 설정
//	@GetMapping("/edit.do")
//	public String boardEditGet(Model model, BoardDTO boardDTO) {
//		// 열람에서 사용한 메서드를 그대로 사용
//		boardDTO = dao.view(boardDTO);
//		model.addAttribute("boardDTO", boardDTO);
//		return "edit";
//	}
//
//	// 수정2 : 사용자가 입력한 내용을 전송하여 update 처리
//	@PostMapping("/edit.do")
//	public String boardEditPost(BoardDTO boardDTO) {
//		// 수정 후 결과는 int형으로 반환
//		int result = dao.edit(boardDTO);
//		System.out.println("글수정결과:" + result);
//		// 수정이 완료되면 열람페이지로 이동. 일련번호가 파라미터로 전달됨.
//		return "redirect:view.do?idx=" + boardDTO.getIdx();
//	}
//
//	// 삭제 : request 내장객체를 통해 폼값 받음
//	@PostMapping("/delete.do")
//	public String boardDeletePost(HttpServletRequest req) {
//		// 단일값을 인수로 전달
//		int result = dao.delete(req.getParameter("idx"));
//		System.out.println("글삭제결과:" + result);
//		// 삭제가 완료되면 목록으로 이동
//		return "redirect:list.do";
//	}
	
	// 파일 업로드 폼 매핑
//	@GetMapping("/mydiary/fileUpload.do")
//	public String fileUpload() {
//		return "fileUpload";
//	}
//	
//	//파일 업로드 처리
//	@PostMapping("/mydiary/uploadProcess.do")
//	public String uploadProcess(HttpServletRequest req, Model model, 
//			MyDiaryDTO myDiaryDTO) {
//		/*
//		사용자가 입력한 폼값은 DTO를 통해 한꺼번에 받아서 사용한다.
//		단, 파일의 경우에는 원본명과 변경된 이름을 별도로 추가해야 한다.
//		 */
//		try {
//			//물리적 경로 얻어오기
//			String uploadDir = 
//				ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
//			System.out.println("물리적 경로:"+uploadDir);
//			//Part 인스턴스를 통해 파일 업로드 처리
//			Part part = req.getPart("ofile");
//			String partHeader = part.getHeader("content-disposition");
//			System.out.println("partHeader="+partHeader);
//			String[] phArr = partHeader.split("filename=");
//			String originalFileName = phArr[1].trim().replace("\"", "");
//
//			if (!originalFileName.isEmpty()) {
//				part.write(uploadDir+File.separator+originalFileName);
//			}
//			
//			//UUID로 파일명 변경
//			String savedFileName = MyFunctions.renameFile(uploadDir, originalFileName);
//
//			//DB입력
//			//원본파일명과 저장된파일명을 DTO에 저장
//			myDiaryDTO.setOfile(originalFileName);
//			myDiaryDTO.setSfile(savedFileName);
//			//테이블에 insert 처리
//			int result = dao.insertFile(myDiaryDTO);
//			if(result==1)System.out.println("입력 성공");
//			else System.out.println("입력 실패");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//			System.out.println("업로드 실패");
//		}
//		//파일 목록으로 이동
//		return "redirect:fileUploadOk.do";
//	}
//	
//	//파일 목록
//	@GetMapping("/fileUploadOk.do")
//	public String fileUploadOk(Model model) {
//		//DB에 저장된 레코드를 인출한 후 View로 전달
//		model.addAttribute("fileRows", dao.selectFile());
//		return "fileUploadOk";
//	}

}
