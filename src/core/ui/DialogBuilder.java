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
*
* @author Lawrence
*/
public class DialogBuilder extends JDialog implements IListener {
	
	private String title;

	private javax.swing.JPanel rootPanel, titlePanel, formPanel;
	private javax.swing.JLabel titleLabel;
	private ArrayList<IEntryPanelProvider> panelList;
	
	private YesNoPanel yesNoPanel;
	
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
		
		setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
		pack();
	}
	
	public void addPanel(IEntryPanelProvider panel) {
		panelList.add(panel);
		formPanel.add(panel.getPanel());
		pack();
	}
	
	public String getTitle() {
		return title;
	}
	
	public void registerYesEvent(IYesNoEvent yesEvent) {
		yesNoPanel.registerYesEvent(yesEvent);
	}
	
	public void registerNoEvent(IYesNoEvent noEvent) {
		yesNoPanel.registerNoEvent(noEvent);
	}
	
    public Map<String, Object> getEntrys() {
    	HashMap<String, Object> fields = new HashMap<String, Object>();
    	
    	for(IEntryPanelProvider panel : panelList) {
    		fields.put(panel.getTitle(), panel.getContent());
    	}
    	
		return fields;
    }

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

	@Override
	public void update(Object element) {
		IEntryPanelProvider panel = (IEntryPanelProvider)element;
		removePanel(panel);
		addPanel(panel);
	}
}
 