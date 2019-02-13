package unalcol.reflect;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

import unalcol.collection.KeyMap;
import unalcol.collection.keymap.HashMap;
import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;
import unalcol.xml.XMLDocument;
import unalcol.xml.XMLElement;
import unalcol.xml.XMLFilter;

public class Loader{
	public static final String PLUGIN = "plugin";
	public static final String LOADER = "loader";
	public static final String SRC = "src";

	public static ParamsException undefined( String plugin ){ return new ParamsException("Undefined plugin %s", plugin); }

	protected KeyMap<String, XMLFilter> filters = new HashMap<String,XMLFilter>();

	protected KeyMap<String, XMLElement> classes = new HashMap<String,XMLElement>();
	
	public Loader(){ filter( XMLElement.TAG ); }

	public Loader( String url ){ this(new String[]{url}); }
	
	public Loader( String[] urls ){ 
		 this(); 
		 try{ for(String url:urls) this.addRepository(url); }catch(Exception e){} 
	}
	
	public void addPackage( String url ) throws ParamsException{
		URLClassLoader loader = null;
		try{ loader =  new URLClassLoader(new URL[]{new URL(url)}, Loader.class.getClassLoader());}
		catch(MalformedURLException e){ throw ParamsException.wrap(InnerCore.IO, e); }
		
		XMLDocument doc = new XMLDocument(url, PLUGIN);
		doc.setRecursive(LOADER, loader);
		for( Object pl : doc.children() ){
			XMLElement elem = (XMLElement)pl;
			for( XMLFilter f:filters ) f.add(elem);
			classes.set((String)elem.get(SRC), elem);
		}
	}

	public void addRepository( String url ) throws ParamsException{
		XMLDocument doc = new XMLDocument(url, PLUGIN);
		for( XMLElement elem:doc.children() ){
			addPackage( url+(String)elem.get(SRC) );
		}
	}
	
	public XMLFilter filter( String attribute ){
		XMLFilter f;
		try{ f= filters.get(attribute); }
		catch(Exception e ){
			f = new XMLFilter(attribute);
			for( XMLElement elem:classes ) f.add(elem);
			filters.add(f);
		}
		return f;
	}
	
	public Object load( XMLElement element ) throws ParamsException{
		try{
			ClassLoader cl = (ClassLoader)element.get(LOADER);
			Class<?> classToLoad = Class.forName((String)element.get(SRC), true, cl);
			return classToLoad.newInstance();
		}catch(Exception e){ throw new ParamsException(e.getMessage()); }			
	}
	
	public Object load( String className ) throws ParamsException{
		XMLElement elem = classes.get(className);
		if(elem == null) throw undefined(className);
		return load(elem);
	}
	
	/**
	 * Loads an Array class from an string
	 * @param className String with an Array class name following the Java convention.
	 * @return An Array class if <i>className</i> string defines one.
	 * @throws ClassNotFoundException If the <i>className</i> string does not define an array class.
	 */
	protected Class<?> loadArrayClass( String className ) throws ClassNotFoundException{
		if( className.charAt(0)=='[' ){
			Class<?> cl = loadArrayClass(className.substring(1));
			return Array.newInstance(cl, 0).getClass();
		}else{
			if( className.length()==1 ){
				switch(className.charAt(0)){
					case 'Z': return Boolean.TYPE;
					case 'B': return Byte.TYPE;
					case 'C': return Character.TYPE; 
					case 'D': return Double.TYPE;
					case 'F': return Float.TYPE;
					case 'I': return Integer.TYPE;
					case 'J': return Long.TYPE;
					case 'S': return Short.TYPE;
				}
			}else{
				if(className.length()>0 && className.charAt(0)=='L' && className.charAt(className.length()-1)==';')
					return loadClass( className.substring(1, className.length()-1) );
			}	
			throw new ClassNotFoundException(className);
		}
	}

	/**
	 * Loads a Class from an String representing the name of the class.
	 * @param className An String representing the class name.
	 * @return The class represented by the string <i>className</i>
	 * @throws ClassNotFoundException If the string <i>className</i> does not represents a valid class Name or a class.
	 */
	public Class<?> loadClass( String className ) throws ClassNotFoundException{
		if( className.charAt(0)=='[' ) return loadArrayClass(className);
		try{
			XMLElement element = classes.get(className);
			ClassLoader cl = (ClassLoader)element.get(LOADER);
			return Class.forName((String)element.get(SRC), true, cl);
		}catch(Exception e){}			
		return ClassLoader.getSystemClassLoader().loadClass(className);
	}	
	
/*	
   public Object load( String owner, String service ) throws PlugInException{
		XMLManifest m = services.get(service);
		if( m==null ) throw undefined(service);
		XMLElement elem = (XMLElement)m.get(owner);
		if(elem==null) throw undefined(owner);
		return load(elem);	
	}
	
	public Collection<String> services(){ return services.keys(); }
	
	public Collection<String> owners( String service ){ return services.get(service).keys(); } 
*/
}