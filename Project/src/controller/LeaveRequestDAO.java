package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EmpListVO;
import model.EmpRequestVO;
import model.EmployeeVO;
import model.LeaveBalanceVO;
import model.LeaveRequestVO;

public class LeaveRequestDAO {
	private String SELECTEmployeeNoALSQL = "SELECT * FROM EMPLOYEE";
	private String INSERTEmployeeALByempNoSQL = "INSERT INTO LEAVE_BALANCE VALUES (LEAVE_BALANCE_SEQ.NEXTVAL, EMP_NO=?, LEAVE_YEAR= SYSDATE, REMAIN_DAY=?)";
	private String SELECTEmployeeALListSQL = "SELECT * FROM LEAVE_REQUEST";
	private String SELECTErequestListSQL = "SELECT *\r\n"
			+ "FROM EMPLOYEE E, LEAVE_REQUEST R\r\n"
			+ "WHERE E.EMP_NO = R.EMP_NO;";
	private String UPDATErequestApproveSQL = "UPDATE LEAVE_REQUEST SET STATUS = 'APPROVE' WHERE EMP_NO = ? AND START_DATE = TO_DATE(?,'YYYY-MM-DD') AND END_DATE = TO_DATE(?,'YYYY-MM-DD') AND STATUS = 'STAY'";
	private String UPDATErequestMinusSQL = "UPDATE LEAVE_BALANCE SET REMAIN_DAY = REMAIN_DAY - ? WHERE EMP_NO =? AND REMAIN_DAY >= ?";
	private String UPDATErequestRefuseSQL = "UPDATE LEAVE_REQUEST SET STATUS = 'REFUSE' WHERE EMP_NO = ? AND LEAVE_TYPE = '연차'";
	private String UPDATEsickRequestApproveSQL = "UPDATE LEAVE_REQUEST SET STATUS = 'APPROVE' WHERE EMP_NO = ? AND LEAVE_TYPE = '병가'";
	private String UPDATEsickRequestRefuseSQL = "UPDATE LEAVE_REQUEST SET STATUS = 'REFUSE' WHERE EMP_NO = ? AND LEAVE_TYPE = '병가'";
	
	
// 사원 리스트
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
			pstmt = con.prepareStatement(SELECTEmployeeALListSQL);
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
	}//list end
	//사원 연차 부여 (연차없는 사원리스트 출력 => 사원번호 선택하여 연차 입력)
//--연차없는 사원 리스트
	public ArrayList<EmployeeVO> noALList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmployeeVO> noALList = new ArrayList<EmployeeVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
			}
			pstmt = con.prepareStatement(SELECTEmployeeNoALSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				int empNo = rs.getInt("EMP_NO");
				String empName = rs.getString("EMP_NAME");
				String empPassword = rs.getString("EMP_PASSWORD");
				String empPosition = rs.getString("EMP_POSITION");
				Date birthDate = rs.getDate("BIRTH_DATE");
				Date joinDate = rs.getDate("JOIN_DATE");
				String phoneNumber = rs.getString("PHONE_NUMBER");
				int isAdmin = rs.getInt("IS_ADMIN");
				
				EmployeeVO employeeVO = new EmployeeVO(empNo,empName,empPassword,empPosition,birthDate,joinDate,phoneNumber,isAdmin);
				noALList.add(employeeVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return noALList;
	}//noALList end
//연차부여 리스트
	public int empALInsert(LeaveBalanceVO leaveBalanceVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt = con.prepareStatement(INSERTEmployeeALByempNoSQL);
			pstmt.setInt(1, leaveBalanceVO.getEmpNo());
			pstmt.setInt(2, leaveBalanceVO.getRemainDay());
			
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}//empALInsert end
//연차,병가 신청자 리스트
	public ArrayList<EmpRequestVO> requestList() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<EmpRequestVO> requestList = new ArrayList<EmpRequestVO>();
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
			}
			pstmt = con.prepareStatement(SELECTErequestListSQL);
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				int empNo = rs.getInt("E.EMP_NO");
				String empName = rs.getString("E.EMP_NAME");
				String empPosition = rs.getString("E.EMP_POSITION");
				String leaveType = rs.getString("R.LEAVE_TYPE");
				String startDate = rs.getString("R.START_DATE");
				String endDate = rs.getString("R.END_DATE");
				String reason = rs.getString("R.REASON");
				String status = rs.getString("R.STATUS");
				
				EmpRequestVO empRequestVO = new EmpRequestVO(empNo,empName,empPosition,leaveType,startDate,endDate,reason,status);
				requestList.add(empRequestVO);
			}

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt, rs);
		}
		return requestList;
	}//list end
	// 연차 병가 승인 거부 
//연차 병가 신청자 리스트	
//연차 승인
	public int empALUpdateA(EmpRequestVO empRequestVO, String startDate, String endDate) {
		Connection con = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		int useDay = empRequestVO.useDays(startDate,endDate);
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt1 = con.prepareStatement(UPDATErequestApproveSQL);
			pstmt2 = con.prepareStatement(UPDATErequestMinusSQL);
			
			pstmt1.setInt(1, empRequestVO.getEmpNo());
			pstmt1.setString(2, empRequestVO.getStartDate());
			pstmt1.setString(3, empRequestVO.getEndDate());
			
			pstmt2.setInt(1, useDay);
			pstmt2.setInt(2, empRequestVO.getEmpNo());
			pstmt2.setInt(3, useDay);
			
			count = pstmt1.executeUpdate();
			count = pstmt2.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt1, pstmt2);
		}
		return count;
	}
	//연차 거부
	public int empALUpdateR(LeaveRequestVO leaveRequestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt = con.prepareStatement(UPDATErequestRefuseSQL);
			pstmt.setInt(1, leaveRequestVO.getEmpNo());
			
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
//병가 승인
	public int empSLUpdateA(LeaveRequestVO leaveRequestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt = con.prepareStatement(UPDATEsickRequestApproveSQL);
			pstmt.setInt(1, leaveRequestVO.getEmpNo());
			
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
//병가 거부
	public int empSLUpdateR(LeaveRequestVO leaveRequestVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			if (con == null) {
				System.out.println("DB connect fail");
				return -1;
			}
			pstmt = con.prepareStatement(UPDATEsickRequestRefuseSQL);
			pstmt.setInt(1, leaveRequestVO.getEmpNo());
			
			count = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("createStatement 오류발생");
		} finally {
			DBUtil.dbClose(con, pstmt);
		}
		return count;
	}
	
	
	
	
	
}
