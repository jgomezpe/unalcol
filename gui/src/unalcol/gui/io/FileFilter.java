package unalcol.gui.io;

import java.io.File;
import java.util.Hashtable;

/**
 * <p>Title: UnalcolFileFilter</p>
 *
 * <p>Description: A File Filter for open/save dialog boxes</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class FileFilter extends javax.swing.filechooser.FileFilter {
    /**
     * Collection of valid file extensions
     */
    protected Hashtable<String,
            String> extensions = new Hashtable<String, String>();
    /**
     * File filer description
     */
    protected String description;

    /**
     * Creates a FileFilter with the given description
     * @param description Description for the given file filtering
     */
    public FileFilter(String description) {
        this.description = description;
    }

    /**
     * Adds a new file extension for filtering files in dialog boxes
     * @param extension file extension for filtering files in dialog boxes that will be added
     */
    public void add(String extension) {
        extensions.put(extension, extension);
    }

    /**
     * Determines if a File has one valid extension
     * @param file File to be analized
     * @return true if the file has a valid extension, false otherwise
     */
    public boolean accept(File file) {
        if (file.isDirectory()) {
            return true;
        }

        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }

        return (ext != null && extensions.get(ext) != null);
    }

    /**
     * Gets the Description for the given file filtering
     * @return String containing the the Description for the given file filtering
     */
    public String getDescription() {
        return description;
    }

}
