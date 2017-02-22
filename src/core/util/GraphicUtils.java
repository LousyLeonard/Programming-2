package core.util;

import javax.swing.ImageIcon;

import core.ui.NavigationFrame;

/**
*
* @author Lawrence
*/
public abstract class GraphicUtils {

	public static ImageIcon createImageIcon(String path, String description) {
		java.net.URL imgURL = NavigationFrame.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
