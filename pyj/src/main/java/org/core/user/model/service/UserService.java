package org.core.user.model.service;

import org.core.user.model.dao.UserDao;
import org.core.user.model.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	@Qualifier("userDao")
	UserDao userDao;

	// 로그인
	public User loginUser(User u) {
		return userDao.loginUser(u);
	}

	// 회원가입
	public void InsertUserData(User user) {
		userDao.InsertUserData(user);
	}

	// 회원탈퇴
	public int deleteuser(String userId) {
		return userDao.deleteuser(userId);
	}

	// 총 회원수 카운트
	public int userCount() {
		return userDao.userCount();
	}

	// 최다 게시글 등록자 이름 출력
	public String userMax() {
		return userDao.userMax();
	}

	// id중복체크
	public int idChk(User user) {
		return userDao.idChk(user);
	}

}
