package unalcol.i18n;

import unalcol.json.JSON;
import unalcol.json.JSONParser;
import unalcol.util.FileResource;

public class LanguageLoader extends Language{
	public static final String MSG = "msg";
	public static final String i18n = "i18n";
	public static final String USES = "uses";
	public static final String PACK = "pack";

	public LanguageLoader( String name ){ super(name); }
	
	protected void load(ClassLoader loader, String pack, String json){
		JSONParser p = new JSONParser();
		try {
			JSON obj = (JSON)p.parse(json);
			try{
				String extra = obj.getString(USES);
				String[] packs = extra.split(",");
				for( int i=packs.length-1; i>=0; i-- ) load( loader, packs[i] );
			}catch(Exception e){}
			codes.merge(obj);
		}catch (Exception e){}
	}
	
	protected void load( ClassLoader loader, String pack ){
		if( !codes.valid(PACK+pack) ){
			String json = FileResource.txt( loader,  name+'-'+pack+'.'+i18n, FileResource.resources+i18n+'/', i18n+'/', true);
			if( json != null ) load(loader, pack, json);
			codes.set(PACK+pack, pack);
		}
	}

	public boolean use(String pack){ return use(LanguageLoader.class.getClassLoader(), pack); }
	
	public boolean use(ClassLoader loader, String pack){
		if( name == null ) return false;
		codes.clear();
		load( loader, pack );
		return true;
	}
	
	/*
	public String get(String key){
		try{
			return codes.get(key);
			//return process(msg);
		}catch(Exception e){ return key; }
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
*/		
}