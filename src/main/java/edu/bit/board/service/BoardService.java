package edu.bit.board.service;

import java.util.List;

import edu.bit.board.page.Criteria;
import edu.bit.board.vo.BoardVO;

public interface BoardService {
	List<BoardVO> getList();
	BoardVO get(int bid);
	void modify(BoardVO boardVO); // 수정
	void remove(int bid); // 삭제
	
	// 게시판 추가 관련
	void writeBoard(BoardVO boardVO); // 새 글 추가
	void writeReply(BoardVO boardVO); // 답변 달기
	void upHit(int bid);
	
	// 페이징 처리 함수
	public int getTotal(Criteria cri);
	public List<BoardVO> getList(Criteria criteria);
	
}
