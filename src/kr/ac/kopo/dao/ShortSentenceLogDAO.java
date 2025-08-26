package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.vo.ShortLogStatVO;
import kr.ac.kopo.vo.UserShortSentenceLogSummaryVO;
import kr.ac.kopo.vo.ShortSentenceLogVO;

public class ShortSentenceLogDAO {

	public static void insertShortSentenceLog(ShortSentenceLogVO log) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = """
					INSERT INTO SHORT_SENTENCE_LOG_TABLE
					(USER_NO, SENT_COUNT, CHAR_COUNT, CORRECT_COUNT, TOTAL_TIME, AVG_SPEED, MAX_SPEED, ACCURACY)
					VALUES (?, ?, ?, ?, ?, ?, ?, ?)
					""";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, log.getUserNo());
			pstmt.setInt(2, log.getSentCount());
			pstmt.setInt(3, log.getCharCount());
			pstmt.setInt(4, log.getCorrectCount());
			pstmt.setDouble(5, log.getTotalTime());
			pstmt.setInt(6, log.getAvgSpeed());
			pstmt.setInt(7, log.getMaxSpeed());
			pstmt.setDouble(8, log.getAccuracy());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}

	public static UserShortSentenceLogSummaryVO getSummaryByUser(int userNo) {
		UserShortSentenceLogSummaryVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = """
					    SELECT
					        COUNT(*) AS log_count,
					        SUM(SENT_COUNT) AS total_sentence,
					        SUM(TOTAL_TIME) AS total_time,
					        SUM(CORRECT_COUNT) AS total_correct,
					        SUM(CHAR_COUNT) AS total_chars,
					        AVG(AVG_SPEED) AS avg_speed,
					        MAX(MAX_SPEED) AS max_speed
					    FROM SHORT_SENTENCE_LOG_TABLE
					    WHERE USER_NO = ?
					""";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo = new UserShortSentenceLogSummaryVO();
				vo.setLogCount(rs.getInt("log_count"));
				vo.setTotalSentenceCount(rs.getInt("total_sentence"));
				vo.setTotalTime(rs.getDouble("total_time"));
				vo.setAvgSpeed(rs.getInt("avg_speed"));
				vo.setMaxSpeed(rs.getInt("max_speed"));

				int correct = rs.getInt("total_correct");
				int chars = rs.getInt("total_chars");
				double acc = (chars == 0) ? 0.0 : (correct * 100.0 / chars);
				vo.setAvgAccuracy(Math.round(acc * 100.0) / 100.0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt, rs);
		}

		return vo;
	}

	public static ShortLogStatVO getStat() {
		ShortLogStatVO vo = new ShortLogStatVO();

		String sql = """
				    SELECT
				        COUNT(*) AS CNT,
				        SUM(SENT_COUNT) AS TOTAL_SENT,
				        ROUND(SUM(CORRECT_COUNT) * 100.0 / SUM(CHAR_COUNT), 2) AS AVG_ACC,
				        ROUND(SUM(TOTAL_TIME), 2) AS TOTAL_TIME,
				        ROUND(SUM(CORRECT_COUNT) / (SUM(TOTAL_TIME) / 60)) AS AVG_SPEED,
				        MAX(MAX_SPEED) AS MAX_SPEED
				    FROM SHORT_SENTENCE_LOG_TABLE
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				vo.setPracticeCount(rs.getInt("CNT"));
				vo.setSentCount(rs.getInt("TOTAL_SENT"));
				vo.setAvgAccuracy(rs.getDouble("AVG_ACC"));
				vo.setTotalTime(rs.getDouble("TOTAL_TIME"));
				vo.setAvgSpeed(rs.getInt("AVG_SPEED"));
				vo.setMaxSpeed(rs.getInt("MAX_SPEED"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

}
