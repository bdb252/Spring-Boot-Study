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
	//작성
	public int write(MyDiaryDTO myDiaryDTO);
	//열람 
	public MyDiaryDTO view(MyDiaryDTO myDiaryDTO);
	//수정
	public int edit(MyDiaryDTO myDiaryDTO);
	//삭제
	public int delete(String idx);
}
