package mydiary;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IMyDiary {
	//첨부파일을 DB에 입력
	public int insertFile(MyDiaryDTO myDiaryDTO);
	//파일 목록
	public List<MyDiaryDTO> selectFile();
	//목록 : 게시물 갯수 카운트
	public int getTotalCount(MyDiaryDTO myDiaryDTO);
	//목록 : 한페이지에 출력할 게시물 인출
	public ArrayList<MyDiaryDTO> listPage(MyDiaryDTO myDiaryDTO);
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
