package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EmpListVO;
import model.EmployeeVO;
import model.LeaveBalanceVO;



public class ManagerDAO {
	private String selectMLoginSQL = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ? AND EMP_PASSWORD = ?";
	private String selectByListSQL = "SELECT E.EMP_NO, E.EMP_NAME, E.EMP_PASSWORD, E.EMP_POSITION, E.BIRTH_DATE, E.JOIN_DATE, E.PHONE_NUMBER, L.REMAIN_DAY, E.IS_ADMIN\r\n"
			+ "FROM EMPLOYEE E, LEAVE_BALANCE L\r\n"
			+ "WHERE E.EMP_NO = L.EMP_NO";
	private String empUpdateByEmployeeSQL = "UPDATE EMPLOYEE\r\n"
			+ "SET EMP_NAME = ?, EMP_PASSWORD = ? ,EMP_POSITION = ?, BIRTH_DATE = ?, JOIN_DATE = ?, PHONE_NUMBER = ?,  IS_ADMIN = ?\r\n"
			+ "WHERE EMP_NO = ?";
	private String insertByEmployeeSQL = "INSERT INTO EMPLOYEE VALUES (EMPLOYEE_SEQ.nextval,?,?,?,?,SYSDATE,?,?)";
	private String deleteEmplyeeByEmpNoSQL = "DELETE FROM EMPLOYEE WHERE EMP_NO = ?";
	
	
//관리자로그인
	public boolean mLoginCheck(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean mLoginCheck = false;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
			}
			pstmt = con.prepareStatement(selectMLoginSQL);
			pstmt.setInt(1, employeeVO.getEmpNo());
			pstmt.setString(2, employeeVO.getEmpPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) { 
				int isAdmin =rs.getInt("IS_ADMIN");
				if(isAdmin == 0) {
					mLoginCheck = true;
				}else {
					System.out.println("권한이 없습니다.");
					mLoginCheck = false;
				}
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return mLoginCheck;
	}//mLogin end
	public boolean eLoginCheck(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean mLoginCheck = false;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
			}
			pstmt = con.prepareStatement(selectMLoginSQL);
			pstmt.setInt(1, employeeVO.getEmpNo());
			pstmt.setString(2, employeeVO.getEmpPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) { 
				int isAdmin =rs.getInt("IS_ADMIN");
				if(isAdmin == 0 || isAdmin == 1) {
					mLoginCheck = true;
				}else {
					System.out.println("권한이 없습니다.");
					mLoginCheck = false;
				}
			}
			
		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return mLoginCheck;
	}//eLogin end
	
//사원정보리스트
	public ArrayList<EmpListVO> eList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpListVO> empList = new ArrayList<EmpListVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
			}
			pstmt = con.prepareStatement(selectByListSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				int empNo = rs.getInt("EMP_NO");
				String empName = rs.getString("EMP_NAME");
				String empPassword = rs.getString("EMP_PASSWORD");
				String empPosition = rs.getString("EMP_POSITION");
				Date birthDate = rs.getDate("BIRTH_DATE");
				Date joinDate = rs.getDate("JOIN_DATE");
				String phoneNumber = rs.getString("PHONE_NUMBER");
				int remainDay = rs.getInt("REMAIN_DAY");
				int isAdmin = rs.getInt("IS_ADMIN");
				
				EmpListVO empListVO = new EmpListVO(empNo,empName,empPassword,empPosition,birthDate,joinDate,phoneNumber,remainDay,isAdmin);
				empList.add(empListVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return empList;
	}//eList end
	
//사원정보수정
	public int eUpdate(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt = con.prepareStatement(empUpdateByEmployeeSQL);
			pstmt.setString(1, employeeVO.getEmpName());
			pstmt.setString(2, employeeVO.getEmpPassword());
			pstmt.setString(3, employeeVO.getEmpPosition()); 
			pstmt.setDate(4, employeeVO.getBirthDate()); 
			pstmt.setDate(5, employeeVO.getJoinDate()); 
			pstmt.setString(6, employeeVO.getPhoneNumber()); 
			pstmt.setInt(7, employeeVO.getIsAdmin()); 
			pstmt.setInt(8, employeeVO.getEmpNo()); 

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;

	}
	
//신규사원등록
	public int empInsert(EmployeeVO employeeVO) {
		// 멤버변수
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt = con.prepareStatement(insertByEmployeeSQL);
			pstmt.setString(1, employeeVO.getEmpPassword());
			pstmt.setString(2, employeeVO.getEmpName());
			pstmt.setString(3, employeeVO.getEmpPosition()); 
			pstmt.setDate(4, employeeVO.getBirthDate()); 
			pstmt.setString(5, employeeVO.getPhoneNumber()); 
			pstmt.setInt(6, employeeVO.getIsAdmin());
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
//사원 삭제
	public int deleteEmplyeeByEmpNo(EmployeeVO employeeVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt = con.prepareStatement(deleteEmplyeeByEmpNoSQL);

			pstmt.setInt(1, employeeVO.getEmpNo());

			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}//deleteEmplyeeByEmpNo end
	
}//class end
