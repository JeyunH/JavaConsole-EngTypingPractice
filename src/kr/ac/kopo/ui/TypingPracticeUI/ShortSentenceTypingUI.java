package kr.ac.kopo.ui.TypingPracticeUI;

import java.util.List;

import kr.ac.kopo.service.TypingService;
import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.util.LoginSession;
import kr.ac.kopo.vo.ShortSentenceLogVO;

public class ShortSentenceTypingUI extends BaseUI { // choice = "22"

	@Override
	public void excute() throws Exception {
		List<String> shortList = TypingService.getShort(3);

		if (shortList == null || shortList.isEmpty()) {
			System.out.println("연습할 단어가 없습니다.");
			Thread.sleep(1000);
			choice = "2";
			return;
		}

		int totalSentenceCnt = shortList.size();
		int currentSentenceSize = 0;
		int correctCount = 0;
		int totalCorrect = 0;
		int totalSentenceSize = 0;
		String currentSentence = null;
		String nextSentence = null;
		String input = null;
		int inputSize = 0;
		long startTime = 0;
		long endTime = 0;
		double currentTimeSec = 0;
		double totalTimeSec = 0;
		int currentSpeed = 0;
		int highestSpeed = 0;
		

		for (int i = 0; i < totalSentenceCnt; i++) {
			
			// 현재 표시할 문장과 다음 표시할 문장 저장
			currentSentence = shortList.get(i);
			currentSentenceSize = currentSentence.length();
			nextSentence = (i < totalSentenceCnt - 1) ? shortList.get(i + 1) : "[없음]";
			
			// 타이틀과 진행완료 바 표시
			clearScreen();;
			typingTitle("짧은 글 연습");
			printProgressBar(i, totalSentenceCnt);
			
			// 실시간 타속, 최고 타속, 평균 정확도 표시
			System.out.printf("실시간 타속: %d타/분\t\t최고 타속: %d타/분\n평균 정확도: %.2f%%\n"
					,currentSpeed, highestSpeed
					,i == 0 ? 0.0 : 100.0*totalCorrect/totalSentenceSize);
			
			// 입력할 문장 표시
			bar();
			System.out.println("단어:" + currentSentence + "\t\t" + nextSentence);
			
			// 사용자 입력 및 시작,종료시간 저장
			startTime = System.currentTimeMillis();
			input = scanStr("입력:");
			endTime = System.currentTimeMillis();
			
			// 타이핑 소요 시간 계산 및 total 시간 저장
			currentTimeSec = (endTime - startTime) / 1000.0;
			totalTimeSec += currentTimeSec;
			
			// 올바른 입력 문자수 카운트
			inputSize = input.length();
			correctCount = 0;
			for (int j = 0; j < Math.min(currentSentenceSize, inputSize); j++) {
				if (currentSentence.charAt(j) == input.charAt(j)) {
					correctCount++;
				} 
			}
			
			// 올바른 입력 문자수 저장 및 전체 문자수 저장
			totalCorrect += correctCount;
			totalSentenceSize += currentSentenceSize;
			
			// 실시간 타속 계산 및 저장, 최고 타속 업데이트
			currentSpeed = (int)(correctCount/(currentTimeSec/60.0));
			if(highestSpeed<currentSpeed) {
				highestSpeed = currentSpeed;
			}
		}
		
		ShortSentenceLogVO log = new ShortSentenceLogVO();
		log.setUserNo(LoginSession.getLoginUser().getNo());
		log.setSentCount(totalSentenceCnt);		// 총 문장 수
		log.setCharCount(totalSentenceSize);	// 총 글자 수
		log.setCorrectCount(totalCorrect);		// 올바른 입력 수
		log.setTotalTime(totalTimeSec);			// 소요 시간 (초, double)
		int avgSpeed = (int)(totalCorrect/(totalTimeSec/60.0));
		log.setAvgSpeed(avgSpeed);				// 평균 타속 (int)
		log.setMaxSpeed(highestSpeed);			// 최고 타속 (int)
		double accuracy = totalCorrect * 100.0 / totalSentenceSize;
		log.setAccuracy(Math.round(accuracy * 100.0) / 100.0);  // 소수점 둘째 자리 반올림

		logService.saveShortSentenceLog(log);

		// 연습 완료 후 결과 요약
		System.out.println("\n낱말 연습이 완료되었습니다!");
		doubleBar();
		System.out.printf("총 문장 수      : %d\n", totalSentenceCnt);
		System.out.printf("최종 정확도     : %.2f%%\n", (accuracy));
		System.out.printf("평균 타속         : %d타/분\n", avgSpeed);
		System.out.printf("최고 타속         : %d타/분\n", highestSpeed);
		doubleBar();

		// 아무 키나 입력하면 돌아가기
		scanStr("계속하려면 Enter를 누르세요...");
		choice = "2";

	}
}
