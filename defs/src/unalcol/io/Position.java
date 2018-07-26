package unalcol.io;

public class Position {
    protected int offset;
    protected int src;
    
    //public Position( int src ){ this(src, 0); }
    
    public Position( int src, int offset ){
    	this.src = src;
    	setOffset(offset); 
    }
    
    public Position(Position pos){ this.init(pos); }
   
    public void init( Position pos ){
    	this.src = pos.src;
    	this.offset = pos.offset; 
    }
    
    public void setSrc( int src ){ this.src = src; }
    public int src(){ return src; }

    public int offset(){ return offset; }
    public void setOffset( int offset ){ this.offset = offset; }
    public void shift(int delta){ this.offset+=delta; }
}