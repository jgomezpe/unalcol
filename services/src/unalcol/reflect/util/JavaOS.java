package unalcol.reflect.util;
import java.io.*;

/**
 * <p>Collection of values which are determined according to the Operative System
 * where the Java Machine is running</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class JavaOS {
    /**
     * Character used for directories hierarchy
     */
    protected static char FILE_SEPARATOR = System.getProperty("file.separator").charAt(0);

    /**
     * Character used for defining class paths for the java compiler and java virtual machine.
     */
    protected static char PATH_SEPARATOR = System.getProperty("path.separator").charAt(0);

    /**
     * Character used for enclosing class paths, class sources, etc for the java compiler and java virtual machine.
     */
    protected static char CLOSING_CHARACTER = 
            (System.getProperty("os.name").indexOf("Windows") != -1)?'"':' ';

    /**
     * Creates an string with the appropriated operating system file separator
     * @param path String standar java path name
     * @return Path with the appropriated file separator character (according to the operating system)
     */
    public static String systemPath(String path) {
        return path.replace('/', FILE_SEPARATOR).replace('\\', FILE_SEPARATOR);
    }

    /**
     * Creates the absolute version of a path (including last / symbol)
     * @param path String to be converted to absolute path
     * @return String with the absolute path
     */
    public static String absolutePath( String path ){
        return (new File(systemPath(path)).getAbsolutePath()) + "/";
    }

    /**
     * Character used for directories hierarchy
     */
    public static char fileSeparator(){
        return FILE_SEPARATOR;
    }

    /**
     * Character used for defining class paths for the java compiler and java virtual machine.
     */
    public static char pathSeparator(){
        return PATH_SEPARATOR;
    }

    /**
     * Character used for enclosing class paths, class sources, etc for the java compiler and java virtual machine.
     */
    public static char closingCharacter(){
        return CLOSING_CHARACTER;
    }
    
    public static String applicationPath( Class<?> cl ){
        String applicationDir = cl.getResource(cl.getSimpleName()+".class").getPath();
        applicationDir = applicationDir.substring(0, applicationDir.lastIndexOf("/unalcol"));
        if( applicationDir.endsWith(".jar!")){
            applicationDir = applicationDir.substring(0, applicationDir.lastIndexOf("/"));
        }
        if( applicationDir.startsWith("file:")){
            applicationDir = applicationDir.substring(5);
        }
        return applicationDir;
    }
}