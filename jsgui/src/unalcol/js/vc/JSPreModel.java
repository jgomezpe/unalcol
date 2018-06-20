package unalcol.js.vc;

import java.net.URL;
import java.net.URLClassLoader;

import unalcol.types.collection.keymap.KeyMap;
import unalcol.vc.BackEnd;
import unalcol.vc.Component;

public interface JSPreModel {
	public BackEnd backend();
	public KeyMap<String, Component> frontend();
	public void use(String url);
	
	public static JSPreModel get(String url, String model_jar, String model_class ){
		try {
			@SuppressWarnings("resource")
			URLClassLoader loader =  new URLClassLoader(new URL[]{new URL(url+model_jar)}, JSPreModel.class.getClassLoader());
			Class<?> cl = loader.loadClass(model_class);
			JSPreModel model = (JSPreModel)cl.newInstance();
			model.use(url);
			return model;
		} catch(Exception e) { e.printStackTrace(); }
		return null;
	}

	public static JSPreModel get(String url){ return get(url, "plugins/model.jar", "JSModel"); }	
}