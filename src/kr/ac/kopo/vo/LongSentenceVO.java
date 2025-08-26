package kr.ac.kopo.vo;

import java.io.Serializable;

public class LongSentenceVO implements Serializable {

	private int sentenceNo;
	private String title;
	private String content;
	private int charLength;
	private int lineCount;
	private String status;
	private String regdate;

	// 기본 생성자
	public LongSentenceVO() {
	}

	// 전체 필드를 받는 생성자
	public LongSentenceVO(int sentenceNo, String title, String content, int charLength, int lineCount, String status,
			String regdate) {
		this.sentenceNo = sentenceNo;
		this.title = title;
		this.content = content;
		this.charLength = charLength;
		this.lineCount = lineCount;
		this.status = status;
		this.regdate = regdate;
	}

	// getter/setter
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getCharLength() {
		return charLength;
	}

	public void setCharLength(int charLength) {
		this.charLength = charLength;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "LongSentenceVO [No=" + sentenceNo + ", Title=" + title + ", Content=" + content + ", CharLength="
				+ charLength + ", LineCount=" + lineCount + ", RegDate=" + regdate + ", Status=" + status + "]";
	}

}
