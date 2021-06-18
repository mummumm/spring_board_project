package edu.bit.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.bit.board.page.Criteria;
import edu.bit.board.vo.BoardVO;

@Mapper
public interface BoardMapper {
	
	public List<BoardVO> getList();
	public BoardVO read(int bid);
	void update(BoardVO boardVO); // 수정
	void delete(int bid); // 삭제
	void insertBoard(BoardVO boardVO); // 새 글 추가
	
	// 댓글 관련
	void updateShape(BoardVO boardVO); // 한 칸씩 미는(밑으로)
	void insertReply(BoardVO boardVO); // 민 것을 입력
	void updateHit(int bid);
	
	// 페이지 처리 관련
	int getTotalCount(Criteria cri);
	List<BoardVO> getListWithPaging(Criteria criteria);
	
}
