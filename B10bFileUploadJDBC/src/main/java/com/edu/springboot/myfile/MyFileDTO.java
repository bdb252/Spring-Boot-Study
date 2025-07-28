package com.edu.springboot.myfile;

import java.sql.Date;

import lombok.Data;

@Data
public class MyFileDTO {
	private String idx;
	private String title;
	private String cate;
	private Object ofile;
	private String sfile;
	private Date postdate;
}
