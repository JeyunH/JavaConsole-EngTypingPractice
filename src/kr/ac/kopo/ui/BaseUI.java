package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.service.ContentService;
import kr.ac.kopo.service.ContentServiceFactory;
import kr.ac.kopo.service.LogService;
import kr.ac.kopo.service.LogServiceFactory;
import kr.ac.kopo.service.TypingService;
import kr.ac.kopo.service.TypingServiceFactory;
import kr.ac.kopo.service.UserService;
import kr.ac.kopo.service.UserServiceFactory;
import kr.ac.kopo.util.LoginSession;
import kr.ac.kopo.vo.LongSentenceVO;

public abstract class BaseUI implements IEngTypingPracticeUI {

	private Scanner sc;
	protected static String choice = "1";
	protected static String preChoice;
	protected UserService userService;
	protected TypingService typingService;
	protected LogService logService;
	protected ContentService contentService;

	protected BaseUI() {
		sc = new Scanner(System.in);
		userService = UserServiceFactory.getUserService();
		typingService = TypingServiceFactory.getUserService();
		logService = LogServiceFactory.getLogService();
		contentService = ContentServiceFactory.getContentService();
	}

	protected String scanStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}

	protected int scanInt(String msg) {
		System.out.print(msg);
		int no = Integer.parseInt(sc.nextLine());
		return no;
	}
	
	protected void bar() {
		System.out.println("-------------------------------------");
	}
	
	protected void doubleBar() {
		System.out.println("=====================================");
	}
	

	protected void title(String msg) {
		System.out.println();
		doubleBar();
		System.out.println("\t<<영문 타자연습 프로그램>>");
		doubleBar();
		System.out.println("\t   [" + msg + "]");
		bar();
	}

	protected void typingTitle(String msg) {
		System.out.println();
		bar();
		System.out.println("\t     [" + msg + "]");
		bar();
	}

	protected void loggedInUser() {
		System.out.println("접속한 유저 : " + LoginSession.getLoginUser().getNickname());
		bar();
	}
	
	protected void clearScreen() {
	    for (int i = 0; i < 30; i++) {
	        System.out.println();
	    }
	}

	protected void printProgressBar(int current, int total) {
		int barLength = 30;
		int filled = (int) ((double) current / total * barLength);
		String bar = "■".repeat(filled) + "□".repeat(barLength - filled);
		int percent = (int) ((double) current / total * 100);
		System.out.printf("[%s] %d%% (%d/%d줄)\n", bar, percent, current, total);
	}

	protected void setSelectedSentence(LongSentenceVO sentence) {
	    LoginSession.setSelectedSentence(sentence);
	}
	protected LongSentenceVO getSelectedSentence() {
	    return LoginSession.getSelectedSentence();
	}
}
