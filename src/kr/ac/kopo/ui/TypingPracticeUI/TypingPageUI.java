package kr.ac.kopo.ui.TypingPracticeUI;

import kr.ac.kopo.ui.BaseUI;

public class TypingPageUI extends BaseUI{

	@Override
	public void excute() throws Exception {		// choice = "2"
		clearScreen();
		title("타자 연습");
		loggedInUser();
		System.out.println("\t1. 낱말 연습");
		System.out.println("\t2. 짧은 글 연습");
		System.out.println("\t3. 긴 글 연습");
		System.out.println("\t4. 마이페이지");
		System.out.println("\t9. 로그아웃");
		System.out.println("\t0. 종료");
		System.out.println("-------------------------------------");
		choice = "2" + scanStr("항목을 선택하세요 : ");
	}	
}
