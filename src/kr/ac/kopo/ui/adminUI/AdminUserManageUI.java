package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;

public class AdminUserManageUI extends BaseUI {

	@Override
	public void excute() throws Exception {		// choice = "31"
		clearScreen();
		title("사용자 관리");

		System.out.println("\t1. 전체 사용자 목록 조회");
		System.out.println("\t2. 사용자 검색");
		System.out.println("\t3. 사용자 상태 변경");
		System.out.println("\t0. 뒤로가기");
		bar();
		choice = "31" + scanStr("메뉴 선택 : ");

	}
}
