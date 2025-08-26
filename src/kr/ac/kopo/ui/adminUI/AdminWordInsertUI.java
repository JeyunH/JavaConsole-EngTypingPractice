package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;

public class AdminWordInsertUI extends BaseUI {

	@Override
	public void excute() throws Exception { 	// choice = "3212"
		clearScreen();
		title("낱말 등록");

		while (true) {
			String input = scanStr("등록할 단어를 입력하세요 (0 입력 시 취소): ").trim();

			if ("0".equals(input)) {
				System.out.println("등록을 취소했습니다.");
				Thread.sleep(1000);
				choice = "321";
				return;
			}

			if (input.isEmpty()) {
				System.out.println("단어는 비어 있을 수 없습니다.");
				continue;
			}

			boolean result = contentService.addWord(input);

			if (result) {
				System.out.println("낱말이 등록되었습니다.");
			} else {
				System.out.println("낱말 등록에 실패했습니다.");
			}

			scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
			choice = "321";
			return;
		}
	}
}
