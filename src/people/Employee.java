package people;
import items.Drinks;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import system.LoginManager;

public class Employee extends LoginManager{
	public static List<String> names = new ArrayList();
	public static List<Integer> pins = new ArrayList();
	public static List<Double> salary = new ArrayList();
	public static List<Double> hours = new ArrayList();
	
	public static void showScreen(int employeeIndex, double shiftStartTimeMs){
		// Shows the main menu for employees ( some things restricted ) 
		
		JFrame mainFrame = new JFrame("Starbucks POS");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		
		List<JButton> buttons = new ArrayList();
		int gap = 2;
		int buttonWidth = 100;
		int buttonHeight = 100;
		int leftMargin = 30;
		int topMargin = 20;
	
		List<String> column1Drinks = new ArrayList();
		List<String> column2Drinks = new ArrayList();
		List<String> column3Drinks = new ArrayList();
		
		for (int i = 0; i < Drinks.names.size(); i++){
			//Checks the sizes of drinks and assigns them to a column list
			String[] splitDrinkName = Drinks.names.get(i).split("\\s+");				
			
			if (splitDrinkName[0].toLowerCase().contains("grande")){
				//Grande sized drink - 2nd column
				column2Drinks.add(Drinks.names.get(i));
			}
			else if (splitDrinkName[0].toLowerCase().contains("venti")){
				//Venti Sized drink - 3rd column
				column3Drinks.add(Drinks.names.get(i));	
			}
			else{
				//Tall sized drink / drink size not available - 1st column
				column1Drinks.add(Drinks.names.get(i));
			}
		}
		
		for (int i = 0; i < column1Drinks.size(); i++){
			//Add the buttons - set their bounds
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitDrinkName = column1Drinks.get(i).split("\\s+");
			
			for (int j = 0; j < splitDrinkName.length; j++){
				text = text + splitDrinkName[j] + "<p>";				
			}
			JButton button = new JButton(text);	
			button.setBackground(Color.BLUE);
			button.setBounds(leftMargin, (topMargin + (i * buttonHeight)) + gap, buttonWidth, buttonHeight);
			mainPanel.add(button);
		}
		for (int i = 0; i < column2Drinks.size(); i++){
			//Add the buttons - set their bounds
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitDrinkName = column2Drinks.get(i).split("\\s+");
			
			for (int j = 0; j < splitDrinkName.length; j++){
				text = text + splitDrinkName[j] + "<p>";				
			}
			JButton button = new JButton(text);	
			button.setBackground(Color.GREEN);
			button.setBounds(leftMargin + buttonWidth + gap, (topMargin + (i * buttonHeight)) + (gap), buttonWidth, buttonHeight);
			mainPanel.add(button);
		}
		for (int i = 0; i < column3Drinks.size(); i++){
			//Add the buttons - set their bounds
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitDrinkName = column3Drinks.get(i).split("\\s+");
			
			for (int j = 0; j < splitDrinkName.length; j++){
				text = text + splitDrinkName[j] + "<p>";				
			}
			JButton button = new JButton(text);	
			button.setBackground(Color.RED);
			button.setBounds(leftMargin + (2 * buttonWidth) + (2 * gap), (topMargin + (i * buttonHeight)) + (gap), buttonWidth, buttonHeight);
			mainPanel.add(button);
		}
		
		
		mainFrame.add(mainPanel);
		mainFrame.setSize(1000, 700);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
		
		System.out.println("Employee Screen");
	}
}
