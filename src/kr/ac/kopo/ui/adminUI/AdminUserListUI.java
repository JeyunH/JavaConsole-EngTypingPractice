package kr.ac.kopo.ui.adminUI;

import java.util.List;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.UserVO;

public class AdminUserListUI extends BaseUI {

    private static final int PAGE_SIZE = 5;

    @Override
    public void excute() throws Exception {		// choice = "311"
        clearScreen();
        title("전체 사용자 목록");

        List<UserVO> userList = userService.getAllUsers();

        if (userList == null || userList.isEmpty()) {
            System.out.println("등록된 사용자가 없습니다.");
            scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
            return;
        }

        int totalUsers = userList.size();
        int totalPages = (int) Math.ceil(totalUsers / (double) PAGE_SIZE);
        int currentPage = 1;

        while (true) {
            clearScreen();
            title(String.format("전체 사용자 목록 (%d / %d 페이지)", currentPage, totalPages));
            System.out.println("=====================================================");
            System.out.printf("%-5s %-15s %-15s %-10s %-10s\n", "번호", "아이디", "닉네임", "유형", "상태");
            System.out.println("=====================================================");

            int start = (currentPage - 1) * PAGE_SIZE;
            int end = Math.min(start + PAGE_SIZE, totalUsers);

            for (int i = start; i < end; i++) {
                UserVO user = userList.get(i);
                String statusStr = user.getStatus().equals("Y") ? "정상" : "정지";

                System.out.printf("%-5d %-15s %-15s %-10s %-10s\n",
                    user.getNo(),
                    user.getUserID(),
                    user.getNickname(),
                    user.getUserType().equals("A") ? "관리자" : "일반",
                    statusStr);
            }
            System.out.println("=====================================================");

            System.out.println("[N] 다음 페이지  [P] 이전 페이지  [Q] 나가기");
            String input = scanStr("선택 > ").toUpperCase();

            switch (input) {
                case "N":
                    if (currentPage < totalPages) currentPage++;
                    break;
                case "P":
                    if (currentPage > 1) currentPage--;
                    break;
                case "Q":
                	choice = "31";
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
                    scanStr("엔터를 누르면 계속합니다...");
            }
        }
    }
}

