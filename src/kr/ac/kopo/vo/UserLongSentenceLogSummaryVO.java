package kr.ac.kopo.vo;

import java.util.List;

public class UserLongSentenceLogSummaryVO {

	private int totalLogCount; // 총 연습 횟수
	private int totalLineCount; // 총 연습한 라인 수
	private double totalTime; // 총 연습 시간 (초)
	private double avgAccuracy; // 평균 정확도 (%)
	private int avgSpeed; // 평균 타속 (타/분)

	// 글별 연습 횟수 리스트
	private List<LongSentencePracticeCountVO> sentenceStats;

	public int getTotalLogCount() {
		return totalLogCount;
	}

	public void setTotalLogCount(int totalLogCount) {
		this.totalLogCount = totalLogCount;
	}

	public int getTotalLineCount() {
		return totalLineCount;
	}

	public void setTotalLineCount(int totalLineCount) {
		this.totalLineCount = totalLineCount;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}

	public double getAvgAccuracy() {
		return avgAccuracy;
	}

	public void setAvgAccuracy(double avgAccuracy) {
		this.avgAccuracy = avgAccuracy;
	}

	public int getAvgSpeed() {
		return avgSpeed;
	}

	public void setAvgSpeed(int avgSpeed) {
		this.avgSpeed = avgSpeed;
	}

	public List<LongSentencePracticeCountVO> getSentenceStats() {
		return sentenceStats;
	}

	public void setSentenceStats(List<LongSentencePracticeCountVO> sentenceStats) {
		this.sentenceStats = sentenceStats;
	}
}
