package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;

public class AdminContentTypeSelectUI extends BaseUI {	// choice = "32"

    @Override
    public void excute() throws Exception {
        clearScreen();
        title("연습 글 종류 선택");

        System.out.println("\t1. 낱말 관리");
        System.out.println("\t2. 짧은 문장 관리");
        System.out.println("\t3. 긴 문장 관리");
        System.out.println("\t0. 뒤로가기");
        bar();

        choice = "32" + scanStr("관리할 종류를 선택하세요 : ");
    }
}
