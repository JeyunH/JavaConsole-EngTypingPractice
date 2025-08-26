package kr.ac.kopo.ui.adminUI;

import java.util.List;

import kr.ac.kopo.service.UserService;
import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.UserVO;

public class AdminUserSearchUI extends BaseUI {

    @Override
    public void excute() throws Exception {
        clearScreen();
        title("사용자 검색");

        String keyword = scanStr("검색할 사용자 ID 또는 닉네임을 입력하세요: ");

        List<UserVO> results = userService.searchUsers(keyword);

        if (results == null || results.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
        	System.out.println("=====================================================");
            System.out.printf("%-5s %-15s %-15s %-10s %-10s\n", "번호", "아이디", "닉네임", "유형", "상태");
            System.out.println("=====================================================");
            for (UserVO user : results) {
            	String statusStr = user.getStatus().equals("Y") ? "정상" : "정지";

            	System.out.printf("%-5d %-15s %-15s %-10s %-10s\n",
            	    user.getNo(),
            	    user.getUserID(),
            	    user.getNickname(),
            	    user.getUserType().equals("A") ? "관리자" : "일반",
            	    statusStr);
            }
            System.out.println("=====================================================");
        }

        scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
        choice = "31";
    }
}
