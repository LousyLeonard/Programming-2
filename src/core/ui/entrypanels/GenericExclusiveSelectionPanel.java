package core.ui.entrypanels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import core.IEntryPanelProvider;
import core.util.EnumUtils;

public class GenericExclusiveSelectionPanel extends JPanel implements IEntryPanelProvider {

    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel selectionPanel;
	
	private javax.swing.ButtonGroup buttonGroup;
	
	private Map<JRadioButton, Object> buttonRepresentationMap;
	
    private String title;
	
	public GenericExclusiveSelectionPanel(String title, ArrayList<Object> arrayList) {
		this.title = title;
		
		init(arrayList);
	}
	
	public <E extends Enum<E>> GenericExclusiveSelectionPanel(String title, Class<E> enumClass) {
		this.title = title;
		
		ArrayList<Object> selections = EnumUtils.enumValues(enumClass);
		
		init(selections);
	}
	
	private void init(ArrayList<Object> selections) {
		titleLabel = new javax.swing.JLabel();
		selectionPanel = new javax.swing.JPanel();
		buttonGroup = new javax.swing.ButtonGroup();

		titleLabel.setText(title);
				
		buttonRepresentationMap = new HashMap<JRadioButton, Object>();
		
		for(Object selection : selections) {
			javax.swing.JRadioButton tempButton = new javax.swing.JRadioButton(selection.toString());
			buttonGroup.add(tempButton);
			buttonRepresentationMap.put(tempButton, selection);
			selectionPanel.add(tempButton);
		}
		
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.PAGE_AXIS));
		
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(selectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(selectionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 26 * buttonGroup.getButtonCount(), javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Object getContent() {
		for(JRadioButton key : buttonRepresentationMap.keySet()) {
			if(key.isSelected()) {
				return buttonRepresentationMap.get(key);
			}
		}
		return null;
	}

	@Override
	public JPanel getPanel() {
		return this;
	}

}
 