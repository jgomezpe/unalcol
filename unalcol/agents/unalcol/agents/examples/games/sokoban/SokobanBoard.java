package unalcol.agents.examples.games.sokoban;

public class SokobanBoard {
    public static final int EMPTY = 0;
    public static final int MARK = 1;
    public static final int BLOCK = 2;
    public static final int WALL = 4;
    public static final int GRASS = 8;

    private int[][] data;
    
    public SokobanBoard(int n){
    	this.data = new int[n][n];
    	for( int i=0; i<n; i++ )
    		for( int j=0; j<n; j++ )
    			this.data[i][j] = SokobanBoard.GRASS;
    }
    
    public SokobanBoard( int[][] data ){ this.data = data; }
    
    public void reset( int x, int y ){ data[x][y]=0; }
    
    public void xor( int x, int y, int value ){
	data[x][y]  ^= value;
    }
    
    public int get(int x, int y){ return data[x][y]; }
    
    public int rows(){ return data.length; }
    public int columns(){ return data[0].length; }
}
