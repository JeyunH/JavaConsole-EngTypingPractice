package kr.ac.kopo.vo;

public class LongSentenceLogVO {
	private int userNo;
	private int sentenceNo;
	private int lineCount;
	private int charCount;
	private int correctCount;
	private double totalTime; // 초 단위
	private int avgSpeed;
	private double accuracy;

	// Getter/Setter
	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getSentenceNo() {
		return sentenceNo;
	}

	public void setSentenceNo(int sentenceNo) {
		this.sentenceNo = sentenceNo;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public int getCharCount() {
		return charCount;
	}

	public void setCharCount(int charCount) {
		this.charCount = charCount;
	}

	public int getCorrectCount() {
		return correctCount;
	}

	public void setCorrectCount(int correctCount) {
		this.correctCount = correctCount;
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

	public double getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(double accuracy) {
		this.accuracy = accuracy;
	}
}
