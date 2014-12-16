package system;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import people.Employee;

public class LoginManager implements ActionListener{
	
	public List<String> employeePins = new ArrayList();
	public static JPasswordField passwordField = new JPasswordField();
	public static String password = "";
	
	JFrame loginFrame;
	
	String loginLogout = "login";
	
	public void showLoginScreen(){
		//type 1 = logout
		//Clears the password - when the employee logins from the order screen- the password needs to be cleared to enter another password
		password = "";
		
		if (passwordField.getPassword().length != 0){
			passwordField.setText("");
		}
		
		//Sets the Look and Feel to the system look and feel	    
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    } catch (InstantiationException e) {
	        e.printStackTrace();
	    } catch (IllegalAccessException e) {
	        e.printStackTrace();
	    } catch (UnsupportedLookAndFeelException e) {
	        e.printStackTrace();
	    }
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		
		
		
		//Initializes the frame and the panel
		loginFrame = new JFrame("Starbucks Login");		
		JPanel loginPanel = new JPanel();
		
		//Allows absolute positioning to occur
		loginPanel.setLayout(null);		
		
		//Initialize buttons with their texts
		//already initialized at the top - class variables 
		
		
		JButton key1 = new JButton("1");
		JButton key2 = new JButton("2");
		JButton key3 = new JButton("3");
		JButton key4 = new JButton("4");
		JButton key5 = new JButton("5");
		JButton key6 = new JButton("6");
		JButton key7 = new JButton("7");
		JButton key8 = new JButton("8");
		JButton key9 = new JButton("9");
		JButton key0 = new JButton("0");
		JButton keyBackspace = new JButton("<");
		JButton keyEnter = new JButton("Enter");
		
		//Login screen preferences - will change the login screen layout depending on what values are used
		int gap = 15;
		int buttonSize = 200;
		int leftMargin = (int) Math.round(((screenWidth / 2) - (((3 * buttonSize) + (3*gap))/2)));		//sets the leftMargin so that the entire login pad is centered on the screen
		int topMargin = 100;
				
		//DON'T EDIT - Positions the buttons and passwordField using the above values
		passwordField.setBounds(leftMargin, topMargin - (gap * 6),(buttonSize*3) + (gap *2) , 40);
		key1.setBounds(leftMargin, topMargin, buttonSize, buttonSize);
					  // vvv where it starts from the left + the width of how many buttons there are on the left + gap between buttons
		key2.setBounds(leftMargin + (buttonSize) + gap, topMargin, buttonSize, buttonSize);		
		key3.setBounds(leftMargin + (buttonSize * 2) + (gap * 2), topMargin, buttonSize, buttonSize);
		key4.setBounds(leftMargin, topMargin + buttonSize + gap, buttonSize, buttonSize);
		key5.setBounds(leftMargin + buttonSize + gap, topMargin + buttonSize + gap, buttonSize, buttonSize);
		key6.setBounds(leftMargin + (buttonSize * 2) + (gap * 2), topMargin + buttonSize + gap, buttonSize, buttonSize);
		key7.setBounds(leftMargin, topMargin + (buttonSize * 2) + (gap * 2), buttonSize, buttonSize);
		key8.setBounds(leftMargin + buttonSize + gap, topMargin + (buttonSize * 2) + (gap * 2), buttonSize, buttonSize);
		key9.setBounds(leftMargin + (buttonSize * 2) + (gap * 2), topMargin + (buttonSize * 2) + (gap * 2), buttonSize, buttonSize);
		key0.setBounds(leftMargin + buttonSize + gap, topMargin + (buttonSize * 3) + (gap * 3), buttonSize, buttonSize);
		keyBackspace.setBounds(leftMargin, topMargin + (buttonSize * 3) + (gap*3), buttonSize, buttonSize);
		keyEnter.setBounds(leftMargin + (buttonSize * 2) + (gap*2), topMargin + (buttonSize * 3) + (gap*3), buttonSize, buttonSize);
		
		//Sets the font of the passwordField
		passwordField.setEditable(false);		
		passwordField.setFont(new Font("Calibri", Font.BOLD, 50));
		
