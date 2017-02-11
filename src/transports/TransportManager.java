/**
 * 
 */
package transports;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Lawrence
 *
 */
public class TransportManager {
	
	// Instance of the TransportManager
	private static TransportManager instance;
	
	private ArrayList<Class<Transportable>> transportTypes;
	private ArrayList<Transportable> transports;

	
	private TransportManager() {
		// private for singleton
		transportTypes = new ArrayList<Class<Transportable>>();
		transports = new ArrayList<Transportable>();
		
		String path = System.getProperties().getProperty("java.class.path", null);
		List<String> fileNames = new ArrayList<String>();
		fileNames = getFileNames(fileNames, Paths.get(path + "//transports"));
		
		for (String fileName : fileNames) {
		    if (fileName.endsWith(".class")) {
		        try {
					Class classObject = Class.forName("transports." + fileName.substring(0, fileName.lastIndexOf('.')));
					if (classObject.isAnnotationPresent(Transport.class)) {
						transportTypes.add(classObject);
					}

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		}
		
		for (Class<Transportable> transtype : transportTypes) {
			try {
				transports.add(transtype.newInstance());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static TransportManager getInstance() {
		if (instance == null) {
			instance = new TransportManager();
		}
		return instance;
	}
	
	public ArrayList<Class<Transportable>> getTransportTypes() {
		return transportTypes;
	}
	
	public ArrayList<Transportable> getTransports() {
		return transports;
	}
	
	private List<String> getFileNames(List<String> fileNames, Path dir) {
	    try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
	        for (Path path : stream) {
	            if(path.toFile().isDirectory()) {
	                getFileNames(fileNames, path);
	            } else {
	                fileNames.add(path.getFileName().toString());
	            }
	        }
	    } catch(IOException e) {
	        e.printStackTrace();
	    }
	    return fileNames;
	} 

}
