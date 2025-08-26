package kr.ac.kopo.service;

import java.util.List;

import kr.ac.kopo.dao.LogDAO;
import kr.ac.kopo.dao.LongSentenceLogDAO;
import kr.ac.kopo.dao.ShortSentenceLogDAO;
import kr.ac.kopo.dao.UserLogDAO;
import kr.ac.kopo.dao.WordLogDAO;
import kr.ac.kopo.vo.LongLogSentenceRankVO;
import kr.ac.kopo.vo.LongLogStatVO;
import kr.ac.kopo.vo.LongSentenceLogVO;
import kr.ac.kopo.vo.ShortLogStatVO;
import kr.ac.kopo.vo.ShortSentenceLogVO;
import kr.ac.kopo.vo.TotalLogSummaryVO;
import kr.ac.kopo.vo.UserLogSummaryVO;
import kr.ac.kopo.vo.UserLongSentenceLogSummaryVO;
import kr.ac.kopo.vo.UserShortSentenceLogSummaryVO;
import kr.ac.kopo.vo.UserWordLogSummaryVO;
import kr.ac.kopo.vo.WordLogStatVO;
import kr.ac.kopo.vo.WordLogVO;

public class LogService {

	private static LogService instance = new LogService();

	private LogService() {
	}

	public static LogService getInstance() {
		return instance;
	}

	public void saveWordLog(WordLogVO log) {
		WordLogDAO.insertWordLog(log);
	}

	public void saveShortSentenceLog(ShortSentenceLogVO log) {
		ShortSentenceLogDAO.insertShortSentenceLog(log);
	}

	public void saveLongSentenceLog(LongSentenceLogVO log) {
		LongSentenceLogDAO.insertLongSentenceLog(log);
	}

	public UserWordLogSummaryVO getWordLogSummary(int userNo) {
		return WordLogDAO.getSummaryByUser(userNo);
	}

	public UserShortSentenceLogSummaryVO getShortLogSummary(int userNo) {
		return ShortSentenceLogDAO.getSummaryByUser(userNo);
	}

	public UserLongSentenceLogSummaryVO getLongLogSummary(int userNo) {
		return LongSentenceLogDAO.getSummaryByUser(userNo);
	}

	public TotalLogSummaryVO getTotalLogSummary() {
		return LogDAO.getSummaryStats();
	}

	public WordLogStatVO getWordLogStat() {
		return WordLogDAO.getStat();
	}

	public ShortLogStatVO getShortLogStat() {
		return ShortSentenceLogDAO.getStat();
	}

	public LongLogStatVO getLongLogStat() {
		return LongSentenceLogDAO.getStat();
	}

	public List<LongLogSentenceRankVO> getLongLogSentenceRank() {
		return LongSentenceLogDAO.getRankList();
	}

	public UserLogSummaryVO getUserLogSummary(int userNo) {
		return UserLogDAO.getUserLogSummary(userNo);
	}

}
