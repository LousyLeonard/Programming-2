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
 * @author Lawrence
 *
 */
public class HelpFrame extends JFrame {

	private JPanel layoutPanel;
	private JPanel buttonPanel;

	private JButton nextButton, prevButton;
	
	private CyclicListNode<String> current;
	
	public HelpFrame() {
		super();
		
		this.nextButton = new JButton(StringConstants.NEXT);
		this.prevButton = new JButton(StringConstants.PREV);
		
		this.buttonPanel = new JPanel();
		
		this.layoutPanel = new JPanel(new CardLayout());
		
		init();
	}
	
	private void init() {
		
		this.buttonPanel.setLayout(new BoxLayout(this.buttonPanel, BoxLayout.LINE_AXIS));
		
		this.layoutPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.layoutPanel.add(StringConstants.HELP_INTRODUCTION, 
				getImageLabel(StringConstants.HELP_INTRODUCTION));
		this.current = new CyclicListNode<String>(StringConstants.HELP_INTRODUCTION);

		this.layoutPanel.add(StringConstants.HELP_ADD_AND_REMOVE, 
				getImageLabel(StringConstants.HELP_ADD_AND_REMOVE));
		CyclicListNode<String> addandremove = 
				new CyclicListNode<String>(StringConstants.HELP_ADD_AND_REMOVE);
		
		this.layoutPanel.add(StringConstants.HELP_ADD_AND_REMOVE_2, 
				getImageLabel(StringConstants.HELP_ADD_AND_REMOVE_2));
		CyclicListNode<String> addandremove2 = 
				new CyclicListNode<String>(StringConstants.HELP_ADD_AND_REMOVE_2);
		
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
