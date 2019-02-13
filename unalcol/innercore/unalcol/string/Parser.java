package unalcol.string;

import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;

public class Parser {
	protected int pos;
	protected int length;
	protected String code;
	
	protected char c(){ return code.charAt(pos); }

	protected char spacedchar() throws Exception{
		space();
		if(end()) throw new ParamsException(InnerCore.EOI);
		return code.charAt(pos); 
	}
	
	protected boolean end(){ return pos==code.length(); }
	
	public void space(){
		int start = pos;
		while( !end() && (c()==' ' || c()=='\n' || c()=='\t' || c()=='\r') ){ pos++; }
		length = pos-start;
	}
	
	protected Object number() throws Exception{ 
		int start = pos;
		if( c()=='-' || c()=='+') pos++;
		if( end() ) throw new ParamsException(InnerCore.NF, code.substring(start,pos));
		if( c()=='0' ){ pos++; }
		else{
			if( !Character.isDigit(c()) ) throw new ParamsException(InnerCore.NF, code.substring(start,pos));
			pos++;
			while( !end() && Character.isDigit(c()) ) pos++;
		}
		this.length = pos-start;
		if( end() ) return Integer.parseInt(code.substring(start, pos));
			
		if( c()=='.' ){
			pos++;
			int counter=0;
			while( !end() && Character.isDigit(c()) ){
				counter++;
				pos++;
			}
			if( counter==0 ) throw new ParamsException(InnerCore.NF, code.substring(start,pos));
		}
		if( !end() && (c()=='e' || c()=='E') ){
			pos++;
			if( end() ) throw new ParamsException(InnerCore.NF, code.substring(start,pos));
			if(c()=='+' || c()=='-' ) pos++;
			if( end() ) throw new ParamsException(InnerCore.NF, code.substring(start,pos));
			int counter=0;
			while( !end() && Character.isDigit(c()) ){
				counter++;
				pos++;
			}
			if( counter==0 ) throw new ParamsException(InnerCore.NF, code.substring(start,pos));
		}
		this.length = pos-start;
		String s = code.substring(start, pos);
		try{ return Integer.parseInt(s); }catch(NumberFormatException e){}
		return Double.parseDouble(s);		
	}
	
	protected char inner() throws Exception{
		int start = pos;
		char c = c();
		if(c=='\\'){
			pos++;
			if(end()) throw new ParamsException(InnerCore.EOI);
			c = c();
			try{
				c = Util.escape_char(c);
				pos++;
			}catch(Exception ec){
				if(c=='u'){
					pos++;
					if(pos+4>=code.length()) throw new ParamsException(InnerCore.NOUNICODE, code.substring(pos));
					String uc = code.substring(pos,pos+4);
					try{
						int k=Integer.parseInt(uc,16);
						c = (char)k;
					}catch(NumberFormatException e){ throw new ParamsException(InnerCore.NOUNICODE, uc); }
					pos += 4;
				}else throw new ParamsException(InnerCore.ESC_CHAR, c); 
			}
		}else{
			pos++;
		}
		length = pos-start;
		return c;
	}
	protected char character() throws Exception{
		int start = pos;
		char c = c();
		if( c !='\'' ) throw new ParamsException(InnerCore.NOCHAR, code.substring(pos,pos+1));
		pos++;
		if(end()) throw new ParamsException(InnerCore.EOI);
		c = inner();
		if(c()!='\'') throw new ParamsException(InnerCore.NOCHAR, code.substring(start,pos));
		pos++;
		this.length = pos-start;
		return c;
	}
	
	protected String string() throws Exception{
		int start = pos;
		if( c()!='"' ) throw new ParamsException(InnerCore.NOSTRING, code.substring(pos,pos+1));
		pos++;
		StringBuilder sb = new StringBuilder();
		while( c()!='"' ){
			char c = inner();
			sb.append(c);
		}
		pos++;
		this.length = pos-start;
		return sb.toString();
	}
	
	public String string( String source, int beginIndex ) throws Exception{
		this.code = source;
		this.pos = beginIndex;
		return string();
	}
	
	public Object number( String source, int beginIndex ) throws Exception{
		this.code = source;
		this.pos = beginIndex;
		return number();
	}	

	public char character( String source, int beginIndex ) throws Exception{
		this.code = source;
		this.pos = beginIndex;
		return character();
	}
	
	public int consumed(){ return length; }		
}
