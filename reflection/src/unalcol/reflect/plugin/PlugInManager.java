package unalcol.reflect.plugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import java.util.Vector;

public class PlugInManager extends PlugInLoader{
	protected String plugin_path = null;
	protected Vector<String> repository_url = new Vector<String>();
	
	public PlugInManager(){}
	
	public PlugInManager( String[] types, String repository_url ){ this(types, repository_url, null); }
	
	public PlugInManager( String[] types, String repository_url, String plugin_path ){
		super( types );
		addRepository(repository_url);
		if( plugin_path != null ){
			try{ this.plugin_path = new URL(plugin_path).getPath(); } catch (MalformedURLException e) { this.plugin_path = plugin_path; }
			loadPluginsForFolder(new File(plugin_path));
		}	
	}
	
	public void addRepository( String repository_url ){ this.repository_url.add(repository_url); }
	
	protected boolean download( String plugin ){
		try{
			for( String url:repository_url ){
				PlugInManifest manifest = new PlugInManifest(url);
				Set<String> plugins = manifest.plugins();
				for( String pl : plugins ){
					String src = manifest.get(pl).getAttribute(PlugIn.src);
					String jarFileURL = url+src;
					PlugInManifest jarManifest = new PlugInManifest(jarFileURL);
					if(jarManifest.contains(plugin)){
						install(jarFileURL);
						return true;
					}
				}
			}
		}catch(IOException e){}	
		return false;
	}
	
 	public Object load(String plugin, String className) throws PlugInException{
 		PlugInSet set = plugins.get(className);
 		if( set==null ) throw undefined(plugin);
		if( set.get(plugin) == null ){ download(plugin); }
		return super.load(plugin, className); 
 	}

 	public PlugIn load(PlugInDescriptor plugin, String className) throws PlugInException{
 		String id = plugin.nick();
 		PlugInSet set = plugins.get(className);
 		if( set==null ) throw undefined(id);
		if( set.get(id) == null ){ download(id); }
		return super.load(plugin, className); 
 	} 	

	
	public void loadPluginsForFolder(final File folder) {
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) loadPluginsForFolder(fileEntry);
	        else{
	        	try{ add(fileEntry.toURI().toURL(), PlugInManager.class.getClassLoader()); }
	        	catch (IOException e){ e.printStackTrace(); }
	        }
	    }
	}
	
	public void install( String url ) throws IOException{
		install( new URL(url) );
	}
	
	public void install( URL url ) throws IOException{
		String path = url.getPath(); 
		if( path.endsWith("/") ){
			// https://stackoverflow.com/questions/1281229/how-to-use-jaroutputstream-to-create-a-jar-file
		}else{
			if( plugin_path != null ){
				InputStream is = url.openStream();
				String container = path.substring(path.lastIndexOf('/')+1, path.length()); 
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
			}else{ add(url, PlugInManager.class.getClassLoader()); }	
		}
	}

	public static void main(String[] args){
		String cl = PlugIn.className;
		PlugInManager manager = new PlugInManager(new String[]{cl}, "http://localhost/unalcol/plugins/");
		try{
			manager.load("A", cl);
			manager.load("B", cl);
			Set<String> plugins = manager.plugins(cl);
			for( String s:plugins ){
				Object c = manager.load(s,cl);
				System.out.println(c);
			}	
		} catch (PlugInException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
