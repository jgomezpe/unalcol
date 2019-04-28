package unalcol.js.vc;

import java.net.URL;
import java.net.URLClassLoader;

import unalcol.collection.KeyMap;
import unalcol.js.Util;
import unalcol.js.vc.mode.fx.FXManager;
import unalcol.js.vc.mode.server.JSServerManager;
import unalcol.vc.BackEnd;
import unalcol.vc.Component;
import unalcol.vc.FrontEnd;
import unalcol.vc.VCModel;

public class JSModel extends VCModel{
	protected JSModelLoader modelLoader=null;
	
	public JSModel( String url ){
		loader(url);
		init( backend(url), frontend(url) ); 
	}
	
	protected void loader( String url ){
		try {
			System.out.println("[JSModel]"+url);
			URL aURL = new URL( url );
			String path = aURL.getProtocol()+"://"+aURL.getHost()+"/";
			String[] query = aURL.getQuery().split("&"); 
			String pack = Util.value(query, "pack");
			if(pack.length()>0 ) pack = pack + "/";
			String model = Util.value(query, "model");
			if( model==null || model.length()==0 ) model = "model.jar";
			@SuppressWarnings("resource")
			URLClassLoader loader =  new URLClassLoader(new URL[]{new URL(path+"plugins/"+pack+model)}, JSModel.class.getClassLoader());
			System.out.println("[JSModel]"+path+"plugins/"+pack+model);
			Class<?> cl = loader.loadClass("js.ModelLoader");
			modelLoader = (JSModelLoader)cl.newInstance();
		} catch(Exception e) { e.printStackTrace(); };
	}
	
	public BackEnd backend( String url ){ return modelLoader.backend(url); }
	
	public FrontEnd frontend( String url ){
		JSFrontEnd front = null;
		try {
			URL aURL = new URL( url );
			String[] query = aURL.getQuery().split("&"); 
			String mode = Util.value(query, "mode");
			if( mode==null || mode.length()==0 ) mode = "servlet";
			KeyMap<String, Component> comps = modelLoader.frontend(url); 
			System.out.println("[JSModel]"+mode);
			if( mode.equals("fx") ) front = new FXManager(url); 
			if( mode.equals("servlet") ) front = new JSServerManager(); 
			if( front != null )	front.init(comps); 
		} catch (Exception e) { e.printStackTrace(); }
		return front;
	}	
}