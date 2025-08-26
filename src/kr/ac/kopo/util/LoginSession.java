package kr.ac.kopo.util;

import kr.ac.kopo.vo.LongSentenceVO;
import kr.ac.kopo.vo.UserVO;

public class LoginSession {
	
	private static UserVO loginUser = null;
	private static LongSentenceVO selectedSentence;
	
	public static void setLoginUser(UserVO user) {
		loginUser = user;
	}
	
	public static void setLogoutUser() {
		loginUser = null;
	}
	
	public static UserVO getLoginUser() {
		return loginUser;
	}
	
	public static boolean isLoggedIn() {
	    return loginUser != null;
	}

	public static void setSelectedSentence(LongSentenceVO sentence) {
	    selectedSentence = sentence;
	}
	public static LongSentenceVO getSelectedSentence() {
	    return selectedSentence;
	}

}