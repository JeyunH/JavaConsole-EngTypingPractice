package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.dao.UserDAO;
import kr.ac.kopo.util.LoginSession;
import kr.ac.kopo.vo.UserVO;

public class UserService {

	private static UserService instance = new UserService();

	private UserService() {
	}

	public static UserService getInstance() {
		return instance;
	}

	public boolean isInputIdDuplicate(UserVO user) {
		return UserDAO.checkID(user);
	}

	public void registerUser(UserVO user) {
		UserDAO.insertUser(user);
	}

	public UserVO login(UserVO user) {
		LoginSession.setLoginUser(UserDAO.loginUser(user));
		return LoginSession.getLoginUser();
	}

	public void changeNickname(String inputNick) {
		LoginSession.getLoginUser().setNickname(inputNick);
		UserDAO.updateInfo(LoginSession.getLoginUser());
	}

	public boolean isCurrentPasswordCorrect(String inputPassword) {
		UserVO user = new UserVO();
		user.setUserID(LoginSession.getLoginUser().getUserID());
		user.setPassword(inputPassword);
		return UserDAO.loginUser(user) != null;
	}

	public void changePassword(String newPassword) {
		UserVO user = LoginSession.getLoginUser();
		user.setPassword(newPassword);
		UserDAO.updateInfo(user);
	}

	public void deleteUser() {
		LoginSession.getLoginUser().setStatus("N");
		UserDAO.updateInfo(LoginSession.getLoginUser());
	}

	public List<UserVO> getAllUsers() {
		return UserDAO.selectAll();
	}

	public List<UserVO> searchUsers(String keyword) {
		return UserDAO.searchByIdOrNickname(keyword);
	}

	public UserVO getUserByNo(int userNo) {
		return UserDAO.findByUserNo(userNo);
	}

	public UserVO getUserById(String userId) {
		return UserDAO.findByUserID(userId);
	}

	public boolean toggleUserStatus(int userNo) {
		UserVO user = UserDAO.findByUserNo(userNo);
		if (user == null)
			return false;

		String currentStatus = user.getStatus();
		String newStatus = currentStatus.equals("Y") ? "N" : "Y";

		return UserDAO.updateStatusByNo(userNo, newStatus);
	}

}
