package model;

import java.sql.Date;

public class EmpListVO {
	
	private int empNo;			//사원번호
	private String empName;		//사원이름	
	private String empPassword; //사원 비밀번호
	private String empPosition; //직급 (사장, 이사, 부장, 차장, 과장, 대리, 사원)
	private Date birthDate;		//생년월일 YYYY-MM-DD
	private Date joinDate;		//입사일 YYYY-MM-DD
	private String phoneNumber; //전화번호 000-XXXX-XXXX
	private int remainDay;		//잔여 연차
	private int isAdmin;		//사원 = 1, 관리자 = 0
	
	public EmpListVO() {
		super();
	}
	public EmpListVO(int empNo, String empName, String empPassword, String empPosition, Date birthDate, Date joinDate,
			String phoneNumber, int remainDay, int isAdmin) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empPassword = empPassword;
		this.empPosition = empPosition;
		this.birthDate = birthDate;
		this.joinDate = joinDate;
		this.phoneNumber = phoneNumber;
		this.remainDay = remainDay;
		this.isAdmin = isAdmin;
	}
	public int getEmpNo() {
		return empNo;
	}
	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpPassword() {
		return empPassword;
	}
	public void setEmpPassword(String empPassword) {
		this.empPassword = empPassword;
	}
	public String getEmpPosition() {
		return empPosition;
	}
	public void setEmpPosition(String empPosition) {
		this.empPosition = empPosition;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public int getRemainDay() {
		return remainDay;
	}
	public void setRemainDay(int remainDay) {
		this.remainDay = remainDay;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
	@Override
	public String toString() {
		return "EmpListVO [empNo=" + empNo + ", empName=" + empName + ", empPassword=" + empPassword + ", empPosition="
				+ empPosition + ", birthDate=" + birthDate + ", joinDate=" + joinDate + ", phoneNumber=" + phoneNumber
				+ ", remainDay=" + remainDay + ", isAdmin=" + isAdmin + "]";
	}
	
	
	
	
	
}
