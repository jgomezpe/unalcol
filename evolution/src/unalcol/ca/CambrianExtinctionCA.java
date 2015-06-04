package unalcol.ca;

/**
 * <p>Title: CambrianExtictionCA</p>
 * <p>Description: The Cambrian Extintion Cellular Automata as
 * proposed by Cantor and Gomez in , Proceedings of WCCI 2010.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class CambrianExtinctionCA extends CellularAutomata{

   /**
     * Creates a Conway's game of life (toroidal) with cambriam and massive extinctions
     * @param n Number of rows
     * @param m Number of columns
     * @param aliveProb Probability of being alive in the initial state of the CA
     */
    public CambrianExtinctionCA( int n, int m, double aliveProb ){
        super( n, m, aliveProb );
    }

    /**
     * generates a cambrian explosion
     */
    public void cambrian(){
        int i = (int)(Math.random() * state.length);
        int j = (int)(Math.random() * state[i].length);
        cambrian( i, j );
    }

    /**
     * Generates a cambrian explosion around the given cell
     * @param i Cell's row
     * @param j Cell's column
     */
    public void cambrian( int i, int j ){

        extinction( i, j );

        int n = state.length;
        int m = state[0].length;

        state[(i-1+n)%n][(j-1+m)%m] = false;
        state[(i-1+n)%n][j] = true;
        state[(i-1+n)%n][(j+1+m)%m] = true;

        state[i][(j-1+m)%m] = true;
        state[i][j] = true;
        state[i][(j+1+m)%m] = false;

        state[(i+1+n)%n][(j-1+m)%m] = false;
        state[(i+1+n)%n][j] = true;
        state[(i+1+n)%n][(j+1+m)%m] = false;
    }


    /**
     * Generates a massive extinction
     */
    public void extinction(){
        int i = (int)(Math.random() * state.length);
        int j = (int)(Math.random() * state[i].length);
        extinction( i, j );
    }

    /**
     * Generates a massive extinction around the given cell
     * @param i Cell's row
     * @param j Cell's column
     */
    public void extinction(int i, int j ){
        int n = state.length;
        int m = state[0].length;
        for( int k=-2; k<3; k++ ){
            int row = (i+k+n)%n;
            for( int l=-2; l<3; l++ ){
                int column = (j+k+m)%m;
                state[row][column] = false;
            }
        }
    }

    /**
     * Simulates the ca with cambrian explosions and massive extinctions una step in a synchronous mode
     */
    public void simulate(){
        int s = aliveCells();
        if( Math.random() < (double)s/(double)(state.length* state[0].length) ){
            extinction();
        }
        if( Math.random() > (double)s/(double)(state.length* state[0].length) ){
            cambrian();
        }
        super.simulate();
    }
}