package com.edu.springboot.mydiary;

import java.sql.Date;

import lombok.Data;

@Data
public class MyDiaryDTO {
	private Long diary_idx;
	private Long member_idx;
	private Object ofile;
	private String sfile;
	private Date postdate;
	private String description;
	private float temperature; 
	private float humidity; 
	private float sunlight; 	
	private float height;
	private int fruit;
}
