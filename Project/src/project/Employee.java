package project;

public class Employee {
	private int empNo;
    private int password;
    private String empName;
    private String birthDay;
    private String joinDate;
    private String phoneNumber;
    private int remainVe;
    
	public Employee(int empNo, int password, String empName, String birthDay, String joinDate, String phoneNumber,
			int remainVe) {
		super();
		this.empNo = empNo;
		this.password = password;
		this.empName = empName;
		this.birthDay = birthDay;
		this.joinDate = joinDate;
		this.phoneNumber = phoneNumber;
		this.remainVe = remainVe;
	}

	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getRemainVe() {
		return remainVe;
	}

	public void setRemainVe(int remainVe) {
		this.remainVe = remainVe;
	}

	@Override
	public String toString() {
		return "Employee [사원번호" + empNo + ", 비밀번호" + password + ", 사원이름" + empName + ", 생년월일" + birthDay
				+ ", 입사날짜" + joinDate + ", 전화번호" + phoneNumber + ", 잔여연차" + remainVe + "]";
	}
    
    

}
