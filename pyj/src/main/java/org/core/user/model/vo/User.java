package org.core.user.model.vo;

public class User {
	private String userId; // 회원 ID (PK)
	private String userPassword; // 회원 비밀번호
	private String userName; // 회원 이름
	private int userCount; // 총 유저 인원수

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String userId, String userPassword, String userName, int userCount) {
		super();
		this.userId       = userId;
		this.userPassword = userPassword;
	    this.userName     = userName;
	    this.userCount    = userCount;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

}
