package system;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import people.Employee;

public class LoginManager implements ActionListener{
	
	public List<String> employeePins = new ArrayList();
	public static JPasswordField passwordField;
	public static String password = "";
	
	JFrame loginFrame;
	
	public void showLoginScreen(){
		
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
		
		//Initializes the frame and the panel
		loginFrame = new JFrame("Starbucks Login");		
		JPanel loginPanel = new JPanel();
		
		//Allows absolute positioning to occur
		loginPanel.setLayout(null);		
		
		//Initialize buttons with their texts
		passwordField = new JPasswordField(4);
		
		
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
		int gap = 10;
		int buttonSize = 120;
		int leftMargin = 150;
		int topMargin = 70;
				
		//DON'T EDIT - Positions the buttons and passwordfield using the above values
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
		passwordField.setFont(new Font("Arial", Font.BOLD, 50));
		
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
		
		//Sets the size of the frame
		loginFrame.setSize(700, 700);
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
		
		employeePins.add("1066");
		
		for (int i = 0; i < employeePins.size(); i++){
			if (password.equals(employeePins.get(i))){
				
				loginSuccess = true;	// 
				employeeIndex = i;	// Sets the employeeIndex - will be used when calling the login function
				break;		//Breaks the loop
							
			}			
		}
		if (loginSuccess){
			//Correct Login
			loginFrame.dispose();
			login(employeeIndex);
		}else{
			//Incorrect Login
			System.out.println("Incorrect Login");
			passwordField.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
			 
		}
		

	}
	public void login(int employeeIndex){
		// Show the employee menu - start the timer of hours worked
		long epoch = System.currentTimeMillis();
		//Employee.showScreen(employeeIndex, epoch);
		System.out.println(epoch);
	}
	public void logout(){
		//Saves all information and displays the login screen after an employee logs out 
		
	}


	
	
}
