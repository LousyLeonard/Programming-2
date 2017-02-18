package core.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import core.util.LabelFunctions;
import core.util.Triplet;

public class InfoLabel extends JPanel implements Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 2207704177048771867L;
	
	private javax.swing.JPanel titlePanel;
	private javax.swing.JLabel titleLabel;
	private javax.swing.JPanel infoPanel;
	private ArrayList<
				Triplet<javax.swing.JPanel, javax.swing.JLabel, javax.swing.JLabel>> infoLabels;
	
	private String title;
	private Map<String, String> info;
	
	public InfoLabel(String title, Map<String, String> info) {
		titlePanel = new javax.swing.JPanel();
		titleLabel = new javax.swing.JLabel();
		infoPanel = new javax.swing.JPanel();
		infoLabels = new ArrayList<
				Triplet<javax.swing.JPanel, javax.swing.JLabel, javax.swing.JLabel>>();
		
		this.title = title;
		this.info = info;
		
		populateInfoLabels();
		init();
	}
	
	private void init() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.LINE_AXIS));
				
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.PAGE_AXIS));
		
		this.add(titlePanel);
		this.add(infoPanel);
		
		titleLabel.setText(title);
		LabelFunctions.underLineLabel(titleLabel);
		titlePanel.add(titleLabel);
		
		for( Triplet<javax.swing.JPanel, javax.swing.JLabel, javax.swing.JLabel> entry : infoLabels) {
			entry.getFirst().add(entry.getSecond());
			entry.getFirst().add(entry.getThird());

			infoPanel.add(entry.getFirst());
		}
	}
	
	private void populateInfoLabels() {
		
    	for ( String key : info.keySet() ) {
    		javax.swing.JLabel titleLabel = new javax.swing.JLabel();
    		titleLabel.setText(key.toString() + ": ");
    		javax.swing.JLabel bodyLabel = new javax.swing.JLabel();
    		bodyLabel.setText(info.get(key).toString());
        	    			
    		javax.swing.JPanel panel = new javax.swing.JPanel();
    		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
    		
    		Triplet<javax.swing.JPanel, javax.swing.JLabel, javax.swing.JLabel> infoEntry =
					new Triplet<javax.swing.JPanel, javax.swing.JLabel, javax.swing.JLabel>(panel, titleLabel, bodyLabel);

    		infoLabels.add(infoEntry);
    	}
	}
	
}
