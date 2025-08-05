package com.edu.springboot.mydiary;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ICalendar {
	List<MyDiaryDTO> selectByMonth(
			@Param("year") String year, @Param("month") String month);
}
