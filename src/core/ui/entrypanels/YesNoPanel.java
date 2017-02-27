package core.ui.entrypanels;

import core.NoEventRegisteredException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import core.IYesNoEvent;
import core.ui.DialogBuilder;

/**
 * A simple panel that has a Yes & No or configurable positive & negative buttons with
 * setable events.
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

    // Handle to the containing DialogBuilder
    private DialogBuilder builder;
    
    /**
     * CONSTRUCTOR
     * 
     * @param builder - The containing DialogBuilder.
     * @param yesLabel - The positive button label.
     * @param noLabel - The negative button label.
     */
    public YesNoPanel(DialogBuilder builder, String yesLabel, String noLabel) {
    	this.builder = builder;
    	
        initComponents(yesLabel, noLabel);
    }
    
    /**
     * CONSTRUCTOR
     * 
     * @param builder - The containing DialogBuilder.
     */
    public YesNoPanel(DialogBuilder builder) {
    	this(builder, DEFAULT_YES, DEFAULT_NO);
    }

    /**
     * Setup the GUI components.
     * 
     * @param yesLabel - The positive button label.
     * @param noLabel - The negative button label.
     */
    private void initComponents(String yesLabel, String noLabel) {

        yesButton = new javax.swing.JButton();
        noButton = new javax.swing.JButton();

        yesButton.setText(yesLabel);
        yesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
                	doYesEvent();
                } catch (NoEventRegisteredException e) {
       			 	JOptionPane.showMessageDialog(null, "Something has gone wrong, no event registered.");
                }
            }
        });

        noButton.setText(noLabel);
        noButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                try {
					doNoEvent();
				} catch (NoEventRegisteredException e) {
       			 	JOptionPane.showMessageDialog(null, "Something has gone wrong, no event registered.");
				}
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
    
	/**
	 * Register an event to do on occasion of the positive button being selected.
	 * 
	 * @param yesEvent - The event to register.
	 */
	public void registerYesEvent(IYesNoEvent yesEvent) {
		this.yesEvent = yesEvent;
	}
	
	/**
	 * Execute the Yes Event.
	 * 
	 * @throws NoEventRegisteredException if no event is registered.
	 */
	public void doYesEvent() throws NoEventRegisteredException {
        if(yesEvent != null) {
        	yesEvent.doEvent(builder);
        } else {
        	throw new NoEventRegisteredException();
        }
	}
	
	/**
	 * Register an event to do on occasion of the negative button being selected.
	 * 
	 * @param yesEvent - The event to register.
	 */
	public void registerNoEvent(IYesNoEvent noEvent) {
		this.noEvent = noEvent;
	}
	
	/**
	 * Execute the No Event.
	 * 
	 * @throws NoEventRegisteredException if no event is registered.
	 */
	public void doNoEvent() throws NoEventRegisteredException {
    	if(noEvent != null) {
        	noEvent.doEvent(builder);
        } else {
        	throw new NoEventRegisteredException();
        }
	}
}

