package unalcol.language.lexer;

import unalcol.language.Typed;
import unalcol.types.collection.array.ImmutableArray;

public class Lexeme<T> extends Typed{
	protected ImmutableArray<T> buffer;
	protected int offset;
	
	public Lexeme( int type ){
		super( type );
	}
	
    public int get(ImmutableArray<T> buffer, int offset ){
		this.buffer = buffer;
		this.offset = offset;
		return 0;
    }
        
    public boolean hasMore(){
    	return offset < buffer.size(); 
    }

/*	public boolean isLetter(){
		return Character.isLetter((char)buffer[offset]);
	}
	
	public boolean isDigit(){
		return Character.isDigit((char)buffer[offset]);
	} */
}