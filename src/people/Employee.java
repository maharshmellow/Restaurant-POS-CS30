package people;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import system.LoginManager;

public class Employee extends LoginManager implements ActionListener{
	public static List<String> loggedIn = new ArrayList();
	public static List<Double> timeWorked = new ArrayList();
	
	
	public static List<String> names = new ArrayList();
	public static List<Integer> pins = new ArrayList();
	public static List<Double> salary = new ArrayList();
	public static List<Double> hours = new ArrayList();
	
	public static List<String> orderNames = new ArrayList();
	public static List<Double> orderPrices = new ArrayList();
	
}