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

import javax.swing.JOptionPane;

/**
 * CustomClassLoader loads in a list of instances of all of the
 * subclasses or realisers of a given class. The search is limited
 * to the package of the given class.
 * 
 * All classes loaded this way must have a no argument constructor available.
 *  
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

	/**
	 * Constructor
	 * 
	 * @param typeClass - The class type of which to load in the subclasses of.
	 */
	public CustomClassLoader(Class<T> typeClass) {
		// private for singleton
		this.types = new ArrayList<Class<T>>();
		this.elements = new ArrayList<T>();
		this.typeClass = typeClass;
		
		init(typeClass.getPackage().getName());
	}
	
	/**
	 * @param packageName - The name of target classes package.
	 */
	private void init(String packageName) {
		String path = System.getProperties().getProperty("java.class.path", null);
		List<String> fileNames = new ArrayList<String>();

		// Get the names of all of the files in the package
		fileNames = getFileNames(fileNames, Paths.get(path + "//" + packageName));
		
		// Load in any classes and then check for whether they are a subclass of the target.
		for (String fileName : fileNames) {
		    if (fileName.endsWith(".class")) {
		        try {
					Class classObject = Class.forName(packageName + "."
							+ fileName.substring(0, fileName.lastIndexOf('.')));
					
					if (typeClass.isAssignableFrom(classObject)
							&& !typeClass.equals(classObject)) {
						types.add(classObject);
					}

				} catch (ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Failed to generate all options of " 
							+ typeClass.getName());
					System.out.println("Failed to generate all options of " + typeClass.getName());
					System.out.println("Problem encountered with " + fileName);
					e1.printStackTrace();
				}
		    }
		}
		
		// For all subclasses of the target call their default constuctor to 
		// generate an instance.
		for (Class<T> transtype : types) {
			try {
				elements.add(transtype.newInstance());
			} catch (InstantiationException e) {
				System.out.println("Anything that needs to be class loaded needs a default constructor.");
				System.out.println("No none argument constructor found in " + transtype.getName());
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return list of classes that are subclasses of the target class
	 */
	public ArrayList<Class<T>> getTypes() {
		return types;
	}
	
	/**
	 * @return list of instances of the classes that are subclasses of the target class
	 */
	public ArrayList<T> getElements() {
		return elements;
	}
	
	/**
	 * Recursive call to get all the children file names from a given path.
	 * 
	 * @param fileNames - the list of filenames found thus far.
	 * @param dir - The path of which to check.
	 * @return a list of all the files within the target path.
	 */
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
