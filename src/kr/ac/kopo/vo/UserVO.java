package kr.ac.kopo.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

	private int userNo;
	private String userID;
	private String password;
	private String nickname;
	private String userType;
	private String regDate;
	private String status;

	public UserVO() {
		super();
	}

	public UserVO(String id, String pwd, String nick) {
		super();
		this.userID = id;
		this.password = pwd;
		this.nickname = nick;
	}

	public UserVO(int num, String id, String pwd, String nick, String date) {
		this(id,pwd,nick);
		this.userNo = num;
		this.regDate = date;
	}
	
	public int getNo() {
		return this.userNo;
	}
	public void setNo(int num) {
		this.userNo = num;
	}
	public String getUserID() {
		return this.userID;
	}
	public void setUserID(String id) {
		this.userID = id;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String pwd) {
		this.password = pwd;
	}
	public String getNickname() {
		return this.nickname;
	}
	public void setNickname(String nick) {
		this.nickname = nick;
	}
	public String getUserType() {
		return this.userType;
	}
	public void setUserType(String type) {
		this.userType = type;
	}
	public String getRegdate() {
		return this.regDate;
	}
	public void setRegdate(String date) {
		this.regDate = date;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String stat) {
		this.status = stat;
	}

	@Override
	public String toString() {
		return "UserVO [No=" + userNo + ", UserID=" + userID + ", Password=" + password + ", Nickname=" + nickname
				+ "UserType=" + userType + ", RegDate=" + regDate + ", Status=" + status + "]";
	}
	
	
}
