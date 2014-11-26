package system;

import java.sql.*;
public class DatabaseManager {
	public static void connectToDatabase(){
		
		Connection conn = null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("0");
			conn = DriverManager.getConnection("jdbc:mysql://sql4.freesqldatabase.com/sql459358", "sql459358", "nL6!bM9!");
			System.out.println("1");
			Statement sqlState = conn.createStatement();
			
//			String selectStuff = "Select Pin from employees";
//			
//			ResultSet rows = sqlState.executeQuery(selectStuff);
//			
//			while (rows.next()){
//				System.out.println(rows.getString("Pin"));
//			}
//			
//			System.out.println("Inserting...");
//			String sql = "INSERT INTO employees " + "VALUES ('Cow5', 1556, 2.5, 10.25)";
//			
//			sqlState.executeUpdate(sql);
//			
//			ResultSet newRows = sqlState.executeQuery("Select Name from employees");
//			while(newRows.next()){
//				System.out.println(newRows.getString("Name"));
//			}
			
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
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("Vendor Error: " + ex.getErrorCode());
			
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}
