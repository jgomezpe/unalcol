/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.games.fourinrow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 *
 * @author Jonatan
 */
public class Board {
    public static int LINE = 4;
    
    protected int[][] values;
    public Board( int n ){
        this(n, n);
    }
    
    public Board( int n, int m ){
        values = new int[n][m];
    }
    
    public void init(){
        for( int i=0; i<values.length; i++ ){
            for( int j=0; j<values.length; j++ ){
                values[i][j] = 0;
            }
        }
     }
    
    public boolean play( int i, int j, int val ){
	int n = values.length;
	if( 0<=i && i<n && 0<=j && j<n && values[i][j]==0 && (i==n-1 || values[i+1][j]!=0)){
	    values[i][j] = val;
	    return true;
	}
        return false;        
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
    
    public int check_ld( int i, int j ){
	int n = values.length;
	if( i<=n-LINE && j>=LINE-1 && values[i][j]!=0){
	    int c = values[i][j];
	    int k = 0;
	    while( k<LINE && c==values[i][j] ){
		i++;
		j--;
		k++;
	    }
	    if( k==LINE ) return c;
	}
	return 0;
    }
    
    public int check_rd( int i, int j ){
		int n = values.length;
		if( i<=n-LINE && j<=n-LINE && values[i][j]!=0){
		    int c = values[i][j];
		    int k = 0;
		    while( k<LINE && c==values[i][j] ){
		    	i++;
		    	j++;
		    	k++;
		    }
		    if( k==LINE ) return c;
		}
		return 0;
    }
    
    public int check_d( int i, int j ){
		int n = values.length;
		if( i<=n-LINE && values[i][j]!=0){
		    int c = values[i][j];
		    int k = 0;
		    while( k<LINE && c==values[i][j] ){
		    	i++;
		    	k++;
		    }
		    if( k==LINE ) return c;
		}
		return 0;
    }
    
    public int check_r( int i, int j ){
		int n = values.length;
		if( j<=n-LINE && values[i][j]!=0){
		    int c = values[i][j];
		    int k = 0;
		    while( k<LINE && c==values[i][j] ){
				j++;
				k++;
		    }
		    if( k==LINE ) return c;
		}
		return 0;
    }
    
   public int check(){
        for(int i=0; i<values.length; i++){
          for(int j=0; j<values[0].length; j++){
              int x = check_ld(i, j);
              if( x != 0 ) return x;
              x = check_rd(i, j);
              if( x != 0 ) return x;
              x = check_d(i, j);
              if( x != 0 ) return x;
              x = check_r(i, j);
              if( x != 0 ) return x;
           }
       }
       return 0;
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
        b.play(9, 6, 1);
        System.out.println(b);
        b.play(9, 5, -1);
        System.out.println(b);
        b.play(9, 4, 1);
        System.out.println(b);
        b.play(8, 4, -1);
        System.out.println(b);
        b.play(8, 5, 1);
        System.out.println(b);
    }
    
    
}
