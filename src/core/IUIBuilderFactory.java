package core;

import java.util.Map;

/**
*
* @author Lawrence
*/
public interface IUIBuilderFactory {

	public UIBuilder getNewInstance(Map<String, Object> entries);
	
}
