package plugins;

import java.io.Serializable;
import java.util.HashMap;

import core.ILabelPlugin;
import transports.Transportable;

public class TransportPlugin implements ILabelPlugin, Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 3692854900708388166L;
	
	private static String PLUGIN_TITLE = "Transport";
	private static String TYPE_TITLE = "Type";
	private static String DEPARTURE_TIME_TITLE = "Departure Time";
	private static String ARRIVAL_TIME_TITLE = "Arrival Time";

	private Transportable transport;
	
	public TransportPlugin(Transportable transport) {
		this.transport = transport;
	}
	
	@Override
	public String getTitle() {
		return PLUGIN_TITLE;
	}

	@Override
	public HashMap<String, String> getInfo() {
		HashMap<String, String> infoBoxes = new HashMap<String, String>();
		
		infoBoxes.put(TYPE_TITLE, transport.getType());
		infoBoxes.put(DEPARTURE_TIME_TITLE, transport.getDepartureTime());
		infoBoxes.put(ARRIVAL_TIME_TITLE, transport.getArrivalTime());

		return infoBoxes;
	}

}
