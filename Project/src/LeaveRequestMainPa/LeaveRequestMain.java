package LeaveRequestMainPa;

import java.sql.Connection;
import java.util.Scanner;

import controller.DBUtil;
import controller.EmployeeManager;
import controller.ManagerManager;
import view.EmployeeChoice;
import view.MainChoice;
import view.MainView;
import view.ManagerChoice;


public class LeaveRequestMain {
	public static Scanner scan = new Scanner(System.in);
	public static void main(String[] args) {
		//DB연결
		Connection con = DBUtil.getConnection();
		if(con == null) {
			System.out.println("DB connect fail");
			return;
		}
		int choice = 0;
		boolean exitFlag = false;
		// main
		while (!exitFlag) {
			try {
				MainView.mainMenu();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				//사원 로그인
				case MainChoice.EMPLOYEE:
					employeeMenu();
					break;
				//관리자 로그인
				case MainChoice.MANAGER:
					managerMenu();
					break;
				case MainChoice.EXIT:
					System.out.println("EXIT");
					exitFlag = true;
					break;
				}//switch end
			} catch (Exception e) {
				System.out.println("연차관리 프로그램 오류발생");
				exitFlag = true;
			}
		} // while end

		System.out.println("연차관리 프로그램 종료");

	}//main end
	//employeeMenu
	public static void employeeMenu() {
		int choice = 0;
		boolean exitFlag = false;
		// main
		while (!exitFlag) {
			try {
				//사원로그인
				EmployeeManager em = new EmployeeManager();
				em.eLogin();
				MainView.employeeMenu();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
				//연차,병가신청
				case EmployeeChoice.REQUEST:
					
					break;
				//연차, 병가 신청현황
				case EmployeeChoice.REQUESTCHECK:
					break;
				
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
		
	}
	//managerMenu
	public static void managerMenu() {
		int choice = 0;
		boolean exitFlag = false;

		// main
		while (!exitFlag) {
			try {
				ManagerManager mm = new ManagerManager();
				mm.mLogin();
				MainView.managerMenu();
				choice = Integer.parseInt(scan.nextLine());
				switch (choice) {
			//사원정보리스트
				case ManagerChoice.EMPLOYEELIST:
					mm.eList();
					break;
			//사원정보수정
				case ManagerChoice.EMPLOYEEUPDATE:
					mm.empUpdate();
					break;
			//사원정보등록
				case ManagerChoice.EMPLOYEELISTREGIST:
					mm.empRegi();
					break;
			//사원정보삭제
				case ManagerChoice.EMPLOYEEDELETE:
					mm.empDelete();
					break;
			//병가/연차 확인
				case ManagerChoice.REQUESTLIST:
					break;
				//연차등록
				case ManagerChoice.ANNUALLEAVE:
					break;
				//관리자모드 종료
				case ManagerChoice.EXIT:
					System.out.println("EXIT");
					exitFlag = true;
					break;
				}//switch end
			} catch (Exception e) {
				System.out.println("관리자 프로그램 오류발생");
				exitFlag = true;
			}
		} // while end
		
	}
	

}//class end
