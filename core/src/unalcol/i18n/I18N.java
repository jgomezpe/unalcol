package unalcol.i18n;

import unalcol.xml.XMLDocument;
import unalcol.xml.XMLManifest;
import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.util.FileResource;

public class I18N{
	public static final String MSG = "msg";
	public static final String i18n = "i18n";
	public static final String parent = "parent";
	protected static HTKeyMap<String, XMLManifest> languages = new HTKeyMap<String, XMLManifest>();
	protected static XMLManifest current=null;

	protected static XMLManifest load(String xmlStr){ return new XMLManifest(i18n, new XMLDocument(xmlStr)); }
	
	protected static boolean load( String language, String parent ){
		String tLanguage = ( parent != null )? language + '-' + parent : language; 
		String config = FileResource.txt( tLanguage+'.'+i18n, FileResource.resources+i18n+'/', i18n+'/', true);
		if( config != null ) return add(language, new XMLDocument(config));
		return false;
	}
	
	public static boolean use(String language){
		boolean flag = languages.valid(language) || load( language, null );
		if( flag ) current = languages.get(language);
		return flag;
	}
	
	public static boolean add(String language, XMLDocument doc){
		XMLManifest manifest = new XMLManifest(i18n, doc);
		XMLManifest cmanifest = languages.get(language);
		if(cmanifest==null){
			languages.set(language, manifest);
			cmanifest = manifest;
		}else cmanifest.merge(manifest);
		String p = (String)doc.get(parent);
		if( p!=null ) load(language, p);
		return true; 
	}
	
	public static boolean add(String language, String xmlStr){ return add(language, new XMLDocument(xmlStr)); }
	
	public static boolean remove(String language) { return languages.remove(language);	}
	
	public static String get(String key){
		if( current!= null && current.valid(key) ) return process((String)current.get(key).get(MSG));
		return key;
	}	

	public static String process( String message ){
		StringBuilder sb = new StringBuilder();
		int i=0; 
		while( i<message.length() ){
			char c = message.charAt(i); 
			if(c=='\\'){
				i++;
				if(i<message.length()){
					c = message.charAt(i);
					switch( c ){
						case '\\': sb.append('\\'); break;
						case 'n':  sb.append('\n'); break;
						case 't':  sb.append('\t'); break;
						default: sb.append('\\'); sb.append(c); 
					}
					i++;
				}else{
					sb.append(c);
				}
			}else{
				sb.append(c);
				i++;
			}
		}
		return sb.toString();
	}
}