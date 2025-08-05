package com.edu.springboot.mydiary;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IMyDiary {
	//목록 : 게시물 갯수 카운트
	public int getTotalCount(ParameterDTO parameterDTO);
	//목록 : 한페이지에 출력할 게시물 인출
	public ArrayList<MyDiaryDTO> listPage(ParameterDTO parameterDTO);
	//작성 : 폼값의 변수명을 어노테이션을 통해 변경한 후 매퍼에서 사용
	public int write(@Param("_name") String name,
			@Param("_title") String title,
			@Param("_content") String content);
	//열람 
	public MyDiaryDTO view(MyDiaryDTO myDiaryDTO);
	//수정
	public int edit(MyDiaryDTO myDiaryDTO);
	//삭제
	public int delete(String idx);
}
