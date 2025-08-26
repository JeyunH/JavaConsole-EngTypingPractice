package kr.ac.kopo.vo;

public class UserShortSentenceLogSummaryVO {

	private int logCount; // 총 연습 횟수
	private int totalSentenceCount; // 총 문장 수
	private double totalTime; // 총 소요 시간 (초)
	private double avgAccuracy; // 평균 정확도 (%)
	private int avgSpeed; // 평균 타속 (타/분)
	private int maxSpeed; // 최고 타속 (타/분)

	public int getLogCount() {
		return logCount;
	}

	public void setLogCount(int logCount) {
		this.logCount = logCount;
	}

	public int getTotalSentenceCount() {
		return totalSentenceCount;
	}

	public void setTotalSentenceCount(int totalSentenceCount) {
		this.totalSentenceCount = totalSentenceCount;
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

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
}
