package org.core.comment.model.service;

import java.util.List;

import org.core.comment.model.dao.CommentDao;
import org.core.comment.model.vo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

	@Autowired
	@Qualifier("commentDao")
	CommentDao commentDao;

	// 댓글 작성
	public int insertcomment(Comment c) {
		return commentDao.insertcomment(c);
	}

	// 댓글 목록 출력
	public List<Comment> commentkist(int bno) {
		return commentDao.commentkist(bno);
	}

	// 댓글 삭제
	public int delcomment(int commentNum) {
		return commentDao.delcomment(commentNum);
	}

}
