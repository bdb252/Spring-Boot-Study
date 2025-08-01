package com.edu.springboot;

import java.io.File;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImageController {
	
	@GetMapping("/image-to-base64.do")
	public Map<String, String> convertImageToBase64(
			@RequestParam("imageName") String imageName){
		String encodedString = null;
		Map<String, String> map = new HashMap<>();
		
		try {
			String uploadDir = ResourceUtils.getFile("classpath:static/").toPath().toString();
			System.out.println("물리적 경로: "+uploadDir);
			String imagePath = uploadDir + File.separator + imageName;
			
			File file = new File(imagePath);
			
			byte[] fileContent = Files.readAllBytes(file.toPath());
			encodedString = Base64.getEncoder().encodeToString(fileContent);
			System.out.println(encodedString);
			
			//Flask 서버 설정(이미지 이름ㅇ르 URL 파라미터로 전달)
			String flaskUrl = "http://127.0.0.1:5000/getDecodeImage.fk"
					+ "?imageName="+imageName;
			
			RestTemplate restTemplate = new RestTemplate();
			String response = restTemplate.postForObject(flaskUrl, encodedString, String.class);
			
			System.out.println("flask 응답:" +response);
			response = response.replace("\\", "").replace("\n", "");
			map.put("result", "success");
			map.put("imageName", imageName);
			map.put("Flask", response);
		}
		catch (Exception e) {
			e.printStackTrace();
			encodedString = "Error: "+ e.getMessage();
			map.put("result", "error");
		}
		
		return map;
	}
}
