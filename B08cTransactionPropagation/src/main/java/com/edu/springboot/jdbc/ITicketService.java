package com.edu.springboot.jdbc;

import org.apache.ibatis.annotations.Mapper;

/*
구매한 티켓과 금액에 대한 insert 처리를 위한 추상 메서드 정의
 */
@Mapper
public interface ITicketService {
	//매개변수는 커맨드객체로 처리하기 위해 각 DTO객체를 사용
	public int ticketInsert(TicketDTO ticketDTO);
	public int payInsert(PayDTO payDTO);
	
	//티켓 구매 시도를 한 회원 이력을 남기기 위한 추상 메서드 추가
	public int memberRegist(TicketDTO ticketDTO);
}
