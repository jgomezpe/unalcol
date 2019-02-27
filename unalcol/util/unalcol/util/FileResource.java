package unalcol.util;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

public class FileResource {
	public static final String CFG = "cfg/";
	public static final String resources="resources/";
	public static final String images="imgs/";

	public static Image image(ClassLoader loader, String name){
		try{ if( name.startsWith("http://") || name.startsWith("https://")) return ImageIO.read(new URL(name)); }catch(Exception ex){}				
		try{ return ImageIO.read(new File(name)); }catch(Exception ex){};
		try{ return ImageIO.read(loader.getResource("/"+images+name)); }catch(Exception ex){}
		try{ return ImageIO.read(new File(resources+images+name)); }catch(Exception ex){};
		return null;
	}

	public static Image image(String name){
		return image(FileResource.class.getClassLoader(), name );
	}
	
	public static String txt_read( InputStream is, boolean write_eol ) throws Exception{
		StringBuilder sb = new StringBuilder();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String line = reader.readLine();
		while( line != null ){ 
			sb.append(line);
			line = reader.readLine();
			if( line != null && write_eol) sb.append('\n');
		}
		return sb.toString();
	}
	
	public static String txt( ClassLoader loader, String file, String optional_path, String resource_path, boolean write_eol ){
		try{ return txt_read(new FileInputStream(file),write_eol); }catch( Exception e ){}
		try{ return txt_read(new FileInputStream(optional_path+file),write_eol); }catch( Exception e ){}
		try{ return txt_read(loader.getResourceAsStream(resource_path+file),write_eol); }catch( Exception e ){}
		return null;
	}

	public static String txt( String file, String optional_path, String resource_path, boolean write_eol ){
		return txt( FileResource.class.getClassLoader(), file, optional_path, resource_path, write_eol );
	}

	
	public static String config(ClassLoader loader, String file){ return txt( loader, file, resources+CFG, CFG,false); }		

	public static String config(String file){ return config( FileResource.class.getClassLoader(), file); }		
}