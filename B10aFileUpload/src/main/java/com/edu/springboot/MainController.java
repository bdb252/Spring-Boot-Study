package com.edu.springboot;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import utils.MyFunctions;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	//파일 업로드 폼 매핑
	@GetMapping("/fileUpload.do")
	public String fileUpload() {
		return "fileUpload";
	}
	
	//파일 업로드 처리
	@PostMapping("/uploadProcess.do")
	public String uploadProcess(HttpServletRequest req, Model model) {
		try {
			//업로드할 디렉토리의 물리적 경로를 얻어온다.
			String uploadDir = 
				ResourceUtils.getFile("classpath:static/uploads/").toPath().toString();
			System.out.println("물리적 경로:"+uploadDir);
			
			//전송된 첨부파일을 통해 Part 객체를 생성한다.
			Part part = req.getPart("ofile");
			//파일명 확인을 위해 헤더값을 얻어온다.
			String partHeader = part.getHeader("content-disposition");
			System.out.println("partHeader="+partHeader);
			//헤더값에서 파일명을 추출하기 위해 문자열을 Split한다.
			String[] phArr = partHeader.split("filename=");
			//따옴표를 제거해서 원본파일명을 추출한다.
			String originalFileName = phArr[1].trim().replace("\"", "");
			
			//전송된 파일이 있다면 서버에 저장한다.
			if (!originalFileName.isEmpty()) {
				part.write(uploadDir+File.separator+originalFileName);
			}
			
			//서버에 저장된 파일의 파일명을 UUID로 생성된 문자열로 변경한다.
			String savedFileName = MyFunctions.renameFile(uploadDir, originalFileName);
			
			model.addAttribute("originalFileName",originalFileName);
			model.addAttribute("savedFileName",savedFileName);
			model.addAttribute("title",req.getParameter("title"));
			model.addAttribute("cate",req.getParameter("cate"));
		}
		catch (Exception e) {
			System.out.println("업로드 실패");
		}
		
		return "fileUploadOk";
	}
}

