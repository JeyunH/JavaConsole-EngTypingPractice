package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.dao.LongSentenceDAO;
import kr.ac.kopo.dao.ShortSentenceDAO;
import kr.ac.kopo.dao.WordDAO;
import kr.ac.kopo.vo.LongSentenceVO;

public class TypingService {	
	private static TypingService instance = new TypingService();
	private TypingService() {
	}
	public static TypingService getInstance() {
		return instance;
	}
	public static List<String> getWords(int cnt) {
		return WordDAO.selectRandomWord("WORD_TABLE", cnt);
	}
	public static List<String> getShort(int cnt) {
		return ShortSentenceDAO.selectRandomShortSentence("SHORT_SENTENCE_TABLE", cnt);
	}
	public static List<LongSentenceVO> getLongSentencePage(int page, int size) {
        return LongSentenceDAO.selectLongSentencePage(page, size);
    }
	
	public static int getLongSentenceCount() {
	    return LongSentenceDAO.countLongSentence();
	}
	
	public static LongSentenceVO selectLongSentence(int sentenceNo) {		
	    return LongSentenceDAO.selectLongSentence(sentenceNo);
	}
	
}
