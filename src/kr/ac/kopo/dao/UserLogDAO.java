package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.UserLogSummaryVO;

public class UserLogDAO {
	public static UserLogSummaryVO getUserLogSummary(int userNo) {
		UserLogSummaryVO vo = new UserLogSummaryVO();

		String sql = """
				    SELECT
				        -- 낱말 통계
				        (SELECT COUNT(*)
				         FROM WORD_LOG_TABLE
				         WHERE USER_NO = ?) AS WORD_CNT,

				        (SELECT ROUND(SUM(CORRECT_CNT) * 100.0 / SUM(WORD_COUNT), 2)
				         FROM WORD_LOG_TABLE
				         WHERE USER_NO = ?) AS WORD_ACC,

				        -- 짧은 문장 통계
				        (SELECT COUNT(*)
				         FROM SHORT_SENTENCE_LOG_TABLE
				         WHERE USER_NO = ?) AS SHORT_CNT,

				        (SELECT ROUND(SUM(CORRECT_COUNT) * 100.0 / SUM(CHAR_COUNT), 2)
				         FROM SHORT_SENTENCE_LOG_TABLE
				         WHERE USER_NO = ?) AS SHORT_ACC,

				        (SELECT ROUND(SUM(CORRECT_COUNT) / (SUM(TOTAL_TIME) / 60))
				         FROM SHORT_SENTENCE_LOG_TABLE
				         WHERE USER_NO = ?) AS SHORT_SPEED,

				        -- 긴 문장 통계
				        (SELECT COUNT(*)
				         FROM LONG_SENTENCE_LOG_TABLE
				         WHERE USER_NO = ?) AS LONG_CNT,

				        (SELECT ROUND(SUM(CORRECT_COUNT) * 100.0 / SUM(CHAR_COUNT), 2)
				         FROM LONG_SENTENCE_LOG_TABLE
				         WHERE USER_NO = ?) AS LONG_ACC,

				        (SELECT ROUND(SUM(CORRECT_COUNT) / (SUM(TOTAL_TIME) / 60))
				         FROM LONG_SENTENCE_LOG_TABLE
				         WHERE USER_NO = ?) AS LONG_SPEED
				    FROM DUAL
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, userNo);
			pstmt.setInt(3, userNo);
			pstmt.setInt(4, userNo);
			pstmt.setInt(5, userNo);
			pstmt.setInt(6, userNo);
			pstmt.setInt(7, userNo);
			pstmt.setInt(8, userNo);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					vo.setWordCount(rs.getInt("WORD_CNT"));
					vo.setWordAccuracy(rs.getDouble("WORD_ACC"));

					vo.setShortCount(rs.getInt("SHORT_CNT"));
					vo.setShortAccuracy(rs.getDouble("SHORT_ACC"));
					vo.setShortAvgSpeed(rs.getInt("SHORT_SPEED"));

					vo.setLongCount(rs.getInt("LONG_CNT"));
					vo.setLongAccuracy(rs.getDouble("LONG_ACC"));
					vo.setLongAvgSpeed(rs.getInt("LONG_SPEED"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

}
