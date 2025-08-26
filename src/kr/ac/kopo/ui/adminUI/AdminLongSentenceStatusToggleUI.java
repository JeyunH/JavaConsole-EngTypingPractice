package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.LongSentenceVO;

public class AdminLongSentenceStatusToggleUI extends BaseUI { // choice = "3234"

	@Override
	public void excute() throws Exception {
		clearScreen();
		title("긴 문장 상태 변경");

		int sentNo = scanInt("상태를 변경할 문장 번호를 입력하세요: ");
		LongSentenceVO sentence = contentService.getLongSentenceByNo(sentNo);

		if (sentence == null) {
			System.out.println("해당 번호의 문장이 존재하지 않습니다.");
			Thread.sleep(1000);
			choice = "323";
			return;
		}

		String currentStatus = sentence.getStatus();
		String statusStr = currentStatus.equals("Y") ? "사용 중" : "비활성";
		String toggleTo = currentStatus.equals("Y") ? "비활성화" : "사용 가능";

		System.out.println("제목: " + sentence.getTitle());
		System.out.println("현재 상태: [" + statusStr + "]");
		String confirm = scanStr(toggleTo + " 상태로 변경하시겠습니까? (y/n): ");

		if (!confirm.equalsIgnoreCase("y")) {
			System.out.println("변경이 취소되었습니다.");
			Thread.sleep(1000);
			choice = "323";
			return;
		}

		boolean result = contentService.toggleLongSentenceStatus(sentNo);

		if (result) {
			System.out.println("상태가 성공적으로 변경되었습니다.");
		} else {
			System.out.println("상태 변경에 실패했습니다.");
		}

		scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
		choice = "323";
	}
}
