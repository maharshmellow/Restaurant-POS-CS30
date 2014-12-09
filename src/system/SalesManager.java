package system;
import system.*;
import items.Drinks;
import items.Food;
import items.Products;
import system.LoginManager;
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
import javax.swing.BorderFactory;
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

public class SalesManager extends LoginManager implements ActionListener{
	
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
	
	public static void showOrderScreen(int employeeIndex){
		
		
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
		setupSettings();
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
			button.setContentAreaFilled(false);
			button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));
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
			button.setContentAreaFilled(false);
			button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));
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
			button.setContentAreaFilled(false);
			button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GREEN));
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
		List<Double> column1Prices = new ArrayList();
		List<String> column2Food = new ArrayList();
		List<Double> column2Prices = new ArrayList();
		
		for (int i = 0; i < Food.names.size(); i++){
			if (i % 2 == 0){	
				//items with index of 0, 2, 4, 6... will go in the first column...
				column1Food.add(Food.names.get(i));
				column1Prices.add(Food.prices.get(i));
			}
			else{
				//...everything else will go in the second column
				column2Food.add(Food.names.get(i));
				column2Prices.add(Food.prices.get(i));
			}
		}
		
		System.out.println("COLUMN1: " + column1Food);
		System.out.println("COLUMN2: " + column2Food);
		
		
		for (int i = 0; i < column1Food.size(); i++){
			final String foodName = column1Food.get(i);
			final Double foodPrice = column1Prices.get(i);
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitFoodName = foodName.split("\\s+");
			
			for (int j = 0; j < splitFoodName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitFoodName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setContentAreaFilled(false);
			button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			button.setBounds(leftMargin + (4 * buttonWidth)+(4*gap), (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			
			button.setActionCommand(column1Food.get(i));
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Adds the name of item and the price of the item to arrays
					orderNames.add(foodName);			//Will be stored in logs 
					orderPrices.add(foodPrice);		//Used to get the order total
					
					//Adds the item to the JTable
					model.addRow(new Object[]{foodName, foodPrice});
					updatePriceIndicator();					
				}
			});
			mainPanel.add(button);
			
		}
		for (int i = 0; i < column2Food.size(); i++){
			final String foodName = column2Food.get(i);
			final double foodPrice = column2Prices.get(i);
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitFoodName = column2Food.get(i).split("\\s+");
			
			for (int j = 0; j < splitFoodName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitFoodName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setContentAreaFilled(false);
			button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
			button.setBounds(leftMargin + (5 * buttonWidth)+(5*gap) , (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			
			button.setActionCommand(column1Food.get(i));
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Adds the name of item and the price of the item to arrays
					orderNames.add(foodName);			//Will be stored in logs 
					orderPrices.add(foodPrice);		//Used to get the order total
					
					//Adds the item to the JTable
					model.addRow(new Object[]{foodName, foodPrice});
					updatePriceIndicator();					
				}
			});
			mainPanel.add(button);
		}
		
	}
	public static void setupProducts(int gap, int buttonWidth, int buttonHeight, int leftMargin, int topMargin){
		List<String> column1Products = new ArrayList();
		List<Double> column1Prices = new ArrayList();
		List<String> column2Products = new ArrayList();
		List<Double> column2Prices = new ArrayList();
		
		
		for (int i = 0; i < Products.names.size(); i++){
			if (i % 2 == 0){	
				//items with index of 0, 2, 4, 6... will go in the first column...
				column1Products.add(Products.names.get(i));
				column1Prices.add(Products.prices.get(i));
			}
			else{
				//...everything else will go in the second column
				column2Products.add(Products.names.get(i));
				column2Prices.add(Products.prices.get(i));
			}
		}
		
		System.out.println("COLUMN1: " + column1Products);
		System.out.println("COLUMN2: " + column2Products);
		
		for (int i = 0; i < column1Products.size(); i++){
			final String productName = column1Products.get(i);
			final double productPrice = column1Prices.get(i);
			String text = "<html>";
			//Splits the drink name by space into a list 
			String[] splitProductName = productName.split("\\s+");
			
			for (int j = 0; j < splitProductName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitProductName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setContentAreaFilled(false);
			button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
			button.setBounds(leftMargin + (7 * buttonWidth)+(6*gap), (topMargin + (i * buttonHeight)) + (i *gap) , buttonWidth, buttonHeight);
			
			button.setActionCommand(column1Products.get(i));
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Adds the name of item and the price of the item to arrays
					orderNames.add(productName);			//Will be stored in logs 
					orderPrices.add(productPrice);		//Used to get the order total
					
					//Adds the item to the JTable
					model.addRow(new Object[]{productName, productPrice});
					updatePriceIndicator();					
				}
			});
			mainPanel.add(button);
			
		}
		for (int i = 0; i < column2Products.size(); i++){
			final String productName = column2Products.get(i);
			final double productPrice = column2Prices.get(i);
			
			String text = "<html>";
			//Splits the drink name by space into a list  - used to display the items names on different lines 
			String[] splitProductName = column2Products.get(i).split("\\s+");
			
			for (int j = 0; j < splitProductName.length; j++){
				text = text + "<div style='text-align:center; font-size: 12px;'>" + splitProductName[j] + "</div>";				
			}
			JButton button = new JButton(text);	
			button.setContentAreaFilled(false);
			button.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
			button.setBounds(leftMargin + (8 * buttonWidth)+(7*gap) , (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			
			button.setActionCommand(column1Products.get(i));
			button.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//Adds the name of item and the price of the item to arrays
					orderNames.add(productName);			//Will be stored in logs 
					orderPrices.add(productPrice);		//Used to get the order total
					
					//Adds the item to the JTable
					model.addRow(new Object[]{productName, productPrice});
					updatePriceIndicator();					
				}
			});
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
		removeSelected.setContentAreaFilled(false);
		removeSelected.setBackground(Color.RED);
		removeSelected.setBorder(BorderFactory.createBevelBorder(1));
		removeSelected.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if (orderTable.getSelectedRow() == -1){
					// Nothing is selected - do nothing
				}else{
					//Removes the item from the orderNames and orderPrices lists
					orderNames.remove(orderTable.getSelectedRow());
					orderPrices.remove(orderTable.getSelectedRow());
					//Removes the row from the table
					model.removeRow(orderTable.getSelectedRow());
					
					updatePriceIndicator();
					
				}
				
			}
			
		});
		
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
		
		discardOrder.setContentAreaFilled(false);
		completeOrder.setContentAreaFilled(false);
		
		discardOrder.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				 
				//Have to do it backwards because the number of rows gets smaller each time - the other way produces errors - tries to delete nothing 
				for (int i = model.getRowCount() - 1; i >= 0; i--){
					System.out.println(model.getRowCount());					
					model.removeRow(i);
				}
				orderNames.clear();
				orderPrices.clear();
				
				updatePriceIndicator();
			}
			
		});
		
		
		
		
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
	
	public static void setupSettings(){
		BufferedImage loginImage = null;
		BufferedImage logoutImage = null;
		BufferedImage settingsImage = null;
		BufferedImage exitImage = null;
		try {
			loginImage = ImageIO.read(new File("src/files/login.png"));
			logoutImage = ImageIO.read(new File("src/files/logout.png"));
			settingsImage = ImageIO.read(new File("src/files/settings.png"));
			exitImage = ImageIO.read(new File("src/files/exit.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		JButton loginButton = new JButton(new ImageIcon(loginImage));
		JButton logoutButton = new JButton(new ImageIcon(logoutImage));
		JButton settingsButton = new JButton(new ImageIcon(settingsImage));
		JButton exitButton = new JButton(new ImageIcon(exitImage));
		
		loginButton.setBounds((int)screenWidth - 200, 15, 35, 35);
		logoutButton.setBounds((int)screenWidth - 150, 15, 35, 35);
		settingsButton.setBounds((int)screenWidth - 100, 15, 35, 35);
		exitButton.setBounds((int)screenWidth - 50, 15, 35, 35);
		
		loginButton.setContentAreaFilled(false);
		logoutButton.setContentAreaFilled(false);
		settingsButton.setContentAreaFilled(false);
		exitButton.setContentAreaFilled(false);
		
		
		loginButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Call the login screen function
				//TODO
				LoginManager loginManager = new LoginManager();
				loginManager.showLoginScreen();
				
			}
			
		});
		
		logoutButton.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent arg0){
				//Call the logout screen - show the login screen but log that person out
				//TODO
				LoginManager loginManager = new LoginManager();
				loginManager.showLogoutScreen();
			}
		});
		
		settingsButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				//Call the settings screen - ask for the manager / admin login - store pin in the program (Edit from program / settings screen)
				//after getting correct pin, open the settings screen with the sales information and the employee information
				
				//TODO showSettings();
			}
		});
		
		exitButton.addActionListener(new ActionListener(){
			
			@Override 
			public void actionPerformed(ActionEvent arg0){
				POS.exit();
			}
		});
		
		mainPanel.add(loginButton);
		mainPanel.add(logoutButton);
		mainPanel.add(settingsButton);
		mainPanel.add(exitButton);
		
	}
	
	public static void updatePriceIndicator(){
		Double  price = 0.0;
		for (int i = 0; i < orderPrices.size(); i++){
			price += orderPrices.get(i);
		}
		price = price * 1.05;		//GST
		priceIndicator.setText("Total: $" + String.format("%.2f", price));
	}
	

}




