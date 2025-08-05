package com.edu.springboot.jpaboard;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>{
	//기본적인 CRUD 기능 지원
	
	//전체 레코드 인출 및 정렬
	/*
	게시판은 최근 게시물이 항상 위에 출력되어야 하므로 내림차순 정렬이 기본이다.
	따라서 아래와 같이 Sort 객체를 사용해서 정렬한 상태로 인출한다. */
	List<BoardEntity> findAll(Sort sort);
	
	//페이징 적용(검색X)
	Page<BoardEntity> findAll(Pageable pageable);
	//페이징 적용(검색O)
	Page<BoardEntity> findByTitleLike(String keyword ,Pageable pageable);	
}
