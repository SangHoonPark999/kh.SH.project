package model;

import java.sql.Date;

public class LeaveListVO {
	private int reqNo; //휴가번호
	private int empNo; //사원번호
	private String empName; //사원이름
	private String leaveType; //휴가유형 (병가 or 휴가)
	private Date startDate; //휴가 시작일
	private Date endDate; 	//휴가종료일
	private String reason;	//휴가사유
	private String status;	//신청상태 (대기, 승인, 반려)
	public LeaveListVO() {
		super();
	}
	public LeaveListVO(int reqNo, int empNo, String empName, String leaveType, Date startDate, Date endDate,
			String reason, String status) {
		super();
		this.reqNo = reqNo;
		this.empNo = empNo;
		this.empName = empName;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.status = status;
	}
	public int getReqNo() {
		return reqNo;
	}
	public void setReqNo(int reqNo) {
		this.reqNo = reqNo;
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
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "LeaveListVO [reqNo=" + reqNo + ", empNo=" + empNo + ", empName=" + empName + ", leaveType=" + leaveType
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", reason=" + reason + ", status=" + status
				+ "]";
	}
	
	
}
