package model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmpRequestVO {
	private int empNo;			//사원번호
	private String empName;		//사원이름	
	private String empPosition; //직급 (사장, 이사, 부장, 차장, 과장, 대리, 사원)
	private String laeveType; //직급 (사장, 이사, 부장, 차장, 과장, 대리, 사원)
	private String startDate;		//연차 시작일
	private String endDate;		//연차 종료일d
	private String reason; //사유
	private String status; //상태
	
	public int useDays(String startDate, String endDate) {
		try {
			LocalDate start = LocalDate.parse(startDate);
			LocalDate end = LocalDate.parse(endDate);
			return (int) ChronoUnit.DAYS.between(start, end) +1;
		} catch(Exception e) {
			return 0;
		}
	}

	public EmpRequestVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EmpRequestVO(int empNo, String empName, String empPosition, String laeveType, String startDate,
			String endDate, String reason, String status) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empPosition = empPosition;
		this.laeveType = laeveType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reason = reason;
		this.status = status;
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

	public String getLaeveType() {
		return laeveType;
	}

	public void setLaeveType(String laeveType) {
		this.laeveType = laeveType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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
		return "EmpRequestVO [empNo=" + empNo + ", empName=" + empName + ", empPosition=" + empPosition + ", laeveType="
				+ laeveType + ", startDate=" + startDate + ", endDate=" + endDate + ", reason=" + reason + ", status="
				+ status + "]";
	}

	
	
	
	
	
	
}
