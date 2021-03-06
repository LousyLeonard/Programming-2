package core.ui.entrypanels;

import javax.swing.JPanel;

import core.IEntryPanelProvider;

/**
* Generic IEntryPanelProvider that allows the entry of text in via a textfield.
* 
* @author Lawrence
*/
public class GenericStringEntryPanel extends JPanel implements IEntryPanelProvider {
	
    private javax.swing.JLabel titleLabel;
    private javax.swing.JLabel validIcon;
    private javax.swing.JTextField entryField;
    
    private boolean valid;
    private String title;

    /**
     * CONSTRUCTOR
     * 
     * @param title - The title of the entry panel.
     */
    public GenericStringEntryPanel(String title) {
    	this.valid = false;
    	this.title = title;
    	
        initComponents();
    }

    /**
     * Setup the GUI components.
     */
    private void initComponents() {

        entryField = new javax.swing.JTextField();
        titleLabel = new javax.swing.JLabel();
        validIcon = new javax.swing.JLabel();

        titleLabel.setText(title);
        
        entryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	entryFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(entryField, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(validIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entryField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleLabel)
                    .addComponent(validIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    
    /**
     * Action to perform on action of the textfield.
     * 
     * @param evt - The event information.
     */
    private void entryFieldActionPerformed(java.awt.event.ActionEvent evt) {                                            
        valid = isStringValid(entryField.getText());
        updateIcon();
    }
    
    /**
     * Update the validity icon.
     */
    private void updateIcon() {
    	// update the icon whether valid or not
    	//TODO
    }
    
    /* (non-Javadoc)
     * @see core.IEntryPanelProvider#getContent()
     */
    public Object getContent() {
    	return entryField.getText();
    }
    
    /**
     * Check whether this entry is valid.
     * 
     * @return boolean true for valid.
     */
    public boolean getValid() {
		return valid;	
    }
    
    /**
     * Check to see if the given string is a valid.
     * 
     * @param input - String to check.
     * @return boolean - true for is valid.
     */
    private boolean isStringValid(String input) {
    	//TODO
    	return true;
    }

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see core.IEntryPanelProvider#getPanel()
	 */
	@Override
	public JPanel getPanel() {
		return this;
	}
         
}
