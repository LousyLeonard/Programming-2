/**
 * 
 */
package tripTracker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * The help menu selector.
 * 
 * @author Lawrence
 */
public class HelpMenu extends JMenu {

	private JMenuItem helpMenuItem;
	
	/**
	 * CONSTRUCTOR
	 */
	public HelpMenu() {
		super(TripTrackerConstants.HELP);
		
		this.helpMenuItem = new JMenuItem(TripTrackerConstants.HELP);
		
		this.add(this.helpMenuItem);
		
		init();
	}
	
	/**
	 * Setup GUI components.
	 */
	private void init() {
		
		helpMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HelpFrame helpFrame = new HelpFrame();
			}
		});
	}
	
}
