package unalcol.language.parser.bottom_up;

public class Token<T> {
	protected int consumed=0;
	protected int start=0;
	protected T element;
	
	public Token( int start, int consumed, T element ){
		this.start = start;
		this.consumed = consumed;
		this.element = element;
	}
	
	public int start(){ return start; }
	public int consumed(){ return consumed; }
	public T element(){ return element; }
}
