package core.ui.entrypanels;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.IEntryPanelProvider;
import core.util.ArrayCastingUtils;

/**
 * Generic selector presented as a series of checkboxes only one of which may be selected.
 * Upon selection a series of input fields will be revealed for additional entry.
 *
 * @author Lawrence
 */
public class SmartExclusiveSelectionPanel extends JPanel implements IEntryPanelProvider {

	private String title;
	
	private GenericExclusiveSelectionPanel selectionPanel;
	private JPanel displayPanel;
	
	private ButtonGroup buttonGroup;
	
	// Providers to display between.
	private List<IEntryPanelProvider> contents;
	
	private IEntryPanelProvider current;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param title - The title to display.
	 * @param selectionToDisplays - The list of inputs to choose between.
	 */
	public SmartExclusiveSelectionPanel(String title, List<IEntryPanelProvider> selectionToDisplays) {
		this.title = title;
		this.contents = selectionToDisplays;
		
		init();
	}
	
	/**
	 * Setup the GUI components.
	 */
	private void init() {
		buttonGroup = new javax.swing.ButtonGroup();
		displayPanel = new JPanel(new CardLayout());
		
		ArrayList<Object> entries = ArrayCastingUtils.convertArray(Object.class, contents);

		Boolean first = true;
		for (IEntryPanelProvider entry : contents) {
			displayPanel.add(entry.getPanel(), entry.getTitle());
			if (first) {
				current = entry;
				first = false;
			}
		}
		
		selectionPanel = new GenericExclusiveSelectionPanel(title, entries);
		
		selectionPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				CardLayout cl = (CardLayout)(displayPanel.getLayout());
				current = (IEntryPanelProvider)selectionPanel.getContent();
			    cl.show(displayPanel, current.getTitle());
			}	
		});
		
        BoxLayout mainlayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        this.setLayout(mainlayout);
        
        this.add(selectionPanel);
        this.add(displayPanel);
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getContent()
	 */
	@Override
	public Object getContent() {
		return current.getContent();
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getPanel()
	 */
	@Override
	public JPanel getPanel() {
		return this;
	}

}
