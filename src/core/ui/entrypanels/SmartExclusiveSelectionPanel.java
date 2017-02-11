package core.ui.entrypanels;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.IEntryPanelProvider;

public class SmartExclusiveSelectionPanel extends JPanel implements IEntryPanelProvider{

	private String title;
	
	private GenericExclusiveSelectionPanel selectionPanel;
	private JPanel displayPanel;
	
	private ButtonGroup buttonGroup;
	
	private Map<String, IEntryPanelProvider> contents;
	private String current;
	
	public SmartExclusiveSelectionPanel(String title, Map<?,IEntryPanelProvider> selectionToDisplays) {
		this.title = title;
		this.contents = new HashMap<String, IEntryPanelProvider>();
		
		init((Map<Object, IEntryPanelProvider>) selectionToDisplays);
	}
	
	private void init(Map<Object, IEntryPanelProvider> selectionToDisplays) {
		buttonGroup = new javax.swing.ButtonGroup();
		displayPanel = new JPanel(new CardLayout());
		
		ArrayList<Object> entries = new ArrayList<Object>();
		for (Map.Entry<Object, IEntryPanelProvider> entry : selectionToDisplays.entrySet()) {
			entries.add(entry.getKey());
			contents.put(entry.getKey().getClass().getName(), entry.getValue());
			displayPanel.add(entry.getValue().getPanel(), entry.getKey().getClass().getName());
		}
		
		selectionPanel = new GenericExclusiveSelectionPanel(title, entries);
		
		selectionPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				CardLayout cl = (CardLayout)(displayPanel.getLayout());
				current = selectionPanel.getContent().getClass().getName();
			    cl.show(displayPanel, current);
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
		return contents.get(current).getContent();
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

}
