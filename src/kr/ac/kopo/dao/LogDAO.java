package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.TotalLogSummaryVO;

public class LogDAO {

	public static TotalLogSummaryVO getSummaryStats() {
		TotalLogSummaryVO vo = new TotalLogSummaryVO();

		String sql = """
				    SELECT
				        (SELECT COUNT(*) FROM USER_TABLE) AS USER_COUNT,

				        (SELECT COUNT(*) FROM WORD_LOG_TABLE) AS WORD_CNT,
				        (SELECT ROUND(SUM(CORRECT_CNT) * 100.0 / SUM(WORD_COUNT), 2) FROM WORD_LOG_TABLE) AS WORD_ACC,

				        (SELECT COUNT(*) FROM SHORT_SENTENCE_LOG_TABLE) AS SHORT_CNT,
				        (SELECT ROUND(SUM(CORRECT_COUNT) * 100.0 / SUM(CHAR_COUNT), 2) FROM SHORT_SENTENCE_LOG_TABLE) AS SHORT_ACC,
				        (SELECT ROUND(SUM(CORRECT_COUNT) / (SUM(TOTAL_TIME) / 60)) FROM SHORT_SENTENCE_LOG_TABLE) AS SHORT_SPEED,

				        (SELECT COUNT(*) FROM LONG_SENTENCE_LOG_TABLE) AS LONG_CNT,
				        (SELECT ROUND(SUM(CORRECT_COUNT) * 100.0 / SUM(CHAR_COUNT), 2) FROM LONG_SENTENCE_LOG_TABLE) AS LONG_ACC,
				        (SELECT ROUND(SUM(CORRECT_COUNT) / (SUM(TOTAL_TIME) / 60)) FROM LONG_SENTENCE_LOG_TABLE) AS LONG_SPEED
				    FROM DUAL
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				vo.setUserCount(rs.getInt("USER_COUNT"));

				vo.setWordPracticeCount(rs.getInt("WORD_CNT"));
				vo.setWordAvgAccuracy(rs.getDouble("WORD_ACC"));

				vo.setShortPracticeCount(rs.getInt("SHORT_CNT"));
				vo.setShortAvgAccuracy(rs.getDouble("SHORT_ACC"));
				vo.setShortAvgSpeed(rs.getInt("SHORT_SPEED"));

				vo.setLongPracticeCount(rs.getInt("LONG_CNT"));
				vo.setLongAvgAccuracy(rs.getDouble("LONG_ACC"));
				vo.setLongAvgSpeed(rs.getInt("LONG_SPEED"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

}
