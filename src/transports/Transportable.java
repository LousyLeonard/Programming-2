package transports;

import java.util.concurrent.CountDownLatch;

import core.IEntryPanelProvider;

public interface Transportable extends IEntryPanelProvider {

	public String getType();
	public String getDepartureTime();
	public String getArrivalTime();
	public void setup(CountDownLatch latch);
	public void setDepartureTime(String departureTime);
	public void setArrivalTime(String arrivalTime);

}
