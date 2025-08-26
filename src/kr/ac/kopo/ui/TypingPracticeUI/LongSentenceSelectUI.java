package kr.ac.kopo.ui.TypingPracticeUI;

import java.util.List;

import kr.ac.kopo.service.TypingService;
import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.LongSentenceVO;

public class LongSentenceSelectUI extends BaseUI {	// choice = "23"

	private final int PAGE_SIZE = 4;

	@Override
	public void excute() throws Exception {
		int page = 1;
		while (true) {
			List<LongSentenceVO> list = TypingService.getLongSentencePage(page, PAGE_SIZE);
			int totalCount = TypingService.getLongSentenceCount();
			int totalPages = (int) Math.ceil(totalCount / (double) PAGE_SIZE);

			clearScreen();
			typingTitle("긴 글 선택");

			if (list == null || list.isEmpty()) {
				System.out.println("연습할 긴 글이 없습니다.");
				scanStr("엔터를 누르면 뒤로 이동합니다...");
				choice = "2";
				return;
			}

			System.out.printf("[페이지 %d / %d]\n", page, totalPages);
			System.out.println("번호\t줄 수\t제목");
			bar();

			for (LongSentenceVO vo : list) {
				System.out.printf("%d\t%d줄\t%s\n", vo.getSentenceNo(), vo.getLineCount(), vo.getTitle());
			}

			System.out.println("\n옵션: [P]이전 [N]다음 [번호 입력: 선택] [Q]뒤로");
			String cmd = scanStr("입력: ").trim().toUpperCase();

			if (cmd.equals("Q")) {
				choice = "2";
				return;
			} else if (cmd.equals("P")) {
				if (page > 1)
					page--;
			} else if (cmd.equals("N")) {
				if (page < totalPages)
					page++;
			} else {
				try {
					int sentenceNo = Integer.parseInt(cmd);
					LongSentenceVO selectedSentence = TypingService.selectLongSentence(sentenceNo);
					if (selectedSentence != null) {
						setSelectedSentence(selectedSentence);
						choice = "231"; // 이후 연습 UI에서 처리
						return;
					} else {
						System.out.println("해당 번호의 글이 없습니다.");
						Thread.sleep(1000);
					}
				} catch (NumberFormatException e) {
					System.out.println("잘못된 입력입니다.");
					Thread.sleep(1000);
				}
			}
		}
	}
}