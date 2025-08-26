package kr.ac.kopo.ui.userInfoUI;

import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.util.LoginSession;

public class MyInfoPageUI extends BaseUI {

	@Override
	public void excute() throws Exception { // choice = "24"
		clearScreen();
		title("내 정보");
		System.out.println("\t회원ID : \t" + LoginSession.getLoginUser().getUserID());
		System.out.println("\t닉네임 : \t" + LoginSession.getLoginUser().getNickname());
		System.out.println("\t회원유형 : " + LoginSession.getLoginUser().getUserType());
		System.out.println("\t가입일 : \t" + LoginSession.getLoginUser().getRegdate());
		System.out.println("-------------------------------------");
		System.out.println("\t1. 닉네임 변경");
		System.out.println("\t2. 비밀번호 변경");
		System.out.println("\t3. 회원 탈퇴");
		System.out.println("\t4. 개인 연습 기록 요약");
		System.out.println("\t9. 로그아웃");
		System.out.println("\t0. 뒤로");
		System.out.println("-------------------------------------");
		choice = "24" + scanStr("항목을 선택하세요 : ");

		a: if (choice.equals("241")) { // 닉네임 변경 선택
			String inputNick = scanStr("변경할 닉네임을 입력하세요 : ");
			if(inputNick.equals("")) {
				System.out.println("빈 문자는 입력하실 수 없습니다.");
				Thread.sleep(2000);
				choice = "24";
				break a;
			}
			userService.changeNickname(inputNick); // 닉네임 변경 서비스 호출
			choice = "24"; // 이전 페이지로 이동
		}

		if (choice.equals("242")) { // 비밀번호 변경 선택
			String currentPw = scanStr("현재 비밀번호를 입력하세요: ");

			b: if (userService.isCurrentPasswordCorrect(currentPw)) { // 비밀번호 확인
				String newPw = scanStr("새 비밀번호를 입력하세요: ");
				if(newPw.equals("")) {
					System.out.println("빈 문자는 입력하실 수 없습니다.");
					Thread.sleep(2000);
					break b;
				}
				userService.changePassword(newPw); // 비밀번호 변경 서비스 호출
				System.out.println("비밀번호가 성공적으로 변경되었습니다.");
				Thread.sleep(2000);
			} else {
				System.out.println("비밀번호가 일치하지 않습니다.");
				Thread.sleep(2000);
			}
			choice = "24"; // 이전 페이지로 이동
		}

		if (choice.equals("243")) { // 회원 탈퇴 선택
			String pwd = scanStr("비밀번호를 입력하세요: ");
			if (userService.isCurrentPasswordCorrect(pwd)) { // 비밀번호 확인
				String check;
				a: while (true) {
					check = scanStr("계정을 정말 삭제하시겠습니까?[y/n]");
					switch (check.toLowerCase()) {
					case "y":
						userService.deleteUser(); // 계정 삭제 서비스 호출
						System.out.println("계정이 삭제되었습니다.");
						Thread.sleep(2000);
						LoginSession.setLogoutUser(); // 로그인 세션 로그아웃
						choice = "1"; // 접속 페이지로 이동
						break a;
					case "n":
						choice = "24"; // 이전 페이지로 이동
						Thread.sleep(1000);
						break a;
					}
				}
			} else { // 비밀번호 틀릴 경우
				System.out.print("비밀번호가 일치하지 않습니다.");
				Thread.sleep(2000);
				choice = "24"; // 이전 페이지로 이동
			}
		}
	}
}
