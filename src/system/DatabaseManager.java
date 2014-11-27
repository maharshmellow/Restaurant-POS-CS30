package system;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
public class DatabaseManager{
	public static void connectToDatabase(){
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
			
			//Queries to be executed to get all the information from the database
			String showEmployees = "Select * from employees";
			String showDrinks = "Select * from drinks";
			String showFood = "Select * from food";
			String showItems = "Select * from items";
			
			//Executing queries to get information from each table of the database
			//Assigns values to the arrays in the employee, drink, food, and item classes
			ResultSet employees = sqlState.executeQuery(showEmployees);
			while(employees.next()){
				System.out.println(employees.getString("Name") + " " + employees.getString("Salary") + " " + employees.getString("Hours Worked") + " " + employees.getString("Pin"));
			}
			
			ResultSet drinks = sqlState.executeQuery(showDrinks);
			while(drinks.next()){
				System.out.println(drinks.getString("Drink Name") + " " + drinks.getString("Price"));
			}
			
			ResultSet food = sqlState.executeQuery(showFood);
			while(food.next()){
				System.out.println(food.getString("Food Name") + " " + food.getString("Price"));
			}
			
			ResultSet items = sqlState.executeQuery(showItems);
			while(items.next()){
				System.out.println(items.getString("Item Name") + " " + items.getString("Price"));
			} 
						
			//Close the loading frame 
			loadingFrame.dispose();
			
		}
		
		catch(SQLException ex){
			statusLabel.setText("ERROR 1 - Check Network Connection");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("Vendor Error: " + ex.getErrorCode());
			
		}
		catch(ClassNotFoundException e){
			statusLabel.setText("ERROR 2 - Check Network Connection");
			e.printStackTrace();
		}
	}
	
}
