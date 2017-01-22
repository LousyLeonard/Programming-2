package plugins;

import java.util.HashMap;

import core.ILabelPlugin;

public class TripFeePlugin implements ILabelPlugin {

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
