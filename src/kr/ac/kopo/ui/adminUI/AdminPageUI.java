package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;

public class AdminPageUI extends BaseUI{
		
	@Override
	public void excute() throws Exception {		// choice = "99"
		clearScreen();
		title("관리자");
		System.out.println("\t1. 사용자 관리");
		System.out.println("\t2. 연습 글 관리");
		System.out.println("\t3. 로그 및 통계 조회");
		System.out.println("\t9. 로그아웃");
		System.out.println("\t0. 종료");
		System.out.println("-------------------------------------");
		choice = "3" + scanStr("메뉴 선택 : ");

	}
}

