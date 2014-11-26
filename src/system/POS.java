package system;

import items.*;
import people.*;

public class POS {
	public static DatabaseManager databaseManager;
	public static FileManager fileManager;
	public static LoginManager loginManager;
	
	
	
	public static void main(String[] args){
		initialize(); //Initializes the database connection, setups local files 
		getLogin();	  //Sets up and shows the login screen - also takes care of the login check
		
		
	}
	public static void initialize(){
		//Initialize the employees, admin, sales - all information from text files
		fileManager = new FileManager();
		
		
		databaseManager = new DatabaseManager();
		databaseManager.connectToDatabase();
		//fileManager.copyDatabaseToTXT();			//Will copy the data gathered from the database to a temporary text file for the session - Data will be updated in the textfiles during the session but at the end, it will go back to the database
		loginManager = new LoginManager();
		loginManager.showLoginScreen();
		
	}
	
	public static void getLogin(){
		System.out.println("Login Screen");
		//Initialize and display login screen
		
		//Get pin input 
		
		//Process pin input 
		
		
		
		Employee employee = new Employee();
		employee.showScreen();
		
		Manager manager = new Manager();
		manager.showScreen();
	}
	

}
