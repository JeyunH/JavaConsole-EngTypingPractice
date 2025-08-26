package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.ShortSentenceVO;

public class AdminShortSentenceStatusToggleUI extends BaseUI { // choice = "3224"

    @Override
    public void excute() throws Exception {
        clearScreen();
        title("짧은 문장 상태 변경");

        int sentNo = scanInt("상태를 변경할 문장 번호를 입력하세요: ");
        ShortSentenceVO sentence = contentService.getShortSentenceByNo(sentNo);

        if (sentence == null) {
            System.out.println("해당 번호의 문장이 존재하지 않습니다.");
            Thread.sleep(1000);
            choice = "322";
            return;
        }

        String statusStr = sentence.getStatus().equals("Y") ? "사용 중" : "비활성";
        String toggleStr = sentence.getStatus().equals("Y") ? "비활성화" : "사용 가능 상태";

        System.out.println("문장:");
        System.out.println("\"" + sentence.getContent() + "\"");
        System.out.println("현재 상태: [" + statusStr + "]");
        String confirm = scanStr(toggleStr + "로 변경하시겠습니까? (y/n): ");

        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("변경이 취소되었습니다.");
            Thread.sleep(1000);
            choice = "322";
            return;
        }

        boolean result = contentService.toggleShortSentenceStatus(sentNo);
        if (result) {
            System.out.println("문장 상태가 성공적으로 변경되었습니다.");
        } else {
            System.out.println("상태 변경에 실패했습니다.");
        }

        scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
        choice = "322";
    }
}
