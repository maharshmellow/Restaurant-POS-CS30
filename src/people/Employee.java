package people;

import system.LoginManager;
import java.util.ArrayList;
import java.util.List;

public class Employee extends LoginManager{
	public List<String> employeeNames = new ArrayList();
	public List<Integer> employeePins = new ArrayList();
	public List<Double> employeeSalary = new ArrayList();
	public List<Double> employeeHours = new ArrayList();
	
	public static void showScreen(int employeeIndex, double shiftStartTimeMs){
		System.out.println("Employee Screen");
	}
}
