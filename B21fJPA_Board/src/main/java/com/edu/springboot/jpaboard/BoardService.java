package com.edu.springboot.jpaboard;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardRepository;
	
	//목록(페이징 없음)
	public List<BoardEntity> selectList(){
		//Sort 객체를 통해 일련번호(idx)를 내림차순으로 정렬
		Sort sort = Sort.by(Sort.Direction.DESC, "idx");
		//Sort 객체를 인수로 전달하여 출력할 게시물 인출
		return boardRepository.findAll(sort);
	}
	
	//쓰기
	public void insertPost(BoardEntity boardTable) {
		boardRepository.save(boardTable);
	}
	
	//열람
	public Optional<BoardEntity> selectPost(Long idx){
		//출력할 게시물 1개 인출
		Optional<BoardEntity> row = boardRepository.findById(idx);
		//조회수 1 증가
		BoardEntity be = row.get();
		Long hits = (be.getHits()==null) ? 0 : be.getHits();
		be.setHits(hits+1);
		//save 메서드를 통해 update 처리
		boardRepository.save(be);
		
		return row;
	}
	
	//수정
	public void updatePost(BoardEntity boardEntity) {
		boardRepository.save(boardEntity);
	}
	
	//삭제
	public void deletePost(Long idx) {
		boardRepository.deleteById(idx);
	}
}
