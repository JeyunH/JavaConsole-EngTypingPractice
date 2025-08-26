package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.ShortLogStatVO;

public class AdminLogShortStatUI extends BaseUI { // choice = "3322"

    @Override
    public void excute() throws Exception {
        clearScreen();
        title("< 짧은 문장 연습 통계 >");

        ShortLogStatVO stat = logService.getShortLogStat();

        if (stat == null) {
            System.out.println("통계 정보를 불러올 수 없습니다.");
        } else {
            System.out.printf("총 연습횟수	: %d회\n", stat.getPracticeCount());
            System.out.printf("총 연습문장 수	: %d문장\n", stat.getSentCount());
            System.out.printf("평균 정확도	: %.2f%%\n", stat.getAvgAccuracy());

            int totalSec = (int) stat.getTotalTime();
            int hr = totalSec / 3600;
            int min = (totalSec % 3600) / 60;
            int sec = totalSec % 60;
            System.out.printf("총 연습시간	: %d시간 %d분 %d초\n", hr, min, sec);

            System.out.printf("전체 평균 타속	: %d타/분\n", stat.getAvgSpeed());
            System.out.printf("전체 최고 타속	: %d타/분\n", stat.getMaxSpeed());
        }

        scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
        choice = "332";
    }
}
