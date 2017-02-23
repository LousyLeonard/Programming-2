package transports;

import java.io.Serializable;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import core.ui.entrypanels.GenericStringEntryPanel;

/**
*
* @author Lawrence
*/
public class BusTransport implements ITransport, Serializable {
	
	/**
	 * Appease the gods of serialisation.
	 */
	private static final long serialVersionUID = -2193386438643529730L;
	
	private final static String TYPE = "Bus";
	private final static String DEPARTURE_TIME = "Departure Time";
	private final static String ARRIVAL_TIME = "Arrival Time";

	private String departureTime;
	private String arrivalTime;
	
	private GenericStringEntryPanel depPanel;
	private GenericStringEntryPanel arrPanel;
	
//	private Date departureTime;
//	private Date arrivalTime;
	
	public BusTransport() {
		this.departureTime = "";
		this.arrivalTime = "";
		
		depPanel = new GenericStringEntryPanel(DEPARTURE_TIME);
		arrPanel = new GenericStringEntryPanel(ARRIVAL_TIME);
	}
	
	public BusTransport(String departureTime, String arrivalTime) {
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
		
		depPanel = new GenericStringEntryPanel(DEPARTURE_TIME);
		arrPanel = new GenericStringEntryPanel(ARRIVAL_TIME);
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public String getDepartureTime() {
//		SimpleDateFormat ft = 
//			      new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
//
//		return ft.format(departureTime);
		return departureTime;

	}

	@Override
	public String getArrivalTime() {
		return arrivalTime;
	}

	@Override
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;		
	}

	@Override
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	@Override
	public String getTitle() {
		return TYPE;
	}

	@Override
	public Object getContent() {
		setDepartureTime((String)depPanel.getContent());
		setArrivalTime((String)arrPanel.getContent());
		
//		ArrayList<Object> contents = new ArrayList<Object>();
//		contents.add(TYPE);
//		contents.add(getDepartureTime());
//		contents.add(getArrivalTime());

		return this;
	}

	@Override
	public JPanel getPanel() {
		JPanel busPanel = new JPanel();
		BoxLayout layout = new BoxLayout(busPanel, BoxLayout.PAGE_AXIS);
		depPanel = new GenericStringEntryPanel(DEPARTURE_TIME);
		arrPanel = new GenericStringEntryPanel(ARRIVAL_TIME);
		busPanel.setLayout(layout);
		busPanel.add(depPanel);
		busPanel.add(arrPanel);
		return busPanel;
	}
	
	@Override
	public String toString() {
		return TYPE;
	}

}