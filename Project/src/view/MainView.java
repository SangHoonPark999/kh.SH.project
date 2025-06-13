package view;

public class MainView {
	//main menu
	public static void mainMenu() {
		System.out.println();
		System.out.println("KH 연차관리 프로그램");
		System.out.println("1. 일반사원 로그인");
		System.out.println("2. 관리자 로그인");
		System.out.println("3. 프로그램 종료");
		System.out.print("번호 선택 : ");
	}
	//employee menu
	public static void employeeMenu() {
		System.out.println();
		System.out.println("사원 정보 메뉴 번호를 입력하세요.");
		System.out.println("1. 휴가/병가 신청");
		System.out.println("2. 신청 내역 확인");
		System.out.println("3. 메인으로 돌아가기");
		System.out.print("번호 선택 : ");
	}
	//manager menu
	public static void managerMenu() {
		System.out.println("관리자 메뉴 입니다.");
		System.out.println("번호를 입력해주세요.");
		System.out.println("1. 사원 정보 조회");
		System.out.println("2. 사원 정보 수정");
		System.out.println("3. 신규 사원 등록");
		System.out.println("4. 사원 정보 삭제");
		System.out.println("5. 병가/연차 확인");
		System.out.println("6. 신규 연차 부여");
		System.out.println("7. 메인으로 돌아가기");
		System.out.print("번호 선택 : ");
	}
	
}//class end
