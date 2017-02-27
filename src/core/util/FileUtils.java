package core.util;

import java.io.File;

/**
* File Utilities.
* 
* @author Lawrence
*/
public abstract class FileUtils {
 
   /**
     * Get the extension of a file.
     * 
     * @param file - The file to get the extension of.
     * @return the file extension.
     */
     public static String getExtension(File file) {
        return getExtension(file.getName());
    }

     /**
     * Get the extension of a file.
	 * 
     * @param n - The String to get the extension of.
     * @return the file extension.
	 */
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
