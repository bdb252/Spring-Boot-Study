package com.edu.springboot.mydiary;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DiaryPostResponse {
	// yyyy-MM-dd (db의 postdate)
    private String date;
    // sfile 값 (서버 파일명)
    private String imageUrl;
}
