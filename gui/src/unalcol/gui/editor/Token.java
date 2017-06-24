package unalcol.gui.editor;

public class Token {
	public static final int UNDEFINED = 0;
	
	protected int type;
	protected int offset;
	protected int length;
	
	public Token( int type, int offset, int length ){
		this.type = type;
		this.offset = offset;
		this.length = length;
	}

	public Token( int offset, int length ){
		this(UNDEFINED,offset,length);
	}

	public int type(){ return type; }
	public int offset(){ return offset; }
	public int length(){ return length; }
	public int endOffset(){ return offset+length; }
	public void shift( int shift ){ offset += shift; }
}
