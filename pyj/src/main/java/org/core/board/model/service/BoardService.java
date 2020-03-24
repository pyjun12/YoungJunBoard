package org.core.board.model.service;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.core.board.model.dao.BoardDao;
import org.core.board.model.vo.Board;
import org.core.board.model.vo.BoardPageData;
import org.core.board.model.vo.MasterBoard;
import org.core.util.FileUtil;
import org.core.util.PagingSearchUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class BoardService {
	@Autowired
	@Qualifier("boardDao")
	BoardDao boardDao;

	@Resource(name = "PagingSearchUtil")
	private PagingSearchUtil PagingSearchUtil;


//@Resource로 FileUtil.class를 사용할수 있게 추가
	@Resource(name = "fileUtil")
	private FileUtil fileUtil;

//게시글 쓰기
	public int insertBoard(Board b, MultipartHttpServletRequest mrequest) throws Exception {

		int result=boardDao.insertBoard(b); // 게시글을 작성하기위한 비즈니스 로직
		List<Map<String, Object>> list = fileUtil.parseInsertFileInfo(b, mrequest); 
		// 이 list에는 위에 실행하고 게시글을 만든후의 게시글번호가 담겨있다.
																				
		int size = list.size();
		for (int i = 0; i < size; i++) {
			 result=boardDao.insertFile(list.get(i)); // 게시글을 만든후의 게시글번호를 가지고 파일 업로드
		}
		return result;
	}
	// 위에가 먼저 실행되고 나서 나온 게시글번호를 넣어줘서 보냄
	// fileUtil.class 안에 parseInsertFileInfo이라는 메소드를 호출 그안에파라미터값도 두개가져와서
	// 파일경로 설정과 등등
	// 거기서 가져온 게시글번호,원본파일명,랜덤문자열을붙인파일명,파일크기를 list값에 넣어줌.

//게시글수정+파일업로드
	public int updateinsertFile(Board b, MultipartHttpServletRequest mrequest) throws Exception {

		int result=boardDao.updateBoardd(b);
		List<Map<String, Object>> list = fileUtil.parseInsertFileInfo(b, mrequest); // 이 list에는 위에 실행하고 게시글을 만든후의 게시글번호가
																					// 담겨있음
		int size = list.size();
		for (int i = 0; i < size; i++) {
			result=boardDao.insertFile(list.get(i));
		}
		return result;
	}
//게시글 내용 클릭시 상세페이지 이동
	public Board boardClick(int bno) {
		return boardDao.boardClick(bno);
	}

//게시글 조회수+1
	public int boardView(Board b) {
		return boardDao.boardViewcheck(b);
	}

//게시글 추천+1
	public int boardgood(Board b) {
		return boardDao.boardgood(b);
	}

//게시글 검색 결과
	public BoardPageData searchBoard(Board board, int currentPage) {
		BoardPageData bpd = PagingSearchUtil.searchpaging(board, currentPage);
		return bpd;
	}

//게시글 수정
	public int updateboard(Board b) {
		return boardDao.updateboard(b);
	}

//게시글 삭제
	public int deleteboard(Board b) {
		int result = boardDao.deleteboard(b);
		return result;
	}

//총 게시글 갯수
	public int totalBoard(int boardCodeNum) {
		return boardDao.totalcount(boardCodeNum);
	}

//index에서 보여지는 최신5개의 게시글
	public List<Board> boardList5(Board board) {
		return boardDao.boardList5(board);
	}

//게시글 검색후 총갯수
	public int searchTotalBoard(Board board) {
		return boardDao.searchTotalBoard(board);
	}

//오늘 올린 게시글 갯수
	public int todayBoard() {
		return boardDao.todayBoard();
	}

//첨부파일 조회(bno)
	public List<Map<String, Object>> selectFilelist(int bno) {
		return boardDao.selectFilelist(bno);
	}

//파일 다운로드
	public Map<String, Object> selectFileInfo(Map<String, Object> map) {
		return boardDao.selectFileInfo(map);
	}

//파일 삭제
	public int deleteFile(Board b) {
		int result=boardDao.deleteFile(b);
		return result;
	}
//기존게시판 최근 5개의 게시글 가져오기
	public int total5Board() {
		return boardDao.total5Board();
	}

//답변달기
	public int insertreply(Board b, MultipartHttpServletRequest mrequest) throws Exception {

		int result = boardDao.updatereply(b); // 게시글을 작성하기 전 업데이트 (boardGroupSeq를 +1해주기위함)

		result = boardDao.insertreply(b); // 게시글을 작성하기위한 비즈니스 로직

		List<Map<String, Object>> list = fileUtil.parseInsertFileInfo(b, mrequest); // 이 list에는 위에 실행하고 게시글을 만든후의 게시글번호가
																					// 담겨있음
		int size = list.size();
		for (int i = 0; i < size; i++) {
			result = boardDao.insertFile(list.get(i));
		}
		return result;
	}

	public int selectdelBoard(Board b) {
		return boardDao.selectdelBoard(b);
	}
   //게시판 이름 전체출력
	public List<Board> MasterBoardlist() {
		return boardDao.MasterBoardlist();
	}
	//boardList.jsp에 보여줄 게시판이름 
	public String MasterBoardName(MasterBoard mb) {
		return boardDao.MasterBoardName(mb);
	}
	//전체게시글 수
	public int totalBoardMax() {
		return boardDao.totalBoardMax();
	}
}
