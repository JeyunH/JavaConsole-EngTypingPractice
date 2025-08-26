package kr.ac.kopo.vo;

public class UserLogSummaryVO {

	private int wordCount;
	private double wordAccuracy;

	private int shortCount;
	private double shortAccuracy;
	private int shortAvgSpeed;

	private int longCount;
	private double longAccuracy;
	private int longAvgSpeed;

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public double getWordAccuracy() {
		return wordAccuracy;
	}

	public void setWordAccuracy(double wordAccuracy) {
		this.wordAccuracy = wordAccuracy;
	}

	public int getShortCount() {
		return shortCount;
	}

	public void setShortCount(int shortCount) {
		this.shortCount = shortCount;
	}

	public double getShortAccuracy() {
		return shortAccuracy;
	}

	public void setShortAccuracy(double shortAccuracy) {
		this.shortAccuracy = shortAccuracy;
	}

	public int getShortAvgSpeed() {
		return shortAvgSpeed;
	}

	public void setShortAvgSpeed(int shortAvgSpeed) {
		this.shortAvgSpeed = shortAvgSpeed;
	}

	public int getLongCount() {
		return longCount;
	}

	public void setLongCount(int longCount) {
		this.longCount = longCount;
	}

	public double getLongAccuracy() {
		return longAccuracy;
	}

	public void setLongAccuracy(double longAccuracy) {
		this.longAccuracy = longAccuracy;
	}

	public int getLongAvgSpeed() {
		return longAvgSpeed;
	}

	public void setLongAvgSpeed(int longAvgSpeed) {
		this.longAvgSpeed = longAvgSpeed;
	}

	public boolean isEmpty() {
		return wordCount == 0 && shortCount == 0 && longCount == 0;
	}
}
