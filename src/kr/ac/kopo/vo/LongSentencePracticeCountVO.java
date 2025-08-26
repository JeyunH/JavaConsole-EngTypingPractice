package kr.ac.kopo.vo;

public class LongSentencePracticeCountVO {

	private int sentenceNo; // 글 번호
	private String title; // 글 제목
	private int practiceCount; // 해당 글의 연습 횟수

	public int getSentenceNo() {
		return sentenceNo;
	}

	public void setSentenceNo(int sentenceNo) {
		this.sentenceNo = sentenceNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPracticeCount() {
		return practiceCount;
	}

	public void setPracticeCount(int practiceCount) {
		this.practiceCount = practiceCount;
	}
}
