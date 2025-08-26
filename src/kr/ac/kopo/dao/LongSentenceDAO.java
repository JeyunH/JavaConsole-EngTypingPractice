package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.vo.LongSentenceVO;

public class LongSentenceDAO {

	public static List<LongSentenceVO> selectLongSentencePage(int page, int size) {
		List<LongSentenceVO> list = new ArrayList<>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SENTENCE_NO, TITLE, LINE_COUNT ");
		sql.append("FROM LONG_SENTENCE_TABLE ");
		sql.append("WHERE STATUS = 'Y' ");
		sql.append("ORDER BY SENTENCE_NO ");
		sql.append("OFFSET ? ROWS FETCH FIRST ? ROWS ONLY");

		try {
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql.toString());

			int offset = (page - 1) * size;

			pstmt.setInt(1, offset); // OFFSET ? ROWS
			pstmt.setInt(2, size); // FETCH FIRST ? ROWS ONLY

			rs = pstmt.executeQuery();
			while (rs.next()) {
				LongSentenceVO vo = new LongSentenceVO();
				vo.setSentenceNo(rs.getInt("SENTENCE_NO"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setLineCount(rs.getInt("LINE_COUNT"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt, rs);
		}

		return list;
	}

	public static int countLongSentence() {
		int count = 0;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM LONG_SENTENCE_TABLE WHERE STATUS = 'Y'";

		try {
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt(1); // 첫 번째 컬럼 (COUNT 결과)
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt, rs);
		}

		return count;
	}

	public static LongSentenceVO selectLongSentence(int sentenceNo) {
		LongSentenceVO selectedSentence = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "SELECT * FROM LONG_SENTENCE_TABLE WHERE SENTENCE_NO = ? AND STATUS = 'Y'";

		try {
			conn = new ConnectionFactory().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sentenceNo);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				selectedSentence = new LongSentenceVO();
				selectedSentence.setSentenceNo(rs.getInt(1));
				selectedSentence.setTitle(rs.getString(2));
				selectedSentence.setContent(rs.getString(3));
				selectedSentence.setCharLength(rs.getInt(4));
				selectedSentence.setLineCount(rs.getInt(5));
				selectedSentence.setStatus(rs.getString(6));
				selectedSentence.setRegdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt, rs);
		}

		return selectedSentence;
	}

	public static List<LongSentenceVO> selectAll() {
		List<LongSentenceVO> list = new ArrayList<>();

		String sql = """
				    SELECT SENTENCE_NO, TITLE, CONTENT, CHAR_LENGTH, LINE_COUNT, STATUS, REGDATE
				    FROM LONG_SENTENCE_TABLE
				    ORDER BY SENTENCE_NO
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				LongSentenceVO vo = new LongSentenceVO();
				vo.setSentenceNo(rs.getInt("SENTENCE_NO"));
				vo.setTitle(rs.getString("TITLE"));
				vo.setContent(rs.getString("CONTENT"));
				vo.setCharLength(rs.getInt("CHAR_LENGTH"));
				vo.setLineCount(rs.getInt("LINE_COUNT"));
				vo.setStatus(rs.getString("STATUS"));
				vo.setRegdate(rs.getString("REGDATE"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static boolean existsTitle(String title) {
		String sql = "SELECT COUNT(*) FROM LONG_SENTENCE_TABLE WHERE TITLE = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean insert(String title, String content) {
		String sql = "INSERT INTO LONG_SENTENCE_TABLE (TITLE, CONTENT) VALUES (?, ?)";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static LongSentenceVO findByNo(int sentNo) {
		LongSentenceVO vo = null;

		String sql = """
				    SELECT SENTENCE_NO, TITLE, CONTENT, CHAR_LENGTH, LINE_COUNT, STATUS, REGDATE
				    FROM LONG_SENTENCE_TABLE
				    WHERE SENTENCE_NO = ?
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, sentNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					vo = new LongSentenceVO();
					vo.setSentenceNo(rs.getInt("SENTENCE_NO"));
					vo.setTitle(rs.getString("TITLE"));
					vo.setContent(rs.getString("CONTENT"));
					vo.setCharLength(rs.getInt("CHAR_LENGTH"));
					vo.setLineCount(rs.getInt("LINE_COUNT"));
					vo.setStatus(rs.getString("STATUS"));
					vo.setRegdate(rs.getString("REGDATE"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public static boolean existsOtherThanTitle(int sentNo, String title) {
		String sql = """
				    SELECT COUNT(*)
				    FROM LONG_SENTENCE_TABLE
				    WHERE TITLE = ? AND SENTENCE_NO != ?
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title);
			pstmt.setInt(2, sentNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return rs.getInt(1) > 0;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public static boolean update(int sentNo, String title, String content) {
		String sql = """
				    UPDATE LONG_SENTENCE_TABLE
				    SET TITLE = ?, CONTENT = ?
				    WHERE SENTENCE_NO = ?
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setInt(3, sentNo);
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateStatus(int sentNo, String newStatus) {
		String sql = "UPDATE LONG_SENTENCE_TABLE SET STATUS = ? WHERE SENTENCE_NO = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, newStatus);
			pstmt.setInt(2, sentNo);
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
