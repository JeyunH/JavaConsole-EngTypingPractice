package kr.ac.kopo.vo;

public class WordLogStatVO {

	private int practiceCount; // 총 연습 횟수 (로그 레코드 수)
	private int wordCount; // 총 연습한 낱말 수
	private double avgAccuracy; // 전체 평균 정확도 (%)

	public int getPracticeCount() {
		return practiceCount;
	}

	public void setPracticeCount(int practiceCount) {
		this.practiceCount = practiceCount;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public double getAvgAccuracy() {
		return avgAccuracy;
	}

	public void setAvgAccuracy(double avgAccuracy) {
		this.avgAccuracy = avgAccuracy;
	}
}
