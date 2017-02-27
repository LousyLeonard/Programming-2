package core;

import java.util.HashMap;

/**
* Label plugin allows for a new static label to show on the UIBuilder.
* This is useful for displaying reference information.
* 
* @author Lawrence
*/
public interface ILabelPlugin {

	/**
	 * The title of the Label.
	 * 
	 * @return The title of the label.
	 */
	public String getTitle();
	
	/**
	 * Get a Map of heading to data entries which show be displayed.
	 * 
	 * @return Map of heading to data entries.
	 */
	public HashMap<String, String> getInfo();
}
