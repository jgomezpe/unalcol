package unalcol.i18n;

import unalcol.json.JSONParser;
import unalcol.types.collection.keymap.LowLevelKeyMap;
import unalcol.util.FileResource;

public class I18N{
	public static final String MSG = "msg";
	public static final String i18n = "i18n";
	public static final String USES = "uses";
	public static final String PACK = "pack";
	protected static LowLevelKeyMap<String, String> msgs = new LowLevelKeyMap<String,String>();
	protected static String lang=null;

	public static boolean setLanguage( String language ){
		if( language.equals(lang) ) return false;
		lang = language;
		return true;
	}
	
	@SuppressWarnings("unchecked")
	protected static void load(String pack, String json){
		JSONParser p = new JSONParser();
		try {
			Object obj = p.parse(json);
			if( obj instanceof LowLevelKeyMap ){
				LowLevelKeyMap<String, String> extraMsgs = (LowLevelKeyMap<String, String>)obj;
				String extra = extraMsgs.get(USES);
				if( extra != null ){
					String[] packs = extra.split(",");
					for( int i=packs.length-1; i>=0; i-- ) load( packs[i] );
				}
				msgs.merge(extraMsgs);
			}
		}catch (Exception e){}
	}
	
	protected static void load( String pack ){
		if( msgs.get(PACK+pack) == null ){
			String json = FileResource.txt( lang+'-'+pack+'.'+i18n, FileResource.resources+i18n+'/', i18n+'/', true);
			if( json != null ) load(pack, json);
			msgs.set(PACK+pack, pack);
		}
	}

	public static boolean use(String pack){
		if( lang == null ) return false;
		msgs.clear();
		load( pack );
		return true;
	}
	
	public static String get(String key){
		String msg = msgs.get(key);
		if( msg==null ) return key;
		return process(msg);
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