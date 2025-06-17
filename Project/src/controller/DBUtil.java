package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	//collection framework : Map(Properties)
	public static Connection getConnection() {
		Properties properties = new Properties();
		Connection con = null;
		try {
			FileInputStream fis = new FileInputStream("C:\\Users\\user\\eclipse-workspace\\kh.SH.project\\Project\\src\\config\\db.properties");
			properties.load(fis);
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String id = properties.getProperty("id");
			String pwd = properties.getProperty("pwd");

			Class.forName(driver);
			con = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB connect succes");
		} catch (IOException e) {
			System.out.println("[" + e.toString() + "]");
		} catch (ClassNotFoundException e) {
			System.out.println("[" + e.toString() + "]");
		} catch (SQLException e) {
			System.out.println("[" + e.toString() + "]");
		}
		return con;
	}

	// DB연결 해제 (오버로딩)
	public static void dbClose(Connection con, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	public static void dbClose(Connection con, PreparedStatement pstmt1, PreparedStatement pstmt2) {
		if (pstmt1 != null) {
			try {
				pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (pstmt2 != null) {
			try {
				pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void dbClose(Connection con, PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
