package core.util;

import java.net.URL;

import javax.swing.ImageIcon;

import core.ui.NavigationFrame;

/**
* Graphical Utilities.
* 
* @author Lawrence
*/
public abstract class GraphicUtils {

	/**
	 * Create and load an icon from the given path.
	 * 
	 * @param path - The path of an icon.
	 * @param description - The textual representation of the icon.
	 * @return The ImageIcon representative object of the icon.
	 */
	public static ImageIcon createImageIcon(String path, String description) {
		URL imgURL = NavigationFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
