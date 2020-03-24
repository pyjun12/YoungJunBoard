package org.core.comment.model.dao;

import java.util.List;

import org.core.comment.model.vo.Comment;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class CommentDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSessionTemplate sqlSession;

	//댓글 달기
	public int insertcomment(Comment c) {
		return sqlSession.insert("comment.insertcomment", c);
	}
	//게시글 번호에 해당하는 댓글 가져오기
	public List<Comment> commentkist(int bno) {
		return sqlSession.selectList("comment.commentkist", bno);
	}
	//댓글 삭제
	public int delcomment(int commentNum) {
		return sqlSession.delete("comment.delcomment", commentNum);
	}

}
