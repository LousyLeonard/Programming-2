package transports;

import core.IEntryPanelProvider;

/**
* A definition of the behaviour of transports.
* 
* @author Lawrence
*/
public interface ITransport extends IEntryPanelProvider {

	/**
	 * Get the type of the transport.
	 * 
	 * @return The type of the transport.
	 */
	public String getType();
	
	/**
	 * Get the departure time.
	 * 
	 * @return the departure time of the transport.
	 */
	public String getDepartureTime();
	
	/**
	 * Get the arrival time.
	 * 
	 * @return the arrival time of the transport.
	 */
	public String getArrivalTime();
	
	/**
	 * Set the departure time.
	 * 
	 * @param departureTime - The departure time to set.
	 */
	public void setDepartureTime(String departureTime);
	
	/**
	 * Set the arrival time.
	 * 
	 * @param arrivalTime - The arrival time to set.
	 */
	public void setArrivalTime(String arrivalTime);

}
