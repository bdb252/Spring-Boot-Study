package com.edu.springboot.mydiary;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarService {
	
	@Autowired
	IMyDiary dao;
	
	public List<DiaryPostResponse> getPostsByMonth(int year, int month) {
		String yearStr = String.valueOf(year);
		String monthStr = String.format("%02d", month);

		List<MyDiaryDTO> diaryList = 
				dao.selectByMonth(yearStr, monthStr);
		return diaryList.stream()
				.filter(dto -> dto.getSfile() != null && !dto.getSfile().isEmpty())
				.map(dto -> new DiaryPostResponse(
						dto.getPostdate().toString(), // e.g., "2025-08-04"
						dto.getSfile() // e.g., "abc123.jpg"
				)).collect(Collectors.toList());
	}

}
