package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;

public class AdminShortSentenceInsertUI extends BaseUI {

	@Override
	public void excute() throws Exception { // choice = "3222"
		clearScreen();
		title("짧은 문장 등록");

		while (true) {
			String content = scanStr("등록할 문장을 입력하세요 (0 입력 시 취소): ").trim();

			if (content.isEmpty()) {
				System.out.println("문장은 비어 있을 수 없습니다.");
				continue;
			} else if ("0".equals(content)) {
				System.out.println("등록을 취소했습니다.");
				Thread.sleep(1000);
				choice = "322";
				return;
			} else if (content.length() < 10) {
				System.out.println("문장이 너무 짧습니다. 10글자 이상 입력하세요.");
				continue;
			}

			System.out.println("\n입력한 문장:");
			System.out.println("\"" + content + "\"");

			String confirm = scanStr("이 문장을 등록하시겠습니까? (y/n): ");
			System.out.println();
			if (!confirm.equalsIgnoreCase("y")) {
				System.out.println("등록이 취소되었습니다.");
				Thread.sleep(1000);
				choice = "322";
				return;
			}

			boolean result = contentService.addShortSentence(content);

			if (result) {
				System.out.println("문장이 성공적으로 등록되었습니다.");
			} else {
				System.out.println("이미 등록된 문장입니다.");
			}

			scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
			choice = "322";
			return;
		}
	}
}
