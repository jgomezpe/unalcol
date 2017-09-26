package unalcol.language.parser;

import unalcol.types.collection.array.Array;
import unalcol.types.collection.array.ImmutableArray;
import unalcol.types.collection.vector.Vector;
import unalcol.language.LanguageException;
import unalcol.language.Token;
import unalcol.language.Typed;

public class LookAHeadParser<T> implements Parser<T>{
	protected ImmutableArray<T> buffer;
	protected Array<Token<T>> tokens;
	protected int offset;
	
	protected Array<Rule<T>> rule;
	
	public LookAHeadParser(){}
	
	public LookAHeadParser(Array<Rule<T>> rule ){
	    this.rule = rule;
	}

	public boolean back(){
	    if( offset<=0 ) return false;
	    offset--;
	    return true;
	}
	
	public boolean go(){
		offset++;
		return offset<=tokens.size();
	}
	
	public boolean move( int offset ){
		if( offset>=0 && offset<tokens.size() ){
			this.offset = offset;
			return true;
		}
		return false;
	}
	
	public int pos(){ return offset; }

	public ImmutableArray<T> buffer(){ return this.buffer; }
	
	public Token<T> get(){
		return tokens.get(offset);
	}
	
	/*
	public String getStr(){
		return new String(get().get(buffer));
	}*/
	
	public int type(){
		return get().type();
	}
	
	public int start(){
		return get().start();
	}
	
	public int length(){
		return get().length();
	}
	
	public int remain(){
	    return tokens.size()-offset;
	}

	public boolean hasMore(){
		return offset < tokens.size(); 
	}
	
/*	public boolean checkword( byte[] word ){
		return Util.checkword(buffer, start(), word);
	} */
	
	public Typed next(){
		Typed o = null;
		int k = 0;
		while( k<rule.size() && o==null ){
			o = rule.get(k).get(this);
			k++;
		}
		return o;
	}
	
	public Array<Typed> apply( Array<Token<T>> tokens, int offset, ImmutableArray<T> buffer ) throws LanguageException{
		this.offset = offset;
		this.tokens = tokens;
		this.buffer = buffer;
		Vector<Typed> obj = new Vector<Typed>();
		while(hasMore()){		    
			Typed o = next(); 
			if( o != null ){
				obj.add(o);
			}else{
				Token<T> t = tokens.get(offset);
				throw new LanguageException("Unexpected token <"+t+"> at position <" + t.start());
			}		   
		}
		return obj;
	}
}