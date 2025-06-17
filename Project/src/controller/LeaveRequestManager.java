package controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import model.EmployeeVO;
import model.LeaveBalanceVO;
import view.MainView;

public class LeaveRequestManager {
	//연차,병가확인 (리스트확인=>(승인,반려,나가기)선택지=> 승인or반려시 DB 데이터 변경)
	// 신규연차부여 (연차없는 사원 리스트 출력 => 사원번호 선택해서 연차부여)
//1.연차없는 사원 리스트
	public void noALList() {
		LeaveRequestDAO lrdao = new LeaveRequestDAO();
		ArrayList<EmployeeVO> noALList = lrdao.noALList();
		if (noALList.size() == 0) {
			System.out.println("모든사원에게 연차가 존재합니다.");
			return;
		} else if (noALList == null) {
			System.out.println("사원리스트 error 발생");
			return;
		}
		for (EmployeeVO data : noALList) {
			//System.out.println(data.toString());
			System.out.printf("사원번호 : %d | 사원이름 : %s | 입사일 : %s | 직급 : %s ",data.getEmpNo(), data.getEmpName(), data.getJoinDate(), data.getEmpPosition());
		System.out.println();
	}//noALList end
		
	
	}//LeaveRequestManager end
//2.사원 연차 부여 
	public void empALRegi() throws Exception {
		Scanner scan = new Scanner(System.in);
		LeaveRequestDAO lrdao = new LeaveRequestDAO();
		LeaveBalanceVO lvo = new LeaveBalanceVO();
		ManagerManager mm = new ManagerManager();
		int empNo;
		int remainDay;
		
		noALList();
		
		System.out.println("연차를 부여할 사원의 번호를 입력해 주세요.");
		System.out.println("0 입력 시 뒤로가기");
		empNo = Integer.parseInt(scan.nextLine());
		if(empNo == 0) {
			return;
		}
		System.out.println("부여하는 연차일수를 입력해주세요");
		System.out.println("1년 미만 근무자 근속 1개월 시 1개 부여");
		System.out.println("1년 이상 근무자 기본15일");
		System.out.println("3년 이상 근무 할 시 1개 추가 부여");
		System.out.println("최대 부여가능한 연차일 수 25일");
		remainDay = Integer.parseInt(scan.nextLine());
		
		lvo.setEmpNo(empNo);
		lvo.setRemainDay(remainDay);
		
		int count = lrdao.empALInsert(lvo);
		
		if (count == 0) {
			System.out.println("연차부여 입력오류발생");
			return;
		} else {
			System.out.println("사원의 연차부여가 완료되었습니다.");
		}
		System.out.println("연차 부여된 사원 현황");
		mm.eList();
		System.out.println();
		MainView.managerMenu();
	}//empALRegi






}