package com.edu.springboot.jpa;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	//findBy 뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능하다.
	Optional<Member> findByName(String keyword);
	Optional<Member> findByEmail(String keyword);
	
	//다음과 같이 다양한 확장이 가능하다.
	List<Member> findByNameLike(String keyword);
	List<Member> findByNameLikeOrderByNameDesc(String keyword);
	List<Member> findByNameLikeOrderByNameAscEmailDesc(String keyword);
	
	//정렬의 조건때문에 메서드명이 길어지게 되므로 Sort 객체로 검색의 조건을 추가할 수 있다.
	List<Member> findByNameLike(String keyword, Sort sort);
}
