package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.WordVO;

public class AdminWordStatusToggleUI extends BaseUI {

    @Override
    public void excute() throws Exception { 	// choice = "3214"
        clearScreen();
        title("낱말 상태 변경");

        int wordNo = scanInt("상태를 변경할 낱말 번호를 입력하세요: ");
        WordVO word = contentService.getWordByNo(wordNo);

        if (word == null) {
            System.out.println("해당 번호의 낱말이 존재하지 않습니다.");
            Thread.sleep(1000);
            choice = "321";
            return;
        }

        String statusStr = word.getStatus().equals("Y") ? "사용 중" : "비활성";
        String toggleStr = word.getStatus().equals("Y") ? "비활성화" : "사용 가능 상태";

        System.out.printf("선택된 단어: %s\n", word.getContent());
        System.out.printf("현재 상태: [%s]\n", statusStr);
        String confirm = scanStr(toggleStr + "로 변경하시겠습니까? (y/n) : ");

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("변경을 취소했습니다.");
            Thread.sleep(1000);
            choice = "321";
            return;
        }

        boolean result = contentService.toggleWordStatus(wordNo);

        if (result) {
            System.out.println("낱말 상태가 변경되었습니다.");
        } else {
            System.out.println("상태 변경에 실패했습니다.");
        }

        scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
        choice = "321";
    }
}
