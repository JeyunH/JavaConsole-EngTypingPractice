package kr.ac.kopo.ui.TypingPracticeUI;

import java.util.List;

import kr.ac.kopo.service.TypingService;
import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.util.LoginSession;
import kr.ac.kopo.vo.WordLogVO;

public class WordTypingUI extends BaseUI {

	@Override
	public void excute() throws Exception {
		List<String> wordList = TypingService.getWords(6);

		if (wordList == null || wordList.isEmpty()) {
			System.out.println("연습할 단어가 없습니다.");
			Thread.sleep(1000);
			choice = "2";
			return;
		}

		int total = wordList.size();
		int correctCount = 0;
		int typoCount = 0;

		for (int i = 0; i < total; i++) {
			String currentWord = wordList.get(i);
			String nextWord = (i < total - 1) ? wordList.get(i + 1) : "[없음]";

			clearScreen();
			typingTitle("낱말 연습");
			printProgressBar(i, total);
			
			System.out.printf("오타수: %d\t\t평균정확도: %.2f%%\n", typoCount,
					(correctCount + typoCount) == 0 ? 0.0 : (correctCount * 100.0 / (correctCount + typoCount)));

			bar();
			System.out.println("단어:" + currentWord + "\t\t"+ nextWord);	
			String input = scanStr("입력:");

			if (input.equals(currentWord)) {
				correctCount++;
			} else {
				typoCount++;
			}
		}
		
		// 연습 완료 후 결과 로그 저장
		WordLogVO log = new WordLogVO();
		log.setUserNo(LoginSession.getLoginUser().getNo());
		log.setWordCount(total);
		log.setCorrectCnt(correctCount);
		log.setIncorrectCnt(total - correctCount);

		logService.saveWordLog(log);

		// 연습 완료 후 결과 요약
		System.out.println("\n낱말 연습이 완료되었습니다!");
		doubleBar();
		System.out.printf("총 단어 수      : %d\n", total);
		System.out.printf("정답 수         : %d\n", correctCount);
		System.out.printf("오답 수         : %d\n", typoCount);
		System.out.printf("최종 정확도     : %.2f%%\n", (correctCount * 100.0 / total));
		doubleBar();;

		// 아무 키나 입력하면 돌아가기
		scanStr("계속하려면 Enter를 누르세요...");
		choice = "2";
	}


}
