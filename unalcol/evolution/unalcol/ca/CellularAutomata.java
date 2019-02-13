package unalcol.ca;

/**
 * <p>Title: CellularAutomata</p>
 * <p>Description: The Conway's Game of Life Cellular Automata.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class CellularAutomata {
    /**
     * Cells state
     */
    protected boolean[][] state;

    /**
     * Creates a Conway's game of life (toroidal)
     * @param n Number of rows
     * @param m Number of columns
     * @param aliveProb Probability of being alive in the initial state of the CA
     */
    public CellularAutomata( int n, int m, double aliveProb ){
        state = new boolean[n][m];
        init( aliveProb );
    }

    /**
     * Inits the state of the CA
     * @param aliveProb Probability of being alive in the initial state of the CA
     */
    public void init( double aliveProb ){
        int n = state.length;
        int m = state[0].length;
        for( int i=0; i<n; i++ ){
            for( int j=0; j<m; j++ ){
                state[i][j] =( Math.random() <= aliveProb);
            }
        }
    }

    /**
     * Converts a cell position to an integer value
     * @param i Row
     * @param j Column
     * @return An integer identifying the cell
     */
    public int id( int i, int j ){
        return i*state[0].length + j;
    }

    /**
     * Gets the cell's neighborhood given the position represented by an integer
     * @param id Cell's id
     * @return The neighborhood of a given cell
     */
    public int[][] neighborhood( int id ){
        return neighboor(id/state[0].length, id%state[0].length);
    }

    /**
     * Gets the cell's neighborhood
     * @param i Cell's row
     * @param j Cell's column
     * @return The neighborhood of a given cell
     */
    public int[][] neighboor( int i, int j ){
        int n = state.length;
        int m = state[0].length;
        int[][] neigboors = new int[10][2];
        int c = 0;
        for( int k=-1; k<2; k++ ){
            int row = (i+k+n)%n;
            for( int l=-1; l<2; l++ ){
                int column = (j+l+m)%m;
                if( state[row][column] ){
                    neigboors[c][0] = row;
                    neigboors[c][1] = column;
                    c++;
                }
            }
        }
        neigboors[c][0] = -1;
        return neigboors;
    }

    /**
     * Determines the next cell state
     * @param i Cell's row
     * @param j Cell's column
     * @return New state of the cell
     */
    public boolean next( int i, int j ){
        int c = 0;
        int[][] n = neighboor(i, j);
        while( n[c][0] >= 0 ){
            c++;
        }
        return( 3<=c && c<=4 );
    }

    /**
     * Number of cells currently alive
     * @return Number of cells currently alive
     */
    public int aliveCells(){
        int c = 0;
        for( int i=0; i<state.length; i++ ){
            for( int j=0; j<state[i].length; j++ ){
                c += state[i][j]?1:0;
            }
        }
        return c;
    }

    /**
     * Simulates the ca una step in a synchronous mode
     */
    public void simulate(){
        boolean[][] new_state = state.clone();
        int n = state.length;
        int m = state[0].length;
        for( int i=0; i<n; i++ ){
            for( int j=0; j<m; j++ ){
                new_state[i][j] = next(i,j);
            }
        }
        state = new_state;
    }

    /**
     * Gest the current state of a cell
     * @param i Cell's row
     * @param j Cell's column
     * @return Cell's state
     */
    public boolean state( int i, int j ){
        return state[i][j];
    }

    /**
     * gets the cells state
     * @param id Cell's id
     * @return Cell's state
     */
    public boolean state( int id ){
        return state[id/state[0].length][id%state[0].length];
    }

    /**
     * Gets a string version of the CA
     * @return String version of the CA
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        int n = state.length;
        int m = state[0].length;
        for( int i=0; i<n; i++ ){
            for( int j=0; j<m; j++ ){
                sb.append(state[i][j]?"1":"0");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main( String[] args ){
        CellularAutomata a = new CellularAutomata(20, 20, 0.3);
        System.out.print(a.toString());
        System.out.println("********************************");
        for( int i=0; i<10; i++ ){
            a.simulate();
            System.out.print(a.toString());
            System.out.println("********************************");
        }
    }
}
