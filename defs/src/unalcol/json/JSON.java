package unalcol.json;

import unalcol.clone.Cloneable;
import unalcol.types.collection.keymap.LowLevelKeyMap;
import unalcol.types.collection.vector.Vector;
import unalcol.util.ParseUtil;

public class JSON extends LowLevelKeyMap<String, Object> implements Cloneable{
	@Override
	public Object clone(){
		JSON json = new JSON();
		for( String key:keys() ){
			Object obj = get(key);
			Cloneable c = Cloneable.cast(obj);
			json.set(key, c.clone());
		}
		return json;
	}
	
	public double getReal( String tag ){
		Double x = (Double)get(tag);
		if( x==null ) return 0.0;
		return x;
	}
	
	public int getInt( String tag ){
		Integer i = (Integer)get(tag);
		if( i==null ) return 0;
		return i;
	}
	
	public boolean getBool( String tag ){
		Boolean i = (Boolean)get(tag);
		if( i==null ) return false;
		return i;
	}
	
	public String toString(Object obj){
		if( obj instanceof String )	return ParseUtil.encode((String)obj);
		else if( obj instanceof Vector ){
			StringBuilder sb = new StringBuilder();
			sb.append('[');
			@SuppressWarnings("unchecked")
			Vector<Object> v = (Vector<Object>)obj;
			boolean prComma = false;
			for( Object x : v ){
				if( prComma ) sb.append(',');
				sb.append(toString(x));
				prComma = true;
			}
			sb.append(']');
			return sb.toString();
		}else return obj.toString();		
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append('{');
		boolean prComma = false;
		for( String key : this.keys() ){
			if( prComma ) sb.append(',');
			sb.append(ParseUtil.encode(key));
			sb.append(':');
			sb.append(toString(this.get(key)));
			prComma = true;
		}
		sb.append('}');
		return sb.toString();
	}	
}