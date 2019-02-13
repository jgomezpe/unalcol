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

	public static Image image(String name){
		try{ if( name.startsWith("http://") || name.startsWith("https://")) return ImageIO.read(new URL(name)); }catch(Exception ex){}				
		try{ return ImageIO.read(new File(name)); }catch(Exception ex){};
		try{ return ImageIO.read(FileResource.class.getResource("/"+images+name)); }catch(Exception ex){}
		try{ return ImageIO.read(new File(resources+images+name)); }catch(Exception ex){};
		return null;
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
	
	public static String txt( String file, String optional_path, String resource_path, boolean write_eol ){
		try{ return txt_read(new FileInputStream(file),write_eol); }catch( Exception e ){}
		try{ return txt_read(new FileInputStream(optional_path+file),write_eol); }catch( Exception e ){}
		try{ return txt_read(FileResource.class.getResourceAsStream("/"+resource_path+file),write_eol); }catch( Exception e ){}
		return null;
	}
	
	public static String config(String file){ return txt( file, resources+CFG, CFG,false); }		
}
