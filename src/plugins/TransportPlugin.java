package plugins;

import java.io.Serializable;
import java.util.HashMap;

import core.ILabelPlugin;
import transports.ITransport;

/**
* A Label representing the Transport information.
* 
* @author Lawrence
*/
public class TransportPlugin implements ILabelPlugin, Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 3692854900708388166L;
	
	private static String PLUGIN_TITLE = "Transport";
	private static String TYPE_TITLE = "Type";
	private static String DEPARTURE_TIME_TITLE = "Departure Time";
	private static String ARRIVAL_TIME_TITLE = "Arrival Time";

	private ITransport transport;
	
	/**
	 * CONSTRUCTOR
	 * 
	 * @param transport - The transport to represent.
	 */
	public TransportPlugin(ITransport transport) {
		this.transport = transport;
	}
	
	/* (non-Javadoc)
	 * @see core.ILabelPlugin#getTitle()
	 */
	@Override
	public String getTitle() {
		return PLUGIN_TITLE;
	}

	/* (non-Javadoc)
	 * @see core.ILabelPlugin#getInfo()
	 */
	@Override
	public HashMap<String, String> getInfo() {
		HashMap<String, String> infoBoxes = new HashMap<String, String>();
		
		infoBoxes.put(TYPE_TITLE, transport.getType());
		infoBoxes.put(DEPARTURE_TIME_TITLE, transport.getDepartureTime());
		infoBoxes.put(ARRIVAL_TIME_TITLE, transport.getArrivalTime());

		return infoBoxes;
	}

}
