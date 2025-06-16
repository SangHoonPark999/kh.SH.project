package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import LeaveRequestMainPa.LeaveRequestMain;
import model.EmpListVO;
import model.EmployeeVO;


public class ManagerManager {
//관리자로그인
	public void mLogin() throws Exception {
		Scanner scan = new Scanner(System.in);
		boolean managerCk = false;
		boolean backMain = false;
		ManagerDAO managerDAO = new ManagerDAO();
		
		int empNo;
		String empPassword;
		String empName = null;
		String empPosition = null;
		Date birthDate = null;
		Date joinDate = null;
		String phoneNumber = null;
		int isAdmin = 0;
		
		
		while(true) {
			System.out.println();
			System.out.println("관리자 로그인");
			
			System.out.println("사원번호 입력 : ");
			empNo = Integer.parseInt(scan.nextLine());

			System.out.println("비밀번호 입력 : ");
			empPassword =scan.nextLine();
			
			EmployeeVO employeeVO = new EmployeeVO(empNo, empPassword,empName,empPosition,birthDate,joinDate,phoneNumber,isAdmin);
			managerCk = managerDAO.mLoginCheck(employeeVO);
			if(managerCk == false) {
				System.out.println("관리자 로그인 실패");
				managerCk = false;
				
			}else {
				System.out.println("관리자 로그인 성공");
				break;
			}
		}
		
	}//login end

//사원정보조회
	public void eList() {
		ManagerDAO md = new ManagerDAO();
		ArrayList<EmpListVO> empList = md.eList();
		if (empList.size() == 0) {
			System.out.println("사원리스트 내용이 없습니다");
			return;
		} else if (empList == null) {
			System.out.println("사원리스트 error 발생");
			return;
		}
		for (EmpListVO data : empList) {
			//System.out.println(data.toString());
			System.out.printf("사원번호 : %d | 사원이름 : %s | 직급 : %s "
							+ " 생년월일 : %s | 입사일 : %s | 전화번호 : %s"
							+ " 잔여연차 : %d | 관리자 여부 : %d \n",data.getEmpNo(), data.getEmpName(), data.getEmpPosition(), data.getBirthDate(), data.getJoinDate(), data.getPhoneNumber(), data.getRemainDay(), data.getIsAdmin() );
		}
		System.out.println();
		
	}//eList end

	//사원정보수정
	public void empUpdate() throws Exception {
		try(Scanner scan = new Scanner(System.in)) {
		EmployeeVO ev = new EmployeeVO();
		ManagerDAO mdao = new ManagerDAO();
		
		int empNo;
		String empName;
		String empPassword;
		String empPosition;
		String phoneNumber;
		int isAdmin;
		
		System.out.println("사원 전체 리스트");
		eList();
		System.out.println("수정할 사원의 번호를 입력해주세요.");
		empNo = Integer.parseInt(scan.nextLine());
		System.out.printf("%d번의 사원의 수정할 정보를 입력해주세요.",empNo);
		System.out.println("이름 : (최대 4글자까지 입력해주세요.)");
		empName = scan.nextLine();
		System.out.println("비밀번호 : (최대 12자까지 입력해주세요.)");
		empPassword = scan.nextLine();
		System.out.println("직급 : (사장, 이사, 부장, 차장, 과장, 대리, 사원)");
		empPosition = scan.nextLine();
		System.out.println("생년월일 : 'YYYY-MM-DD'");
		String _birthDate = scan.nextLine();
		System.out.println("입사일자 : 'YYYY-MM-DD'");
		String _joinDate = scan.nextLine();
		System.out.println("전화번호 : '000-XXXX-XXXX'");
		phoneNumber = scan.nextLine();
		System.out.println("관리자여부 : '사원 = 1, 관리자 = 0'");
		isAdmin = Integer.parseInt(scan.nextLine());
		
		LocalDate localDate = LocalDate.parse(_birthDate);
		Date sqlbirthDate = Date.valueOf(localDate);
		LocalDate localDate1 = LocalDate.parse(_joinDate);
		Date sqljoinDate = Date.valueOf(localDate1);
		
		ev.setEmpNo(empNo);
		ev.setEmpName(empName);
		ev.setEmpPassword(empPassword);
		ev.setEmpPosition(empPosition);
		ev.setBirthDate(sqlbirthDate);
		ev.setJoinDate(sqljoinDate);
		ev.setPhoneNumber(phoneNumber);
		ev.setIsAdmin(isAdmin);
		
		int count = mdao.eUpdate(ev);

		scan.close();
		if (count == 0) {
			System.out.println("사원정보 수정의 입력오류발생");
			return;
		} else {
			System.out.println("사원정보 수정의 완료");
		}
		System.out.println("수정된 사원정보 리스트");
		eList();
		System.out.println();
		} catch (Exception e) {
	        System.out.println("오류 발생: " + e.getMessage());
	        e.printStackTrace();
	    }
	}//empUpdate end
	
//신규사원등록
	public void empRegi() throws Exception {
		Scanner scan = new Scanner(System.in);
		EmployeeVO ev = new EmployeeVO();
		ManagerDAO mdao = new ManagerDAO();
		
		String empName;
		String empPassword;
		String empPosition;
		String phoneNumber;
		int isAdmin;
		
		
		System.out.println("신규사원의 번호를 입력해주세요.");
		System.out.println("비밀번호 : (최대 12자까지 입력해주세요.)");
		empPassword = scan.nextLine();
		System.out.println("이름 : (최대 4글자까지 입력해주세요.)");
		empName = scan.nextLine();
		System.out.println("직급 : (사장, 이사, 부장, 차장, 과장, 대리, 사원)");
		empPosition = scan.nextLine();
		System.out.println("생년월일 : 'YYYY-MM-DD'");
		String _birthDate = scan.nextLine();
		System.out.println("전화번호 : '000-XXXX-XXXX'");
		phoneNumber = scan.nextLine();
		System.out.println("관리자여부 : '사원 = 1, 관리자 = 0'");
		isAdmin = Integer.parseInt(scan.nextLine());
		
		LocalDate localDate = LocalDate.parse(_birthDate);
		Date sqlbirthDate = Date.valueOf(localDate);
		
		ev.setEmpPassword(empPassword);
		ev.setEmpName(empName);
		ev.setEmpPosition(empPosition);
		ev.setBirthDate(sqlbirthDate);
		ev.setPhoneNumber(phoneNumber);
		ev.setIsAdmin(isAdmin);
		
		int count = mdao.empInsert(ev);

		scan.close();
		if (count == 0) {
			System.out.println("사원정보 수정의 입력오류발생");
			return;
		} else {
			System.out.println("사원정보 수정의 완료");
		}
		System.out.println("수정된 사원정보 리스트");
		eList();
		System.out.println();
		
	}//empRegi end
//사원정보삭제
	public void empDelete() throws Exception {
		Scanner scan = new Scanner(System.in);
		EmployeeVO ev = new EmployeeVO();
		ManagerDAO mdao = new ManagerDAO();

		int empNo; // 입력한 일련번호
		eList();
		System.out.println();

		System.out.println("삭제할 사원번호 입력");
		System.out.print("사원번호 입력 : ");
		empNo = Integer.parseInt(scan.nextLine());
		ev.setEmpNo(empNo);
		int count = mdao.deleteEmplyeeByEmpNo(ev);
		if (count == 0) {
			System.out.printf("%s 번호 삭제의 입력오류발생\n", empNo);
			return;
		} else {
			System.out.printf("%s 번호 삭제 성공", empNo);
		}
		System.out.println();
		eList();
		System.out.println();
	}

}//MM end
