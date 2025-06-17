package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EmpInfoVO;
import model.EmployeeVO;
import model.LeaveBalanceVO;
import model.LeaveRequestVO;


public class EmployeeDAO {
	private String selectELoginSQL = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ? AND EMP_PASSWORD = ?";
	private String selectEListSQL = "SELECT E.EMP_NO, E.EMP_NAME, E.EMP_POSITION, E.BIRTH_DATE, E.JOIN_DATE, E.PHONE_NUMBER, L.REMAIN_DAY FROM EMPLOYEE E, LEAVE_BALANCE L WHERE E.EMP_NO = L.EMP_NO AND E.EMP_NO =?";
	private String insertALSQL = "INSERT INTO LEAVE_REQUEST (REQ_NO, EMP_NO, LEAVE_TYPE, START_DATE, END_DATE, REASON) \r\n"
			+ "VALUES (LEAVE_REQUEST_SEQ.NEXTVAL, ?, '연차', ?, ?, ?)";
	private String insertSLSQL = "INSERT INTO LEAVE_REQUEST (REQ_NO, EMP_NO, LEAVE_TYPE, REASON) \r\n"
			+ "VALUES (LEAVE_REQUEST_SEQ.NEXTVAL, ?, '병가', ?)";
	private String selectStatusSQL = "SELECT * FROM LEAVE_REQUEST WHERE EMP_NO = ?";
	
//사원로그인
	public boolean eLoginCheck(EmployeeVO employeeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean eLoginCheck = false;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
			}
			pstmt = con.prepareStatement(selectELoginSQL);
			pstmt.setInt(1, employeeVO.getEmpNo());
			pstmt.setString(2, employeeVO.getEmpPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) { 
				
					eLoginCheck = true;
				}else {
					System.out.println("로그인 실패");
					eLoginCheck = false;
				}
			

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return eLoginCheck;
	}//mLogin end
//사원정보
	public EmpInfoVO empInfo(EmpInfoVO empInfoVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		EmpInfoVO _empInfoVO = null;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return null;
			}
			pstmt = con.prepareStatement(selectEListSQL);
			pstmt.setInt(1, empInfoVO.getEmpNo());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int empNo = rs.getInt("EMP_NO");
				String empName = rs.getString("EMP_NAME");
				String empPosition = rs.getString("EMP_POSITION");
				Date birthDate = rs.getDate("BIRTH_DATE");
				Date joinDate = rs.getDate("JOIN_DATE");
				String phoneNumber = rs.getString("PHONE_NUMBER");
				int remainDay = rs.getInt("REMAIN_DAY");

				_empInfoVO = new EmpInfoVO(empNo, empName, empPosition, birthDate, joinDate, phoneNumber, remainDay);
				
			}
			

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return _empInfoVO;
	}//empInfo end
//연차신청
	public int employeeALInsert(LeaveRequestVO leaveRequestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt = con.prepareStatement(insertALSQL);
			pstmt.setInt(1, leaveRequestVO.getEmpNo());
			pstmt.setDate(2, leaveRequestVO.getStartDate());
			pstmt.setDate(3, leaveRequestVO.getEndDate());
			pstmt.setString(4, leaveRequestVO.getReason());
			
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
//병가신청
	public int empSLInsert(LeaveRequestVO leaveRequestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt = con.prepareStatement(insertSLSQL);
			pstmt.setInt(1, leaveRequestVO.getEmpNo());
			pstmt.setString(2, leaveRequestVO.getReason());
			
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
//연차, 병가 신청현황
	public ArrayList<LeaveRequestVO> leaveRequestStatus(LeaveRequestVO leaveRequestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<LeaveRequestVO> requestList = new ArrayList<LeaveRequestVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return null;
			}
			pstmt = con.prepareStatement(selectStatusSQL);
			pstmt.setInt(1, leaveRequestVO.getEmpNo());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int reqNo = rs.getInt("REQ_NO");
				int empNo = rs.getInt("EMP_NO");
				String leaveType = rs.getString("LEAVE_TYPE");
				Date startDate = rs.getDate("START_DATE");
				Date endDate = rs.getDate("END_DATE");
				String reason = rs.getString("REASON");
				String status = rs.getString("STATUS");
				LeaveRequestVO _leaveRequestVO = new LeaveRequestVO(reqNo, empNo, leaveType, startDate, endDate, reason, status);
				requestList.add(_leaveRequestVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return requestList;
	}//leaveRequestStatus end
	
	
}//EmployeeDAO end
