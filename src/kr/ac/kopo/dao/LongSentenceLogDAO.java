package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.vo.LongLogSentenceRankVO;
import kr.ac.kopo.vo.LongLogStatVO;
import kr.ac.kopo.vo.UserLongSentenceLogSummaryVO;
import kr.ac.kopo.vo.LongSentenceLogVO;
import kr.ac.kopo.vo.LongSentencePracticeCountVO;

public class LongSentenceLogDAO {

	public static void insertLongSentenceLog(LongSentenceLogVO log) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();
			String sql = """
					INSERT INTO LONG_SENTENCE_LOG_TABLE
					(USER_NO, SENTENCE_NO, LINE_COUNT, CHAR_COUNT, CORRECT_COUNT,
					 TOTAL_TIME, AVG_SPEED, ACCURACY)
					VALUES (?, ?, ?, ?, ?, ?, ?, ?)
					""";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, log.getUserNo());
			pstmt.setInt(2, log.getSentenceNo());
			pstmt.setInt(3, log.getLineCount());
			pstmt.setInt(4, log.getCharCount());
			pstmt.setInt(5, log.getCorrectCount());
			pstmt.setDouble(6, log.getTotalTime());
			pstmt.setInt(7, log.getAvgSpeed());
			pstmt.setDouble(8, log.getAccuracy());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}

	public static UserLongSentenceLogSummaryVO getSummaryByUser(int userNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		UserLongSentenceLogSummaryVO summary = new UserLongSentenceLogSummaryVO();
		List<LongSentencePracticeCountVO> sentenceStats = new ArrayList<>();

		try {
			conn = new ConnectionFactory().getConnection();

			// 1. 전체 요약 정보
			String sql1 = """
					SELECT
					    COUNT(*) AS total_log_count,
					    SUM(LINE_COUNT) AS total_line,
					    SUM(TOTAL_TIME) AS total_time,
					    SUM(CORRECT_COUNT) AS total_correct,
					    SUM(CHAR_COUNT) AS total_chars,
					    AVG(AVG_SPEED) AS avg_speed
					FROM LONG_SENTENCE_LOG_TABLE
					WHERE USER_NO = ?
					""";
			pstmt = conn.prepareStatement(sql1);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				summary.setTotalLogCount(rs.getInt("total_log_count"));
				summary.setTotalLineCount(rs.getInt("total_line"));
				summary.setTotalTime(rs.getDouble("total_time"));
				summary.setAvgSpeed(rs.getInt("avg_speed"));

				int correct = rs.getInt("total_correct");
				int chars = rs.getInt("total_chars");
				double acc = (chars == 0) ? 0.0 : (correct * 100.0 / chars);
				summary.setAvgAccuracy(Math.round(acc * 100.0) / 100.0);
			}

			rs.close();
			pstmt.close();

			// 2. 글별 연습 횟수 (JOIN으로 제목까지 가져오기)
			String sql2 = """
					    SELECT
					        S.SENTENCE_NO,
					        S.TITLE,
					        COUNT(*) AS PRACTICE_COUNT
					    FROM LONG_SENTENCE_LOG_TABLE L
					    JOIN LONG_SENTENCE_TABLE S ON L.SENTENCE_NO = S.SENTENCE_NO
					    WHERE L.USER_NO = ?
					    GROUP BY S.SENTENCE_NO, S.TITLE
					    ORDER BY S.SENTENCE_NO
					""";

			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, userNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				LongSentencePracticeCountVO vo = new LongSentencePracticeCountVO();
				vo.setSentenceNo(rs.getInt("SENTENCE_NO"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setPracticeCount(rs.getInt("PRACTICE_COUNT"));
				sentenceStats.add(vo);
			}

			summary.setSentenceStats(sentenceStats);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt, rs);
		}

		return summary;
	}

	public static LongLogStatVO getStat() {
		LongLogStatVO vo = new LongLogStatVO();

		String sql = """
				    SELECT
				        COUNT(*) AS CNT,
				        SUM(LINE_COUNT) AS TOTAL_LINE,
				        ROUND(SUM(TOTAL_TIME), 2) AS TOTAL_TIME,
				        ROUND(SUM(CORRECT_COUNT) * 100.0 / SUM(CHAR_COUNT), 2) AS AVG_ACC,
				        ROUND(SUM(CORRECT_COUNT) / (SUM(TOTAL_TIME) / 60)) AS AVG_SPEED,
				        MAX(AVG_SPEED) AS MAX_SPEED
				    FROM LONG_SENTENCE_LOG_TABLE
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			if (rs.next()) {
				vo.setPracticeCount(rs.getInt("CNT"));
				vo.setLineCount(rs.getInt("TOTAL_LINE"));
				vo.setTotalTime(rs.getDouble("TOTAL_TIME"));
				vo.setAvgAccuracy(rs.getDouble("AVG_ACC"));
				vo.setAvgSpeed(rs.getInt("AVG_SPEED"));
				vo.setMaxSpeed(rs.getInt("MAX_SPEED"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public static List<LongLogSentenceRankVO> getRankList() {
		List<LongLogSentenceRankVO> list = new ArrayList<>();

		String sql = """
				    SELECT
				        L.SENTENCE_NO,
				        S.TITLE,
				        COUNT(*) AS PRACTICE_COUNT
				    FROM LONG_SENTENCE_LOG_TABLE L
				    JOIN LONG_SENTENCE_TABLE S ON L.SENTENCE_NO = S.SENTENCE_NO
				    GROUP BY L.SENTENCE_NO, S.TITLE
				    ORDER BY PRACTICE_COUNT DESC
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				LongLogSentenceRankVO vo = new LongLogSentenceRankVO();
				vo.setSentenceNo(rs.getInt("SENTENCE_NO"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setPracticeCount(rs.getInt("PRACTICE_COUNT"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
