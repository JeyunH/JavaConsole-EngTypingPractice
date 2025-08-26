package kr.ac.kopo.ui.accessUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.UserVO;

public class LoginUI extends BaseUI {

	@Override
	public void excute() throws Exception { // choice = "11"
		clearScreen();
		title("로그인");
		UserVO user = new UserVO();
		String id = scanStr("ID를 입력하세요 : ");
		String pwd = scanStr("비밀번호를 입력하세요 : ");
		user.setUserID(id);
		user.setPassword(pwd);		
		UserVO loggedinUser = userService.login(user);	// 로그인 서비스 호출
		if (loggedinUser != null) {
			if(loggedinUser.getUserType().equals("A")) {
				System.out.println();
				System.out.println("-------------------------------------");
				System.out.println("관리자 계정으로 로그인합니다.");
				System.out.println("-------------------------------------");
				Thread.sleep(1000);
				choice = "99";
				return;
			}			
		}else {
			System.out.println();
			System.out.println("-------------------------------------");
			System.out.println("ID 또는 비밀번호가 일치하지 않습니다");
			System.out.println("-------------------------------------");
			Thread.sleep(1000);
			choice = "1"; // 접속 페이지로 이동
			return;
		}
		choice = "2"; 	// 타자 연습 페이지로 이동
	}
}