package unalcol.agents.examples.games.sokoban;

public class Board {
    public static final int EMPTY = 0;
    public static final int MARK = 1;
    public static final int BLOCK = 2;
    public static final int WALL = 4;
    public static final int GRASS = 8;

    protected int[][] data;
    
    public Board( int[][] data ){ this.data = data; }
    
    public void xor( int i, int j, int value ){
	data[i][j]  ^= value;
    }
    
    public int rows(){ return data.length; }
    public int columns(){ return data[0].length; }
}
