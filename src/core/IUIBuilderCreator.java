package core;

import java.util.Map;

/**
 * 
 * An interface for instantiating a UIBuilder creator. It's main purpose is
 * to get around the serialisation of the GUI by allowing storage of a GUI
 * generator in the required area.
 * Strategy rather than factory combined with CustomClassLoader
 * allow for a single class to created, removed or modified to add/change/remove
 * a UIBuilder. A factory would need to modify 3.
 *
 * @author Lawrence
 */
public interface IUIBuilderCreator {

	/**
	 * Get a new instance of the requested UIBuilder.
	 * 
	 * @param entries - A map of values that are to be held in the given UIBuilder.
	 * @return The UIBuilder specified.
	 */
	public UIBuilder getNewInstance(Map<String, Object> entries);
	
}
