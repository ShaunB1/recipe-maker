package presentation;

import java.awt.FlowLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import objects.Recipes;
import repository.RecipeRepository;

import javax.swing.BoxLayout;
import java.awt.TextArea;
import java.awt.Font;

@SuppressWarnings("serial")
public class ViewRecipe extends JDialog {

	private final JPanel contentPanel = new JPanel();


	public void NewScreen(String name) {
		try {
			ViewRecipe dialog = new ViewRecipe(name);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ViewRecipe(String name) {
		setBounds(100, 100, 862, 574);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 836, 503);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
		{
			TextArea textArea = new TextArea();
			textArea.setFont(new Font("Dialog", Font.PLAIN, 23));
			textArea.setEditable(false);
			contentPanel.add(textArea);
			for (Recipes r: RecipeRepository.getRecipes()) {
				if(r.getName().equals(name)) {
					textArea.setText(r.toString());
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 503, 666, 33);
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
	}

}
