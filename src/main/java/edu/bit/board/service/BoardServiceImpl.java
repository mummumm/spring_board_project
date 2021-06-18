package edu.bit.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.bit.board.mapper.BoardMapper;
import edu.bit.board.page.Criteria;
import edu.bit.board.vo.BoardVO;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	private BoardMapper boardmapper;

	@Override
	public List<BoardVO> getList() {
		System.out.println("getList()..");
		
		return boardmapper.getList();
	}
	
	@Override
	public BoardVO get(int bid) {
		log.info("service:get()..");
		
		upHit(bid);
				
		return boardmapper.read(bid);
	}

	@Override
	public void modify(BoardVO boardVO) {
		log.info("service:modify()..");
		boardmapper.update(boardVO);
		
	}

	@Override
	public void remove(int bid) {
		log.info("service:remove()..");
		boardmapper.delete(bid);
		
	}

	@Override
	public void writeBoard(BoardVO boardVO) {
		boardmapper.insertBoard(boardVO);
		
	}

	@Transactional(rollbackFor = Exception.class)
	// 컨트롤러에서 트랜잭션 넣어주면 안된다.
	// checked Exception을 위하여 rollbackFor = Exception.class 적용
	@Override
	public void writeReply(BoardVO boardVO) {
		boardmapper.updateShape(boardVO);
		boardmapper.insertReply(boardVO);
		
	}

	@Override
	public void upHit(int bid) {
		boardmapper.updateHit(bid);
		
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("service:getTotal()..");
				
		return boardmapper.getTotalCount(cri);
	}

	@Override
	public List<BoardVO> getList(Criteria criteria) {
		log.info("service:getList..");
		
		return boardmapper.getListWithPaging(criteria);
	}
	
}
