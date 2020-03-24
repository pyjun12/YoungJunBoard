package org.core.board.model.vo;

public class MasterBoard {

	private int boardCodeNumber; //게시판 번호
	private String boardCodeName; //게시판 이름 
	public MasterBoard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MasterBoard(int boardCodeNumber, String boardCodeName) {
		super();
		this.boardCodeNumber = boardCodeNumber;
		this.boardCodeName = boardCodeName;
	}
	public int getBoardCodeNumber() {
		return boardCodeNumber;
	}
	public void setBoardCodeNumber(int boardCodeNumber) {
		this.boardCodeNumber = boardCodeNumber;
	}
	public String getBoardCodeName() {
		return boardCodeName;
	}
	public void setBoardCodeName(String boardCodeName) {
		this.boardCodeName = boardCodeName;
	}
	

	
}
