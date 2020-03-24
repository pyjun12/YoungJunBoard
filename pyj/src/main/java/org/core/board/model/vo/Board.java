package org.core.board.model.vo;

import java.sql.Timestamp;

public class Board {

	private int bno; // 게시글 번호(PK)
	private String boardUserId; // TB_USER 테이블의 회원ID (FK)
	private String boardSubject; // 게시글 제목
	private String boardContent; // 게시글 내용
	private Timestamp boardRegdate; // 게시글 등록 일자
	private int boardViewcheck; // 조회수
	private int boardRecommend; // 추천수
	private String selectPage; // 10개 20개 30개 단위로 보기
	private String select; // 검색선택 (db x)
	private String search; // 검색목록 (db x)
	private int start; // (db x)
	private int end; // (db x)
	private int commentCount; // 댓글 수 (db x)
	private int fno; // 해당 파일을 삭제하기위한 번호 (db x)
	private int fileox; // 파일여부확인
	private int fileNum; // 다운받을 파일번호
	private int No; // 게시글번호 불러오기
	private String datedate; // 단순히 날짜를 담을 변수
	private String boardRegdateymd;
	private String boardRegdateymdhms;
	
	// 계층형 게시판을위한 컬럼추가
	private int boardGroupNo; // 답글이 속한 게시글번호
	private int boardGroupSeq; // 원글에대한 순서
	private int boardGroupDep; // 원글(1) 원글의답글 (2) 원글의답글의답글(3)
	private int boardParentNo;

	//MasterBoard 테이블의 외래기
	private int boardCodeNum;
	
	//페이징 url값 form태그 url값 받아오기
	private String path;

	//datepicker
	private String datepick1;
	private String datepick2;
	public Board() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Board(int bno, String boardUserId, String boardSubject, String boardContent, Timestamp boardRegdate,
			int boardViewcheck, int boardRecommend, String selectPage, String select, String search, int start, int end,
			int commentCount, int fno, int fileox, int fileNum, int no, String datedate, String boardRegdateymd,
			String boardRegdateymdhms, int boardGroupNo, int boardGroupSeq, int boardGroupDep, int boardParentNo,String path,int boardCodeNum
			,String datepick1 , String datepick2) {
		super();
		        this.bno = bno;
		this.boardUserId = boardUserId;
	   this.boardSubject = boardSubject;
	   this.boardContent = boardContent;
	   this.boardRegdate = boardRegdate;
	 this.boardViewcheck = boardViewcheck;
	 this.boardRecommend = boardRecommend;
		 this.selectPage = selectPage;
		     this.select = select;
		     this.search = search;
		      this.start = start;
		        this.end = end;
	   this.commentCount = commentCount;
		        this.fno = fno;
		     this.fileox = fileox;
		    this.fileNum = fileNum;
		              No = no;
		   this.datedate = datedate;
	this.boardRegdateymd = boardRegdateymd;
 this.boardRegdateymdhms = boardRegdateymdhms;
	   this.boardGroupNo = boardGroupNo;
	  this.boardGroupSeq = boardGroupSeq;
	  this.boardGroupDep = boardGroupDep;
	  this.boardParentNo = boardParentNo;
	  this.path = path;
	  this.boardCodeNum=boardCodeNum;
	  this.datepick1=datepick1;
	  this.datepick2=datepick2;
	}
	public String getDatepick1() {
		return datepick1;
	}

	public void setDatepick1(String datepick1) {
		this.datepick1 = datepick1;
	}

	public String getDatepick2() {
		return datepick2;
	}

	public void setDatepick2(String datepick2) {
		this.datepick2 = datepick2;
	}

	public int getBoardCodeNum() {
		return boardCodeNum;
	}

