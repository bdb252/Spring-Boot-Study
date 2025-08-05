package com.edu.springboot.jpa;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
	/*
	반환타입을 List로 설정하면 엔티티에서 인출한 레코드만 반환한다.
	즉 해당 페이지에 출력할 ResultSet만 가져오게 된다.
	 */
//	List<Member> findByNameLike(String keyword, Pageable pageable);
	
	/*
	반환타입을 Page로 설정하면 인출된 ResultSet을 포함하여 페이지에 관련된 다양한 
	정보를 반환한다. 총 페이지수, 레코드 개수 등이 포함된다. */
	Page<Member> findByNameLike(String keyword, Pageable pageable);
}
