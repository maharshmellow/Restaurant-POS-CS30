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
import javax.swing.SwingConstants;
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
	
	public static JFrame orderFrame;
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double screenWidth = screenSize.getWidth();
	//double screenHeight = screenSize.getHeight();
	
	public static void showOrderScreen(int employeeIndex){
		
		
		// Shows the main menu for employees ( some things restricted ) 
		mainFrame = new JFrame("Starbucks POS");
		mainPanel = new JPanel();
		mainPanel.setLayout(null);

		int gap = 5;
		int gapBetweenGroups = 100;
		int buttonWidth = 150;
		int buttonHeight = 150;
		int leftMargin = 30;
		int topMargin = 100;	
		
		try {
			setupLogo();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setupDrinks(gap, buttonWidth, buttonHeight, leftMargin, topMargin);
		setupFood(gap, gapBetweenGroups, buttonWidth, buttonHeight, leftMargin, topMargin);
		setupProducts(gap, gapBetweenGroups, buttonWidth, buttonHeight, leftMargin, topMargin);
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
	
	
	public static void setupFood(int gap, int gapBetweenGroups, int buttonWidth, int buttonHeight, int leftMargin, int topMargin){
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
			button.setBounds(leftMargin + (3 * buttonWidth)+(3*gap) + gapBetweenGroups, (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			
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
			button.setBounds(leftMargin + (4 * buttonWidth)+(4*gap) + (gapBetweenGroups) , (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			
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
	public static void setupProducts(int gap, int gapBetweenGroups, int buttonWidth, int buttonHeight, int leftMargin, int topMargin){
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
			button.setBounds(leftMargin + (5 * buttonWidth)+(5*gap) + (gapBetweenGroups * 2), (topMargin + (i * buttonHeight)) + (i *gap) , buttonWidth, buttonHeight);
			
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
			button.setBounds(leftMargin + (6 * buttonWidth)+(6*gap) + (gapBetweenGroups*2), (topMargin + (i * buttonHeight)) + (i * gap) , buttonWidth, buttonHeight);
			
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
		scrollPane.setBounds((int) screenWidth - 400, 100, 300, 700);
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
		priceIndicator.setBounds((int)screenWidth - 400, 850, 300, 50);
		
		
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
				
				clearTable();
				updatePriceIndicator();
			}
			
		});
		
		completeOrder.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e){
				completeOrder(getOrderTotal());
				
			}
		});
		
	
		
		
		
		
		removeSelected.setBounds((int) screenWidth - 400, 900, 300, 40);
		discardOrder.setBounds((int) screenWidth - 400, 950, 145, 145);
		completeOrder.setBounds((int) screenWidth - 250, 950, 145, 145);
		
		//Sales.showOrderScreen(); when the completeOrder button is clicked
		
		
		
		scrollPane.setViewportView(orderTable);
		mainPanel.add(priceIndicator);
		mainPanel.add(scrollPane);
		mainPanel.add(removeSelected);
		mainPanel.add(discardOrder);
		mainPanel.add(completeOrder);
	}
	
	public static void clearTable(){
		//TODO
		
		for (int i = model.getRowCount() - 1; i >= 0; i--){
			System.out.println(model.getRowCount());					
			model.removeRow(i);
		}
		orderNames.clear();
		orderPrices.clear();
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
				LoginManager loginManager = new LoginManager();
				loginManager.showLoginScreen();
				
			}
			
		});
		
		logoutButton.addActionListener(new ActionListener(){
			@Override 
			public void actionPerformed(ActionEvent arg0){
				//Call the logout screen - show the login screen but log that person out
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
		
		priceIndicator.setText("Total: $" + String.format("%.2f", getOrderTotal()));
	}
	
	public static double getOrderTotal(){
		Double  orderTotal = 0.0;
		for (int i = 0; i < orderPrices.size(); i++){
			orderTotal += orderPrices.get(i);
		}
		orderTotal = orderTotal * 1.05;		//GST
		
		return(orderTotal);
		
	}
	
	public static void completeOrder(double orderTotal){
		//Shows the credit/debit/giftcard/cash screen
		
		
		orderFrame = new JFrame("Starbucks POS");
		JPanel orderPanel = new JPanel();
		orderPanel.setLayout(null);
		
		BufferedImage creditDebitCardImage = null;		
		BufferedImage giftCardImage = null;
		BufferedImage cashImage = null;
		BufferedImage backImage = null;
		try {
			creditDebitCardImage = ImageIO.read(new File("src/files/creditDebitCard.png"));			
			giftCardImage = ImageIO.read(new File("src/files/giftCard.png"));
			cashImage = ImageIO.read(new File("src/files/cash.png"));
			backImage = ImageIO.read(new File("src/files/backArrow.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
			
		JButton creditDebitCardButton = new JButton(new ImageIcon(creditDebitCardImage));
		JButton giftCardButton = new JButton(new ImageIcon(giftCardImage));
		JButton cashButton = new JButton(new ImageIcon(cashImage));
		JButton backButton = new JButton(new ImageIcon(backImage));
		
		JLabel creditDebitLabel = new JLabel ("Credit/Debit");
		JLabel giftLabel = new JLabel ("Gift Card");
		JLabel cashLabel = new JLabel ("Cash");
		
		
		//Actionlisteners 
		
		creditDebitCardButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				processPayment(0);				
			}
			
		});
		
		giftCardButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				processPayment(1);
			}
		});
		
		cashButton.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent arg0){
				processPayment(2);
			}
		});
		
		backButton.addActionListener(new ActionListener(){
			
			@Override 
			public void actionPerformed(ActionEvent arg0){
				
				//mainPanel.setVisible(true);
				orderFrame.dispose();
			}
		});
		
		
		int buttonSize = 128;
		int leftMargin = (int) ((screenWidth / 3) - (buttonSize / 2));
		int centerMargin = (int) ((screenWidth / 2) - (buttonSize/2));
		int rightMargin = (int) ((screenWidth) - leftMargin - buttonSize);
		int gap = 100;
		
		backButton.setBounds(30, 30, 64, 64);
		backButton.setContentAreaFilled(false);
		
		creditDebitCardButton.setBounds(centerMargin - gap - buttonSize, 350, 128, 128);
		creditDebitLabel.setBounds(centerMargin - gap - buttonSize, 480, 128, 75);
		creditDebitLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		creditDebitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		giftCardButton.setBounds(centerMargin, 350, 128, 128);
		giftLabel.setBounds(centerMargin, 480, 128, 75);
		giftLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		giftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		cashButton.setBounds(centerMargin + (buttonSize) + gap, 350, 128, 128);
		cashLabel.setBounds(centerMargin + buttonSize + gap, 480, 128, 75);
		cashLabel.setFont(new Font("Arial", Font.PLAIN, 15));
		cashLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		orderPanel.add(backButton);
		orderPanel.add(creditDebitCardButton);
		orderPanel.add(creditDebitLabel);
		orderPanel.add(giftCardButton);
		orderPanel.add(giftLabel);
		orderPanel.add(cashButton);
		orderPanel.add(cashLabel);
		
		
		orderFrame.add(orderPanel);
		orderFrame.setUndecorated(true);
		orderFrame.setExtendedState(mainFrame.MAXIMIZED_BOTH);  
		orderFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		orderFrame.setVisible(true);
		
		
	}
	public static void processPayment(int type){
		//orderFrame.dispose();			//Closes the frame that shows the 3 buttons (credit/debit, giftcard, cash);
		
		JFrame processingFrame = new JFrame("Processing Transaction");
		JPanel processingPanel = new JPanel();
		JLabel processingLabel = new JLabel();
		
		processingLabel.setFont(new Font("Arial", Font.BOLD, 13));
		
		processingPanel.add(processingLabel);
		processingFrame.add(processingPanel);
		
		processingFrame.setBounds(500, 500, 400, 20);
		processingFrame.setUndecorated(true);
		processingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		processingFrame.setVisible(true);
		
		double total = 0;
		String order = "";
		long epoch = System.currentTimeMillis();
		String payment = "";
		
		
		
		
		if (type == 0){
			//Credit or Debit Card Payment
			processingLabel.setText("Processing Credit/Debit Transaction");
			payment = "Credit/Debit";
			//TODO Update Database
		}
		else if (type == 1){
			//Gift Card Payment
			processingLabel.setText("Processing Gift Card Transaction");
			payment = "Gift Card";
			//TODO Update Database
			
		}
		else if (type == 2){
			//Cash Payment 			
			processingLabel.setText("Processing Cash Transaction");
			payment = "Cash";
			//TODO Update Database
		}
		
		//Get items ordered 
		for (int i = 0; i < orderNames.size(); i++){
			order += "'" + orderNames.get(i) + "'";
		}
		
		System.out.println("Order Total: " + Double.parseDouble(String.format("%.2f", getOrderTotal())));
		System.out.println("Order: " + order);
		System.out.println("Time: " + epoch);
		System.out.println("Payment: " + payment);
		
		orderNames.clear();
		orderPrices.clear();
		
		processingFrame.dispose();
		orderFrame.dispose();
		
	}
	

}




