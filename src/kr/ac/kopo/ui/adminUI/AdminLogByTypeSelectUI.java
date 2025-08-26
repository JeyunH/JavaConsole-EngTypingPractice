package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;

public class AdminLogByTypeSelectUI extends BaseUI { // choice = "332"

    @Override
    public void excute() throws Exception {
        clearScreen();
        title("연습 유형별 통계");

        System.out.println("\t1. 낱말 통계");
        System.out.println("\t2. 짧은 문장 통계");
        System.out.println("\t3. 긴 문장 통계");
        System.out.println("\t0. 뒤로가기");
        bar();

        choice = "332" + scanStr("메뉴 선택 : ");
    }
}
