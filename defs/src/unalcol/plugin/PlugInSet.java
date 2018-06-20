package unalcol.plugin;

import java.lang.reflect.Field;

import unalcol.i18n.I18NException;
import unalcol.reflect.Loader;
import unalcol.types.collection.keymap.Key;
import unalcol.types.collection.keymap.MultiKeyCollection;
import unalcol.types.collection.keymap.SplitMultiKey;
import unalcol.types.object.ThingKey;
import unalcol.types.object.Thing;
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
	
	public Object load( XMLElement element ) throws PlugInException{
		XMLFilter filter = loader.filter(XMLElement.TAG);
		XMLElementSet manifest = filter.module(module);
		Object obj;
		try{ obj = loader.load( manifest.get(xmlKey.key(element)) ); } catch (I18NException e1){ obj=null; }
		if( obj==null ) return obj;
		if( obj instanceof PlugIn ){
			((PlugIn) obj).init(element, this);
			return obj;
		}
		try{
			Class<?> objClass = obj.getClass();
			for( String attr:element.attributes() ){
				try{
					Field field = objClass.getField(attr);
					String type = field.getDeclaringClass().getName();
					if( type.equals("String") ) field.set(obj, (String)element.get(attr));
					if( type.equals("int") ) field.set(obj, Integer.parseInt((String)element.get(attr)));
					if( type.equals("double") ) field.set(obj, Double.parseDouble((String)element.get(attr)));
					if( type.equals("long") ) field.set(obj, Long.parseLong((String)element.get(attr)));
					if( type.equals("char") ) field.set(obj, ((String)element.get(attr)).charAt(0));
				}catch( NoSuchFieldException nf ){}	
			}
			return obj;
		}catch(Exception e){ throw new PlugInException(e.getMessage()); }		
	}
		
	
	@SuppressWarnings("unchecked")
	public T get(XMLElement element){
		String owner = (String)element.get(Thing.ID);
		//if( owner!=null ) owner = owner.split(",")[0];
		T p = get( owner );
		if( p!=null ) return p;
		if( defaultPlugIn!=null ) p = defaultPlugIn.get(); 
		try{ p = (T)load(element); } catch (PlugInException e) {}
		if( p==null ) return null;
		add(p);
		return p; 
	}
	
	public boolean valid(XMLElement key){ return defaultPlugIn!=null || get(key)!=null; }
}