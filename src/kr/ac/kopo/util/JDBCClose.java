package kr.ac.kopo.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCClose {
	public static void close(Connection conn, Statement stmt) {

		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void close(Connection conn, Statement stmt, ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		close(conn, stmt);

	}
}
