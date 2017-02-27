package core;

import javax.swing.JPanel;

/**
* An interface for specifying the behaviours of an Entry Panel
* which are the panels that make up the input fields of a DialogBuilder.
* 
* @author Lawrence
*/
public interface IEntryPanelProvider {
	
	/**
	 * Returns the title of the panel.
	 * 
	 * @return the title of the panel.
	 */
	public String getTitle();
	
	/**
	 * Returns the held content of the panel as an object.
	 * For example this would be the text in a text input field.
	 * 
	 * @return The content of the input field.
	 */
	public Object getContent();
	
	/**
	 * The actual panel needed for the input of data.
	 * 
	 * @return The GUI panel to be displayed on the DialogBuilder. 
	 */
	public JPanel getPanel();
}
