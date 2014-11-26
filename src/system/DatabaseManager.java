package system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class DatabaseManager{
	public static void connectToDatabase(){
		
		JFrame loadingFrame = new JFrame("Loading Databases");
		JPanel loadingPanel = new JPanel();
		JLabel statusLabel = new JLabel();
		
		loadingPanel.add(statusLabel);
		loadingFrame.add(loadingPanel);		
		loadingFrame.setBounds(400, 400, 300, 50);		
		loadingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loadingFrame.setVisible(true);
		
		statusLabel.setText("Establishing Connection to Databases");
		Connection conn = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			//Connects to the database
			conn = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql459358", "sql459358", "nL6!bM9!");
			
			Statement sqlState = conn.createStatement();
					
			statusLabel.setText("Connection Established - Initializing Program");
			String showEmployees = "Select * from employees";
			ResultSet employeeNames = sqlState.executeQuery(showEmployees);
			while(employeeNames.next()){
				System.out.println(employeeNames.getString("Name") + " " + employeeNames.getString("Salary"));
			}
			
//			String showDrinks = "Select * from drinks";
//			ResultSet drinkNames = sqlState.executeQuery(showDrinks);
//			while(drinkNames.next()){
//				System.out.println(drinkNames.getString("Drink Name") + " " + drinkNames.getString("Price"));
//				
//			}
			
		}
		
		catch(SQLException ex){
			statusLabel.setText("ERROR 1 - Check Network Connection");
			loadingFrame.dispose();
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("Vendor Error: " + ex.getErrorCode());
			
		}
		catch(ClassNotFoundException e){
			statusLabel.setText("ERROR 2 - Check Network Connection");
			loadingFrame.dispose();
			e.printStackTrace();
		}
	}
}
