package transports;

import core.IEntryPanelProvider;

/**
*
* @author Lawrence
*/
public interface ITransport extends IEntryPanelProvider {

	public String getType();
	public String getDepartureTime();
	public String getArrivalTime();
	public void setDepartureTime(String departureTime);
	public void setArrivalTime(String arrivalTime);

}
