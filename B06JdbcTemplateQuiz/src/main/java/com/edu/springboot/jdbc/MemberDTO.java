package com.edu.springboot.jdbc;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String id;
	private String pass;
	private String name;	
	private String regidate;
	//검색기능
	private String searchField;
	private String searchKeyword;
}
