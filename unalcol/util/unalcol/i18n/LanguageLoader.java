package unalcol.i18n;

import unalcol.collection.KeyMap;
import unalcol.json.JSONParser;
import unalcol.util.FileResource;

public class LanguageLoader extends Language{
	public static final String MSG = "msg";
	public static final String i18n = "i18n";
	public static final String USES = "uses";
	public static final String PACK = "pack";

	public LanguageLoader( String name ){ super(name); }
	
	@SuppressWarnings("unchecked")
	protected void load(String pack, String json){
		JSONParser p = new JSONParser();
		try {
			Object obj = p.parse(json);
			if( obj instanceof KeyMap ){
				KeyMap<String, String> extraMsgs = (KeyMap<String, String>)obj;
				String extra = extraMsgs.get(USES);
				if( extra != null ){
					String[] packs = extra.split(",");
					for( int i=packs.length-1; i>=0; i-- ) load( packs[i] );
				}
			//	msgs.merge(extraMsgs);
			}
		}catch (Exception e){}
	}
	
	protected void load( String pack ){
		if( !codes.valid(PACK+pack) ){
			String json = FileResource.txt( name+'-'+pack+'.'+i18n, FileResource.resources+i18n+'/', i18n+'/', true);
			if( json != null ) load(pack, json);
			codes.set(PACK+pack, pack);
		}
	}

	public boolean use(String pack){
		if( name == null ) return false;
		codes.clear();
		load( pack );
		return true;
	}
	
	public String get(String key){
		try{
			return codes.get(key);
			//return process(msg);
		}catch(Exception e){ return key; }
	}
	
	/*
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
*/		
}