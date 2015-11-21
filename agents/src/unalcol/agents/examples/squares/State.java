package unalcol.agents.examples.squares;



public class State implements Cloneable, Comparable<State> {
	
	private String id;
	private int lostSquares;
	private Board board;
	private boolean ifMax;
	private String play;
	
	public State(String id, Board board, boolean max, int value) {
		 this.id = id;
		 this.board = board;
		 //this.board.values = board.values.clone();
		 this.ifMax = max;
		 this.lostSquares = value;
	}


	public boolean isMax() {
		return ifMax;
	}

	public void setMax(boolean max) {
		this.ifMax = max;
	}

	public String getPlay() {
		return play;
	}

	public void setPlay(String play) {
		this.play = play;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getLostSqueares() {
		return lostSquares;
	}


	public void setLostSquares(int value) {
		this.lostSquares = value;
	}


	public Board getBoard() {
		return board;
	}


	public void setBoard(Board board) {
		this.board = board;
	}

	public Board getClonBoard(int s) {
		Board b = new Board(s); 
		for( int i = 0; i < board.values.length; i++ ){
	          for( int j = 0; j < board.values[i].length; j++ ){
	        	  b.values[i][j] = board.values[i][j];
	          }
		}
		return b;
	}


	@Override
	public int compareTo(State o) {
		if(lostSquares > o.lostSquares ) return 1;
		if(lostSquares == o.lostSquares ) return 0;
		return -1;
	}
	
	
}
