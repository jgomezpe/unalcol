package unalcol.agents.examples.sudoku;

import java.util.Random;

import unalcol.collection.Vector;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class Board {
    /**
     * The basic random generator
     */
    protected static Random g = new Random();

    protected int DIGITS=SudokuLanguage.DIGITS;
    protected int SQRT_DIGITS = (int)(Math.sqrt( SudokuLanguage.DIGITS ) + 0.1);

    protected int[][] board = new int[DIGITS][DIGITS];

    protected void init(){
        int[] set = new int[DIGITS];
        for( int i=0; i<DIGITS; i++ ){
            set[i] = i+1;
        }
        for( int i=0; i<DIGITS; i++ ){
            permutation( set );
            setRow( i, board, set );
        }
    }

    public Board( int[][] _board ){
      board = _board;
    }

    public Board(){
        init();
        int counter = 0;
        int[][] c = conflicts();
        while( !valid( c ) ){
            if( counter == 10*DIGITS ){
                init();
                counter = 0;
            }
            update(c);
            c = conflicts();
            counter++;
        }

/*
        permutation( set );
        int SQRT_DIGITS = DIGITS/3;
        int k =0;
        for( int i=0; i<SQRT_DIGITS; i++ ){
          for( int j=0; j<SQRT_DIGITS; j++ ){
              setRow(k, board, set);
              for (int m = 0; m < SQRT_DIGITS; m++) {
                  rotate(set);
              }
              k++;
          }
          rotate( set );
        }

        for( int i=0; i<2*DIGITS; i++ ){
            permutation(board);
        }
*/
    }


    public Board( Board source ){
        for( int i=0; i<DIGITS; i++ ){
            for( int j=0; j<DIGITS; j++ ){
                board[i][j] = source.board[i][j];
            }
        }
    }

    protected Board( int emptyPlaces ){
        this();
        int n = DIGITS*DIGITS;
        Vector<Integer> place = new Vector<Integer>();
        for( int i=0; i<n; i++ ){
            place.add( new Integer(i) );
        }

        try{
	        for( int k=0; k<emptyPlaces; k++ ){
	            int m = (int)(n*g.nextDouble());
	            int tmp = ((Integer)place.get(m)).intValue();
	            place.del(m);
	            m = tmp;
	            int i = m / DIGITS;
	            int j = m % DIGITS;
	            board[i][j] = 0;
	            n--;
	        }
        }catch(Exception e){}
    }

    public int emptyPlaces(){
      int count = 0;
      for( int i=0; i<DIGITS; i++ ){
        for( int j=0; j<DIGITS; j++ ){
          if( board[i][j] == 0 ){ count++; }
        }
      }
      return count;
    }

    /**
     * Creates a random permutation of the given set
     * @param set Set to be permuted
     */
    public void permutation(int[] set) {
      int i, j, k;
      int n = set.length;
      int temp;

      for (i = 0; i < n; i++){
        j = (int)(n*g.nextDouble());
        k = (int)(n*g.nextDouble());
        temp = set[j];
        set[j] = set[k];
        set[k] = temp;
      }
    }

    public void permutation( int[][] board ){
       int[] set = new int[6];
       for( int i=0; i<set.length; i++ ){
           set[i] = board[i/3][i%3];
       }
       permutation(set);
       for( int i=0; i<set.length; i++ ){
          board[i/3][i%3]  = set[i];
       }

       for( int i=0; i<3; i++ ){
           for( int j=0; j<3; j++ ){
               if( i+j > 0 ){

               }
           }
       }
    }

    public void permutation2( int[][] board ){
        int n_permutation = 2 * DIGITS;
        int perm = g.nextInt(n_permutation);
        System.out.println( perm );
        boolean horz_perm = (perm >= DIGITS);
        perm %= DIGITS;
        int group = perm / SQRT_DIGITS;
        perm %= SQRT_DIGITS;
        int x=0, y=1;
        switch( perm ){
          case 0:
            x = 0;
            y = 1;
          break;
          case 1:
            x = 0;
            y = 2;
          break;
          case 2:
            x = 1;
            y = 2;
          break;
        }
        x += group*SQRT_DIGITS;
        y += group*SQRT_DIGITS;
        if( horz_perm ){
            for (int k = 0; k < DIGITS; k++) {
                int temp = board[x][k];
                board[x][k] = board[y][k];
                board[y][k] = temp;
            }
        }else{
            for (int k = 0; k < DIGITS; k++) {
                int temp = board[k][x];
                board[k][x] = board[k][y];
                board[k][y] = temp;
            }
        }
    }

    public void rotate( int[] set ){
      int x = set[0];
      int n = set.length;
      for( int i=0; i<n-1; i++ ){
          set[i] = set[i+1];
      }
      set[n-1] = x;
    }

    public void setRow( int k, int[][] board, int[] set ){
        int n = set.length;
        for( int j=0; j<n; j++ ){
            board[k][j] = set[j];
        }
    }

    public int row_counter( int[][] board, int row, int value ){
        int counter = 0;
        for( int i=0; i<DIGITS; i++ ){
            if( board[row][i] == value ){ counter++; }
        }
        return counter;
    }

    public int column_counter( int[][] board, int column, int value ){
        int counter = 0;
        for( int i=0; i<DIGITS; i++ ){
            if( board[i][column] == value ){ counter++; }
        }
        return counter;
    }

    public int group_counter( int[][] board, int group, int value ){
        int row = (SQRT_DIGITS)*(group / SQRT_DIGITS);
        int column = (SQRT_DIGITS)*(group % SQRT_DIGITS);
        int counter = 0;
        for( int i=0; i<SQRT_DIGITS; i++ ){
            for( int j=0; j<SQRT_DIGITS; j++ ){
                if (board[row+i][column+j] == value) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public int[][] conflicts( int[][] board ){
        int[][] c = new int[DIGITS][DIGITS];
        for( int i=0; i<DIGITS; i++ ){
            for( int j=0; j<DIGITS; j++ ){
                int group = SQRT_DIGITS*(i/SQRT_DIGITS) + j/SQRT_DIGITS;
                if( board[i][j] > 0 ){
                    c[i][j] = row_counter(board, i, board[i][j]) - 1 +
                              column_counter(board, j, board[i][j]) - 1 +
                              group_counter(board, group, board[i][j]) - 1;
                }else{
                    c[i][j] = 0;
                }
            }
        }
        return c;
    }

    public int[][] conflicts(){
        return conflicts( board );
    }

    public int cost( int[][] c ){
        int counter = 0;
        for( int i=0; i<DIGITS; i++ ){
            for (int j = 0; j<DIGITS; j++) {
                counter += c[i][j];
            }
        }
        return counter;
    }

    public boolean valid(){
        return valid( conflicts() );
    }

    public boolean solved(){
        boolean flag = true;
        for( int i=0; i<DIGITS && flag; i++ ){
            for( int j=0; j<DIGITS && flag; j++ ){
                flag = (board[i][j]>0);
            }
        }
        return flag && valid();
    }

    public boolean valid( int[][] c ){
        boolean flag = true;
        for( int i=0; i<DIGITS && flag; i++ ){
            for( int j=0; j<DIGITS && flag; j++ ){
                flag = (c[i][j]==0);
            }
        }
        return flag;
    }

    public int get( int row, int column ){
        return board[row][column];
    }

    public void hardSet( int row, int column, int value ){
      board[row][column] = value;
    }

    public boolean set( int row, int column, int value ){
        boolean flag = (0 <= row && row < DIGITS &&
                        0 <= column && column < DIGITS &&
                        0 < value && value <= DIGITS &&
                        board[row][column] == 0);
        if( flag ){
            board[row][column] = value;
            flag = valid();
            if( !flag ){ board[row][column] = 0; }
        }
        return flag;
    }


    public void update( int[][] c ){
        Vector<int[]> v = new Vector<int[]>();
        int high = 0;
        for( int i=0; i<DIGITS; i++ ){
            for( int j=0; j<DIGITS; j++ ){
                if( c[i][j] > high ){
                    high = c[i][j];
                    v.clear();
                    v.add( new int[]{i,j} );
                }else{
                    if( c[i][j] == high ){
                        v.add(new int[] {i, j});
                    }
                }
            }
        }
        if( v.size() > 0 ){
        	try{
	            int index = (int)(v.size()*g.nextDouble());
	            int[] val = (int[])v.get(index);
	            int row = val[0];
	            int column = val[1];
	            int[] new_c = new int[DIGITS];
	            int temp;
	            for( int j=0; j<DIGITS; j++ ){
	                temp = board[row][j];
	                board[row][j] = board[row][column];
	                board[row][column] = temp;
	                int[][] nc = conflicts(board);
	                new_c[j] = cost( nc );
	                temp = board[row][j];
	                board[row][j] = board[row][column];
	                board[row][column] = temp;
	            }
	            int low = 1000;
	            Vector<Integer> vi = new Vector<Integer>();
	            for( int j=0; j<DIGITS; j++ ){
	                if( new_c[j] < low ){
	                    low = new_c[j];
	                    vi.clear();
	                    vi.add(new Integer(j));
	                }
	            }
	            index = (int)(vi.size()*g.nextDouble());
	            int k = ((Integer)vi.get(index)).intValue();
	            temp = board[row][k];
	            board[row][k] = board[row][column];
	            board[row][column] = temp;
        	}catch(Exception e){}
        }
    }

    public String toString(){
        StringBuffer sb = new StringBuffer();
        for( int i=0; i<DIGITS; i++ ){
            for( int j=0; j<DIGITS; j++ ){
                if( board[i][j] > 0 ){
                    sb.append(board[i][j]);
                }else{
                    sb.append(" ");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main( String[] argv ){
      Board board;
      board = new Board();
      System.out.print(board);
      System.out.println( board.valid() );
      System.out.println( board.solved() );
      board = new Board(20);
      System.out.println("*************************************");
      System.out.print(board);
      System.out.println( board.valid() );
      System.out.println( board.solved() );
    }
}
