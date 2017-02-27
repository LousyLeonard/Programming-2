package core.ui;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JDialog;

import core.IYesNoEvent;
import core.ui.entrypanels.YesNoPanel;
import core.IEntryPanelProvider;
import core.IListener;
import core.util.LabelFunctions;

/**
* Building block class for creating a dialog window. Display and input panels
* can be added and removed. Yes and No events need to be defined.
* 
* @author Lawrence
*/
public class DialogBuilder extends JDialog implements IListener {
	
	/**
	 * The title of the DialogBuilder.
	 */
	private String title;

	private javax.swing.JPanel rootPanel, titlePanel, formPanel;
	private javax.swing.JLabel titleLabel;
	
	/**
	 * ArrayList of the added panels.
	 */
	private ArrayList<IEntryPanelProvider> panelList;
	
	/**
	 * The accept and reject panel.
	 */
	private YesNoPanel yesNoPanel;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param title - The title of the DialogBuilder.
	 */
	public DialogBuilder(String title) {
		this.title = title;
		
		panelList = new ArrayList<IEntryPanelProvider>();
		rootPanel = new javax.swing.JPanel();	
		titlePanel = new javax.swing.JPanel();
		formPanel = new javax.swing.JPanel();
		titleLabel = new javax.swing.JLabel();
		
		yesNoPanel = new YesNoPanel(this, "Add", "Cancel");
				
		init();
	}
	
	/**
	 * Setup the GUI components.
	 */
	private void init() {
		rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.PAGE_AXIS));
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.PAGE_AXIS));
		formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.PAGE_AXIS));
		
		rootPanel.add(titlePanel);
		rootPanel.add(formPanel);
		rootPanel.add(yesNoPanel);
		
		titleLabel.setText(title);
		LabelFunctions.underLineLabel(titleLabel);
		titlePanel.add(titleLabel);
		
		this.add(rootPanel);
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		pack();
	}
	
	/**
	 * Add an IEntryPanelProvider to the list of IEntryPanelProviders.
	 * 
	 * @param panel - The IEntryPanelProvider instance to add.
	 */
	public void addPanel(IEntryPanelProvider panel) {
		panelList.add(panel);
		formPanel.add(panel.getPanel());
		pack();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.Dialog#getTitle()
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Add an event to the Approval selection.
	 * 
	 * @param yesEvent - The event to add.
	 */
	public void registerYesEvent(IYesNoEvent yesEvent) {
		yesNoPanel.registerYesEvent(yesEvent);
	}
	
	/**
	 * Add an event to the Rejection selection.
	 * 
	 * @param noEvent - The event to add.
	 */
	public void registerNoEvent(IYesNoEvent noEvent) {
		yesNoPanel.registerNoEvent(noEvent);
	}
	
    /**
     * Get the data entered in all of the IEntryPanelProviders.
     * 
     * @return A map of the titles to data for the IEntryPanelProviders.
     */
    public Map<String, Object> getEntrys() {
    	HashMap<String, Object> fields = new HashMap<String, Object>();
    	
    	for(IEntryPanelProvider panel : panelList) {
    		fields.put(panel.getTitle(), panel.getContent());
    	}
    	
		return fields;
    }

	/**
	 * Remove an IEntryPanelProvider from the list.
	 * 
	 * @param panel - The IEntryPanelProvider to remove.
	 */
	public void removePanel(IEntryPanelProvider panel) {

		for (IEntryPanelProvider element : panelList) {
			if (element.getTitle().equals(panel.getTitle())) {
				panelList.remove(element);
				break;
			}
		}
		
		for (Component element : formPanel.getComponents()) {
			if (((IEntryPanelProvider)element).getTitle().equals(panel.getTitle())) {
				formPanel.remove(element);
				break;
			}
		}
		pack();		
	}

	/* (non-Javadoc)
	 * @see core.IListener#update(java.lang.Object)
	 */
	@Override
	public void update(Object element) {
		// Update a given panel.
		IEntryPanelProvider panel = (IEntryPanelProvider)element;
		removePanel(panel);
		addPanel(panel);
	}
}
 