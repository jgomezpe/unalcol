package unalcol.io;

public class Position {
    protected int offset;
    public Position(){ this(0); }
    public Position( int offset ){ setOffset(offset); }
    public Position(Position pos){ this.offset=pos.offset; }
    public int offset(){ return offset; }
    public void setOffset( int offset ){ this.offset = offset; }
    public void shift(int delta){ this.offset+=delta; }
}