package people;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import system.LoginManager;

public class Employee extends LoginManager implements ActionListener{
	public static List<String> loggedIn = new ArrayList();		//Stores the indexes of employees signed in 	
	public static List<Long> startTimeMs = new ArrayList();
	public static List<Double> endTimeMs = new ArrayList();
	
	public static List<Double> timeWorked = new ArrayList();	//Store the hoursworked at the employee index - everything else is 0
	
	
	public static List<String> names = new ArrayList();
	public static List<Integer> pins = new ArrayList();
	public static List<Double> salary = new ArrayList();
	public static List<Double> hours = new ArrayList();
	
	public static List<String> orderNames = new ArrayList();
	public static List<Double> orderPrices = new ArrayList();
	
}