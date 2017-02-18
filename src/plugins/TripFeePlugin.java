package plugins;

import java.io.Serializable;
import java.util.HashMap;

import core.ILabelPlugin;

/**
*
* @author Lawrence
*/
public class TripFeePlugin implements ILabelPlugin, Serializable {

	/**
	 *  Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = 3689524512617139254L;
	
	private static String PLUGIN_TITLE = "Trip Fee";
	private static String PRICE_HEADING = "Price";
	
	private Double tripFee;
	
	public TripFeePlugin(Double tripFee) {
		this.tripFee = tripFee;
	}
	
	@Override
	public String getTitle() {
		return PLUGIN_TITLE;
	}

	@Override
	public HashMap<String, String> getInfo() {
		HashMap<String, String> infoBoxes = new HashMap<String, String>();
		infoBoxes.put(PRICE_HEADING,Double.toString(tripFee));
		return infoBoxes;
	}

}
