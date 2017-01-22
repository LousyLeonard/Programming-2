package tripTracker;

public enum TripTypes {
	
	DAY_TRIP_EXTERNAL_PROVIDER(StringConstants.DAY_TRIP_EXTERNAL_PROVIDER),
	DAY_TRIP_TEACHER(StringConstants.DAY_TRIP_TEACHER),
	RESIDENTIAL_TRIP_TEACHER(StringConstants.RESIDENTIAL_TRIP_TEACHER),
	RESIDENTIAL_TRIP_EXTERNAL_PROVIDER(StringConstants.RESIDENTIAL_TRIP_EXTERNAL_PROVIDER);
	
	private String type;
	
	TripTypes(final String type) {
		this.type = type;
	}
	
	public String getStringType() {
		return type;
	}
	
	public String toString() {
		return type;
	}

}
