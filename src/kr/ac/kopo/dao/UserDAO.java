package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.util.JDBCClose;
import kr.ac.kopo.vo.UserVO;

public class UserDAO {

	public static boolean checkID(UserVO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT COUNT(*) FROM USER_TABLE WHERE USER_ID = ?");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user.getUserID());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0; // true: 중복 있음, false: 사용 가능
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt, rs);
		}

		return false;
	}

	public static void insertUser(UserVO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO USER_TABLE(USER_NO, USER_ID, PASSWORD, NICKNAME) ");
			sql.append(" VALUES(USER_SEQ.NEXTVAL, ?, ?, ?) ");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3, user.getNickname());

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}

	public static UserVO loginUser(UserVO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM USER_TABLE WHERE USER_ID = ? AND PASSWORD = ? AND STATUS = 'Y'");

			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getPassword());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				user.setNo(rs.getInt(1));
				user.setUserID(rs.getString(2));
				user.setPassword(null);
				user.setNickname(rs.getString(4));
				user.setUserType(rs.getString(5));
				user.setRegdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(6)));
				user.setStatus(rs.getString(7));
				return user;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt, rs);
		}
		return null;
	}

	public static void updateInfo(UserVO user) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = new ConnectionFactory().getConnection();

			StringBuilder sql = new StringBuilder();

			if (user.getStatus().equals("N")) {
				sql.append("UPDATE USER_TABLE SET STATUS = ? WHERE USER_ID = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, user.getStatus());
				pstmt.setString(2, user.getUserID());
			} else if (user.getPassword() == null) {
				sql.append("UPDATE USER_TABLE SET NICKNAME = ? WHERE USER_ID = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, user.getNickname());
				pstmt.setString(2, user.getUserID());
			} else {
				sql.append("UPDATE USER_TABLE SET PASSWORD = ? WHERE USER_ID = ?");
				pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, user.getPassword());
				pstmt.setString(2, user.getUserID());
			}

			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCClose.close(conn, pstmt);
		}
	}

	public static List<UserVO> selectAll() {
		List<UserVO> list = new ArrayList<>();

		String sql = """
				    SELECT USER_NO, USER_ID, NICKNAME, USER_TYPE, STATUS
				    FROM USER_TABLE
				    ORDER BY USER_NO
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setNo(rs.getInt("USER_NO"));
				user.setUserID(rs.getString("USER_ID"));
				user.setNickname(rs.getString("NICKNAME"));
				user.setUserType(rs.getString("USER_TYPE"));
				user.setStatus(rs.getString("STATUS"));
				list.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public static List<UserVO> searchByIdOrNickname(String keyword) {
		List<UserVO> list = new ArrayList<>();

		String sql = """
				    SELECT USER_NO, USER_ID, NICKNAME, USER_TYPE, STATUS
				    FROM USER_TABLE
				    WHERE USER_ID LIKE ? OR NICKNAME LIKE ?
				    ORDER BY USER_NO
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			String likeKeyword = "%" + keyword + "%";
			pstmt.setString(1, likeKeyword);
			pstmt.setString(2, likeKeyword);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					UserVO user = new UserVO();
					user.setNo(rs.getInt("USER_NO"));
					user.setUserID(rs.getString("USER_ID"));
					user.setNickname(rs.getString("NICKNAME"));
					user.setUserType(rs.getString("USER_TYPE"));
					user.setStatus(rs.getString("STATUS"));
					list.add(user);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 1. 사용자 번호로 조회
	public static UserVO findByUserNo(int userNo) {
		UserVO user = null;

		String sql = """
				    SELECT USER_NO, USER_ID, NICKNAME, USER_TYPE, STATUS
				    FROM USER_TABLE
				    WHERE USER_NO = ?
				""";
		

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, userNo);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					user = new UserVO();
					user.setNo(rs.getInt("USER_NO"));
					user.setUserID(rs.getString("USER_ID"));
					user.setNickname(rs.getString("NICKNAME"));
					user.setUserType(rs.getString("USER_TYPE"));
					user.setStatus(rs.getString("STATUS"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	// 2. 사용자 ID로 조회
	public static UserVO findByUserID(String userId) {
		UserVO user = null;

		String sql = """
				    SELECT USER_NO, USER_ID, NICKNAME, USER_TYPE, STATUS
				    FROM USER_TABLE
				    WHERE USER_ID = ?
				""";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					user = new UserVO();
					user.setNo(rs.getInt("USER_NO"));
					user.setUserID(rs.getString("USER_ID"));
					user.setNickname(rs.getString("NICKNAME"));
					user.setUserType(rs.getString("USER_TYPE"));
					user.setStatus(rs.getString("STATUS"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	// 3. 사용자 상태 변경
	public static boolean updateStatusByNo(int userNo, String status) {
		String sql = "UPDATE USER_TABLE SET STATUS = ? WHERE USER_NO = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setString(1, status);
			pstmt.setInt(2, userNo);
			int updated = pstmt.executeUpdate();
			return updated > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
