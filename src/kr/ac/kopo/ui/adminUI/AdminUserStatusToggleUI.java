package kr.ac.kopo.ui.adminUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.util.LoginSession;
import kr.ac.kopo.vo.UserVO;

public class AdminUserStatusToggleUI extends BaseUI {

    @Override
    public void excute() throws Exception {
        clearScreen();
        title("사용자 상태 변경");

        String method = scanStr("검색 기준을 선택하세요 (1. 번호 / 2. ID) : ");
        UserVO user = null;

        if ("1".equals(method)) {
            int userNo = scanInt("사용자 번호 입력 : ");
            user = userService.getUserByNo(userNo);
        } else if ("2".equals(method)) {
            String userId = scanStr("사용자 ID 입력 : ");
            user = userService.getUserById(userId);
        } else {
            System.out.println("잘못된 선택입니다.");
            scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
            choice = "31";
            return;
        }

        if (user == null) {
            System.out.println("해당 사용자를 찾을 수 없습니다.");
            scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
            choice = "31";
            return;
        }

        // 자기 자신이면 막기
        if (user.getNo() == LoginSession.getLoginUser().getNo()) {
            System.out.println("자신의 계정 상태는 변경할 수 없습니다.");
            scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
            choice = "31";
            return;
        }

        // 현재 상태 확인
        boolean isActive = "Y".equals(user.getStatus());
        boolean isAdmin = "A".equals(user.getUserType());
        String currentStatusStr = isActive ? "정상" : "정지";
        String targetStatusStr = isActive ? "정지" : "정상";
        String userType = isAdmin ? "관리자" : "일반 사용자";
        
        System.out.println();        
        System.out.printf("사용자 ID : [%s]\n", user.getUserID());
        System.out.printf("사용자 유형 : [%s]\n", userType);
        System.out.printf("현재 상태: [%s]\n", currentStatusStr);
        String confirm = scanStr(targetStatusStr + " 상태로 변경하시겠습니까? (y/n) : ");

        if (confirm.equalsIgnoreCase("y")) {
            boolean result = userService.toggleUserStatus(user.getNo());

            if (result) {
                System.out.printf("사용자 상태가 [%s]로 변경되었습니다.\n", targetStatusStr);
            } else {
                System.out.println("상태 변경에 실패했습니다.");
            }
        } else {
            System.out.println("변경을 취소했습니다.");
        }

        scanStr("엔터를 누르면 이전 화면으로 돌아갑니다...");
        choice = "31";
    }
}
