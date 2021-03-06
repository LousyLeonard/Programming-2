/**
 * 
 */
package tripTracker;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import core.util.CyclicListNode;

/**
 * A frame to contain the help information.
 * 
 * @author Lawrence
 *
 */
public class HelpFrame extends JFrame {

	private JPanel layoutPanel;
	private JPanel buttonPanel;

	private JButton nextButton, prevButton;
	
	private CyclicListNode<String> current;
	
	/**
	 * CONSTRUCTOR
	 */
	public HelpFrame() {
		super();
		
		this.nextButton = new JButton(TripTrackerConstants.NEXT);
		this.prevButton = new JButton(TripTrackerConstants.PREV);
		
		this.buttonPanel = new JPanel();
		
		this.layoutPanel = new JPanel(new CardLayout());
		
		init();
	}
	
	/**
	 * Setup the GUI Components.
	 */
	private void init() {
		
		this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.LINE_AXIS));
		
		this.layoutPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.layoutPanel.add(TripTrackerConstants.HELP_INTRODUCTION, 
				getImageLabel(TripTrackerConstants.HELP_INTRODUCTION));
		this.current = new CyclicListNode<String>(TripTrackerConstants.HELP_INTRODUCTION);

		this.layoutPanel.add(TripTrackerConstants.HELP_ADD_AND_REMOVE, 
				getImageLabel(TripTrackerConstants.HELP_ADD_AND_REMOVE));
		CyclicListNode<String> addandremove = 
				new CyclicListNode<String>(TripTrackerConstants.HELP_ADD_AND_REMOVE);
		
		this.layoutPanel.add(TripTrackerConstants.HELP_ADD_AND_REMOVE_2, 
				getImageLabel(TripTrackerConstants.HELP_ADD_AND_REMOVE_2));
		CyclicListNode<String> addandremove2 = 
				new CyclicListNode<String>(TripTrackerConstants.HELP_ADD_AND_REMOVE_2);
		
		current.setNext(addandremove);
		addandremove.setNext(addandremove2);
		addandremove2.setNext(current);
		
		nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
        	    CardLayout layout = (CardLayout)(layoutPanel.getLayout());
            	layout.show(layoutPanel, current.getNext().getElement());
            	current = current.getNext();
            }
        });	
		
		prevButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
        	    CardLayout layout = (CardLayout)(layoutPanel.getLayout());
            	layout.show(layoutPanel, current.getPrev().getElement());
            	current = current.getPrev();
            }
        });
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

		buttonPanel.add(prevButton);
		buttonPanel.add(nextButton);
		
		this.add(layoutPanel);
		this.add(buttonPanel);
		
		this.setAlwaysOnTop(true);
		
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * Create a label with the requested image on.
	 * 
	 * @param path - The path of the requested image.
	 * @return the label with the image loaded on.
	 */
	private JLabel getImageLabel(String path) {
		JLabel result = new JLabel();
		
		URL imgURL = TripTrackerMain.class.getResource(path);

		try {
			BufferedImage myPicture = ImageIO.read(new File(imgURL.getPath()));
			result = new JLabel(new ImageIcon(myPicture));
		} catch (IOException e) {
			result.setText("Help File not Found");
		}
		
		return result;
	}
}
