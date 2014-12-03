package people;
import items.Drinks;
import items.Food;
import items.Products;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import system.LoginManager;

public class Employee extends LoginManager implements ActionListener{
	public static List<String> names = new ArrayList();
	public static List<Integer> pins = new ArrayList();
	public static List<Double> salary = new ArrayList();
	public static List<Double> hours = new ArrayList();
	
	public static List<String> orderNames = new ArrayList();
	public static List<Double> orderPrices = new ArrayList();
	
	static JFrame mainFrame;
	static JPanel mainPanel;
	
	public static DefaultTableModel model;
	public static JTable orderTable;
	
	public static JLabel priceIndicator;
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double screenWidth = screenSize.getWidth();
	//double screenHeight = screenSize.getHeight();
	
	public static void showScreen(int employeeIndex, double shiftStartTimeMs){
		// Shows the main menu for employees ( some things restricted ) 
		mainFrame = new JFrame("Starbucks POS");
		mainPanel = new JPanel();
		mainPanel.setLayout(null);

		int gap = 5;
		int buttonWidth = 100;
		int buttonHeight = 100;
		int leftMargin = 30;
		int topMargin = 100;	
		
		try {
			setupLogo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setupDrinks(gap, buttonWidth, buttonHeight, leftMargin, topMargin);
		setupFood(gap, buttonWidth, buttonHeight, leftMargin, topMargin);
		setupProducts(gap, buttonWidth, buttonHeight, leftMargin, topMargin);
		setupGrid();
		//setupSettings();
		mainFrame.add(mainPanel);
		//mainFrame.setSize(1000, 700);
		mainFrame.setExtendedState(mainFrame.MAXIMIZED_BOTH);  
		mainFrame.setUndecorated(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		
		
	}
	public static void setupLogo() throws IOException{
		BufferedImage logo = ImageIO.read(new File("src/files/logo.png"));
		JLabel logoLabel = new JLabel (new ImageIcon(logo));
		//TODO Put image in the center of the screen (horizontally) --> (screensize / 2) - (picwidth /2)
		logoLabel.setBounds((int) ((screenWidth/2) - 40), 2, 80, 80);
		mainPanel.add(logoLabel);
	}
	
	public static void setupDrinks(int gap, int buttonWidth, int buttonHeight, int leftMargin, int topMargin){
			
		List<String> column1Drinks = new ArrayList();
		List<Double> column1Prices = new ArrayList();
		
		List<String> column2Drinks = new ArrayList();
		List<Double> column2Prices = new ArrayList();
		
		List<String> column3Drinks = new ArrayList();
		List<Double> column3Prices = new ArrayList();
		
		for (int i = 0; i < Drinks.names.size(); i++){
			//Checks the sizes of drinks and assigns them to a column list
			String[] splitDrinkName = Drinks.names.get(i).split("\\s+");				
			
			if (splitDrinkName[0].toLowerCase().contains("grande")){
				//Grande sized drink - 2nd column
				column2Drinks.add(Drinks.names.get(i));
				column2Prices.add(Drinks.prices.get(i));
			}
			else if (splitDrinkName[0].toLowerCase().contains("venti")){
				//Venti Sized drink - 3rd column
				column3Drinks.add(Drinks.names.get(i));
				column3Prices.add(Drinks.prices.get(i));
			}
			else{
				//Tall sized drink / drink size not available - 1st column
				column1Drinks.add(Drinks.names.get(i));
				column1Prices.add(Drinks.prices.get(i));
			}
		}
		
		for (int i = 0; i < column1Drinks.size(); i++){
			final String drinkName = column1Drinks.get(i);		// Gets the drink name
			final Double drinkPrice = column1Prices.get(i);		// Gets the drink price
			//Add the buttons - set their bounds
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitDrinkName = drinkName.split("\\s+");
			
			for (int j = 0; j < splitDrinkName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitDrinkName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setBackground(Color.GREEN);
			button.setName(drinkName);
			button.setBounds(leftMargin, (topMargin + (i * buttonHeight)) + (i * gap), buttonWidth, buttonHeight);
			
			button.setActionCommand(column1Drinks.get(i));
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Adds the name of item and the price of the item to arrays
					orderNames.add(drinkName);			//Will be stored in logs 
					orderPrices.add(drinkPrice);		//Used to get the order total
					
					//Adds the item to the JTable
					model.addRow(new Object[]{drinkName, drinkPrice});
					updatePriceIndicator();					
				}
			});
			mainPanel.add(button);
		}
		
		for (int i = 0; i < column2Drinks.size(); i++){
			final String drinkName = column2Drinks.get(i);		// Gets the drink name
			final Double drinkPrice = column2Prices.get(i);		// Gets the drink pric
			//Add the buttons - set their bounds
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitDrinkName = column2Drinks.get(i).split("\\s+");
			
			for (int j = 0; j < splitDrinkName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitDrinkName[j] + "</div>";				
			}
			JButton button = new JButton(text);				
			button.setBackground(Color.GREEN);
			button.setName(column2Drinks.get(i));
			button.setBounds(leftMargin + buttonWidth + gap, (topMargin + (i * buttonHeight)) + (i * gap), buttonWidth, buttonHeight);
			
			button.setActionCommand(column2Drinks.get(i));
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Adds the name of item and the price of the item to arrays
					orderNames.add(drinkName);			//Will be stored in logs 
					orderPrices.add(drinkPrice);		//Used to get the order total
					
					//Adds the item to the JTable
					model.addRow(new Object[]{drinkName, drinkPrice});
					updatePriceIndicator();					
				}
			});
			mainPanel.add(button);
		}
		
		for (int i = 0; i < column3Drinks.size(); i++){
			final String drinkName = column3Drinks.get(i);		// Gets the drink name
			final Double drinkPrice = column3Prices.get(i);		// Gets the drink pric
			
			//Add the buttons - set their bounds
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitDrinkName = column3Drinks.get(i).split("\\s+");
			
			for (int j = 0; j < splitDrinkName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitDrinkName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setBackground(Color.GREEN);
			button.setName(column3Drinks.get(i));
			button.setBounds(leftMargin + (2 * buttonWidth) + (2 * gap), (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			
			button.setActionCommand(column3Drinks.get(i));
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Adds the name of item and the price of the item to arrays
					orderNames.add(drinkName);			//Will be stored in logs 
					orderPrices.add(drinkPrice);		//Used to get the order total
					
					//Adds the item to the JTable
					model.addRow(new Object[]{drinkName, drinkPrice});
					updatePriceIndicator();					
				}
			});
			mainPanel.add(button);
		}
		
		
		
		
		
		System.out.println("Employee Screen");
	}
	
	
	public static void setupFood(int gap, int buttonWidth, int buttonHeight, int leftMargin, int topMargin){
		List<String> column1Food = new ArrayList();
		List<String> column2Food = new ArrayList();
		
		for (int i = 0; i < Food.names.size(); i++){
			if (i % 2 == 0){	
				//items with index of 0, 2, 4, 6... will go in the first column...
				column1Food.add(Food.names.get(i));
			}
			else{
				//...everything else will go in the second column
				column2Food.add(Food.names.get(i));
			}
		}
		
		System.out.println("COLUMN1: " + column1Food);
		System.out.println("COLUMN2: " + column2Food);
		for (int i = 0; i < column1Food.size(); i++){
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitFoodName = column1Food.get(i).split("\\s+");
			
			for (int j = 0; j < splitFoodName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitFoodName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setBackground(Color.BLACK);
			button.setName(column1Food.get(i));
			button.setBounds(leftMargin + (4 * buttonWidth)+(4*gap), (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			mainPanel.add(button);
			
		}
		for (int i = 0; i < column2Food.size(); i++){
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitFoodName = column2Food.get(i).split("\\s+");
			
			for (int j = 0; j < splitFoodName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitFoodName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setBackground(Color.BLACK);
			button.setName(column2Food.get(i));
			button.setBounds(leftMargin + (5 * buttonWidth)+(5*gap) , (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			mainPanel.add(button);
		}
		
	}
	public static void setupProducts(int gap, int buttonWidth, int buttonHeight, int leftMargin, int topMargin){
		List<String> column1Products = new ArrayList();
		List<String> column2Products = new ArrayList();
		
		for (int i = 0; i < Products.names.size(); i++){
			if (i % 2 == 0){	
				//items with index of 0, 2, 4, 6... will go in the first column...
				column1Products.add(Products.names.get(i));
			}
			else{
				//...everything else will go in the second column
				column2Products.add(Products.names.get(i));
			}
		}
		
		System.out.println("COLUMN1: " + column1Products);
		System.out.println("COLUMN2: " + column2Products);
		
		for (int i = 0; i < column1Products.size(); i++){
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitProductName = column1Products.get(i).split("\\s+");
			
			for (int j = 0; j < splitProductName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitProductName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setBackground(Color.BLUE);
			button.setName(column1Products.get(i));
			button.setBounds(leftMargin + (7 * buttonWidth)+(6*gap), (topMargin + (i * buttonHeight)) + (i *gap) , buttonWidth, buttonHeight);
			mainPanel.add(button);
			
		}
		for (int i = 0; i < column2Products.size(); i++){
			String text = "<html>";
			//Splits the drink name by space into a list  - used to display the items names on different lines 
			String[] splitProductName = column2Products.get(i).split("\\s+");
			
			for (int j = 0; j < splitProductName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitProductName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setBackground(Color.BLUE);
			button.setName(column2Products.get(i));
			button.setBounds(leftMargin + (8 * buttonWidth)+(7*gap) , (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			mainPanel.add(button);
		}
	}
	public static void setupGrid(){
		//Grid setup
		Object[][] d = {};		//The data
		Object[] s = {"Item", "Price ($)"};	//The column names
		model = new DefaultTableModel(d, s){	
			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		};
		
		orderTable = new JTable(model);		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1000, 100, 300, 350);
		scrollPane.getViewport().setBackground(Color.WHITE);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(40, 0));		//Increases the scrollbar width
		
		orderTable.setBounds(10, 10, 300, 500);
		orderTable.setFont(new Font("Cambria", Font.PLAIN, 18));
		orderTable.setRowHeight(27);
		orderTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		//Total Price Indicator 
		priceIndicator = new JLabel("Total: $0.00");
		priceIndicator.setForeground(Color.RED);
		priceIndicator.setFont(new Font("Sans Serif", Font.BOLD, 30));
		priceIndicator.setBounds(1000, 450, 300, 40);
		
		
		//Buttons Setup
		JButton removeSelected = new JButton("Remove Selected Item");
		
		BufferedImage discardIcon = null;
		BufferedImage completeIcon = null;
		
		try {
			discardIcon = ImageIO.read(new File("src/files/cancel_order.png"));
			completeIcon = ImageIO.read(new File("src/files/complete_order.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		JButton discardOrder = new JButton(new ImageIcon(discardIcon));
		JButton completeOrder = new JButton(new ImageIcon(completeIcon));
		
		removeSelected.setBounds(1000, 500, 300, 40);
		discardOrder.setBounds(1000, 545, 145, 145);
		completeOrder.setBounds(1155, 545, 145, 145);
		
		//Sales.showOrderScreen(); when the completeOrder button is clicked
		
		
		
		scrollPane.setViewportView(orderTable);
		mainPanel.add(priceIndicator);
		mainPanel.add(scrollPane);
		mainPanel.add(removeSelected);
		mainPanel.add(discardOrder);
		mainPanel.add(completeOrder);
	}
	
	static void updatePriceIndicator(){
		Double  price = 0.0;
		for (int i = 0; i < orderPrices.size(); i++){
			price += orderPrices.get(i);
		}
		priceIndicator.setText("Total: $" + String.format("%.2f", price));
	}
	
		


}




