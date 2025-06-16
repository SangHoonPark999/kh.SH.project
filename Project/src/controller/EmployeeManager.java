package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.EmpInfoVO;
import model.EmployeeVO;
import model.LeaveRequestVO;
import view.EmployeeChoice;
import view.MainView;

public class EmployeeManager {
//사원로그인
	public void eLogin() throws Exception {
		Scanner scan = new Scanner(System.in);
		boolean employeeCk = false;
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
	
//사원정보 및 연차,병가 신청 및 확인
	public void empInfo(int empNo) throws Exception {
		Scanner scan = new Scanner(System.in);
		int choice = 0;
		boolean exitFlag = false;
		
		EmployeeDAO edao = new EmployeeDAO();
		EmpInfoVO evo = new EmpInfoVO();
		
		LeaveRequestDAO lrdao = new LeaveRequestDAO();
		LeaveRequestVO lrvo = new LeaveRequestVO();
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
		MainView.employeeMenu();
		
		// 연차/병가 신청 및 신청현황
		while (!exitFlag) {
			try {
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				//연차,병가신청
				case EmployeeChoice.REQUEST:
					
					System.out.println("사용하시는 연차의 종류를 입력해주세요.");
					System.out.println("1.연차 | 2.병가 | 3.종료");
					int leaveChoice = Integer.parseInt(scan.nextLine());
					switch(leaveChoice) {
					//연차신청
					case 1:
						System.out.println("연차시작일을 입력해 주세요");
						String startDate = scan.nextLine();
						System.out.println("연차 종료일을 입력해주세요");
						String endDate = scan.nextLine();
						System.out.println("연차사유를 입력해주세요");
						String reason = scan.nextLine();
						
						LocalDate sDate = LocalDate.parse(startDate);
						Date sDateSQL= Date.valueOf(sDate);
						LocalDate eDate = LocalDate.parse(endDate);
						Date eDateSQL = Date.valueOf(eDate);
						lrvo.setEmpNo(empNo);
						lrvo.setStartDate(sDateSQL);
						lrvo.setEndDate(eDateSQL);
						lrvo.setReason(reason);
						
						int count = edao.employeeALInsert(lrvo);

						scan.close();
						if (count == 0) {
							System.out.println("연차입력 오류발생");
							return;
						} else {
							System.out.println("연차신청 완료");
						}
						
						break;
					//병가신청
					case 2:
						lrvo.setEmpNo(empNo);
						System.err.println("병가 사유를 입력해주세요");
						String sickReason = scan.nextLine();
						
						lrvo.setReason(sickReason);
						
						int _count = edao.empSLInsert(lrvo);

						scan.close();
						if (_count == 0) {
							System.out.println("병가신청 오류발생");
							return;
						} else {
							System.out.println("병가신청 완료");
						}
						break;
					//종료
					case 3:
						System.out.println("EXIT");
						exitFlag = true;
						break;
					}//leaveChoiceSwitch
					break;
				//연차, 병가 신청현황
				case EmployeeChoice.REQUESTCHECK:
					lrvo.setEmpNo(empNo);
					LeaveRequestVO leaveStatus = edao.leaveRequestStatus(lrvo);
					if(result != null) {
						System.out.printf("사원번호 :	%d | 휴가 유형 : %s | 상  태 : %s\n",leaveStatus.getEmpNo(), leaveStatus.getLeaveType(), leaveStatus.getStatus());
						System.out.printf("사  유 :	%s ",leaveStatus.getReason());
						MainView.employeeMenu();
						break;
					}else {
						System.out.println("신청하신 연차 or 병가가 없습니다.");
						MainView.employeeMenu();
						break;
					}
					
					
				
				case EmployeeChoice.EXIT:
					System.out.println("EXIT");
					exitFlag = true;
					break;
				}//switch end
			} catch (Exception e) {
				System.out.println("사원메뉴 오류발생");
				exitFlag = true;
			}
		} // while end
		
		System.out.println();
	}
	//사원연차신청 사원번호 입력? 휴가 유형 1.연차, 2.병가 3.종료
	//1.연차 입력 시 시작날짜,종료날짜 입력 'yyyyMMdd'
	//사유까지 입력 후 신청종료 (상태 디폴트값은 stay)
	
	//신청 현황은 leave_request에서 상태를 표시
	
	
	
}
