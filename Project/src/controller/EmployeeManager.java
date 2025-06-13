package controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import model.EmpInfoVO;
import model.EmployeeVO;

public class EmployeeManager {
	
	//사원정보
	public void empInfo(int empNo) throws Exception {
		EmployeeDAO edao = new EmployeeDAO();
		EmpInfoVO evo = new EmpInfoVO();
		System.out.println("방문을 환영합니다.");
		evo.setEmpNo(empNo);
		EmpInfoVO result = edao.empInfo(evo);
		
		if(result != null) {
			System.out.printf("사원번호 :	%d | 사원이름 : %s | 직  급 : %s\n",result.getEmpNo(), result.getEmpName(), result.getEmpPosition());
			System.out.printf("생년월일 :	%s | 입사일자 : %s	| 전화번호 : %s\n",result.getBirthDate(), result.getJoinDate(),result.getPhoneNumber());
			System.out.printf("잔여연차 :	%d",result.getRemainDay());
		}else {
			System.out.println("해당 사원을 찾을 수 없습니다.");
		}
		
		System.out.println();
	}
	//사원로그인
	public void eLogin() throws Exception {
		Scanner scan = new Scanner(System.in);
		boolean employeeCk = false;
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
			System.out.println("사원 로그인");
			System.out.println("사원번호 입력 : ");
			empNo = Integer.parseInt(scan.nextLine());

			System.out.println("비밀번호 입력 : ");
			empPassword =scan.nextLine();
			
			EmployeeVO employeeVO = new EmployeeVO(empNo, empPassword,empName,empPosition,birthDate,joinDate,phoneNumber,isAdmin);
			employeeCk = managerDAO.mLoginCheck(employeeVO);
			if(employeeCk == false) {
				System.out.println("사원 로그인 실패");
				employeeCk = false;
				
			}else {
				System.out.println("사원 로그인 성공");
				empInfo(empNo);
				break;
			}
		}
		
	}//login end
	//사원정보 출력
//	public void empList() throws Exception {
//		EmployeeDAO edao = new EmployeeDAO();
//		System.out.println("학생 전체 리스트");
//		ArrayList<EmpInfoVO> empInfoList = edao.empInfo();
//		if (empInfoList.size() == 0) {
//			System.out.println("학생리스트 내용이 없습니다");
//			return;
//		} else if (empInfoList == null) {
//			System.out.println("학생리스트 error 발생");
//			return;
//		}
//		for (EmpInfoVO data : studentList) {
//			System.out.println(data.toString());
//		}
//		System.out.println();
//	}//empList end
	//휴가병가신청
	//신청내역확인
	
}
