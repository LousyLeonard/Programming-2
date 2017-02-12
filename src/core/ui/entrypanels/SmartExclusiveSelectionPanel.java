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

public class SmartExclusiveSelectionPanel extends JPanel implements IEntryPanelProvider{

	private String title;
	
	private GenericExclusiveSelectionPanel selectionPanel;
	private JPanel displayPanel;
	
	private ButtonGroup buttonGroup;
	
	private List<IEntryPanelProvider> contents;
	private IEntryPanelProvider current;
	
	public SmartExclusiveSelectionPanel(String title, List<IEntryPanelProvider> selectionToDisplays) {
		this.title = title;
		this.contents = selectionToDisplays;
		
		init();
	}
	
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

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Object getContent() {
		return current.getContent();
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

}
