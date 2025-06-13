package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.EmpInfoVO;
import model.EmployeeVO;


public class EmployeeDAO {
	private String selectELoginSQL = "SELECT * FROM EMPLOYEE WHERE EMP_NO = ? AND EMP_PASSWORD = ?";
	private String selectEListSQL = "SELECT E.EMP_NO, E.EMP_NAME, E.EMP_POSITION, E.BIRTH_DATE, E.JOIN_DATE, E.PHONE_NUMBER, L.REMAIN_DAY FROM EMPLOYEE E, LEAVE_BALANCE L WHERE E.EMP_NO = L.EMP_NO AND E.EMP_NO =?";
	
	
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
	
	
	
	
	
	
}//EmployeeDAO end
