package unalcol.agents.examples.squares;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

import unalcol.agents.Action;

public class WalkBoard {
	
	Board board;
	int[] y = {-1, 0, 1, 0 };
	int[] x = {0, -1, 0, 1 };
	String result = "";
	
	public String walk( Board m, int ale, int size ){
		this.board = m;
		
		///////////////////////////////////
		//Hay oriblemas con el CLONE por lo tanto lo voy a hacer de es forma(provicional)
		//Board b = new Board(m.length);
		//b.values = m;
    	//State state = new State( "none", b, true, 0);
    	//this.board = state.getClonBoard(m.length).values;
    	///////////////////////
			
		switch ( ale ) {
		case 0:
			
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					result = searchPossiblePos(i, j);
					if(!result.equals("")){
						return i+":"+j+":"+ result;
					}
				}
			}
			break;
		
		case 1:
			for (int i = size- 1; i >= 0; i--) {
				for (int j = 0; j < size; j++) {
					 result = searchPossiblePos(i, j);
					if(!result.equals("")){
						return i+":"+j+":"+ result;
					}
				}	
			}
			break;
		
		case 2:
			for (int j = 0; j < size; j++) {
				for (int i = 0; i < size; i++) {
					 result = searchPossiblePos(i, j);
					if(!result.equals("")){
						return i+":"+j+":"+ result;
					}
				}
			}
			break;
		
		case 3:
			for (int j = size- 1; j >=0 ; j--) {
				for (int i = 0; i < size; i++) {
					result = searchPossiblePos(i, j);
					if(!result.equals("")){
						return i+":"+j+":"+ result;
					}
				}	
			}
			break;
		
		case 4:
			for (int i = 0; i < size; i++) {
				for (int j = size- 1; j >= 0; j--) {
					 result = searchPossiblePos(i, j);
					if(!result.equals("")){
						return i+":"+j+":"+ result;
					}
				}	
			}
			break;
		
		case 5:
			for (int i = size- 1; i >= 0 ; i--) {
				for (int j = size- 1; j >= 0; j--) {
					result = searchPossiblePos(i, j);
					if(!result.equals("")){
						return i+":"+j+":"+ result;
					}
				}	
			}
			break;
		
		case 6:
			for (int j = 0; j < size; j++) {
				for (int i = size- 1; i >= 0; i--) {
					result = searchPossiblePos(i, j);
					if(!result.equals("")){
						return i+":"+j+":"+ result;
					}
				}	
			}
			break;
			
		case 7:
			for (int j = size- 1; j >= 0 ; j--) {
				for (int i = size- 1; i >= 0; i--) {
					result = searchPossiblePos(i, j);
					if(!result.equals("")){
						return i+":"+j+":"+ result;
					}
				}	
			}
			break;
		
		case 8:
			for (int s = 0; s < 2 * size - 1; ++s) {
		    	
		    	int z = s < size ? 0 : s - size + 1;
		    	for (int j = z; j <= s - z; ++j) {
		    		result = searchPossiblePos((s - j), j);
		    		if(!result.equals("")){
						return s-j+":"+j+":"+ result;
					}
		    			    		
		    	}
		    }
			break;
		
		default:
			break;
		}
		
		return "";
	}
	
	public String searchPossiblePos(int i, int j ){
		int numWalls = 4;
		ArrayList<String> values = new ArrayList<>();
		
		String side = "";
		if((board.values[i][j]&Board.LEFT) != Board.LEFT){
			numWalls--;
			if(board.lines(i + x[0],j + y[0]) <= 1){
				values.add(Squares.LEFT);
			}
		}
		if((board.values[i][j]&Board.TOP) != Board.TOP){
			numWalls--;
			if(board.lines(i + x[1],j + y[1]) <= 1){
			values.add(Squares.TOP);
			}
		}
		if((board.values[i][j]&Board.RIGHT) != Board.RIGHT){
			numWalls--;
			if(board.lines(i + x[2],j + y[2]) <= 1){
			values.add(Squares.RIGHT);
			}
		}
		if((board.values[i][j]&Board.BOTTOM) != Board.BOTTOM){
			numWalls--;
			if(board.lines(i + x[3],j + y[3]) <= 1){
			values.add(Squares.BOTTOM);			
			}
		}
		
		if( numWalls <= 1 && !values.isEmpty()){
			return values.get((int)(Math.random()*values.size()));
		}
		else
			return "";
}
	
}
