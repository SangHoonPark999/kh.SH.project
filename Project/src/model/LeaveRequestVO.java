package model;

import java.sql.Date;

public class LeaveRequestVO {
	private int reqNo;			//휴가신청번호
	private int empNo; 			//사원번호
	private String leaveType;	//휴가유형 (연차/병가)
	private Date startDate; 	//시작일자
	private Date endDate;		//종료일자
	private String reason;		//사유
	private String status;		//상태(대기, 반려, 승인)
	
	public LeaveRequestVO() {
		super();
	}

	public LeaveRequestVO(int reqNo, int empNo, String leaveType, Date startDate, Date endDate, String reason,
			String status) {
		super();
		this.reqNo = reqNo;
		this.empNo = empNo;
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
		return "LeaveRequestVO [reqNo=" + reqNo + ", empNo=" + empNo + ", leaveType=" + leaveType + ", startDate="
				+ startDate + ", endDate=" + endDate + ", reason=" + reason + ", status=" + status + "]";
	}
}
