package unalcol.json;

import unalcol.clone.Cloneable;
import unalcol.collection.keymap.HashMap;
import unalcol.string.Util;

public class JSON extends HashMap<String, Object> implements Cloneable{
	public JSON(){}
	
	public JSON( JSON source ){
		try{
			for( String key:source.keys() ){
				Object obj = source.get(key);
				Cloneable c = Cloneable.cast(obj);
				set(key, c.clone());
			}
		}catch(Exception e){}	
	}
	
	@Override
	public Object clone(){ return new JSON(this); }
	
	public double getReal( String tag ){
		try{
			Object obj = get(tag);
			if( obj instanceof Double ) return (Double)obj;
			if( obj instanceof Integer ) return (Integer)obj;
		}catch(Exception e){}
		return 0;
	}
	
	public int getInt( String tag ){ try{ return (Integer)get(tag); }catch(Exception e){ return 0; } } 
	
	public boolean getBool( String tag ){ try{ return (Boolean)get(tag); }catch(Exception e){ return false; } }

	public String getString( String tag ){ try{ return (String)get(tag); }catch(Exception e){ return null; } }

	public Object[] getArray( String tag ){ try{ return (Object[])get(tag); }catch(Exception e){ return null; } }

	public int[] getIntArray( String tag ){ 
		Object[] a = getArray(tag);
		int[] x = null;
		if( a!=null ){
			x = new int[a.length];
			try{ for(int i=0; i<a.length; i++ ) x[i] = (Integer)a[i]; }catch(Exception e){ x = null; }
		} 
		return x;
	}
	public double[] getRealArray( String tag ){
		Object[] a = getArray(tag);
		double[] x = null;
		if( a!=null ){
			x = new double[a.length];
			try{ for(int i=0; i<a.length; i++ ) x[i] = (Double)a[i]; }catch(Exception e){ x = null; }
		} 
		return x;
	}

	public JSON getJSON( String tag ){ try{ return (JSON)get(tag); }catch(Exception e){ return null; } }

	
	public boolean storable(Object obj){
		if( obj == null || obj instanceof double[] || obj instanceof int[]) return true;
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
		if( storable(obj ) ){
			if( obj instanceof double[] ){
				double[] a = (double[])obj;
				Object[] x = new Object[a.length];
				for( int i=0; i<a.length; i++ ) x[i] = a[i];
				obj = x;
			}else if( obj instanceof int[] ){
				int[] a = (int[])obj;
				Object[] x = new Object[a.length];
				for( int i=0; i<a.length; i++ ) x[i] = a[i];
				obj = x;
			}
			return super.set(key, obj);
		}
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