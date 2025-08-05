package com.edu.springboot.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
	
	//DAO 역할의 인터페이스를 자동주입
	@Autowired
	private MemberRepository dao;
	
	//입력
	public Member insert(Member member) {
		//save()를 통해 insert 처리
		Member resultMember = dao.save(member);
		return resultMember;
	}
	
	//개별조회
	public Optional<Member> select(Long id){
		//findById()를 통해 하나의 레코드를 select 처리.
		Optional<Member> member = dao.findById(id);
		return member;
	}
	
	//전체조회
	public List<Member> selectAll(){
		//조건없이 전체 레코드를 select처리
		return dao.findAll();
	}
	
	//삭제
	public void delete(Long id) {
		//인수로 전달된 id에 해당하는 레코드를 delete처리
		dao.deleteById(id);
	}
	
	//수정
	public Member update(Member member) {
		/*
		insert와 완전히 동일하다. 동일한 키값이 있으면 update, 없으면
		insert 쿼리가 실행된다.
		 */
		Member resultMember = dao.save(member);
		return resultMember;
	}
}
