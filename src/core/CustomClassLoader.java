/**
 * 
 */
package core;

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
public class CustomClassLoader<T> {
	
	// The type of the classloader, likely an interface
	private Class<T> typeClass;

	// All the classes that extend the class loader type
	private ArrayList<Class<T>> types;
	
	// Objects of the classes that extend the class loader type
	// They must have a default constructor
	private ArrayList<T> elements;

	public CustomClassLoader(Class<T> typeClass) {
		// private for singleton
		this.types = new ArrayList<Class<T>>();
		this.elements = new ArrayList<T>();
		this.typeClass = typeClass;
		
		init(typeClass.getPackage().getName());
	}
	
	private void init(String packageName) {
		String path = System.getProperties().getProperty("java.class.path", null);
		List<String> fileNames = new ArrayList<String>();

		fileNames = getFileNames(fileNames, Paths.get(path + "//" + packageName));
		
		for (String fileName : fileNames) {
		    if (fileName.endsWith(".class")) {
		        try {
					Class classObject = Class.forName(packageName + "." + fileName.substring(0, fileName.lastIndexOf('.')));
					if (typeClass.isAssignableFrom(classObject)
							&& !typeClass.equals(classObject)) {
						types.add(classObject);
					}

				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		}
		
		for (Class<T> transtype : types) {
			try {
				elements.add(transtype.newInstance());
			} catch (InstantiationException e) {
				System.out.println("Anything that needs to be class loaded needs a default constructor.");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<Class<T>> getTypes() {
		return types;
	}
	
	public ArrayList<T> getElements() {
		return elements;
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
