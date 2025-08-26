package kr.ac.kopo.vo;

public class TotalLogSummaryVO {

	private int userCount;

	private int wordPracticeCount;
	private double wordAvgAccuracy;

	private int shortPracticeCount;
	private double shortAvgAccuracy;
	private int shortAvgSpeed;

	private int longPracticeCount;
	private double longAvgAccuracy;
	private int longAvgSpeed;

	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public int getWordPracticeCount() {
		return wordPracticeCount;
	}

	public void setWordPracticeCount(int wordPracticeCount) {
		this.wordPracticeCount = wordPracticeCount;
	}

	public double getWordAvgAccuracy() {
		return wordAvgAccuracy;
	}

	public void setWordAvgAccuracy(double wordAvgAccuracy) {
		this.wordAvgAccuracy = wordAvgAccuracy;
	}

	public int getShortPracticeCount() {
		return shortPracticeCount;
	}

	public void setShortPracticeCount(int shortPracticeCount) {
		this.shortPracticeCount = shortPracticeCount;
	}

	public double getShortAvgAccuracy() {
		return shortAvgAccuracy;
	}

	public void setShortAvgAccuracy(double shortAvgAccuracy) {
		this.shortAvgAccuracy = shortAvgAccuracy;
	}

	public int getShortAvgSpeed() {
		return shortAvgSpeed;
	}

	public void setShortAvgSpeed(int shortAvgSpeed) {
		this.shortAvgSpeed = shortAvgSpeed;
	}

	public int getLongPracticeCount() {
		return longPracticeCount;
	}

	public void setLongPracticeCount(int longPracticeCount) {
		this.longPracticeCount = longPracticeCount;
	}

	public double getLongAvgAccuracy() {
		return longAvgAccuracy;
	}

	public void setLongAvgAccuracy(double longAvgAccuracy) {
		this.longAvgAccuracy = longAvgAccuracy;
	}

	public int getLongAvgSpeed() {
		return longAvgSpeed;
	}

	public void setLongAvgSpeed(int longAvgSpeed) {
		this.longAvgSpeed = longAvgSpeed;
	}
}
