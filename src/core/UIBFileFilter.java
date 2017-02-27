/**
 * 
 */
package core;

import java.io.File;

import javax.swing.filechooser.FileFilter;

import core.util.FileUtils;

/**
 * A FileFilter to be used with the FileChooser which will filter out
 * all extensions that are not .uib files. Also only permits the 
 * saving of .uib files.
 * 
 * @author Lawrence
 */
public class UIBFileFilter extends FileFilter {

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
	        return true;
	    }

	    String extension = FileUtils.getExtension(f);
	    if (extension != null) {
	        if (extension.equals(CoreConstants.FILE_EXTENSION)) {
	                return true;
	        } else {
	            return false;
	        }
	    }

	    return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	@Override
	public String getDescription() {
	    return CoreConstants.FILE_EXTENSION_DESC;
	}
}
