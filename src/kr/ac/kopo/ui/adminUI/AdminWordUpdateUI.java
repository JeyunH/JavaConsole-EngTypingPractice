package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.WordVO;

public class AdminWordUpdateUI extends BaseUI {

	@Override
	public void excute() throws Exception {		 // choice = "3213"
		clearScreen();
		title("낱말 수정");

		int wordNo = scanInt("수정할 낱말 번호 입력: ");
		WordVO word = contentService.getWordByNo(wordNo);

		if (word == null) {
			System.out.println("해당 번호의 낱말이 존재하지 않습니다.");
			Thread.sleep(1000);
			choice = "321";
			return;
		}

		System.out.println("현재 단어: " + word.getContent());

		String newContent = scanStr("새 단어 입력 (취소: 0) : ").trim();
		if ("0".equals(newContent)) {
			System.out.println("수정을 취소했습니다.");
			Thread.sleep(1000);
			choice = "321";
			return;
		}

		if (newContent.isEmpty()) {
			System.out.println("단어는 비어 있을 수 없습니다.");
			Thread.sleep(1000);
			choice = "321";
			return;
		}

		String confirm = scanStr("정말 수정하시겠습니까? (y/n) : ");
		if (!confirm.equalsIgnoreCase("y")) {
			System.out.println("수정이 취소되었습니다.");
			Thread.sleep(1000);
			choice = "321";
			return;
		}

		boolean result = contentService.updateWord(wordNo, newContent);
		if (result) {
			System.out.println("단어가 수정되었습니다.");
		} else {
			System.out.println("수정에 실패했습니다.");
		}

		scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
		choice = "321";
	}
}
