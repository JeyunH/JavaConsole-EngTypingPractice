package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.vo.WordVO;

public class WordDAO {
	public static List<String> selectRandomWord(String tableName, int count) {
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

	public static List<WordVO> selectAll() {
		List<WordVO> list = new ArrayList<>();

		String sql = """
				    SELECT WORD_NO, CONTENT, WORD_LEN, WORD_DIF, STATUS, REGDATE
				    FROM WORD_TABLE
				    ORDER BY WORD_NO
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				WordVO word = new WordVO();
				word.setWordNo(rs.getInt("WORD_NO"));
				word.setContent(rs.getString("CONTENT"));
				word.setWordLen(rs.getInt("WORD_LEN"));
				word.setWordDif(rs.getString("WORD_DIF"));
				word.setStatus(rs.getString("STATUS"));
				word.setRegDate(rs.getString("REGDATE"));

				list.add(word);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static boolean exists(String content) {
		String sql = "SELECT COUNT(*) FROM WORD_TABLE WHERE CONTENT = ?";

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

	public static boolean insert(String word) {
		String sql = "INSERT INTO WORD_TABLE (CONTENT) VALUES (?)";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, word);
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static WordVO findByNo(int wordNo) {
		WordVO word = null;

		String sql = """
				    SELECT WORD_NO, CONTENT, STATUS, REGDATE
				    FROM WORD_TABLE
				    WHERE WORD_NO = ?
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, wordNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					word = new WordVO();
					word.setWordNo(rs.getInt("WORD_NO"));
					word.setContent(rs.getString("CONTENT"));
					word.setStatus(rs.getString("STATUS"));
					word.setRegDate(rs.getString("REGDATE"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return word;
	}

	public static boolean existsOtherThan(int wordNo, String content) {
		String sql = """
				    SELECT COUNT(*)
				    FROM WORD_TABLE
				    WHERE CONTENT = ?
				    AND WORD_NO != ?
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, content);
			pstmt.setInt(2, wordNo);
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

	public static boolean updateContent(int wordNo, String newContent) {
		String sql = "UPDATE WORD_TABLE SET CONTENT = ? WHERE WORD_NO = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, newContent);
			pstmt.setInt(2, wordNo);
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public static boolean updateStatus(int wordNo, String status) {
		String sql = "UPDATE WORD_TABLE SET STATUS = ? WHERE WORD_NO = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, status);
			pstmt.setInt(2, wordNo);
			int result = pstmt.executeUpdate();
			return result > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
