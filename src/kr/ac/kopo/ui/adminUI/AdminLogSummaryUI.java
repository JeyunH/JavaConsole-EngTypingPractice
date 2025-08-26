package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.TotalLogSummaryVO;

public class AdminLogSummaryUI extends BaseUI { // choice = "331"

	@Override
	public void excute() throws Exception {
		clearScreen();
		title("종합 통계 요약");

		TotalLogSummaryVO summary = logService.getTotalLogSummary();

		if (summary == null) {
			System.out.println("통계 정보를 불러올 수 없습니다.");
		} else {
			System.out.printf("총 사용자 수	: %d명\n", summary.getUserCount());
			
			System.out.printf("총 낱말 연습 횟수	: %d회\n", summary.getWordPracticeCount());
			System.out.printf("낱말 연습 평균 정확도	: %.2f%%\n", summary.getWordAvgAccuracy());

			System.out.printf("총 짧은 글 연습 횟수	: %d회\n", summary.getShortPracticeCount());
			System.out.printf("짧은 글 평균 정확도	: %.2f%%\n", summary.getShortAvgAccuracy());
			System.out.printf("짧은 글 평균 타속	: %d타/분\n", summary.getShortAvgSpeed());

			System.out.printf("총 긴 글 연습 횟수	: %d회\n", summary.getLongPracticeCount());
			System.out.printf("긴 글 평균 정확도	: %.2f%%\n", summary.getLongAvgAccuracy());
			System.out.printf("긴 글 평균 타속	: %d타/분\n", summary.getLongAvgSpeed());
		}

		scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
		choice = "33";
	}
}
