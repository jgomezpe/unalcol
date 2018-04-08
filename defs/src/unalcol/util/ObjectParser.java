package unalcol.util;

import java.util.Vector;

public class ObjectParser {
	public static String INVALID = "Invalid expression..";
	
	public static String store( Object obj ){
		if( obj instanceof Object[] ) return store((Object[])obj);
		if( obj instanceof String ) return store( (String)obj );
		return obj.toString();
	}
	
	public static String store( String str ){
		StringBuilder sb = new StringBuilder();
		sb.append('"');
		for( int i=0; i<str.length(); i++ ){
			char c = str.charAt(i);
			if( c=='[' || c==']' || c==',' || c=='/' || c=='"') sb.append('/'); 
			sb.append(c);
		}
		sb.append('"');
		return sb.toString();
	}
	
	public static String store( Object[] obj ){
		StringBuilder sb = new StringBuilder();
		sb.append('[');
		sb.append(store(obj[0]));
		for( int i=1; i<obj.length; i++ ){
			sb.append(',');
			sb.append(store(obj[i]));			
		}
		sb.append(']');
		return sb.toString();
	}
	
	public static Object load( String str ) throws Exception{
		if(str.charAt(0)=='"' ){
			StringBuilder sb = new StringBuilder();
			int i=1;
			while( i<str.length() && str.charAt(i)!='"' ){
				if(str.charAt(i)=='/'){
					i++;
					if( i==str.length() ) throw new Exception(INVALID);
					sb.append(str.charAt(i));
				}else sb.append(str.charAt(i));
				i++;
			}
			if( i<str.length()-1 ) throw new Exception(INVALID);
			return sb.toString();
		}
		if(str.equals("true")) return true;
		if(str.equals("false")) return false;
		try{ return Integer.parseInt(str); }catch(NumberFormatException e){}
		try{ return Long.parseLong(str); }catch(NumberFormatException e){}
		try{ return Double.parseDouble(str); }catch(NumberFormatException e){}
		throw new Exception(INVALID);
	}
	
	public static Object[] parse(String obj) throws Exception{
		if( obj.charAt(0) != '[' || obj.charAt(obj.length()-1) != ']') throw new Exception(INVALID);
		Vector<Object> v = new Vector<Object>();
		obj = obj.substring(1, obj.length()-1);
		int cl=0;
		int start=0;
		int i=0;
		while(i<obj.length()){
			switch(obj.charAt(i)){
				case '/' :
					i++;
					if(i==obj.length()) throw new Exception(INVALID);
					i++;
				break;	
				case '[' :
					if( start != i ) throw new Exception(INVALID);
					cl=1;
					i++;
					while(cl>0 && i<obj.length()){
						switch(obj.charAt(i)){
							case '[': cl++; break;
							case ']': cl--; break;
						}
						i++;
					}
					if( cl!=0 ) throw new Exception(INVALID);
					v.add(parse(obj.substring(start,i)));
					if(i<obj.length() && obj.charAt(i)==',') i++;
					start=i;
				break;
				case ']' : throw new Exception(INVALID);
				case ',' :
					if(start==i) throw new Exception(INVALID);
					v.add(load(obj.substring(start,i)));
					i++;
					start=i;
				break;	
				default: i++;
			}
		}
		if(start<obj.length()) v.add(load(obj.substring(start,i)));
		Object[] tobj = new Object[v.size()];
		for(i=0; i<v.size(); i++){ tobj[i] = v.get(i); }
		return tobj;
	}
	
/*	public static void main( String[] args ){
		String txt="[2345678901111,true,\"Hello\n world!\"]";
		try{ 
			Object[] obj = parse(txt);
			for( Object x:obj )
				System.out.println(x.getClass() +":"+x);
		}catch(Exception e){ e.printStackTrace(); }
	} */
}
