package unalcol.reflect.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class PlugInManager extends PlugInLoader{
	protected String plugin_path;

	public void loadPluginsForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) loadPluginsForFolder(fileEntry);
	        else{
	        	try{ add(fileEntry.toURI().toURL(), PlugInManager.class.getClassLoader()); }
	        	catch (IOException e){ e.printStackTrace(); }
	        }
	    }
	}
	
	public PlugInManager( String path ){
		try{ plugin_path = new URL(path).getPath(); } catch (MalformedURLException e) { plugin_path = path; }
		loadPluginsForFolder(new File(plugin_path));
	}
	
	
	public void install( String url ) throws IOException{
		install( new URL(url) );
	}
	
	public void install( URL url ) throws IOException{
		String path = url.getPath(); 
		if( path.endsWith("/") ){
			// https://stackoverflow.com/questions/1281229/how-to-use-jaroutputstream-to-create-a-jar-file
		}else{
			String container = path.substring(path.lastIndexOf('/')+1, path.length()); 
			InputStream is = url.openStream();
			FileOutputStream os = new FileOutputStream(plugin_path+container);
			byte[] buffer = new byte[100000];
			int k = is.read(buffer);
			while(k>0){
				os.write(buffer,0,k);
				k = is.read(buffer);
			}
			os.close();
			is.close();
			add(new File(plugin_path+container).toURI().toURL(), PlugInManager.class.getClassLoader());
		}
	}

	public static void main(String[] args){
		PlugInManager manager = new PlugInManager("plugins");
		try {
			//manager.install("file:/home/jgomez/workspace/pluginA.jar");
			//manager.install("file:/home/jgomez/workspace/pluginB.jar");
			Set<String> plugins = manager.plugins();
			for( String s:plugins ){
				Object c = manager.newInstance(s);
				System.out.println(c);
			}	
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
