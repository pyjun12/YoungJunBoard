package org.core.comment.model.vo;

import java.sql.Timestamp;

public class Comment {

	private int commentNum; // 댓글 번호 (PK)
	private int commentBoardNum; // TB_BOARD테이블의 게시판번호 (FK)
	private String commentBoardUserId; // TB_USER테이블의 회원 ID(FK)
	private String commentContent; // 댓글 내용
	private Timestamp commentRegTime; // 댓글 등록 시간

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(int commentNum, int commentBoardNum, String commentBoardUserId, String commentContent,
			Timestamp commentRegTime) {
		super();
	this.commentNum         = commentNum;
	this.commentBoardNum    = commentBoardNum;
	this.commentBoardUserId = commentBoardUserId;
	this.commentContent     = commentContent;
	this.commentRegTime     = commentRegTime;
	}

	public int getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}

	public int getCommentBoardNum() {
		return commentBoardNum;
	}

	public void setCommentBoardNum(int commentBoardNum) {
		this.commentBoardNum = commentBoardNum;
	}

	public String getCommentBoardUserId() {
		return commentBoardUserId;
	}

	public void setCommentBoardUserId(String commentBoardUserId) {
		this.commentBoardUserId = commentBoardUserId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Timestamp getCommentRegTime() {
		return commentRegTime;
	}

	public void setCommentRegTime(Timestamp commentRegTime) {
		this.commentRegTime = commentRegTime;
	}

}
