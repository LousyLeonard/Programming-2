package tripTracker;

import javax.swing.JPanel;

import core.IEntryPanelProvider;
import transports.NoTransport;

public class TransportEntryPanel implements IEntryPanelProvider {
	
	private String title;
	
	public TransportEntryPanel(String title) {
		this.title = title;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public Object getContent() {
		return new NoTransport();
	}

	@Override
	public JPanel getPanel() {
		return new JPanel();
	}

}
