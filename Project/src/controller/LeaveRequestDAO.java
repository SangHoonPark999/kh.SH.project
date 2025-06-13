package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.EmpListVO;
import model.EmployeeVO;

public class LeaveRequestDAO {
	private String SELECTEmployeeNoALSQL = "SELECT EMP_NO, EMP_NAME, JOIN_DATE, EMP_POSITION FROM EMPLOYEE";
	private String SELECTEmployeeALListSQL = "SELECT * FROM LEAVE_REQUEST ";
	private String INSERTEmployeeALSQL = "INSERT INTO ";
	
	// 연차/병가 신청자 확인 리스트
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
//연차없는 사원 리스트
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
	
}
