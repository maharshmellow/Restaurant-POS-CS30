package system;

import javax.swing.SwingUtilities;

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
				
		databaseManager = new DatabaseManager();
		databaseManager.connectToDatabase();
				
	}
	
	public static void getLogin(){
		System.out.println("Login Screen");
		
		loginManager = new LoginManager();
		
		//loginManager.showLoginScreen();	
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				loginManager.showLoginScreen();
			}
		});
		
		
	}
	public static void exit(){
		//TODO Database connection - send information back 
	}
	

}