		//Sets the fonts of the buttons
		key1.setFont(new Font("Arial", Font.BOLD, 50));
		key2.setFont(new Font("Arial", Font.BOLD, 50));
		key3.setFont(new Font("Arial", Font.BOLD, 50));
		key4.setFont(new Font("Arial", Font.BOLD, 50));
		key5.setFont(new Font("Arial", Font.BOLD, 50));
		key6.setFont(new Font("Arial", Font.BOLD, 50));
		key7.setFont(new Font("Arial", Font.BOLD, 50));
		key8.setFont(new Font("Arial", Font.BOLD, 50));
		key9.setFont(new Font("Arial", Font.BOLD, 50));
		key0.setFont(new Font("Arial", Font.BOLD, 50));
		keyBackspace.setFont(new Font("Arial", Font.ITALIC, 30));
		keyEnter.setFont(new Font("Arial", Font.ITALIC, 30));
		
		//Changes the colours of the backspace and enter buttons
		keyBackspace.setForeground(Color.RED);
		keyBackspace.setBackground(Color.RED);
		keyEnter.setForeground(Color.GREEN);
		keyEnter.setBackground(Color.GREEN);
		
		//Action listeners for the buttons
		key1.setActionCommand("key1");
		key1.addActionListener(this);
		key2.setActionCommand("key2");
		key2.addActionListener(this);
		key3.setActionCommand("key3");
		key3.addActionListener(this);
		key4.setActionCommand("key4");
		key4.addActionListener(this);
		key5.setActionCommand("key5");
		key5.addActionListener(this);
		key6.setActionCommand("key6");
		key6.addActionListener(this);
		key7.setActionCommand("key7");
		key7.addActionListener(this);
		key8.setActionCommand("key8");
		key8.addActionListener(this);
		key9.setActionCommand("key9");
		key9.addActionListener(this);
		key0.setActionCommand("key0");
		key0.addActionListener(this);		
		keyBackspace.setActionCommand("BACK_KEY");
		keyBackspace.addActionListener(this);		
		keyEnter.setActionCommand("ENTER_KEY");
		keyEnter.addActionListener(this);
		
		
		
		//Adds the buttons to the panel
		loginPanel.add(passwordField);
		loginPanel.add(key1);
		loginPanel.add(key2);
		loginPanel.add(key3);
		loginPanel.add(key4);
		loginPanel.add(key5);
		loginPanel.add(key6);
		loginPanel.add(key7);
		loginPanel.add(key8);
		loginPanel.add(key9);
		loginPanel.add(key0);
		loginPanel.add(keyBackspace);
		loginPanel.add(keyEnter);
		
		//Adds the panel to the frame
		loginFrame.add(loginPanel);
		
		
		//loginFrame.setSize(700, 700);
		loginFrame.setExtendedState(loginFrame.MAXIMIZED_BOTH);
		loginFrame.setUndecorated(true);
		loginFrame.setAlwaysOnTop(true);
		
		
		
		//Makes the program exit when the the frame is closed
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Displays the frame
		loginFrame.setVisible(true);
		
//		Use in the employee and 		
//		drinkNames.add("Small Coffee");
//		drinkNames.add("Medium Coffee");
//		
//		
//		JFrame loginFrame = new JFrame("Starbucks Login");
//		JPanel loginPanel = new JPanel();
//		
//		for (int i = 0; i < drinkNames.size(); i++ ){
//			JButton button = new JButton(drinkNames.get(i));
//			loginPanel.add(button);
//			button.setBackground(Color.GREEN);
//		}
		
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if ("key1".equals(command)){			
			password = password + "1";
			passwordField.setText(password);			
		}
		else if ("key2".equals(command)){
			password = password + "2";
			passwordField.setText(password);
		}
		else if ("key3".equals(command)){
			password = password + "3";
			passwordField.setText(password);
		}
		else if ("key4".equals(command)){
			password = password + "4";
			passwordField.setText(password);
		}
		else if ("key5".equals(command)){
			password = password + "5";
			passwordField.setText(password);
		}
		else if ("key6".equals(command)){
			password = password + "6";
			passwordField.setText(password);
		}
		else if ("key7".equals(command)){
			password = password + "7";
			passwordField.setText(password);
		}
		else if ("key8".equals(command)){
			password = password + "8";
			passwordField.setText(password);
		}
		else if ("key9".equals(command)){
			password = password + "9";
			passwordField.setText(password);
		}
		else if ("key0".equals(command)){
			password = password + "0";
			passwordField.setText(password);
		}
		
