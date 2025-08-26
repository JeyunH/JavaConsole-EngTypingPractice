package kr.ac.kopo.ui.adminUI;

import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.LongSentenceVO;

public class AdminLongSentenceUpdateUI extends BaseUI { // choice = "3233"

	@Override
	public void excute() throws Exception {
		clearScreen();
		title("긴 문장 수정");

		int sentNo = scanInt("수정할 문장 번호를 입력하세요: ");
		LongSentenceVO sentence = contentService.getLongSentenceByNo(sentNo);

		if (sentence == null) {
			System.out.println("해당 번호의 문장이 존재하지 않습니다.");
			Thread.sleep(1000);
			choice = "323";
			return;
		}

		String newTitle = sentence.getTitle();
		String newContent = sentence.getContent();

		System.out.println("현재 제목: " + newTitle);
		String tEdit = scanStr("제목을 수정하시겠습니까? (y/n): ");
		if (tEdit.equalsIgnoreCase("y")) {
			while (true) {
				String input = scanStr("새 제목 입력 (0 입력 시 취소): ").trim();
				if ("0".equals(input)) {
					System.out.println("수정을 취소했습니다.");
					Thread.sleep(1000);
					choice = "323";
					return;
				}
				if (input.isEmpty()) {
					System.out.println("제목은 비어 있을 수 없습니다.");
					continue;
				}
				if (contentService.existsOtherTitle(sentNo, input)) {
					System.out.println("이미 사용 중인 제목입니다.");
					continue;
				}
				newTitle = input;
				break;
			}
		}

		System.out.println("\n현재 본문:");
		System.out.println("-------------------------------------");
		System.out.println(newContent);
		System.out.println("-------------------------------------");

		String cEdit = scanStr("본문을 수정하시겠습니까? (y/n): ");
		if (cEdit.equalsIgnoreCase("y")) {
			System.out.println("새 본문을 입력하세요. (입력 종료: 0)");
			List<String> lines = new ArrayList<>();
			while (true) {
				String line = scanStr("");
				if ("0".equals(line))
					break;
				lines.add(line);
			}
			if (!lines.isEmpty()) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < lines.size(); i++) {
					sb.append(lines.get(i));
					if (i < lines.size() - 1)
						sb.append("\n");
				}
				newContent = sb.toString();
			} else {
				System.out.println("본문이 비어 있어 수정을 취소합니다.");
				Thread.sleep(1000);
				choice = "323";
				return;
			}
		}

		// 미리보기
		clearScreen();
		title("긴 문장 수정 - 확인");
		System.out.println("제목 : " + newTitle);
		System.out.println("-------------------------------------");
		System.out.println(newContent);
		System.out.println("-------------------------------------");
		System.out.printf("총 줄 수 : %d\n", newContent.split("\n").length);

		String confirm = scanStr("이 내용으로 수정하시겠습니까? (y/n): ");
		if (!confirm.equalsIgnoreCase("y")) {
			System.out.println("수정이 취소되었습니다.");
			Thread.sleep(1000);
			choice = "323";
			return;
		}

		boolean result = contentService.updateLongSentence(sentNo, newTitle, newContent);
		if (result) {
			System.out.println("문장이 성공적으로 수정되었습니다.");
		} else {
			System.out.println("수정에 실패했습니다.");
		}

		scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
		choice = "323";
	}
}
