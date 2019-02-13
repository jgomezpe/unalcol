package unalcol.iterator;

public class PositionTrack {
    protected int offset;
    protected int src;
    
    //public PositionTrack( int offset ){ this(0,offset); }
    
    public PositionTrack( int src, int offset ){
    	this.src = src;
    	setOffset(offset); 
    }
    
    public PositionTrack(PositionTrack pos){ this.init(pos); }
   
    public void init( PositionTrack pos ){
    	this.src = pos.src;
    	this.offset = pos.offset; 
    }
    
    public void setSrc( int src ){ this.src = src; }
    public int src(){ return src; }

    public int offset(){ return offset; }
    public void setOffset( int offset ){ this.offset = offset; }
    public void shift(int delta){ this.offset+=delta; }
}