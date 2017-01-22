package core.ui.entrypanels;

import core.NoEventRegisteredException;
import core.IYesNoEvent;
import core.ui.DialogBuilder;

/**
 *
 * @author Lawrence
 */
public class YesNoPanel extends javax.swing.JPanel {
	
	private final static String DEFAULT_YES = "Yes"; 
	private final static String DEFAULT_NO = "No"; 
	
    private javax.swing.JButton yesButton;
    private javax.swing.JButton noButton;
    
    private IYesNoEvent yesEvent;
    private IYesNoEvent noEvent;

    private DialogBuilder builder;
    
    /**
     * Creates new form YesNoPanel
     */
    public YesNoPanel(DialogBuilder builder, String yesLabel, String noLabel) {
    	this.builder = builder;
    	
        initComponents(yesLabel, noLabel);
    }
    
    public YesNoPanel(DialogBuilder builder) {
    	this(builder, DEFAULT_YES, DEFAULT_NO);
    }

    @SuppressWarnings("unchecked")
    private void initComponents(String yesLabel, String noLabel) {

        yesButton = new javax.swing.JButton();
        noButton = new javax.swing.JButton();

        yesButton.setText(yesLabel);
        yesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doYesEvent();
            }
        });

        noButton.setText(noLabel);
        noButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doNoEvent();
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(161, Short.MAX_VALUE)
                .addComponent(yesButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noButton)
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(noButton)
                    .addComponent(yesButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }             
    
	public void registerYesEvent(IYesNoEvent yesEvent) {
		this.yesEvent = yesEvent;
	}
	
	public void doYesEvent() {
        if(yesEvent != null) {
        	yesEvent.doEvent(builder);
        } else {
        	System.out.println(new NoEventRegisteredException());
        }
	}
	
	public void registerNoEvent(IYesNoEvent noEvent) {
		this.noEvent = noEvent;
	}
	
	public void doNoEvent() {
    	if(noEvent != null) {
        	noEvent.doEvent(builder);
        } else {
        	System.out.println(new NoEventRegisteredException());
        }
	}
}

