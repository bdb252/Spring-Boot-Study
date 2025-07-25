package com.edu.springboot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class MemberDTO {
	
	private String id;
	private String name;
	private String pass;
	private String regidate;
}
