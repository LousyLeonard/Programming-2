package core;

import java.util.Map;

public interface IUIBuilderFactory {

	public UIBuilder getNewInstance(Map<String, Object> entries);
	
}
