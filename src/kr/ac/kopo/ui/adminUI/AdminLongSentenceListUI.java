package kr.ac.kopo.ui.adminUI;

import java.util.List;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.LongSentenceVO;

public class AdminLongSentenceListUI extends BaseUI { // choice = "3231"

	private static final int PAGE_SIZE = 6;

	@Override
	public void excute() throws Exception {
		List<LongSentenceVO> list = contentService.getAllLongSentences();

		if (list == null || list.isEmpty()) {
			System.out.println("등록된 문장이 없습니다.");
			scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
			choice = "323";
			return;
		}

		int total = list.size();
		int totalPages = (int) Math.ceil(total / (double) PAGE_SIZE);
		int page = 1;

		while (true) {
			clearScreen();
			title("전체 긴 문장 목록");
			System.out.printf("\t\t(%d / %d 페이지)\n", page, totalPages);
			System.out.println("===============================================================");
			System.out.printf("%-5s %-40s %-8s %-10s\n", "번호", "제목", "라인수", "상태");
			System.out.println("===============================================================");

			int start = (page - 1) * PAGE_SIZE;
			int end = Math.min(start + PAGE_SIZE, total);

			for (int i = start; i < end; i++) {
				LongSentenceVO l = list.get(i);
				String statusStr = l.getStatus().equals("Y") ? "사용 중" : "비활성";
				System.out.printf("%-5d %-40s %-8d %-10s\n", l.getSentenceNo(), l.getTitle(), l.getLineCount(),
						statusStr);
			}

			System.out.println("===============================================================");
			System.out.println("[번호 입력: 본문 확인] [N] 다음 [P] 이전 [Q] 종료");
			String cmd = scanStr("선택 > ").toUpperCase();

			if ("Q".equals(cmd)) {
				choice = "323";
				return;
			} else if ("N".equals(cmd)) {
				if (page < totalPages)
					page++;
			} else if ("P".equals(cmd)) {
				if (page > 1)
					page--;
			} else if (cmd.matches("\\d+")) {
				int selectedNo = Integer.parseInt(cmd);
				LongSentenceVO selected = contentService.getLongSentenceByNo(selectedNo);
				if (selected != null) {
					clearScreen();
					title("긴 문장 상세 보기");
					System.out.println("제목: " + selected.getTitle());
					System.out.println("--------------------------------------------------");
					System.out.println(selected.getContent());
					System.out.println("--------------------------------------------------");
					System.out.printf("총 줄 수: %d\n", selected.getLineCount());
					scanStr("엔터를 누르면 목록으로 돌아갑니다...");
				} else {
					System.out.println("해당 번호의 문장이 존재하지 않습니다.");
					Thread.sleep(1000);
				}
			} else {
				System.out.println("잘못된 입력입니다.");
				Thread.sleep(1000);
			}
		}
	}
}
