package model;

import java.sql.Date;

public class EmpInfoVO {
	private int empNo;			//사원번호
	private String empName;		//사원이름
	private String empPosition; //직급 (사장, 이사, 부장, 차장, 과장, 대리, 사원)
	private Date birthDate;		//생년월일 YYYY-MM-DD
	private Date joinDate;		//입사일 YYYY-MM-DD
	private String phoneNumber; //전화번호 000-XXXX-XXXX
	private int remainDay;		//잔여 연차
	public EmpInfoVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmpInfoVO(int empNo, String empName, String empPosition, Date birthDate, Date joinDate, String phoneNumber,
			int remainDay) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empPosition = empPosition;
		this.birthDate = birthDate;
		this.joinDate = joinDate;
		this.phoneNumber = phoneNumber;
		this.remainDay = remainDay;
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
	@Override
	public String toString() {
		return "EmpInfoVO [empNo=" + empNo + ", empName=" + empName + ", empPosition=" + empPosition + ", birthDate="
				+ birthDate + ", joinDate=" + joinDate + ", phoneNumber=" + phoneNumber + ", remainDay=" + remainDay
				+ "]";
	}
	
	
	
	
}
