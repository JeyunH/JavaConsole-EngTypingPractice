package kr.ac.kopo.vo;

public class ShortLogStatVO {

	private int practiceCount; // 총 연습 횟수
	private int sentCount; // 총 문장 수
	private double avgAccuracy; // 평균 정확도
	private double totalTime; // 총 시간 (초)
	private int avgSpeed; // 평균 타속
	private int maxSpeed; // 최고 타속

	public int getPracticeCount() {
		return practiceCount;
	}

	public void setPracticeCount(int practiceCount) {
		this.practiceCount = practiceCount;
	}

	public int getSentCount() {
		return sentCount;
	}

	public void setSentCount(int sentCount) {
		this.sentCount = sentCount;
	}

	public double getAvgAccuracy() {
		return avgAccuracy;
	}

	public void setAvgAccuracy(double avgAccuracy) {
		this.avgAccuracy = avgAccuracy;
	}

	public double getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
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
