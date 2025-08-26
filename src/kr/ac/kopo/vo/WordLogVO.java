package kr.ac.kopo.vo;

import java.io.Serializable;

public class WordLogVO implements Serializable {

	private int userNo;
	private int wordCount;
	private int correctCnt;
	private int incorrectCnt;

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getWordCount() {
		return wordCount;
	}

	public void setWordCount(int wordCount) {
		this.wordCount = wordCount;
	}

	public int getCorrectCnt() {
		return correctCnt;
	}

	public void setCorrectCnt(int correctCnt) {
		this.correctCnt = correctCnt;
	}

	public int getIncorrectCnt() {
		return incorrectCnt;
	}

	public void setIncorrectCnt(int incorrectCnt) {
		this.incorrectCnt = incorrectCnt;
	}
}
