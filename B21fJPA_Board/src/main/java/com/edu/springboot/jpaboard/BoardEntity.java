package com.edu.springboot.jpaboard;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import oracle.jdbc.proxy.annotation.Pre;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Entity(name="jpaboard")
public class BoardEntity {
	@Id
	@SequenceGenerator(
			name="mySequence",
			sequenceName = "jpaboard_seq",
			initialValue = 1,
			allocationSize = 1
	)
	@GeneratedValue(generator = "mySequence")
	private Long idx;	//일련번호
	private String name; //이름
	private String title; //제목
	private String contents; //내용
	private Long hits;	//조회수
	@Column(columnDefinition = "DATE DEFAULT SYSDATE")
	private LocalDateTime postdate; //작성일
	
	/*
	@PrePersist : 엔티티가 저장(insert)되기 전에 실행되는 콜백 메서드를 정의할 때
		사용하는 어노테이션으로, 엔티티가 처음 저장될 때 필요한 작업을 수행하는 데 사용된다.
	 */
	@PrePersist
	protected void onPrePersist() {
		this.postdate = LocalDateTime.now();
		if(this.hits == null) {
			this.hits = 0L;
		}
	}
}

