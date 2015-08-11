/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.reversi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Jonatan
 */
public class Board {
    protected int[][] values;
    public Board( int n ){
        this(n, n);
    }
    
    public Board( int n, int m ){
        values = new int[n][m];
        int n2 = n/2 - 1;
        int m2 = m/2 - 1;
        values[n2][m2] = 1;
        values[n2][m2+1] = -1;
        values[n2+1][m2+1] = 1;
        values[n2+1][m2] = -1;
    }
    
    public void init(){
        for( int i=0; i<values.length; i++ ){
            for( int j=0; j<values.length; j++ ){
                values[i][j] = 0;
            }
        }
        int n2 = values.length/2 - 1;
        int m2 = values[0].length/2 - 1;
        values[n2][m2] = 1;
        values[n2][m2+1] = -1;
        values[n2+1][m2+1] = 1;
        values[n2+1][m2] = -1;
    }
    
    public boolean l_play( int i, int j, int val ){
        int nval = -val;
        int j1=j-1;
        while( j1>=0 && values[i][j1] == nval ){
            j1--;
        }
        if( j1>=0 && j1+1 < j && values[i][j1] == val ){
            // Valid play
            for( int k=j1+1; k<j; k++ ){
                values[i][k] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean u_play( int i, int j, int val ){
        int nval = -val;
        int i1 = i-1;
        while( i1>=0 && values[i1][j] == nval ){
            i1--;
        }
        if( i1>=0 && i1+1 < i && values[i1][j] == val ){
            // Valid play
            for( int k=i1+1; k<i; k++ ){
                values[k][j] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean r_play( int i, int j, int val ){
        int nval = -val;
        int j1=j+1;
        while( j1<values[0].length && values[i][j1] == nval ){
            j1++;
        }
        if( j1<values[0].length && j+1 < j1 && values[i][j1] == val ){
            // Valid play
            for( int k=j+1; k<j1; k++ ){
                values[i][k] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean d_play( int i, int j, int val ){
        int nval = -val;
        int i1 = i+1;
        while( i1<values.length && values[i1][j] == nval ){
            i1++;
        }
        if( i1<values.length && i+1<i1 && values[i1][j] == val ){
            // Valid play
            for( int k=i+1; k<i1; k++ ){
                values[k][j] = val;
            }
            return true;
        }
        return false;        
    }
    
    public boolean lu_play( int i, int j, int val ){
        int nval = -val;
        int i1 = i-1;
        int j1=j-1;
        while( i1>=0 && j1>=0 && values[i1][j1] == nval ){
            i1--;
            j1--;
        }
        if( i1>=0 && j1>=0 && i1+1 < i && values[i1][j1] == val ){
            // Valid play
            for( int k=i1+1; k<i; k++ ){
                j--;
                values[k][j] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean ru_play( int i, int j, int val ){
        int nval = -val;
        int i1 = i-1;
        int j1=j+1;
        while( i1>=0 && j1<values[0].length && values[i1][j1] == nval ){
            i1--;
            j1++;
        }
        if( i1>=0 && i1+1 < i && j1<values[0].length && values[i1][j1] == val ){
            // Valid play
            for( int k=i1+1; k<i; k++ ){
                j++;
                values[k][j] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean ld_play( int i, int j, int val ){
        int nval = -val;
        int i1 = i+1;
        int j1=j-1;
        while( i1<values.length && j1>=0 && values[i1][j1] == nval ){
            i1++;
            j1--;
        }
        if( i1<values.length && j1>=0 && i+1 < i1 && values[i1][j1] == val ){
            // Valid play
            for( int k=i+1; k<i1; k++ ){
                j--;
                values[k][j] = val;
            }
            return true;
        }
        return false;        
    }
    
    public boolean rd_play( int i, int j, int val ){
        int nval = -val;
        int i1 = i+1;
        int j1=j+1;
        while( i1<values.length && j1<values[0].length && values[i1][j1] == nval ){
            i1++;
            j1++;
        }
        if( i1<values.length && j1<values[0].length && i+1 < i1 && values[i1][j1] == val ){
            // Valid play
            for( int k=i+1; k<i1; k++ ){
                j++;
                values[k][j] = val;
            }
            return true;
        }
        return false;        
    }

    public boolean play( int i, int j, int val ){
        if( values[i][j] != 0 ) return false;
        boolean flag1 = l_play(i, j, val);
        boolean flag2 = u_play(i, j, val);
        boolean flag3 = r_play(i, j, val);
        boolean flag4 = d_play(i, j, val);
        boolean flag5 = lu_play(i, j, val);
        boolean flag6 = ld_play(i, j, val);
        boolean flag7 = ru_play(i, j, val);
        boolean flag8 = rd_play(i, j, val);
        boolean flag = flag1 || flag2 || flag3 || flag4 || flag5 || flag6 || flag7 || flag8;
        if( flag ){
            values[i][j] = val;
        }
        return flag;
    }
    
    public String toString(){
      StringBuilder sb = new StringBuilder();
      for( int i=0; i<values.length; i++ ){
          for( int j=0; j<values[i].length; j++ ){
              switch(values[i][j]){
                  case -1:
                      sb.append('O');
                      break;
                  case 1:
                      sb.append('X');
                      break;
                  default:
                      sb.append(' ');                      
              }
              
          }
          sb.append('\n');
      }
      return sb.toString();
    }
    
    public void draw( Graphics g, int DRAW_AREA_SIZE, int MARGIN ){
          g.setColor(Color.lightGray);
          Font font = g.getFont();
          g.setFont( new Font(font.getName(), font.getStyle(), 20 ));
          int n = values.length;
          int m = values[0].length;
          int CELL_SIZE = (DRAW_AREA_SIZE - 2*MARGIN)/Math.max(n, m);
          int fm = m * CELL_SIZE + MARGIN;
          int fn = n * CELL_SIZE + MARGIN;
          int ci = MARGIN;
          for (int i = 0; i<=n; i++) {
              g.drawLine(fm, ci, MARGIN, ci);
              ci += CELL_SIZE;
          }
          

          int cj = MARGIN;
          for (int j = 0; j<=m; j++) {
              g.drawLine(cj, fn, cj, MARGIN);
              cj += CELL_SIZE;
          }
          
          ci = MARGIN;
          for (int i = 0; i<n; i++) {
              cj=MARGIN;
              for (int j = 0; j<m; j++) {
                  int value = values[i][j];
                  if( value > 0 ){
                      g.setColor(Color.blue);
                      g.fillOval(cj+1, ci+1, CELL_SIZE-2, CELL_SIZE-2);
                  }else{
                     if( value < 0 ){
                        g.setColor(Color.red);
                        g.fillOval(cj+1, ci+1, CELL_SIZE-2, CELL_SIZE-2);
                     }
                  }
                  cj += CELL_SIZE;
              }
              ci += CELL_SIZE;
          }
    }
    
    public int white_count(){
       int c=0;
       for(int i=0; i<values.length; i++){
          for(int j=0; j<values[0].length; j++){
              if(values[i][j] > 0 ){
                  c++;
              }
          }
       }
       return c;
    }

    public int black_count(){
       int c=0;
       for(int i=0; i<values.length; i++){
          for(int j=0; j<values[0].length; j++){
              if(values[i][j] < 0 ){
                  c++;
              }
          }
       }
       return c;
    }
    
    public boolean full(){
        boolean flag = true;
        for( int i=0; i<values.length&&flag; i++){
            for( int j=0; j<values[0].length&&flag; j++){
                flag &= values[i][j] != 0;
            }
        }
        return flag;
    }
    
    public static void main( String[] args ){
        Board b = new Board(10);
        System.out.println(b);
        b.play(4, 6, 1);
        System.out.println(b);
        b.play(3, 6, -1);
        System.out.println(b);
        b.play(3, 5, 1);
        System.out.println(b);
        b.play(3, 4, -1);
        System.out.println(b);
        b.play(3, 3, -1);
        System.out.println(b);
    }
    
    
}
