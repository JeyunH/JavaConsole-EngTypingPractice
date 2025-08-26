package kr.ac.kopo.ui;

import kr.ac.kopo.ui.TypingPracticeUI.LongSentenceSelectUI;
import kr.ac.kopo.ui.TypingPracticeUI.LongSentenceTypingUI;
import kr.ac.kopo.ui.TypingPracticeUI.ShortSentenceTypingUI;
import kr.ac.kopo.ui.TypingPracticeUI.TypingPageUI;
import kr.ac.kopo.ui.TypingPracticeUI.WordTypingUI;
import kr.ac.kopo.ui.accessUI.AccessPageUI;
import kr.ac.kopo.ui.accessUI.LoginUI;
import kr.ac.kopo.ui.accessUI.RegisterUI;
import kr.ac.kopo.ui.adminUI.AdminContentTypeSelectUI;
import kr.ac.kopo.ui.adminUI.AdminLogByTypeSelectUI;
import kr.ac.kopo.ui.adminUI.AdminLogLongStatUI;
import kr.ac.kopo.ui.adminUI.AdminLogMenuSelectUI;
import kr.ac.kopo.ui.adminUI.AdminLogShortStatUI;
import kr.ac.kopo.ui.adminUI.AdminLogSummaryUI;
import kr.ac.kopo.ui.adminUI.AdminLogUserSelectUI;
import kr.ac.kopo.ui.adminUI.AdminLogWordStatUI;
import kr.ac.kopo.ui.adminUI.AdminLongSentenceInsertUI;
import kr.ac.kopo.ui.adminUI.AdminLongSentenceListUI;
import kr.ac.kopo.ui.adminUI.AdminLongSentenceManageUI;
import kr.ac.kopo.ui.adminUI.AdminLongSentenceStatusToggleUI;
import kr.ac.kopo.ui.adminUI.AdminLongSentenceUpdateUI;
import kr.ac.kopo.ui.adminUI.AdminPageUI;
import kr.ac.kopo.ui.adminUI.AdminShortSentenceInsertUI;
import kr.ac.kopo.ui.adminUI.AdminShortSentenceListUI;
import kr.ac.kopo.ui.adminUI.AdminShortSentenceManageUI;
import kr.ac.kopo.ui.adminUI.AdminShortSentenceStatusToggleUI;
import kr.ac.kopo.ui.adminUI.AdminShortSentenceUpdateUI;
import kr.ac.kopo.ui.adminUI.AdminUserListUI;
import kr.ac.kopo.ui.adminUI.AdminUserManageUI;
import kr.ac.kopo.ui.adminUI.AdminUserSearchUI;
import kr.ac.kopo.ui.adminUI.AdminUserStatusToggleUI;
import kr.ac.kopo.ui.adminUI.AdminWordInsertUI;
import kr.ac.kopo.ui.adminUI.AdminWordListUI;
import kr.ac.kopo.ui.adminUI.AdminWordManageUI;
import kr.ac.kopo.ui.adminUI.AdminWordStatusToggleUI;
import kr.ac.kopo.ui.adminUI.AdminWordUpdateUI;
import kr.ac.kopo.ui.userInfoUI.MyInfoPageUI;
import kr.ac.kopo.ui.userInfoUI.PracticeLogSummaryUI;
import kr.ac.kopo.util.LoginSession;

public class MainUI extends BaseUI { // main에서 호출되어 UI를 선택적으로 호출하는 클래스

	@Override
	public void excute() throws Exception {
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");
		while (true) {

			IEngTypingPracticeUI ui = null;
			switch (choice) {
			case "1":
				ui = new AccessPageUI();
				break;
			case "11":
				ui = new LoginUI();
				break;
			case "12":
				ui = new RegisterUI();
				break;
			case "10":
			case "20":
			case "30":
				ui = new ExitUI();
				break;
			case "2":
			case "240":
				ui = new TypingPageUI();
				break;
			case "21":
				ui = new WordTypingUI();
				break;
			case "22":
				ui = new ShortSentenceTypingUI();
				break;
			case "23":
				ui = new LongSentenceSelectUI();
				break;
			case "231":
				ui = new LongSentenceTypingUI();
				break;
			case "24":
				ui = new MyInfoPageUI();
				break;
			case "244":
				ui = new PracticeLogSummaryUI();
				break;
			case "31":
				ui = new AdminUserManageUI();
				break;
			case "311":
				ui = new AdminUserListUI();
				break;
			case "312":
				ui = new AdminUserSearchUI();
				break;
			case "313":
				ui = new AdminUserStatusToggleUI();
				break;
			case "32":
			case "3210":
			case "3220":
			case "3230":
				ui = new AdminContentTypeSelectUI();
				break;
			case "321":
				ui = new AdminWordManageUI();
				break;
			case "3211":
				ui = new AdminWordListUI();
				break;
			case "3212":
				ui = new AdminWordInsertUI();
				break;
			case "3213":
				ui = new AdminWordUpdateUI();
				break;
			case "3214":
				ui = new AdminWordStatusToggleUI();
				break;
			case "322":
				ui = new AdminShortSentenceManageUI();
				break;
			case "3221":
				ui = new AdminShortSentenceListUI();
				break;
			case "3222":
				ui = new AdminShortSentenceInsertUI();
				break;
			case "3223":
				ui = new AdminShortSentenceUpdateUI();
				break;
			case "3224":
				ui = new AdminShortSentenceStatusToggleUI();
				break;
			case "323":
				ui = new AdminLongSentenceManageUI();
				break;
			case "3231":
				ui = new AdminLongSentenceListUI();
				break;
			case "3232":
				ui = new AdminLongSentenceInsertUI();
				break;
			case "3233":
				ui = new AdminLongSentenceUpdateUI();
				break;
			case "3234":
				ui = new AdminLongSentenceStatusToggleUI();
				break;
			case "33": case "3320" :
				ui = new AdminLogMenuSelectUI();
				break;
			case "331":
				ui = new AdminLogSummaryUI();
				break;
			case "332":
				ui = new AdminLogByTypeSelectUI();
				break;
			case "3321":
				ui = new AdminLogWordStatUI();
				break;
			case "3322":
				ui = new AdminLogShortStatUI();
				break;
			case "3323":
				ui = new AdminLogLongStatUI();
				break;
			case "333":
				ui = new AdminLogUserSelectUI();
				break;
			case "99":
			case "310":
			case "320":
			case "330":
				ui = new AdminPageUI();
				break;
			case "249":
			case "39":
			case "29": // 로그아웃 선택
				clearScreen();
				LoginSession.setLogoutUser(); // 로그인 세션 로그아웃
				ui = new AccessPageUI();
				choice = "1"; // 접속 페이지로 이동
				break;
			}

			if (ui != null) {
				preChoice = choice;
				ui.excute();
			} else {
				System.out.println("-------------------------------------");
				System.out.println("\t항목을 잘못 선택하셨습니다");
				System.out.println("-------------------------------------");
				Thread.sleep(1000);
				choice = preChoice;
			}

		}

	}

}
