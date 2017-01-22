package transports;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

import core.IYesNoEvent;
import core.ui.DialogBuilder;

public class SetupTransport implements IYesNoEvent{
	
	private CountDownLatch latch;
	private Transportable transport;

	public SetupTransport(CountDownLatch latch, Transportable transport) {
		this.latch = latch;
		this.transport = transport;
	}
	
	@Override
	public void doEvent(DialogBuilder builder) {
		Map<String, Object> results = builder.getEntrys();
		transport.setDepartureTime((String)results.get("Departure Date"));
		transport.setArrivalTime((String)results.get("Arrival Date"));
		builder.dispose();
		latch.countDown();		
	}

}
