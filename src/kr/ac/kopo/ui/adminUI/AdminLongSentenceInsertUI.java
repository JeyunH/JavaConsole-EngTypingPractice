package kr.ac.kopo.ui.adminUI;

import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.ui.BaseUI;

public class AdminLongSentenceInsertUI extends BaseUI { // choice = "3232"

    @Override
    public void excute() throws Exception {
        clearScreen();
        title("긴 문장 등록");

        String title = null;

        // 제목 입력 및 중복 검사
        while (true) {
            title = scanStr("제목을 입력하세요 (0 입력 시 취소): ").trim();

            if ("0".equals(title)) {
                System.out.println("등록을 취소했습니다.");
                Thread.sleep(1000);
                choice = "323";
                return;
            }

            if (title.isEmpty()) {
                System.out.println("제목은 비어 있을 수 없습니다.");
                continue;
            }

            if (contentService.existsLongSentenceTitle(title)) {
                System.out.println("이미 존재하는 제목입니다. 다른 제목을 입력하세요.");
                continue;
            }

            break; // 중복 없고 유효한 제목
        }

        // 본문 입력
        System.out.println("\n본문을 입력하세요. (입력을 마치려면 0 입력)");
        List<String> lines = new ArrayList<>();
        while (true) {
            String line = scanStr("");
            if ("0".equals(line)) break;
            lines.add(line);
        }

        if (lines.isEmpty()) {
            System.out.println("본문이 비어 있어 등록을 취소합니다.");
            Thread.sleep(1000);
            choice = "323";
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            sb.append(lines.get(i));
            if (i < lines.size() - 1) sb.append("\n");
        }
        String content = sb.toString();

        // 미리보기
        clearScreen();
        title("긴 문장 등록 - 확인");
        System.out.println("제목 : " + title);
        System.out.println("-------------------------------------");
        System.out.println(content);
        System.out.println("-------------------------------------");
        System.out.printf("총 줄 수 : %d\n", lines.size());

        String confirm = scanStr("이 내용을 등록하시겠습니까? (y/n): ");
        if (!confirm.equalsIgnoreCase("y")) {
            System.out.println("등록이 취소되었습니다.");
            Thread.sleep(1000);
            choice = "323";
            return;
        }
        
        String contentWithCRLF = content.replace("\n", "\r\n");
        boolean result = contentService.addLongSentence(title, contentWithCRLF);

        if (result) {
            System.out.println("긴 문장이 성공적으로 등록되었습니다.");
        } else {
            System.out.println("등록에 실패했습니다.");
        }

        scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
        choice = "323";
    }
}
