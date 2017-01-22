package transports;

import java.text.SimpleDateFormat;
import java.util.Date;

@Transport
public class BusTransport implements Transportable {
	
	private final static String TYPE = "Bus";
	
	private Date departureTime;
	private Date arrivalTime;
	
	public BusTransport(Date departureTime, Date arrivalTime) {
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getDepartureTime() {
		SimpleDateFormat ft = 
			      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

		return ft.format(departureTime);
	}

	@Override
	public String getArrivalTime() {
		return arrivalTime.toString();
	}

}