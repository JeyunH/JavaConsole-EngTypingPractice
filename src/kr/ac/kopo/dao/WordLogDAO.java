package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.vo.WordLogStatVO;
import kr.ac.kopo.vo.UserWordLogSummaryVO;
import kr.ac.kopo.vo.WordLogVO;

public class WordLogDAO {

	public static void insertWordLog(WordLogVO log) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = """
					INSERT INTO WORD_LOG_TABLE
					(USER_NO, WORD_COUNT, CORRECT_CNT, INCORRECT_CNT)
					VALUES (?, ?, ?, ?)
					""";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, log.getUserNo());
			pstmt.setInt(2, log.getWordCount());
			pstmt.setInt(3, log.getCorrectCnt());
			pstmt.setInt(4, log.getIncorrectCnt());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}

	public static UserWordLogSummaryVO getSummaryByUser(int userNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserWordLogSummaryVO summary = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = """
					SELECT
					    COUNT(*) AS log_count,
					    SUM(WORD_COUNT) AS total_word,
					    SUM(CORRECT_CNT) AS total_correct,
					    SUM(INCORRECT_CNT) AS total_incorrect
					FROM WORD_LOG_TABLE
					WHERE USER_NO = ?
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				summary = new UserWordLogSummaryVO();
				summary.setLogCount(rs.getInt("log_count"));
				summary.setTotalWordCount(rs.getInt("total_word"));
				summary.setTotalCorrect(rs.getInt("total_correct"));
				summary.setTotalIncorrect(rs.getInt("total_incorrect"));

				int totalWords = summary.getTotalWordCount();
				int totalCorrect = summary.getTotalCorrect();
				double accuracy = totalWords == 0 ? 0.0 : (totalCorrect * 100.0 / totalWords);
				summary.setAccuracy(Math.round(accuracy * 100.0) / 100.0); // 둘째자리 반올림
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt, rs);
		}

		return summary;
	}

	public static WordLogStatVO getStat() {
		WordLogStatVO vo = new WordLogStatVO();

		String sql = """
				    SELECT
				        COUNT(*) AS CNT,             -- 총 연습 횟수
				        SUM(WORD_COUNT) AS TOTAL_WORD,
				        ROUND(SUM(CORRECT_CNT) * 100.0 / SUM(WORD_COUNT), 2) AS AVG_ACC
				    FROM WORD_LOG_TABLE
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				vo.setPracticeCount(rs.getInt("CNT"));
				vo.setWordCount(rs.getInt("TOTAL_WORD"));
				vo.setAvgAccuracy(rs.getDouble("AVG_ACC"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}
}
