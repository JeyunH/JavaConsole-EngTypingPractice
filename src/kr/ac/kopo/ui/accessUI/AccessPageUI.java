package kr.ac.kopo.ui.accessUI;

import kr.ac.kopo.ui.BaseUI;

public class AccessPageUI extends BaseUI{

	@Override
	public void excute() throws Exception {		// choice = "1"
		title("사용자 접속");
		System.out.println("\t1. 로그인");
		System.out.println("\t2. 회원가입");
		System.out.println("\t0. 종료");
		System.out.println("-------------------------------------");
		BaseUI.choice = "1" + scanStr("항목을 선택하세요 : ");
	}
}
