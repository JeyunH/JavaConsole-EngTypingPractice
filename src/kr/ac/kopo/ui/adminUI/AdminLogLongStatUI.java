package kr.ac.kopo.ui.adminUI;

import java.util.List;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.LongLogSentenceRankVO;
import kr.ac.kopo.vo.LongLogStatVO;

public class AdminLogLongStatUI extends BaseUI { // choice = "3323"

	private static final int PAGE_SIZE = 5;

	@Override
	public void excute() throws Exception {
		LongLogStatVO stat = logService.getLongLogStat();
		List<LongLogSentenceRankVO> rankList = logService.getLongLogSentenceRank();

		if (stat == null) {
			System.out.println("통계 정보를 불러올 수 없습니다.");
			scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
			choice = "332";
			return;
		}

		int total = rankList.size();
		int totalPages = (int) Math.ceil(total / (double) PAGE_SIZE);
		int page = 1;

		while (true) {
			clearScreen();
			title("< 긴 문장 연습 통계 >");

			System.out.printf("총 연습 횟수	: %d회\n", stat.getPracticeCount());
			System.out.printf("총 라인 수		: %d줄\n", stat.getLineCount());

			int totalSec = (int) stat.getTotalTime();
			int hr = totalSec / 3600;
			int min = (totalSec % 3600) / 60;
			int sec = totalSec % 60;
			System.out.printf("총 연습 시간	: %d시간 %d분 %d초\n", hr, min, sec);

			System.out.printf("평균 정확도	: %.2f%%\n", stat.getAvgAccuracy());
			System.out.printf("평균 타속		: %d타/분\n", stat.getAvgSpeed());
			System.out.printf("최고 타속		: %d타/분\n", stat.getMaxSpeed());

			System.out.println("\n[ 문장별 연습 순위 ]");
			System.out.printf("(%d / %d 페이지)\n", page, totalPages);
			System.out.println("------------------------------------------------------------");
			System.out.printf("%-5s %-32s %s\n", "번호", "제목", "연습 횟수");
			System.out.println("------------------------------------------------------------");

			int start = (page - 1) * PAGE_SIZE;
			int end = Math.min(start + PAGE_SIZE, total);

			for (int i = start; i < end; i++) {
				LongLogSentenceRankVO row = rankList.get(i);
				System.out.printf("%-5d %-35s %d회\n", row.getSentenceNo(), row.getTitle(), row.getPracticeCount());
			}

			System.out.println("------------------------------------------------------------");
			System.out.println("[N] 다음 페이지  [P] 이전 페이지  [Q] 종료");
			String cmd = scanStr("선택 > ").toUpperCase();

			switch (cmd) {
			case "Q" -> {
				choice = "332";
				return;
			}
			case "N" -> {
				if (page < totalPages)
					page++;
			}
			case "P" -> {
				if (page > 1)
					page--;
			}
			default -> {
				System.out.println("잘못된 입력입니다.");
				Thread.sleep(1000);
			}
			}
		}
	}
}
