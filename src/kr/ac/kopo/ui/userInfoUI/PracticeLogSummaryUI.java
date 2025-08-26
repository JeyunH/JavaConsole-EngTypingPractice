package kr.ac.kopo.ui.userInfoUI;

import java.util.List;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.util.LoginSession;
import kr.ac.kopo.vo.UserLongSentenceLogSummaryVO;
import kr.ac.kopo.vo.LongSentencePracticeCountVO;
import kr.ac.kopo.vo.UserShortSentenceLogSummaryVO;
import kr.ac.kopo.vo.UserWordLogSummaryVO;

public class PracticeLogSummaryUI extends BaseUI {

    @Override
    public void excute() throws Exception {		// choice = "244"
        clearScreen();
        title("개인 연습 기록 요약");

        int userNo = LoginSession.getLoginUser().getNo();

        // --- 단어 통계 출력 ---
        UserWordLogSummaryVO wordSummary = logService.getWordLogSummary(userNo);
        if (wordSummary == null || wordSummary.getLogCount() == 0) {
            System.out.println("단어 연습 기록이 없습니다.");
        } else {
            doubleBar();
            System.out.println("         [단어 연습 요약]");
            doubleBar();
            System.out.printf("총 연습 횟수   : %d회\n", wordSummary.getLogCount());
            System.out.printf("총 단어 수     : %d개\n", wordSummary.getTotalWordCount());
            System.out.printf("정답 수        : %d개\n", wordSummary.getTotalCorrect());
            System.out.printf("오답 수        : %d개\n", wordSummary.getTotalIncorrect());
            System.out.printf("평균 정확도    : %.2f%%\n", wordSummary.getAccuracy());
            doubleBar();
        }

        scanStr("엔터를 누르면 짧은 문장 연습 통계를 확인합니다...");

        // --- 짧은 문장 통계 출력 ---
        UserShortSentenceLogSummaryVO shortSummary = logService.getShortLogSummary(userNo);
        clearScreen();
        System.out.println("         [짧은 문장 연습 요약]");
        doubleBar();
        System.out.printf("총 연습 횟수     : %d회\n", shortSummary.getLogCount());
        System.out.printf("총 문장 수       : %d개\n", shortSummary.getTotalSentenceCount());

        int totalSec = (int)Math.round(shortSummary.getTotalTime());
        int hh = totalSec / 3600;
        int mm = (totalSec % 3600) / 60;
        int ss = totalSec % 60;
        System.out.printf("총 소요 시간     : %02d시간 %02d분 %02d초\n", hh, mm, ss);

        System.out.printf("평균 정확도      : %.2f%%\n", shortSummary.getAvgAccuracy());
        System.out.printf("평균 타속        : %d타/분\n", shortSummary.getAvgSpeed());
        System.out.printf("최고 타속        : %d타/분\n", shortSummary.getMaxSpeed());
        doubleBar();

        scanStr("엔터를 누르면 긴 문장 연습 통계를 확인합니다...");

        // --- 긴 문장 통계 출력 ---
        UserLongSentenceLogSummaryVO longSummary = logService.getLongLogSummary(userNo);
        clearScreen();
        System.out.println("         [긴 문장 연습 요약]");
        doubleBar();

        System.out.printf("총 연습 횟수     : %d회\n", longSummary.getTotalLogCount());
        System.out.printf("총 라인 수       : %d줄\n", longSummary.getTotalLineCount());

        int totalLongSec = (int)Math.round(longSummary.getTotalTime());
        hh = totalLongSec / 3600;
        mm = (totalLongSec % 3600) / 60;
        ss = totalLongSec % 60;
        System.out.printf("총 소요 시간     : %02d시간 %02d분 %02d초\n", hh, mm, ss);

        System.out.printf("평균 정확도      : %.2f%%\n", longSummary.getAvgAccuracy());
        System.out.printf("평균 타속        : %d타/분\n", longSummary.getAvgSpeed());
        doubleBar();

        List<LongSentencePracticeCountVO> sentenceStats = longSummary.getSentenceStats();
        if (sentenceStats == null || sentenceStats.isEmpty()) {
            System.out.println("개별 글 연습 기록이 없습니다.");
        } else {
            System.out.println("\n[글별 연습 횟수]");
            for (LongSentencePracticeCountVO vo : sentenceStats) {
                System.out.printf(" - (%03d) %s : %d회\n",
                        vo.getSentenceNo(),
                        vo.getTitle(),
                        vo.getPracticeCount());
            }
        }

        doubleBar();
        scanStr("엔터를 누르면 마이페이지로 돌아갑니다...");
        choice = "24";
    }
}
