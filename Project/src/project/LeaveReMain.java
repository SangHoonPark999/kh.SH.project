package project;

import java.io.*;
import java.util.ArrayList;
//휴가연차 저장클래스
public class LeaveReMain {
	private static final String FILE_PATH = "LeaveRequests.txt";

    // 파일읽기
    public static ArrayList<LeaveRe> loadRequests() {
        ArrayList<LeaveRe> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                LeaveRe request = LeaveRe.fromCSV(line);
                list.add(request);
            }
        } catch (IOException e) {
            System.out.println("병가/연차 데이터를 읽는 중 오류 발생: " + e.getMessage());
        }
        return list;
    }

    // 파일저장
    public static void saveRequests(ArrayList<LeaveRe> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (LeaveRe req : list) {
                writer.write(req.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("병가/연차 데이터를 저장하는 중 오류 발생: " + e.getMessage());
        }
    }

}//end
