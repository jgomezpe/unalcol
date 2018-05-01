package unalcol.vc.plugin;

import java.io.IOException;

import unalcol.reflect.plugin.PlugInSet;
import unalcol.reflect.xml.XMLDocument;
import unalcol.vc.SimpleVCEnd;

public class PlugInsVCEnd<T,S> extends SimpleVCEnd<T,S>{
	protected String url;
	public PlugInsVCEnd(String url, PlugInSet<T>  components) {
		super(components);
		this.url = url;
	}
	
	protected T build( String end ){
		try {
			XMLDocument doc;
			doc = new XMLDocument(url, end+".xml");
			components.clear();
			return ((PlugInSet<T>)components).get(doc);
		} catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void load( String end ){ build( end ); }
}