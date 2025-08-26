package kr.ac.kopo.main;

import kr.ac.kopo.ui.MainUI;

public class TypingPracticeMain {

	public static void main(String[] args) throws Exception {
		MainUI ui = new MainUI();

		try {
			ui.excute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
