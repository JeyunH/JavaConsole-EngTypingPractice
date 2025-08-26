package kr.ac.kopo.vo;

public class ShortSentenceVO {

	private int sentNo; // SENTENCE_NO: 문장 번호 (PK)
	private String content; // CONTENT: 문장 내용
	private int charLength; // CHAR_LENGTH: 글자 수 (트리거 자동)
	private String status; // STATUS: 'Y' or 'N'
	private String regDate; // REGDATE: 등록일 (출력용 문자열)

	public int getSentNo() {
		return sentNo;
	}

	public void setSentNo(int sentNo) {
		this.sentNo = sentNo;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
}