	public void setBoardCodeNum(int boardCodeNum) {
		this.boardCodeNum = boardCodeNum;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getBoardUserId() {
		return boardUserId;
	}

	public void setBoardUserId(String boardUserId) {
		this.boardUserId = boardUserId;
	}

	public String getBoardSubject() {
		return boardSubject;
	}

	public void setBoardSubject(String boardSubject) {
		this.boardSubject = boardSubject;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public Timestamp getBoardRegdate() {
		return boardRegdate;
	}

	public void setBoardRegdate(Timestamp boardRegdate) {
		this.boardRegdate = boardRegdate;
	}

	public int getBoardViewcheck() {
		return boardViewcheck;
	}

	public void setBoardViewcheck(int boardViewcheck) {
		this.boardViewcheck = boardViewcheck;
	}

	public int getBoardRecommend() {
		return boardRecommend;
	}

	public void setBoardRecommend(int boardRecommend) {
		this.boardRecommend = boardRecommend;
	}

	public String getSelectPage() {
		return selectPage;
	}

	public void setSelectPage(String selectPage) {
		this.selectPage = selectPage;
	}

	public String getSelect() {
		return select;
	}

	public void setSelect(String select) {
		this.select = select;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getFno() {
		return fno;
	}

	public void setFno(int fno) {
		this.fno = fno;
	}

	public int getFileox() {
		return fileox;
	}

	public void setFileox(int fileox) {
		this.fileox = fileox;
	}

	public int getFileNum() {
		return fileNum;
	}

	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}

	public int getNo() {
		return No;
	}

	public void setNo(int no) {
		No = no;
	}

	public String getDatedate() {
		return datedate;
	}

	public void setDatedate(String datedate) {
		this.datedate = datedate;
	}

	public String getBoardRegdateymd() {
		return boardRegdateymd;
	}

	public void setBoardRegdateymd(String boardRegdateymd) {
		this.boardRegdateymd = boardRegdateymd;
	}

	public String getBoardRegdateymdhms() {
		return boardRegdateymdhms;
	}

	public void setBoardRegdateymdhms(String boardRegdateymdhms) {
		this.boardRegdateymdhms = boardRegdateymdhms;
	}

	public int getBoardGroupNo() {
		return boardGroupNo;
	}

	public void setBoardGroupNo(int boardGroupNo) {
		this.boardGroupNo = boardGroupNo;
	}

	public int getBoardGroupSeq() {
		return boardGroupSeq;
	}

	public void setBoardGroupSeq(int boardGroupSeq) {
		this.boardGroupSeq = boardGroupSeq;
	}

	public int getBoardGroupDep() {
		return boardGroupDep;
	}

	public void setBoardGroupDep(int boardGroupDep) {
		this.boardGroupDep = boardGroupDep;
	}

	public int getBoardParentNo() {
		return boardParentNo;
	}

	public void setBoardParentNo(int boardParentNo) {
		this.boardParentNo = boardParentNo;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Board [bno=" + bno + ", boardUserId=" + boardUserId + ", boardSubject=" + boardSubject
				+ ", boardContent=" + boardContent + ", boardRegdate=" + boardRegdate + ", boardViewcheck="
				+ boardViewcheck + ", boardRecommend=" + boardRecommend + ", selectPage=" + selectPage + ", select="
				+ select + ", search=" + search + ", start=" + start + ", end=" + end + ", commentCount=" + commentCount
				+ ", fno=" + fno + ", fileox=" + fileox + ", fileNum=" + fileNum + ", No=" + No + ", datedate="
				+ datedate + ", boardRegdateymd=" + boardRegdateymd + ", boardRegdateymdhms=" + boardRegdateymdhms
				+ ", boardGroupNo=" + boardGroupNo + ", boardGroupSeq=" + boardGroupSeq + ", boardGroupDep="
				+ boardGroupDep + ", boardParentNo=" + boardParentNo + ", boardCodeNum=" + boardCodeNum + ", path="
				+ path + ", datepick1=" + datepick1 + ", datepick2=" + datepick2 + "]";
	}
	


}