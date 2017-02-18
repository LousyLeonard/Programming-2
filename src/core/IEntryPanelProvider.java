package core;

import javax.swing.JPanel;

/**
*
* @author Lawrence
*/
public interface IEntryPanelProvider {
	
	public String getTitle();
	
	public Object getContent();
	
	public JPanel getPanel();
}
