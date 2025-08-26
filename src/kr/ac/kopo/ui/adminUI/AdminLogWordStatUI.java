package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.WordLogStatVO;

public class AdminLogWordStatUI extends BaseUI { // choice = "3321"

    @Override
    public void excute() throws Exception {
        clearScreen();
        title("< 낱말 연습 통계 >");

        WordLogStatVO stat = logService.getWordLogStat();

        if (stat == null) {
            System.out.println("통계 정보를 불러올 수 없습니다.");
        } else {
            System.out.printf("총 낱말 연습 횟수	: %d회\n", stat.getPracticeCount());
            System.out.printf("총 연습한 낱말 수	: %d개\n", stat.getWordCount());
            System.out.printf("전체 평균 정확도	: %.2f%%\n", stat.getAvgAccuracy());
        }

        scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
        choice = "332";
    }
}
