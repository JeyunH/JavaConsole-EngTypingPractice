package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;

public class AdminLongSentenceManageUI extends BaseUI { // choice = "323"

    @Override
    public void excute() throws Exception {
        clearScreen();
        title("긴 문장 관리");

        System.out.println("\t1. 전체 문장 목록 조회");
        System.out.println("\t2. 문장 등록");
        System.out.println("\t3. 문장 수정");
        System.out.println("\t4. 문장 상태 변경");
        System.out.println("\t0. 뒤로가기");
        bar();

        choice = "323" + scanStr("메뉴 선택 : ");
    }
}
