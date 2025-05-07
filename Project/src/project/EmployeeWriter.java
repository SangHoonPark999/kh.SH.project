package project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeWriter {

	public static void main(String[] args) {
	
	        List<Employee> employees = new ArrayList<>();
	        
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("employeeData.txt"))) {
	            for (Employee emp : employees) {
	                writer.write(emp.getEmpNo() + "," +
	                             emp.getPassword() + "," +
	                             emp.getEmpName() + "," +
	                             emp.getBirthDay() + "," +
	                             emp.getJoinDate() + "," +
	                             emp.getPhoneNumber() + "," +
	                             emp.getRemainVe());
	                writer.newLine();
	            }
	            System.out.println("직원 정보가 employeeData.txt에 저장되었습니다.");
	        } catch (IOException e) {
	            System.out.println("파일 저장 중 오류 발생: " + e.getMessage());
	        }
	    

	}//main.end

}
