package transports;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.swing.JPanel;

import core.IEntryPanelProvider;
import core.IYesNoEvent;
import core.events.HideWindowEvent;
import core.ui.DialogBuilder;
import core.ui.entrypanels.GenericStringEntryPanel;

@Transport
public class BusTransport implements Transportable {
	
	private final static String TYPE = "Bus";

	private String departureTime;
	private String arrivalTime;
	
//	private Date departureTime;
//	private Date arrivalTime;
	
	public BusTransport() {
		this.departureTime = "";
		this.arrivalTime = "";
	}
	
	public BusTransport(String departureTime, String arrivalTime) {
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
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
	public void setup(CountDownLatch latch) {
//		new Thread(new Runnable() {
//				public void run() {
					DialogBuilder dialog = new DialogBuilder("Add Transport");
					dialog.addPanel(new GenericStringEntryPanel("Arrival Date"));
					dialog.addPanel(new GenericStringEntryPanel("Departure Date"));
					
					dialog.registerYesEvent(new SetupTransport(latch, this));
					dialog.registerNoEvent(new IYesNoEvent() {

						@Override
						public void doEvent(DialogBuilder builder) {
							builder.dispose();							
						}
						
					});
							
					dialog.setVisible(true);	
//				}
//		}).start();

		
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
		ArrayList<Object> contents = new ArrayList<Object>();
		contents.add(TYPE);
		contents.add(departureTime);
		contents.add(arrivalTime);

		return contents;
	}

	@Override
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		return new GenericStringEntryPanel("test");
	}

}