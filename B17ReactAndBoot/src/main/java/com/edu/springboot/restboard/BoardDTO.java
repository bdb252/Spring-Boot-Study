package com.edu.springboot.restboard;

import java.sql.Date;

import lombok.Data;

//board 테이블과 동일하게 생성
@Data
public class BoardDTO {
	private String num;
	private String title;
	private String content;
	private String id;
	private Date postdate;
	private String visitcount;
}
