package unalcol.plugin;

import java.lang.reflect.Field;

import unalcol.reflect.Loader;
import unalcol.collection.keymap.Key;
import unalcol.collection.keymap.MultiKeyCollection;
import unalcol.collection.keymap.SplitMultiKey;
import unalcol.exception.ParamsException;
import unalcol.object.ThingKey;
import unalcol.object.Thing;
import unalcol.xml.XMLElement;
import unalcol.xml.XMLElementKey;
import unalcol.xml.XMLElementSet;
import unalcol.xml.XMLFilter;

public class PlugInSet<T> extends MultiKeyCollection<T>{
	protected Loader loader;
	protected String module;
	protected PlugInInstance<T> defaultPlugIn;
	protected XMLElementKey xmlKey;
	
	public PlugInSet( Loader loader, String module ){ this( loader, module, XMLElement.TAG); }

	public PlugInSet( Loader loader, String module, String xmlKey ){ this( loader, module, xmlKey, null); }
	
	@SuppressWarnings("unchecked")
	public PlugInSet( Loader loader, String module, String xmlKey, PlugInInstance<T> defaultPlugIn ){
		super( new SplitMultiKey<T>( (Key<String, T>)new ThingKey() ) );
		this.module = module;
		this.loader = loader;
		this.xmlKey = new XMLElementKey(xmlKey);
		this.defaultPlugIn = defaultPlugIn;
	}
	
	public Object load( XMLElement element ){
		XMLFilter filter = loader.filter(XMLElement.TAG);
		XMLElementSet manifest = filter.module(module);
		try{
			Object obj = loader.load( manifest.get(xmlKey.key(element)) ); 
			if( obj instanceof PlugIn ){
				((PlugIn) obj).init(element, this);
				return obj;
			}
			Class<?> objClass = obj.getClass();
			for( String attr:element.keys() ){
				try{
					Field field = objClass.getField(attr);
					String type = field.getDeclaringClass().getName();
					if( type.equals("String") ) field.set(obj, (String)element.get(attr));
					if( type.equals("int") ) field.set(obj, Integer.parseInt((String)element.get(attr)));
					if( type.equals("double") ) field.set(obj, Double.parseDouble((String)element.get(attr)));
					if( type.equals("long") ) field.set(obj, Long.parseLong((String)element.get(attr)));
					if( type.equals("char") ) field.set(obj, ((String)element.get(attr)).charAt(0));
				}catch( NoSuchFieldException nf ){} catch (IllegalArgumentException e) {} catch (IllegalAccessException e) {}	
			}
			return obj;
		}catch (ParamsException e1){ return null; }
	}
		
	
	@SuppressWarnings("unchecked")
	public T get(XMLElement element) throws ParamsException{
		try{
			String owner = (String)element.get(Thing.ID);
			return get( owner );
		}catch(Exception e){};
		T p=null;
		if( defaultPlugIn!=null ){
			p = defaultPlugIn.get();
			if( p!=null ) return p;
		}
		p = (T)load(element); 
		add(p);
		return p;
	}
	
	public boolean valid(XMLElement key){ return defaultPlugIn!=null || valid(key); }
}