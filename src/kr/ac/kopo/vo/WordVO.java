package kr.ac.kopo.vo;

public class WordVO {

	private int wordNo; // WORD_NO: 단어 번호 (PK)
	private String content; // CONTENT: 단어 문자열
	private int wordLen; // WORD_LEN: 단어 길이 (자동 계산)
	private String wordDif; // WORD_DIF: 난이도 ('E', 'M', 'H') (자동 설정)
	private String status; // STATUS: 'Y' 또는 'N'
	private String regDate; // REGDATE: 등록일 (yyyy-MM-dd 형식)

	public int getWordNo() {
		return wordNo;
	}

	public void setWordNo(int wordNo) {
		this.wordNo = wordNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getWordLen() {
		return wordLen;
	}

	public void setWordLen(int wordLen) {
		this.wordLen = wordLen;
	}

	public String getWordDif() {
		return wordDif;
	}

	public void setWordDif(String wordDif) {
		this.wordDif = wordDif;
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
