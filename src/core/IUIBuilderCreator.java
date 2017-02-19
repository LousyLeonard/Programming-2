package core;

import java.util.Map;

/**
* Strategy rather than factory combined with CustomClassLoader
* allow for a single class to created, removed or modified to add/change/remove
* a trip. A factory would need to modify 3.
*
* @author Lawrence
*/
public interface IUIBuilderCreator {

	public UIBuilder getNewInstance(Map<String, Object> entries);
	
}
