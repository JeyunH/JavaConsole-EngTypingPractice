package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;

public class AdminWordManageUI extends BaseUI {

    @Override
    public void excute() throws Exception { 	// choice = "321"
        clearScreen();
        title("낱말 관리");

        System.out.println("\t1. 전체 낱말 목록 조회");
        System.out.println("\t2. 낱말 등록");
        System.out.println("\t3. 낱말 수정");
        System.out.println("\t4. 낱말 상태 변경");
        System.out.println("\t0. 뒤로가기");
        bar();

        choice = "321" + scanStr("메뉴 선택 : ");
    }
}
