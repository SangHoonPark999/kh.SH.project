package project;

public class LeaveRe {
	private int empNo;
	private String reason;
	private String status; // 승인, 반려, 대기
	private int days;//병가는 0으로
	private String type; // "병가" or "연차"

	public LeaveRe(int empNo, String reason, String status, int days, String type) {
		super();
		this.empNo = empNo;
		this.reason = reason;
		this.status = status;
		this.days = days;
		this.type = type;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
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

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "LeaveRequest [empNo=" + empNo + ", reason=" + reason + ", status=" + status + ", days=" + days
				+ ", type=" + type + "]";
	}

	public static LeaveRe fromCSV(String csv) {
		String[] tokens = csv.split(",");
		return new LeaveRe(Integer.parseInt(tokens[0]), tokens[1], tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
	}
}