		else if ("BACK_KEY".equals(command)){
			if (password.length() > 0){
				//If the password field is not empty - remove the last element from the field 
				password = password.substring(0, password.length()-1);
				passwordField.setText(password);
			}
			else{
				//The password is empty - cannot backspace from nothing
			}
			
		}
		
		if ("ENTER_KEY".equals(command)){
			//Check Password 
			checkPassword();
			
		}
		
	}
	
	public void checkPassword(){
		boolean loginSuccess = false;
		int employeeIndex = 0; 
		
				
		for (int i = 0; i < Employee.pins.size(); i++){
			if (Integer.parseInt(password)  == Employee.pins.get(i)){
				
				loginSuccess = true;	// 
				employeeIndex = i;	// Sets the employeeIndex - will be used when calling the login function
				break;		//Breaks the loop
							
			}			
		}
		if (loginSuccess){
			//Correct Login
			loginFrame.dispose();
			System.out.println("Employee Index: " + employeeIndex);
			System.out.println("Employee Name: " + Employee.names.get(employeeIndex));
			
			if (loginLogout == "login"){
				login(employeeIndex);
			}
			else{
				logout(employeeIndex);
			}
			
			//login(employeeIndex);
			//TODO add logout(employeeIndex); 
			
		}else{
			//Incorrect Login
			System.out.println("Incorrect Login");
			passwordField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
			 
		}
		

	}
	public void login(int employeeIndex){
		// Show the employee menu - start the timer of hours worked
		long epoch = System.currentTimeMillis();
		//if there is an employee signed in --- don't open another screen - just add the new employee to the list
		if (Employee.loggedIn.isEmpty()){
			Employee.loggedIn.add(Employee.names.get(employeeIndex));
			Employee.startTimeMs.add(epoch);
			SalesManager.showOrderScreen(employeeIndex);
			
		}else{
			//If there is an employee signed in already, it doesn't open a new screen
			Employee.loggedIn.add(Employee.names.get(employeeIndex));
			Employee.startTimeMs.add(epoch);
			
			System.out.println(employeeIndex);
			
			
		}
		//Employee.loggedIn.add(employeeIndex);
		//SalesManager.showOrderScreen(employeeIndex, epoch);
		
		System.out.println(epoch);
		
	}
	public void showLogoutScreen(){
		loginLogout = "logout";
		showLoginScreen();
	}
	public void logout(int employeeIndex){
		//Gets the time when the shift ended to calculate how long the employee worked
		long epoch = System.currentTimeMillis();		
		
		try{						
			double hoursWorked = (epoch - Employee.startTimeMs.get(Employee.loggedIn.indexOf(Employee.names.get(employeeIndex))))/3600000.0;
			
			//Adds the hoursWorked to the employee's total hours worked 
			Employee.hours.set(employeeIndex, Employee.hours.get(employeeIndex) + hoursWorked);		//Adds the time worked to the array - will be updated to the database 
			
			//Removes the startTime that was originally stored in the array - just in case the employee logs in again, a new timer starts
			Employee.startTimeMs.remove(Employee.loggedIn.indexOf(Employee.names.get(employeeIndex))); 
			
			//Removes the employee name from the loggedIn list 
			Employee.loggedIn.remove(Employee.names.get(employeeIndex));	
			
			
		}catch(IndexOutOfBoundsException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(loginFrame, "EMPLOYEE NOT LOGGED IN", "Logout Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
		
		if (Employee.loggedIn.isEmpty()){
			// If there are no more employees signed in - close the program 
			POS.exit();
			SalesManager.mainFrame.dispose();	// Closes the program
			
		}
		
		
	}
	


	
	
}
