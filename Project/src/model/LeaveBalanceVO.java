package model;

import java.sql.Date;

public class LeaveBalanceVO {
	
	private int balNo;			//잔여연차 번호
	private int empNo; 			//사원번호
	private Date leaveYear; 	//연차 연도 SELECT TO_CHAR(LEAVE_YEAR, 'YYYY') FROM LEAVE_BALANCE;
	private int remainDay;		//잔여 연차
	
	public LeaveBalanceVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LeaveBalanceVO(int balNo, int empNo, Date leaveYear, int remainDay) {
		super();
		this.balNo = balNo;
		this.empNo = empNo;
		this.leaveYear = leaveYear;
		this.remainDay = remainDay;
	}

	public int getBalNo() {
		return balNo;
	}

	public void setBalNo(int balNo) {
		this.balNo = balNo;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public Date getLeaveYear() {
		return leaveYear;
	}

	public void setLeaveYear(Date leaveYear) {
		this.leaveYear = leaveYear;
	}

	public int getRemainDay() {
		return remainDay;
	}

	public void setRemainDay(int remainDay) {
		this.remainDay = remainDay;
	}

	@Override
	public String toString() {
		return "LeaveBalanceVO [balNo=" + balNo + ", empNo=" + empNo + ", leaveYear=" + leaveYear + ", remainDay="
				+ remainDay + "]";
	}
	
	
	
}
