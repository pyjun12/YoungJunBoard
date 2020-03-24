package org.core.user.model.dao;

import org.core.user.model.vo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	SqlSessionTemplate sqlSession;

	// 로그인
	public User loginUser(User u) {
		User user = null;
		     user = sqlSession.selectOne("user.loginUser", u);
		return user;
	}
	// 회원가입
	public void InsertUserData(User user) {
		sqlSession.insert("user.InsertUserData", user);
	}

	// 회원 탈퇴
	public int deleteuser(String userId) {
		return sqlSession.delete("user.deleteuser", userId);
	}

	// 총 회원수 카운트
	public int userCount() {
		return sqlSession.selectOne("user.userCount");
	}

	// 게시글 최다 등록자 수
	public String userMax() {
		return sqlSession.selectOne("user.userMax");
	}

	public int idChk(User user) {
		return sqlSession.selectOne("user.idChk", user);
	}

}
