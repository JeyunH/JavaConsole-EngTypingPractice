package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.dao.LongSentenceDAO;
import kr.ac.kopo.dao.ShortSentenceDAO;
import kr.ac.kopo.dao.WordDAO;
import kr.ac.kopo.vo.LongSentenceVO;
import kr.ac.kopo.vo.ShortSentenceVO;
import kr.ac.kopo.vo.WordVO;

public class ContentService {

	private static ContentService instance = new ContentService();

	private ContentService() {
	}

	public static ContentService getInstance() {
		return instance;
	}

	public List<WordVO> getAllWords() {
		return WordDAO.selectAll(); // DAO에서 전체 낱말 리스트 조회
	}

	public boolean addWord(String word) {
		if (WordDAO.exists(word)) {
			System.out.println("이미 등록된 단어입니다.");
			return false;
		}
		return WordDAO.insert(word);
	}

	public WordVO getWordByNo(int wordNo) {
		return WordDAO.findByNo(wordNo);
	}

	public boolean updateWord(int wordNo, String newContent) {
		if (WordDAO.existsOtherThan(wordNo, newContent)) {
			System.out.println("이미 등록된 단어입니다.");
			return false;
		}
		return WordDAO.updateContent(wordNo, newContent);
	}

	public boolean toggleWordStatus(int wordNo) {
		WordVO word = WordDAO.findByNo(wordNo);
		if (word == null)
			return false;

		String currentStatus = word.getStatus();
		String newStatus = currentStatus.equals("Y") ? "N" : "Y";

		return WordDAO.updateStatus(wordNo, newStatus);
	}

	public List<ShortSentenceVO> getAllShortSentences() {
		return ShortSentenceDAO.selectAll();
	}

	public boolean addShortSentence(String content) {
		if (ShortSentenceDAO.exists(content)) {
			return false; // 중복 문장 존재
		}

		return ShortSentenceDAO.insert(content);
	}

	public ShortSentenceVO getShortSentenceByNo(int sentNo) {
		return ShortSentenceDAO.findByNo(sentNo);
	}

	public boolean updateShortSentence(int sentNo, String newContent) {
		if (ShortSentenceDAO.existsOtherThan(sentNo, newContent)) {
			return false; // 중복 문장 존재
		}
		return ShortSentenceDAO.updateContent(sentNo, newContent);
	}

	public boolean toggleShortSentenceStatus(int sentNo) {
		ShortSentenceVO sentence = ShortSentenceDAO.findByNo(sentNo);
		if (sentence == null)
			return false;

		String currentStatus = sentence.getStatus();
		String newStatus = currentStatus.equals("Y") ? "N" : "Y";

		return ShortSentenceDAO.updateStatus(sentNo, newStatus);
	}

	public List<LongSentenceVO> getAllLongSentences() {
		return LongSentenceDAO.selectAll();
	}

	public boolean existsLongSentenceTitle(String title) {
		return LongSentenceDAO.existsTitle(title);
	}

	public boolean addLongSentence(String title, String content) {
		return LongSentenceDAO.insert(title, content);
	}

	public LongSentenceVO getLongSentenceByNo(int sentNo) {
		return LongSentenceDAO.findByNo(sentNo);
	}

	public boolean existsOtherTitle(int sentNo, String title) {
		return LongSentenceDAO.existsOtherThanTitle(sentNo, title);
	}

	public boolean updateLongSentence(int sentNo, String newTitle, String newContent) {
		String contentWithCRLF = newContent.replace("\n", "\r\n");
		return LongSentenceDAO.update(sentNo, newTitle, contentWithCRLF);
	}

	public boolean toggleLongSentenceStatus(int sentNo) {
		LongSentenceVO sentence = LongSentenceDAO.findByNo(sentNo);
		if (sentence == null)
			return false;

		String currentStatus = sentence.getStatus();
		String newStatus = currentStatus.equals("Y") ? "N" : "Y";

		return LongSentenceDAO.updateStatus(sentNo, newStatus);
	}

}
