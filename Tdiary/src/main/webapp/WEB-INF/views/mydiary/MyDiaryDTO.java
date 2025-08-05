package mydiary;

import java.sql.Date;

import lombok.Data;

@Data
public class MyDiaryDTO {
	private String diary_idx;
	private String member_idx;
	private Object ofile;
	private String sfile;
	private Date postdate;
	private String description;
	private float temperature; 
	private float humidity; 
	private float sunlight; 	
}
