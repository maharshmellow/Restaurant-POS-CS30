package people;

import system.LoginManager;
import java.util.ArrayList;
import java.util.List;

public class Employee extends LoginManager{
	public static List<String> names = new ArrayList();
	public static List<Integer> pins = new ArrayList();
	public static List<Double> salary = new ArrayList();
	public static List<Double> hours = new ArrayList();
	
	public static void showScreen(int employeeIndex, double shiftStartTimeMs){
		System.out.println("Employee Screen");
	}
}
