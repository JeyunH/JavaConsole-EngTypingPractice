package kr.ac.kopo.ui.TypingPracticeUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kr.ac.kopo.service.LogService;
import kr.ac.kopo.ui.BaseUI;
import kr.ac.kopo.util.LoginSession;
import kr.ac.kopo.vo.LongSentenceLogVO;
import kr.ac.kopo.vo.LongSentenceVO;

public class LongSentenceTypingUI extends BaseUI {

	@Override
	public void excute() throws Exception {
		LongSentenceVO vo = getSelectedSentence();
		String content = vo.getContent();
		List<String> lines = Arrays.asList(content.split("\\r?\\n"));

		int totalLines = lines.size();
		int totalCharCount = 0;
		int totalCorrectCount = 0;
		long startTime = System.currentTimeMillis();

		List<String> originalLines = new ArrayList<>();
		List<String> typedLines = new ArrayList<>();

		for (int i = 0; i < totalLines; i++) {
			clearScreen();

			String currentLine = lines.get(i);

			// 타이틀, 제목
			title("긴 글 연습");
			System.out.println("Title : " + vo.getTitle());
			bar();

			// 이전 줄 출력
			for (int j = 0; j < originalLines.size(); j++) {
				System.out.println(originalLines.get(j));
				System.out.println(typedLines.get(j));
				System.out.println();
			}

			// 진행률 표시
			printProgressBar(i, totalLines);
			int percent = (int) ((i / (double) totalLines) * 100);
			System.out.printf("  %d%% (%d/%d)\n", percent, i, totalLines);
			System.out.printf("평균 타속 : %d타/분    정확도 : %.1f%%\n", getSpeed(totalCorrectCount, startTime),
					getAccuracy(totalCorrectCount, totalCharCount));

			// 현재 줄 입력
			System.out.println("현재: " + currentLine);
			String input = scanStr("입력: ");

			int correct = countCorrectChars(currentLine, input);
			totalCharCount += currentLine.length();
			totalCorrectCount += correct;

			originalLines.add(currentLine);
			typedLines.add(input);
		}

		long endTime = System.currentTimeMillis();
		long elapsedMillis = endTime - startTime;
		
		// 결과 로그 저장
		LongSentenceLogVO log = new LongSentenceLogVO();
		log.setUserNo(LoginSession.getLoginUser().getNo());
		log.setSentenceNo(vo.getSentenceNo());
		log.setLineCount(totalLines);
		log.setCharCount(totalCharCount);
		log.setCorrectCount(totalCorrectCount);
		log.setTotalTime(elapsedMillis/1000.0);
		log.setAvgSpeed(getSpeed(totalCorrectCount, startTime));
		log.setAccuracy(Math.round(getAccuracy(totalCorrectCount, totalCharCount) * 100.0) / 100.0);

		logService.saveLongSentenceLog(log);

		// 결과 출력 화면 준비
		clearScreen();
		title("긴 글 연습");
		System.out.println("Title : " + vo.getTitle());
		bar();

		for (int j = 0; j < originalLines.size(); j++) {
			System.out.println(originalLines.get(j));
			System.out.println(typedLines.get(j));
			System.out.println();
		}
		
		// 결과 요약
		System.out.println("\n긴 글 연습이 완료되었습니다!");
		doubleBar();
		System.out.printf("총 문장 수      : %d\n", totalLines);
		System.out.printf("최종 정확도     : %.1f%%\n", getAccuracy(totalCorrectCount, totalCharCount));
		System.out.printf("평균 타속         : %d타/분\n", getSpeed(totalCorrectCount, startTime));
		System.out.printf("소요 시간         : %s\n", formatElapsedTime(elapsedMillis));
		doubleBar();

		scanStr("계속하려면 Enter를 누르세요...");
		choice = "2";
	}

	private int countCorrectChars(String expected, String input) {
		int len = Math.min(expected.length(), input.length());
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (expected.charAt(i) == input.charAt(i)) {
				count++;
			}
		}
		return count;
	}

	private int getSpeed(int correctCharCount, long startTime) {
        long now = System.currentTimeMillis();
        double minutes = (now - startTime) / 60000.0;
        return (int) (correctCharCount / (minutes == 0 ? 1 : minutes));
    }

	private double getAccuracy(int correctCount, int totalCount) {
		if (totalCount == 0)
			return 100.0;
		return correctCount * 100.0 / totalCount;
	}

	private String formatElapsedTime(long millis) {
		long totalSeconds = millis / 1000;
		long minutes = totalSeconds / 60;
		long seconds = totalSeconds % 60;
		return String.format("%d분 %d초", minutes, seconds);
	}
}
