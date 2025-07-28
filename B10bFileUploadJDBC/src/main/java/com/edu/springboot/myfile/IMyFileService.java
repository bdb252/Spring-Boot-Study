package com.edu.springboot.myfile;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/*
컨트롤러와 Mapper(XML파일) 사이에서 매개 역할을 하는 인터페이스 생성.
Mapper에서 namespace로 연결한다.
 */
@Mapper
public interface IMyFileService {
	//첨부파일을 DB에 입력
	public int insertFile(MyFileDTO myfileDTO);
	//파일 목록
	public List<MyFileDTO> selectFile();
}
