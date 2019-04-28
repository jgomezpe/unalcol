package unalcol.js;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

public class Util {
	
	public static final String resources="resources/";
	public static final String images="imgs/";
	public static final String cfg="cfg/";
	public static final String i18n="I18N/";
	
	public static String value(String[] args, String tag){
		String v = null;
		int i=0;
		while(i<args.length && v==null){
			String[] p = args[i].split("=");
			if( p[0].equals(tag) ) v = p[1];
			i++;
		}
		return v;
	}
		
	public static String resource( String name, boolean asResource ){
		return asResource?"/"+images+name:resources+images+name;
	}
	
	public static Image image(String name){
		try{ if( name.startsWith("http://") || name.startsWith("https://")) return ImageIO.read(new URL(name)); }catch(Exception ex){}				
		try{ return ImageIO.read(new File(name)); }catch(Exception ex){};
		try{ return ImageIO.read(Util.class.getResource("/"+images+name)); }catch(Exception ex){}
		try{ return ImageIO.read(new File(resources+images+name)); }catch(Exception ex){};
		return null;
	}	
	
	public static String plain_file_read( InputStream is, boolean write_eol ) throws Exception{
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

	public static String config(String file){
		try{ return plain_file_read(new FileInputStream(file),true); }catch( Exception e ){}
		try{ return plain_file_read(new FileInputStream(resources+cfg+file),true); }catch( Exception e ){}
		try{ return plain_file_read(Util.class.getResourceAsStream("/"+cfg+file),true); }catch( Exception e ){}
		return null;
	}	
	
	public static String toXml( String code ){
		StringBuilder sb = new StringBuilder();
		for( int i=0; i<code.length(); i++ ){
			char c = code.charAt(i); 
			switch(c){
				case'<': sb.append("&lt;"); break;
				case'&': sb.append("&amp;");  break;
				case'>': sb.append("&gt;"); break;
				case'"': sb.append("&quot;");  break;
				case'\'': sb.append("&apos;");  break;
				default:
					sb.append(c);
			}		
		}
		return sb.toString();
	}
	
	public static String toJS( String code ){
		StringBuilder sb = new StringBuilder();
		for( int i=0; i<code.length(); i++ ){
			char c = code.charAt(i); 
			switch(c){
				case'\n': sb.append("\\n"); break;
				case'\t': sb.append("\\t"); break;
				case'\r': sb.append("\\r"); break;
				default:
					sb.append(c);
			}		
		}
		return sb.toString();
	}		
}
