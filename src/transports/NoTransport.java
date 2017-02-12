package transports;

import javax.swing.JPanel;

public class NoTransport implements Transportable {

	@Override
	public String getType() {
		return "N/A";
	}

	@Override
	public String getDepartureTime() {
		return "N/A";
	}

	@Override
	public String getArrivalTime() {
		return "N/A";
	}

	@Override
	public void setDepartureTime(String departureTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setArrivalTime(String arrivalTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTitle() {
		return getType();
	}

	@Override
	public Object getContent() {
		return this;
	}

	@Override
	public JPanel getPanel() {
		return new JPanel();
	}
	
	@Override
	public String toString() {
		return "No Transport";
	}

}
