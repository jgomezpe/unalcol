package unalcol.language;

import unalcol.types.collection.array.ImmutableArray;

public class Token<T> extends Typed{
	protected int offset;
	protected int length;
	protected ImmutableArray<T> buffer;
	
	public Token( int type, int offset, int length, ImmutableArray<T> buffer ){
		super(type);
		this.offset = offset;
		this.length = length;
		this.buffer = buffer;
	}
	
	public T[] get(){
		@SuppressWarnings("unchecked")
		T[] dest = (T[])new Object[length];
		for( int i=0; i<length; i++ ) dest[i] = buffer.get(i+offset);
		return dest;
	}
	
	public int length(){ return length; }
	
	public int start(){ return offset; }		
}