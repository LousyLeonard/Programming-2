package core.util;

import java.io.File;

/**
*
* @author Lawrence
*/
public abstract class FileUtils {

    public final static String uib = "uib";
    public final static String dot = ".";


    /*
     * Get the extension of a file.
     */  
    public static String getExtension(File file) {
        return getExtension(file.getName());
    }

	public static String getExtension(String n) {
		String name = n;
        String extension = "";
        int i = name.lastIndexOf('.');

        if (i > 0 &&  i < name.length() - 1) {
        	extension = name.substring(i+1).toLowerCase();
        }
        return extension;
	}
}
