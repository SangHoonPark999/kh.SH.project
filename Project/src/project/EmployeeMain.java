package project;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeMain {
	public static String menuTitle;

	public static void main(String[] args) throws IOException {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		ArrayList<LeaveRe> requests = LeaveReMain.loadRequests();
		Scanner scan = new Scanner(System.in);
		UpLoad(empList);
		// 변수선언
		boolean stopFlag = false;
		// 반복문(사원정보조회, 관리자로그인, 종료)
		while (!stopFlag) {
			// 메뉴출력
			menuDisplay();
			// 번호선택
			int choice = 0;
			choice = selectNo(scan);

			// switch
			// 1.일반사원페이지, 2.관리자이용페이지, 3.종료
			switch (choice) {
			// 사용자페이지(1,본인정보조회, 2.병가신청, 3.연차신청)
			// 사원번호와 비밀번호를 입력하여 정보조회가능,그 후 병가신청, 연차신청,종료/스위치문안에 스위치문
			case 1:

				System.out.println("사원번호와 비밀번호를 입력해주세요.");
				System.out.println("사원번호 입력 :");
				int empNo = Integer.parseInt(scan.nextLine());
				System.out.println("비밀번호 입력 :");
				int password = Integer.parseInt(scan.nextLine());
				//사원정보일치여부
				Employee found = null;
				for (Employee emp : empList) {
					if (emp.getEmpNo() == empNo && emp.getPassword() == password) {
						found = emp;
						System.out.printf("사원번호 : %d 이름 : %s 생년월일 : %s\n",emp.getEmpNo(),emp.getEmpName(),emp.getBirthDay());
						System.out.printf("입사년도 : %s 전화번호 : %s 잔여 연차 : %d\n",emp.getJoinDate(), emp.getPhoneNumber(), emp.getRemainVe());
					}
				}
				if (found == null) {
					System.out.println("정보가 틀렸습니다.\n");
					return;
				}
				

				boolean worker = false;
				while (!worker) {
					System.out.println("+---------------------------------------+");
					System.out.println(" 1. 병가 신청   2. 연차 신청   3. 종료     ");
					System.out.println("+---------------------------------------+");

					int no1 = noSelect(scan);

					switch (no1) {
					//병가신청
					case 1:
						System.out.println("병가 신청 사유를 입력해주세요:");
						String reason = scan.nextLine();
						LeaveRe req = new LeaveRe(empNo, reason, "대기", 0, "병가");
						requests.add(req);
						LeaveReMain.saveRequests(requests);
						System.out.println("병가 신청이 완료되었습니다.");
						break;
						//연차신청
					case 2:
						System.out.println("연차 신청 사유를 입력해주세요:");
						String reason1 = scan.nextLine();
						LeaveRe req2 = new LeaveRe(empNo, reason1, "대기", 0, "병가");	
						requests.add(req2);
						LeaveReMain.saveRequests(requests);
						System.out.println("연차 신청 일수를 입력해주세요:");
						int days = Integer.parseInt(scan.nextLine());
						if (days > found.getRemainVe()) {
						    System.out.println("신청 가능한 연차 일수를 초과했습니다. 현재 남은 연차: " + found.getRemainVe() + "일");
						} else {
						    LeaveRe req3 = new LeaveRe(found.getEmpNo(), reason1, "대기", days, "연차");
						    requests.add(req3);
						    LeaveReMain.saveRequests(requests);
						    System.out.println("연차 신청이 완료되었습니다.");
						}
						break;
						//종료(종료후 입력한 내용이 저장되게 한다)
					case 3:
						worker = true;
					}//switch.end
				}//while.end
				break;
				// 관리자페이지(1.사원리스트 출력, 2.신규사원등록, 3.사원 정보 수정, 4.병가리스트, 5.연차리스트)
			case 2:
				System.out.print("관리자번호 입력: ");
				int no = Integer.parseInt(scan.nextLine());
				System.out.print("비밀번호 입력: ");
				int pw = Integer.parseInt(scan.nextLine());

				if (no != 9999 || pw != 1234) {
					System.out.println("관리자 정보가 틀렸습니다.\n");
					return;
				}
				boolean admin = false;
				while (!admin) {
					System.out.println("+---------------------------------------+");
					System.out.println("🌟	🌟	🌟 메뉴 🌟     🌟       🌟");
					System.out.println("+---------------------------------------+");
					System.out.println("1.사원조회				2.신규사원등록		");
					System.out.println("3.사원정보수정			4.사원정보 삭제		");
					System.out.println("5.병가/연차신청 리스트		6.종료	 	");
					System.out.println("+---------------------------------------+");

					int no2 = 0;
					no2 = selectNo2(scan);

					switch (no2) {
					// 사원의 리스트를 페이징 기법을 사용해 출력한다.
					case 1:
						int page = 1;
						while (true) {
							int totalPage = empList.size() / 6;
							int remainValue = empList.size() % 6;
							if (remainValue != 0) {
								totalPage += 1;
							}
							int start = 6 * (page - 1);
							int stop = 6 * (page - 1) + 6;
							if (page == totalPage && remainValue != 0) {
								stop = 6 * (page - 1) + remainValue;
							}
							System.out.printf("전체 %d 페이지 / 현제 %d 페이지\n", totalPage, page);
							for (int i = start; i < stop; i++) {
								System.out.println(empList.get(i).toString());
							}
							System.out.println("(-1 : exit) 페이지 선택:");
							page = Integer.parseInt(scan.nextLine());
							if (page == -1) {
								break;
							}
						}
						break;
					// 신규사원등록
					case 2:
					
					    String empName;
					    String birthDay;
					    String joinDate;
					    String phoneNumber;
					    int remainVe;
						System.out.println("신규사원 등록을 시작합니다.");
						System.out.println("신규사원의 사번을 등록해주세요 : ");
						empNo = Integer.parseInt(scan.nextLine());
						System.out.println("신규사원의 비밀번호을 등록해주세요 : ");
						password = Integer.parseInt(scan.nextLine());
						System.out.println("신규사원의 이름을 등록해주세요 : ");
						empName = scan.nextLine();
						System.out.println("신규사원의 생년월일을 등록해주세요 : ");
						birthDay = scan.nextLine();
						System.out.println("신규사원의 입사날짜을 등록해주세요 : ");
						joinDate = scan.nextLine();
						System.out.println("신규사원의 전화번호를 등록해주세요 : ");
						phoneNumber = scan.nextLine();
						System.out.println("신규사원의 잔여연차 등록해주세요 : ");
						remainVe = Integer.parseInt(scan.nextLine());
						System.out.println("등록이 완료되었습니다. 감사합니다.");
						
						Employee emp = new Employee(empNo, password, empName, birthDay, joinDate, phoneNumber, remainVe);
						empList.add(emp);
						
						break;
					
					// 사원정보수정(txt에 저장되어있는 정보와 사번이 일치하는 사원의 정보를 수정하는 기능)
					case 3:
						
						System.out.println("수정하는 사원의 사번을 입력해주세요 : ");
						empNo = Integer.parseInt(scan.nextLine());
						Employee findEmpData = null;
						
						for(Employee emp1 : empList) {
							if(emp1.getEmpNo() == empNo) {
								findEmpData = emp1;
							}
						}
						if (findEmpData == null) {
							System.out.printf("입력하신 %d번의 사원은 존재하지 않습니다.", empNo);
						}
						System.out.println("수정하는 사원의 비밀번호을 등록해주세요 : ");
						password = Integer.parseInt(scan.nextLine());
						System.out.println("수정하는 사원의 이름을 등록해주세요 : ");
						empName = scan.nextLine();
						System.out.println("수정하는 사원의 생년월일을 등록해주세요 : ");
						birthDay = scan.nextLine();
						System.out.println("수정하는 사원의 입사날짜를 등록해주세요 : ");
						joinDate = scan.nextLine();
						System.out.println("수정하는 사원의 전화번호을 등록해주세요 : ");
						phoneNumber = scan.nextLine();
						findEmpData.setPassword(password);
						findEmpData.setEmpName(empName);
						findEmpData.setBirthDay(birthDay);
						findEmpData.setJoinDate(joinDate);
						findEmpData.setPhoneNumber(phoneNumber);
						System.out.printf("%s의 정보수정이 완료되었습니다. 감사합니다.",empName);
						break;
					// 사원정보삭제
					case 4:
						System.out.println("삭제하려는 사원의 사번을 입력해주세요.");
						empNo = Integer.parseInt(scan.nextLine());
						boolean removeEmp = false;
						for(Employee emp2 : empList) {
							if(emp2.getEmpNo() == empNo) {
								
							}
						}
						System.out.println("정말로 삭제하시겠습니까? (Y/N)");
						System.out.println("삭제가 완료되었습니다. 감사합니다.");
						break;
					// 병가연차신청리스트
					case 5:
						 if (requests.isEmpty()) {
						        System.out.println("📭 현재 신청된 병가/연차가 없습니다.");
						        return;
						    }

						    System.out.println("병가/연차 신청 리스트:");
						    for (LeaveRe req : requests) {
						        System.out.printf("사번: %d | 종류: %s | 상태: %s | 일수: %d | 사유: %s\n",
						                req.getEmpNo(), req.getType(), req.getStatus(), req.getDays(), req.getReason());
						    }

						    System.out.println("처리할 신청의 사번을 입력하세요 (-1 입력 시 종료):");
						    int targetEmpNo = Integer.parseInt(scan.nextLine());
						    if (targetEmpNo == -1) return;

						    boolean found1 = false;
						    for (LeaveRe req : requests) {
						        if (req.getEmpNo() == targetEmpNo && req.getStatus().equals("대기")) {
						            found1 = true;

						            System.out.println("신청 상세:");
						            System.out.println("종류: " + req.getType());
						            System.out.println("사유: " + req.getReason());
						            System.out.println("일수: " + req.getDays());
						            System.out.println("상태: " + req.getStatus());

						            System.out.println("승인(Y) / 반려(N)?");
						            String input = scan.nextLine().trim().toUpperCase();

						            if (input.equals("Y")) {
						                req.setStatus("승인");
						                if (req.getType().equals("연차")) {
						                    for (Employee emp1 : empList) {
						                        if (emp1.getEmpNo() == targetEmpNo) {
						                            emp1.setRemainVe(emp1.getRemainVe() - req.getDays());
						                            break;
						                        }
						                    }
						                    System.out.println("✅ 연차 승인 완료. 연차 일수 차감됨.");
						                } else {
						                    System.out.println("✅ 병가 승인 완료.");
						                }
						            } else {
						                req.setStatus("반려");
						                System.out.println("⛔ 반려 처리 완료.");
						            }
						        }
						    }
						break;

					// 종료
					case 6:
						System.out.println("종료가 완료되었습니다. 감사합니다.");
						admin = false;
						break;
					}//switch.end
				}//while.end

				// 종료
			case 3:
				stopFlag = true;
				break;

			}// switch.end
		} // while.end
		// 종료문
		System.out.println("end");

	}// main.end

	private static int selectNo2(Scanner scan) {
		int choice = 0;
		do {
			System.out.print("번호를 선택해주세요: ");
			String data = scan.nextLine();
			try {
				choice = Integer.parseInt(data);
				if (choice >= 1 && choice <= 6)
					break;
			} catch (NumberFormatException ignored) {
			}
			System.out.println("표시된 번호 내에서 선택해주세요.");
		} while (true);
		return choice;
	}

	// 일반사원 번호선택
	private static int noSelect(Scanner scan) {
		
			int choice = 0;
			do {
				System.out.print("번호를 선택해주세요: ");
				String data = scan.nextLine();
				try {
					choice = Integer.parseInt(data);
					if (choice >= 1 && choice <= 6)
						break;
				} catch (NumberFormatException ignored) {
				}
				System.out.println("표시된 번호 내에서 선택해주세요.");
			} while (true);
			return choice;
		

	}

	// 사원정보 로드
	public static void UpLoad(ArrayList<Employee> empList) {
		FileInputStream fi;
		try {
			fi = new FileInputStream("src/project/EmployeeData.txt");
			Scanner scan = new Scanner(fi);
			if (scan.hasNextLine()) {
				EmployeeMain.menuTitle = scan.nextLine();
			}
			while (true) {
				if (!scan.hasNextLine()) {
					break;
				}
				String data = scan.nextLine();
				String[] tokens = data.split(",");
				int empNo = Integer.parseInt(tokens[0]);
				int password = Integer.parseInt(tokens[1]);
				String empName = tokens[2];
				String birthDay = tokens[3];
				String joinDate = tokens[4];
				String phoneNumber = tokens[5];
				int remainVe = Integer.parseInt(tokens[6]);
				Employee emp = new Employee(empNo, password, empName, birthDay, joinDate, phoneNumber, remainVe);
				empList.add(emp);
			} // while.end
			System.out.println("List 로드 완료");
			scan.close();
			fi.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("오류발생 점검요청");
		}

	}

	// 메뉴디스플레이
	public static void menuDisplay() {
		System.out.println("+---------------------------------------+");
		System.out.println("🌟	🌟	🌟 메뉴 🌟    🌟      🌟");
		System.out.println("+---------------------------------------+");
		System.out.println(" 1. 		사용자 로그인				");
		System.out.println(" 2.		관리자 로그인				");
		System.out.println(" 3. 		   종료                  ");
		System.out.println("+---------------------------------------+");

	}

	// 메뉴번호선택
	// 스위치문번호선택
	public static int selectNo(Scanner scan) {
		{
			int choice = 0;
			do {
				System.out.print("번호를 선택해주세요: ");
				String data = scan.nextLine();
				try {
					choice = Integer.parseInt(data);
					if (choice >= 1 && choice <= 3)
						break;
				} catch (NumberFormatException ignored) {
				}
				System.out.println("표시된 번호 내에서 선택해주세요.");
			} while (true);
			return choice;
		}
	}

}
