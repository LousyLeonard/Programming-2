package core.ui.entrypanels;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
	
	private JLabel titleLabel;

	private ButtonGroup buttonGroup;
	
	public SmartExclusiveSelectionPanel(String title, Map<?,JPanel> selectionToDisplays) {
		this.title = title;
		
		init((Map<Object, JPanel>) selectionToDisplays);
	}
	
	private void init(Map<Object, JPanel> selectionToDisplays) {
		titleLabel = new javax.swing.JLabel();
		buttonGroup = new javax.swing.ButtonGroup();
		displayPanel = new JPanel(new CardLayout());

		titleLabel.setText(title);	
		
		ArrayList<Object> entries = new ArrayList<Object>();
		for (Map.Entry<Object, JPanel> entry : selectionToDisplays.entrySet()) {
			entries.add(entry.getKey());
			displayPanel.add(entry.getValue(), entry.getKey().getClass().getName());
		}
		
		selectionPanel = new GenericExclusiveSelectionPanel(title + " Selection", entries);
		
		selectionPanel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println("selected change card");
				CardLayout cl = (CardLayout)(displayPanel.getLayout());
			    cl.show(displayPanel, selectionPanel.getContent().getClass().getName());
			}	
		});
		
        BoxLayout mainlayout = new BoxLayout(this, BoxLayout.PAGE_AXIS);
        		
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(selectionPanel);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            	.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(displayPanel))
        );
        
        this.add(selectionPanel);
        this.add(displayPanel);
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Object getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

}
