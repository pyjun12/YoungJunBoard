package org.core.board.model.dao;

import java.util.List;
import java.util.Map;
import org.core.board.model.vo.Board;
import org.core.board.model.vo.MasterBoard;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDao {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSessionTemplate sqlSession;

	// 게시글 쓰기
	public int insertBoard(Board b) {
		return sqlSession.insert("board.insertBoard", b);
	}

	// 파일 첨부
	public int insertFile(Map<String, Object> map) {
		return sqlSession.insert("board.insertFile", map);
		
	}
	
	// 게시글 내용 클릭시 상세페이지 이동
	public Board boardClick(int bno) {
		return sqlSession.selectOne("board.boardClick", bno);
	}

	// 게시글 내용 클릭시 조회수 +1
	public int boardViewcheck(Board b) {
		return sqlSession.update("board.boardView", b);
	}

	// 게시글 추천수 +1
	public int boardgood(Board b) {
		return sqlSession.update("board.boardgood", b);
	}

	// 게시글 검색
	public List<Board> searchBoard(Board board) {
		
		List<Board> bl = sqlSession.selectList("board.searchBoard", board);
		
		if(bl.equals(null) || bl.isEmpty()) {
			return bl;
		} else bl.get(0).setStart(board.getStart());
		
		return bl;
	}

	// 게시글 수정
	public int updateboard(Board b) {
		return sqlSession.update("board.updateboard", b);
	}

	// 게시글 삭제
	public int deleteboard(Board b) {
		return sqlSession.delete("board.deleteboard", b); // 해당게시글지우기
	}

	// 게시글 총갯수
	public int totalcount(int boardCodeNum) {
		return sqlSession.selectOne("board.totalcount",boardCodeNum);
	}

	public int searchTotalBoard(Board board) {
		return sqlSession.selectOne("board.searchTotalBoard", board);
	}
	
	// index에서 로그인후 최신5개의
	public List<Board> boardList5(Board board) {
		return sqlSession.selectList("board.boardList5", board);
	}

	// 총 검색 게시글 갯수
	public int totalSearchBoard(Board board) {
		return sqlSession.selectOne("board.totalSearchBoard", board);
	}

	// 오늘 올라온 게시글 갯수
	public int todayBoard() {
		return sqlSession.selectOne("board.todayBoard");
	}

	// 첨부파일 조회 (bno)
	public List<Map<String, Object>> selectFilelist(int bno) {
		return sqlSession.selectList("board.selectFilelist", bno);
	}

	// 첨부파일 다운로드
	public Map<String, Object> selectFileInfo(Map<String, Object> map) {
		return sqlSession.selectOne("board.selectFileInfo", map);
	}

	// 파일 삭제
	public int deleteFile(Board b) {
		return sqlSession.delete("board.deleteFile", b);
	}

	// 파일수정 전 게시글 수정
	public int updateBoardd(Board b) {
		return sqlSession.update("board.updateboard", b);
	}

	// 최근5일내의 등록된게시글 총갯수
	public int total5Board() {
		return sqlSession.selectOne("board.total5Board");
	}

	// 답글 인서트하기전 기존의 답글보다 seq+1해주기
	public int updatereply(Board b) {
		return sqlSession.update("board.updatereply", b);
	}

	// 답글 인서트 (업데이트 후) 부모의 SEQ.DEP값에 1씩증가해서 인서트
	public int insertreply(Board b) {
		b.setBoardGroupSeq(b.getBoardGroupSeq() + 1);
		b.setBoardGroupDep(b.getBoardGroupDep() + 1);
		return sqlSession.insert("board.insertreply", b);
	}
    //삭제하기 전에 답글 확인
	public int selectdelBoard(Board b) {
		return sqlSession.selectOne("board.selectdelBoard", b);
	}
	
	//게시판 목록 출력
	public List<Board> MasterBoardlist() {
		return sqlSession.selectList("board.MasterBoardlist");
	}
	// 게시판 이름 가져오기
	public String MasterBoardName(MasterBoard mb) {
		return sqlSession.selectOne("board.MasterBoardName",mb);
	}
    //총 게시물 수
	public int totalBoardMax() {
		return sqlSession.selectOne("board.totalBoardMax");
	}
}
