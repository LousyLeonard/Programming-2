package core.util;

import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Map;

/**
* Label Utilities.
* 
* @author Lawrence
*/
public abstract class LabelFunctions {
	
	/**
	 * Underline the content of a given label.
	 * 
	 * @param label - The label to underline.
	 */
	public static void underLineLabel(final javax.swing.JLabel label) {
		Font font = label.getFont();
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		label.setFont(font.deriveFont(attributes));
	}
}
