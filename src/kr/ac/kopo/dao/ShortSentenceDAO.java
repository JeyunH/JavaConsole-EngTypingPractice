package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.vo.ShortSentenceVO;

public class ShortSentenceDAO {
	public static List<String> selectRandomShortSentence(String tableName, int count) {
		List<String> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT CONTENT ");
			sql.append("FROM " + tableName + " ");
			sql.append("WHERE STATUS = 'Y' ");
			sql.append("ORDER BY DBMS_RANDOM.VALUE ");
			sql.append("FETCH FIRST ? ROWS ONLY");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, count);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt, rs);
		}

		return list;
	}

	public static List<ShortSentenceVO> selectAll() {
		List<ShortSentenceVO> list = new ArrayList<>();

		String sql = """
				    SELECT SENTENCE_NO, CONTENT, CHAR_LENGTH, STATUS, REGDATE
				    FROM SHORT_SENTENCE_TABLE
				    ORDER BY SENTENCE_NO
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				ShortSentenceVO vo = new ShortSentenceVO();
				vo.setSentNo(rs.getInt("SENTENCE_NO"));
				vo.setContent(rs.getString("CONTENT"));
				vo.setCharLength(rs.getInt("CHAR_LENGTH"));
				vo.setStatus(rs.getString("STATUS"));
				vo.setRegDate(rs.getString("REGDATE"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static boolean exists(String content) {
		String sql = "SELECT COUNT(*) FROM SHORT_SENTENCE_TABLE WHERE CONTENT = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, content);
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

	public static boolean insert(String content) {
		String sql = "INSERT INTO SHORT_SENTENCE_TABLE (CONTENT) VALUES (?)";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, content);
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static ShortSentenceVO findByNo(int sentNo) {
		ShortSentenceVO vo = null;

		String sql = """
				    SELECT SENTENCE_NO, CONTENT, CHAR_LENGTH, STATUS, REGDATE
				    FROM SHORT_SENTENCE_TABLE
				    WHERE SENTENCE_NO = ?
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, sentNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					vo = new ShortSentenceVO();
					vo.setSentNo(rs.getInt("SENTENCE_NO"));
					vo.setContent(rs.getString("CONTENT"));
					vo.setCharLength(rs.getInt("CHAR_LENGTH"));
					vo.setStatus(rs.getString("STATUS"));
					vo.setRegDate(rs.getString("REGDATE"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return vo;
	}

	public static boolean existsOtherThan(int sentNo, String content) {
		String sql = """
				    SELECT COUNT(*)
				    FROM SHORT_SENTENCE_TABLE
				    WHERE CONTENT = ? AND SENTENCE_NO != ?
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, content);
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

	public static boolean updateContent(int sentNo, String content) {
		String sql = "UPDATE SHORT_SENTENCE_TABLE SET CONTENT = ? WHERE SENTENCE_NO = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, content);
			pstmt.setInt(2, sentNo);
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateStatus(int sentNo, String newStatus) {
		String sql = "UPDATE SHORT_SENTENCE_TABLE SET STATUS = ? WHERE SENTENCE_NO = ?";

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
