package presentation;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import objects.*;
import persistence.*;

@SuppressWarnings("serial")
public class UserRecipeCollection extends JFrame {

	//Content pane object.
	private JPanel contentPane;
	
	//List section object
	private JList<String> list;
	
	//Button objects
	private final JButton backButton = new JButton("Back");
	private final JButton addRecipeButton = new JButton("Add Custom Recipe");
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Create a new frame.
					UserRecipeCollection frame = new UserRecipeCollection();
					//Make the frame visible.
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	//Adds the current user's saved recipes to the list section to display them.
	public void addUserRecipes() {
		//Create a new list model for the user's recipes.
		DefaultListModel<String> model = new DefaultListModel<String>();		
		//get a new instance of the user database.
		UsersDAO db = new UserDAOImpl();		
		//save a reference of the user's recipes.
		ArrayList<Recipes> recipes = new ArrayList<Recipes>(); //= db.getRecipes(/*get current user*/);
		recipes = db.getRecipes(db.getCurrentUser());
		//Add all the user's recipes to the list model.
		for(Recipes r: recipes) {
			model.addElement(r.getName());
		}
		
		//Set the model for the list section to be the one that was 
		list.setModel(model);
	}

	/**
	 * Create the frame.
	 */
	public UserRecipeCollection() {
		setTitle("RIMA - User Recipes");
		//Set the application to exit when closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 		
		//Set the bounds of the window.
		setBounds(100, 100, 375, 303);		

		setLocationRelativeTo(null);
		//Create a new content pane.
		contentPane = new JPanel(); 		
		//Set an invisible border for the content pane.
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		//Replace the frame's content pane with the one that was just set up.
		setContentPane(contentPane);		
		//Set the content pane's layout manager to null for full customization.
		contentPane.setLayout(null);
		
		//Create a new section for an item list.
		list= new JList<String>(); 		
		//Set the background colour of the list section.
		list.setBackground(new Color(255, 255, 255));
		//Set the bounds of the list section
		list.setBounds(10, 11, 343, 222);
		
		//Add the current user's saved recipes to the list section to display them.
		addUserRecipes();
		
		//Set up what to do when an item in the list is selected.
		list.getSelectionModel().addListSelectionListener(e-> {
			//Get the selected list item
			String name = (String) list.getSelectedValue();
			//Create a ViewRecipe window for the selected list item/recipe.
			ViewRecipeCollection newWindow = new ViewRecipeCollection(name);
			//Set up the ViewRecipe window and make it visible.
			newWindow.NewScreen(name);
		});
		
		//Add the list section to the content pane.
		contentPane.add(list);
		
		//Set up the font and bounds of the back button.
		backButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		backButton.setBounds(272, 244, 65, 18);
		
		//add the back button to the content pane.
		contentPane.add(backButton);
		
		//Set up what to do when the back button is pressed.
		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a HomePage window
				HomePage homePage = new HomePage();
				
				//Make the HomePage window visible and the UserRecipeCollection window invisible.
				homePage.setVisible(true);
				contentPane.setVisible(false);
				
				//Close the UserRecipeCollection Window.
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();				
			}
		});
		
		//Set up the font and bounds of the add button.
		addRecipeButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		addRecipeButton.setBounds(20, 244, 120, 18);
				
		//add the add button to the content pane.
		contentPane.add(addRecipeButton);
		
		btnNewButton = new JButton("Remove Recipe");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnNewButton.setBounds(150, 244, 112, 18);
		contentPane.add(btnNewButton);
				
		//Set up what to do when the add button is pressed.
		addRecipeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a SaveRecipesView window
				SaveRecipesView addRecipePage = new SaveRecipesView();
						
				//Make the SaveRecipesView window visible.
				addRecipePage.setVisible(true);	
				contentPane.setVisible(false);
				
				Window win = SwingUtilities.getWindowAncestor(contentPane);
				win.dispose();
			}
		});
	}

}