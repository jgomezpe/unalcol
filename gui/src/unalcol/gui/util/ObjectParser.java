package unalcol.gui.util;

import java.util.Vector;

public class ObjectParser {
	
	public static Object parse(String obj) throws Exception{
		if( obj.charAt(0) != '[' || obj.charAt(obj.length()-1) != ']') throw new Exception("Invalid object representation..");
		Vector<Object> v = new Vector<Object>();
		obj = obj.substring(1, obj.length()-1);
		int cl=0;
		int start=0;
		int i=0;
		while(i<obj.length()){
			switch(obj.charAt(i)){
				case '[' :
					if( start != i ) throw new Exception("Invalid expression..");
					cl=1;
					i++;
					while(cl>0 && i<obj.length()){
						switch(obj.charAt(i)){
							case '[': cl++; break;
							case ']': cl--; break;
						}
						i++;
					}
					if( cl!=0 ) throw new Exception("Invalid object representation..");
					v.add(parse(obj.substring(start,i)));
					if(i<obj.length() && obj.charAt(i)==',') i++;
					start=i;
				break;
				case ']' : throw new Exception("Invalid object representation..");
				case ',' :
					if(start==i) throw new Exception("Invalid object representation..");
					String s = obj.substring(start,i);
					v.add(s);
					i++;
					start=i;
				break;	
				default: i++;
			}
		}
		if(start<obj.length()) v.add(obj.substring(start,i));
		return v;
	}
	
	public static void main( String[] args ){
		String txt="[[2]]";
		try{ 
			System.out.println(parse(txt));
		}catch(Exception e){ e.printStackTrace(); }
	}
}
