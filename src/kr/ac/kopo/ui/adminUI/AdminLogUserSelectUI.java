package kr.ac.kopo.ui.adminUI;

import java.util.List;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.UserLogSummaryVO;
import kr.ac.kopo.vo.UserVO;

public class AdminLogUserSelectUI extends BaseUI { // choice = "333"

	private static final int PAGE_SIZE = 5;

	@Override
	public void excute() throws Exception {
		List<UserVO> userList = userService.getAllUsers();

		if (userList == null || userList.isEmpty()) {
			System.out.println("등록된 사용자가 없습니다.");
			scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
			choice = "33";
			return;
		}

		int total = userList.size();
		int totalPages = (int) Math.ceil(total / (double) PAGE_SIZE);
		int page = 1;

		while (true) {
			clearScreen();
			title("사용자 선택");

			System.out.printf("(%d / %d 페이지)\n", page, totalPages);
			bar();
			System.out.printf("%-5s %-15s %-10s\n", "번호", "ID", "닉네임");
			bar();

			int start = (page - 1) * PAGE_SIZE;
			int end = Math.min(start + PAGE_SIZE, total);

			for (int i = start; i < end; i++) {
				UserVO user = userList.get(i);
				System.out.printf("%-5d %-15s %-10s\n", user.getNo(), user.getUserID(), user.getNickname());
			}

			bar();
			System.out.println("[번호] 선택 | [N] 다음 | [P] 이전 | [Q] 종료");
			String cmd = scanStr("선택 > ").toUpperCase();

			if (cmd.equals("Q")) {
				choice = "33";
				return;
			} else if (cmd.equals("N")) {
				if (page < totalPages)
					page++;
			} else if (cmd.equals("P")) {
				if (page > 1)
					page--;
			} else if (cmd.matches("\\d+")) {
				int selectedNo = Integer.parseInt(cmd);
				boolean exists = userList.stream().anyMatch(u -> u.getNo() == selectedNo);

				if (exists) {
					clearScreen();
					title("사용자 연습 통계 요약");

					UserLogSummaryVO stat = logService.getUserLogSummary(selectedNo);
					if (stat == null || stat.isEmpty()) {
						System.out.println("해당 사용자의 연습 로그가 없습니다.");
						Thread.sleep(1500);
						choice = "333";
						return;
					}

					System.out.printf("회원번호: %d\n", selectedNo);
					System.out.println("------------------------------------");

					if (stat.getWordCount() > 0) {
						System.out.printf("<낱말 연습>\n- 총 횟수: %d회\n- 평균 정확도: %.2f%%\n\n", stat.getWordCount(),
								stat.getWordAccuracy());
					}

					if (stat.getShortCount() > 0) {
						System.out.printf("<짧은 문장>\n- 총 횟수: %d회\n- 평균 정확도: %.2f%%\n- 평균 타속: %d타/분\n\n",
								stat.getShortCount(), stat.getShortAccuracy(), stat.getShortAvgSpeed());
					}

					if (stat.getLongCount() > 0) {
						System.out.printf("<긴 문장>\n- 총 횟수: %d회\n- 평균 정확도: %.2f%%\n- 평균 타속: %d타/분\n",
								stat.getLongCount(), stat.getLongAccuracy(), stat.getLongAvgSpeed());
					}

					scanStr("엔터를 누르면 사용자 목록으로 돌아갑니다...");
					// 통계 출력 후 다시 사용자 목록으로 돌아감
				} else {
					System.out.println("해당 번호의 사용자가 존재하지 않습니다.");
					Thread.sleep(1000);
				}
			} else {
				System.out.println("잘못된 입력입니다.");
				Thread.sleep(1000);
			}
		}
	}
}
