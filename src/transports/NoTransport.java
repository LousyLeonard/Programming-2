package transports;

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

}
