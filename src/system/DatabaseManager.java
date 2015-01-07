package system;
import items.Drinks;
import items.Food;
import items.Products;

import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import people.Employee;

public class DatabaseManager extends Employee{
	static JFrame loadingFrame;

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
		
		loadingFrame = new JFrame();
		JPanel loadingPanel = new JPanel();
		JLabel statusLabel = new JLabel();
		loadingFrame.setTitle("Loading Databases");
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
				System.out.println(employees.getString("Name") + " " + employees.getString("Salary") + " " + employees.getString("Hours_Worked") + " " + employees.getString("Pin"));
				Employee.names.add(employees.getString("Name"));
				Employee.salary.add(Double.parseDouble(employees.getString("Salary")));
				Employee.hours.add(Double.parseDouble(employees.getString("Hours_Worked")));
				Employee.pins.add(Integer.parseInt(employees.getString("Pin")));
				
				
				
			}
			
			ResultSet drinks = sqlState.executeQuery(showDrinks);
			while(drinks.next()){
				System.out.println(drinks.getString("Drink Name") + " " + drinks.getString("Price"));
				Drinks.names.add(drinks.getString("Drink Name"));
				Drinks.prices.add(Double.parseDouble(drinks.getString("Price")));
			}
			
			ResultSet food = sqlState.executeQuery(showFood);
			while(food.next()){
				System.out.println(food.getString("Food Name") + " " + food.getString("Price"));
				Food.names.add(food.getString("Food Name"));
				Food.prices.add(Double.parseDouble(food.getString("Price")));
				
				
			}
			
			ResultSet items = sqlState.executeQuery(showItems);
			while(items.next()){
				System.out.println(items.getString("Item Name") + " " + items.getString("Price"));
				Products.names.add(items.getString("Item Name"));
				Products.prices.add(Double.parseDouble(items.getString("Price")));
			} 
			//Close the database connection to free up memory
			conn.close();
						
			//Close the loading frame 
			loadingFrame.setVisible(false);
			System.out.println(Employee.names);
			System.out.println(Employee.hours);
			System.out.println(Employee.salary);
			System.out.println(Employee.pins);
			System.out.println(Drinks.names);
			System.out.println(Drinks.prices);
			System.out.println(Food.names);
			System.out.println(Food.prices);
			System.out.println(Products.names);
			System.out.println(Products.prices);
			
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
	
	public static void addTransaction(double transactionTotal, String order, long time, String payment){
		//TODO NEED INVOKELATER SOMEWHERE IN THE CODE - SEARCH IT UP
		try {
					
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conn = null;
			conn = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql459358", "sql459358", "nL6!bM9!");
			
			Statement sqlState = conn.createStatement();
				
			//Queries to be executed to get all the information from the database
			String transactionQuery = "INSERT INTO sales " + "VALUES (" + transactionTotal + ",'" + order + "'," + time + ",'" + payment + "'" + ")";
			//TODO connect to database and executeUpdate vvvvvvvv(2 lines donw)
			System.out.println("Transaction Query: " + transactionQuery);
			//Adds the transaction to the sales table of the database
			sqlState.executeUpdate(transactionQuery);
			conn.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error: Could not save transaction to database");
			e.printStackTrace();
		}
		
		
		
		
		
	}
	
	
}

