package kr.ac.kopo.vo;

public class LongLogStatVO {

	private int practiceCount;
	private int lineCount;
	private double totalTime;
	private double avgAccuracy;
	private int avgSpeed;
	private int maxSpeed;

	public int getPracticeCount() {
		return practiceCount;
	}

	public void setPracticeCount(int practiceCount) {
		this.practiceCount = practiceCount;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
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
