package kr.ac.kopo.ui.adminUI;

import java.util.List;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.ShortSentenceVO;

public class AdminShortSentenceListUI extends BaseUI {	// choice = "3221"

    private static final int PAGE_SIZE = 6;

    @Override
    public void excute() throws Exception {
        List<ShortSentenceVO> list = contentService.getAllShortSentences();

        if (list == null || list.isEmpty()) {
            System.out.println("등록된 문장이 없습니다.");
            scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
            choice = "322";
            return;
        }

        int total = list.size();
        int totalPages = (int) Math.ceil(total / (double) PAGE_SIZE);
        int page = 1;

        while (true) {
            clearScreen();
            title("전체 짧은 글 목록");
            System.out.printf("\t\t(%d / %d 페이지)\n", page, totalPages);
            System.out.println("==================================================================================");
            System.out.printf("%-5s %-45s %-10s %-12s\n", "번호", "문장", "상태", "등록일");
            System.out.println("==================================================================================");

            int start = (page - 1) * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, total);

            for (int i = start; i < end; i++) {
                ShortSentenceVO s = list.get(i);
                String statusStr = s.getStatus().equals("Y") ? "사용 중" : "비활성";
                System.out.printf("%-5d %-45s %-10s %-12s\n",
                        s.getSentNo(),
                        s.getContent(),
                        statusStr,
                        s.getRegDate());
            }

            System.out.println("==================================================================================");
            System.out.println("[N] 다음 페이지  [P] 이전 페이지  [Q] 종료");
            String cmd = scanStr("선택 > ").toUpperCase();

            switch (cmd) {
                case "N" -> { if (page < totalPages) page++; }
                case "P" -> { if (page > 1) page--; }
                case "Q" -> {
                    choice = "322";
                    return;
                }
                default -> {
                    System.out.println("잘못된 입력입니다.");
                    scanStr("엔터를 누르면 계속합니다...");
                }
            }
        }
    }
}
