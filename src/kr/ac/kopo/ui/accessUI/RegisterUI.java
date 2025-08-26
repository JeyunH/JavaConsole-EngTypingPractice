package kr.ac.kopo.ui.accessUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.vo.UserVO;

public class RegisterUI extends BaseUI{

	@Override
	public void excute() throws Exception {		// choice = "12"
		clearScreen();
		title("회원가입");
				
		String id = scanStr("사용할 ID를 입력하세요 : ");
		
		UserVO user = new UserVO();
		user.setUserID(id);
		while(true) {
			if(userService.isInputIdDuplicate(user)) {	//사용자ID 중복 체크 서비스 호출 -> 중복 여부에 따라 true/false 반환
				System.out.println("\n-------------------------------------");
				System.out.println("\t입력한 ID가 이미 존재합니다.");
				System.out.println("-------------------------------------\n");
				id = scanStr("다시 사용할 ID를 입력하세요 : ");
				user.setUserID(id);
			}else {
				break;
			}
		}
		
		String pwd;
		String pwd_check;
		while(true) {		//비밀번호 확인
			pwd = scanStr("비밀번호를 입력하세요 : ");
			pwd_check = scanStr("비밀번호 확인 : ");
			if(pwd.equals(pwd_check)) {
				break;
			}else {
				System.out.println("-------------------------------------");
				System.out.println("\t비밀번호가 틀렸습니다");
				System.out.println("-------------------------------------");
			}
		}
		String nick = scanStr("닉네임을 입력하세요 : ");
				
		user.setPassword(pwd);
		user.setNickname(nick);
		userService.registerUser(user);		//회원 등록 서비스를 호출
		Thread.sleep(300);
		System.out.println("\n-------------------------------------");
		System.out.println("\t계정이 생성되었습니다");
		System.out.println("-------------------------------------");
		Thread.sleep(1000);
		choice = "1";		// 접속 페이지로 이동
	}
}
