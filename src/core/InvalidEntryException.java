/**
 * 
 */
package core;

/**
 * @author Lawrence
 *
 */
public class InvalidEntryException extends Exception {
	 
	private String panelName;
	
	public InvalidEntryException(String panelName) {
		this.panelName = panelName;
	}
	
	public String getPanelName() {
		return panelName;
	}

}
