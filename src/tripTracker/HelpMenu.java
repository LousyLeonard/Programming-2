/**
 * 
 */
package tripTracker;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * @author Lawrence
 *
 */
public class HelpMenu extends JMenu {

	private JMenuItem helpMenuItem;
	
	public HelpMenu() {
		super(TripTrackerConstants.HELP);
		
		this.helpMenuItem = new JMenuItem(TripTrackerConstants.HELP);
		
		this.add(this.helpMenuItem);
		
		init();
	}
	
	private void init() {
		
		helpMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				HelpFrame helpFrame = new HelpFrame();
			}
			
		});
	}
	
}
