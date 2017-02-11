package transports;

import java.util.concurrent.CountDownLatch;

import javax.swing.JPanel;

@Transport
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
	public void setup(CountDownLatch latch) {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return getType();
	}

	@Override
	public Object getContent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return new JPanel();
	}

}
