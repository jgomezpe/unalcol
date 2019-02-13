package unalcol.json;

import unalcol.clone.Cloneable;
import unalcol.collection.keymap.HashMap;
import unalcol.string.Util;

public class JSON extends HashMap<String, Object> implements Cloneable{
	@Override
	public Object clone(){
		JSON json = new JSON();
		try{
			for( String key:keys() ){
				Object obj = get(key);
				Cloneable c = Cloneable.cast(obj);
				json.set(key, c.clone());
			}
		}catch(Exception e){}	
		return json;
	}
	
	public double getReal( String tag ){ try{ return (Double)get(tag); }catch(Exception e){ return 0.0; } }
	
	public int getInt( String tag ){ try{ return (Integer)get(tag); }catch(Exception e){ return 0; } } 
	
	public boolean getBool( String tag ){ try{ return (Boolean)get(tag); }catch(Exception e){ return false; } }
	
	public boolean storable(Object obj){
		if( obj == null ) return true;
		if( obj instanceof Object[] ){
			Object[] v = (Object[])obj;
			int i=0;
			while( i<v.length && storable(v[i]) ){ i++; }
			return i==v.length;
		}
		return ( obj instanceof String || obj instanceof Integer || obj instanceof Double ||  obj instanceof Boolean || obj instanceof JSON );
	}
	
	@Override
	public boolean set(String key, Object obj ){
		if( storable(obj ) ) return super.set(key, obj);
		return false;
	} 
	
	protected String toString(Object obj){
		if( obj instanceof String )	return Util.store((String)obj);
		else if( obj instanceof Object[] ){
			StringBuilder sb = new StringBuilder();
			sb.append('[');
			Object[] v = (Object[])obj;
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
		try{
			boolean prComma = false;
			for( String key : this.keys() ){
				if( prComma ) sb.append(',');
				sb.append(Util.store(key));
				sb.append(':');
				sb.append(toString(this.get(key)));
				prComma = true;
			}
		}catch( Exception e ){}	
		sb.append('}');
		return sb.toString();
	}	
}