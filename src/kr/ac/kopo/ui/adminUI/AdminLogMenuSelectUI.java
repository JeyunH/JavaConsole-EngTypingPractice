package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;

public class AdminLogMenuSelectUI extends BaseUI { // choice = "33"

	@Override
	public void excute() throws Exception {
		clearScreen();
		title("통계 종류 선택");

		System.out.println("\t1. 종합 통계 보기");
		System.out.println("\t2. 연습 유형별 통계");
		System.out.println("\t3. 사용자별 통계 조회");
		System.out.println("\t0. 뒤로가기");
		bar();

		choice = "33" + scanStr("메뉴 선택 : ");
	}
}