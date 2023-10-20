import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

public class InterLabAssignment {
		
	public static void main(String[] args) {
		JFrame frame = new JFrame("Inter Lab Assignment - Pizza GUI");
		JTextArea textArea = new JTextArea("");
		frame.add(textArea);

		JMenu usersMenu = new JMenu("Users");
		JMenuItem loginMI = new JMenuItem("Login");
		usersMenu.add(loginMI);
		loginMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		
		loginMI.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
					JLabel userLbl = new JLabel("User:");
					JLabel passLbl = new JLabel("Password:");
					JTextField userField = new JTextField();
					JPasswordField passField = new JPasswordField();
					String message = "Please enter your user name and password.";
					int result = JOptionPane.showOptionDialog(frame, 
					          new Object[] { message, userLbl, userField, passLbl, passField },
					           "Login",  JOptionPane.OK_CANCEL_OPTION,
					           JOptionPane.QUESTION_MESSAGE, null, null, null);
					if (result == JOptionPane.YES_OPTION)
					{
						textArea.append("User ["+userField.getText()+"] has logged using a ["+
									passField.getPassword().length+"] letter password.\n");
					}
					else
					{
						textArea.append("User pressed login, but cancelled operation.\n");
					}
					
				}});
				
		JMenuItem logoutMI = new JMenuItem("Logout");
		usersMenu.add(logoutMI);
		logoutMI.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_T, Event.SHIFT_MASK));
		
		logoutMI.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int result = JOptionPane.showConfirmDialog(null, 
						"Are you sure you want to logout?");
				if (result == JOptionPane.YES_OPTION)
				{
					System.exit(0);
				}
				else
				{
					textArea.append("User pressed logout, but cancelled operation.\n");
				}
			}});
		
		
		JMenu ingredientsMenu = new JMenu("Ingredients");
		ingredientsMenu.add(new JCheckBoxMenuItem("Tomato sauce"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Cheese"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Pineapple"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Olives"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Mushrooms"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Onions"));
		ingredientsMenu.addSeparator( );
		ingredientsMenu.add(new JCheckBoxMenuItem("Pork Meatballs"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Pepperoni"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Ham"));
		ingredientsMenu.add(new JCheckBoxMenuItem("Bacon"));
		
		Set<String> meatIngredients = new HashSet<>();
		meatIngredients.add("Pork Meatballs");
		meatIngredients.add("Pepperoni");
		meatIngredients.add("Ham");
		meatIngredients.add("Bacon");
		
		for (int x =0; x<=ingredientsMenu.getItemCount()-1; x++)
		{
			JCheckBoxMenuItem nextMI = (JCheckBoxMenuItem) ingredientsMenu.getItem(x);
			if (nextMI != null)	// To discard the separator!
			{
				//System.out.println(nextMI.getText());
				
				nextMI.addItemListener(new ItemListener() {
			        public void itemStateChanged(ItemEvent e) {
			          //System.out.println("Checked? " + nextMI.isSelected());
			          textArea.append("Ingredient ["+nextMI.getText()+"] has been ["+
			        		  (nextMI.isSelected()?"checked":"unchecked")+"]!"+"\n");
			        }
			      });
			}
		}
		
		JMenu chilliMenu = new JMenu("Chilli Level");
		chilliMenu.add(new JRadioButtonMenuItem("Level 0: No chilli", null, true));
		chilliMenu.add(new JRadioButtonMenuItem("Level 1: Mild chilli"));
		chilliMenu.add(new JRadioButtonMenuItem("Level 2: Hot chilli"));
		chilliMenu.add(new JRadioButtonMenuItem("Level 3: Extra-hot chilli"));
		chilliMenu.add(new JRadioButtonMenuItem("Level 4: Habanero-hot"));
		
		ButtonGroup group = new ButtonGroup();
		
		for (int x =0; x<=chilliMenu.getItemCount()-1; x++)
		{
			JRadioButtonMenuItem nextMI = (JRadioButtonMenuItem) chilliMenu.getItem(x);
			group.add(nextMI);
				
			nextMI.addItemListener(new ItemListener() {
		        public void itemStateChanged(ItemEvent e) {
		        	if (nextMI.isSelected())
		          textArea.append("New chilli hotness level selected: ["+nextMI.getText()+"]"+"\n");
		        }
		      });

		}
		
		JMenu ordersMenu = new JMenu("Orders");
		JMenuItem prepareRegPizza = new JMenuItem("Prepare Regular Pizza");
		ordersMenu.add(prepareRegPizza);
		
		prepareRegPizza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean containsMeat = false;
				String ingredients = "";
				for (int x =0; x<ingredientsMenu.getItemCount()-1; x++)
				{
					JCheckBoxMenuItem nextMI = (JCheckBoxMenuItem) ingredientsMenu.getItem(x);
					if (nextMI != null && nextMI.isSelected())
					{
						if (ingredients.length()>0) 
							{
								ingredients +=",";
							}
						ingredients += nextMI.getText();
						if (meatIngredients.contains(nextMI.getText()))
						{
							containsMeat = true;
							//break;
						}
					}
				}
				
				if (!containsMeat)
				{
					JOptionPane.showMessageDialog(frame, 
						      "No meat selected.", "Your pizza does not contain any type of meat!", 	     	
						       JOptionPane.INFORMATION_MESSAGE);
				}

				String chilliLevel = "";
		        for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) {
		            	chilliLevel = button.getText();
		            }
		        }
				
				textArea.append("Preparing a regular pizza with the ingredients: ["+
						ingredients+"] and the level of chilli hotness: ["+chilliLevel+"]."+"\n");
			}});
		
		JMenuItem prepareVeggiePizza = new JMenuItem("Prepare Veggie Pizza");
		ordersMenu.add(prepareVeggiePizza);

		prepareVeggiePizza.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				boolean containsMeat = false;
				String ingredients = "";
				for (int x =0; x<ingredientsMenu.getItemCount()-1; x++)
				{
					JCheckBoxMenuItem nextMI = (JCheckBoxMenuItem) ingredientsMenu.getItem(x);
					if (nextMI != null && nextMI.isSelected())
					{
						if (ingredients.length()>0) 
						{
							ingredients +=",";
						}
						ingredients += nextMI.getText();
						
						if (meatIngredients.contains(nextMI.getText()))
						{
							containsMeat = true;
						}
					}
				}
	
				String chilliLevel = "";

				Enumeration<AbstractButton> buttons = group.getElements();
		        while(buttons.hasMoreElements()) {
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) {
		            	chilliLevel = button.getText();
		            }
		        }
				
				if (containsMeat)
				{
					JOptionPane.showMessageDialog(frame, 
						      "Meat selected.", "Your veggie pizza contains meat!", 	     	
						       JOptionPane.ERROR_MESSAGE);
					textArea.append("Your selection of ingredients involves meat, so the veggie pizza cannot be cooked."+"\n");
				}
				else
				{
					textArea.append("Preparing a veggie pizza with the ingredients: ["+
							ingredients+"] and the level of chilli hotness: ["+chilliLevel+"]."+"\n");
				}			

			}});
		

		
		// create a menu bar and use it in this JFrame
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(usersMenu);
		menuBar.add(ingredientsMenu);
		menuBar.add(chilliMenu);
		menuBar.add(ordersMenu);
		frame.setJMenuBar(menuBar);
		
		// Final JFrame methods to set close operation + set size and visibility!
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700,400);
		frame.setVisible(true);
	    }

				

}
