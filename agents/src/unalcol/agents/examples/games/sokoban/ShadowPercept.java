package unalcol.agents.examples.games.sokoban;

import unalcol.agents.Percept;

public class ShadowPercept extends Percept{  
	public ShadowPercept( int left, int top, int right, int bottom, int value ) {
		setAttribute( SokobanUtil.WALL[0], top==Board.WALL );
		setAttribute( SokobanUtil.WALL[1], right==Board.WALL );
		setAttribute( SokobanUtil.WALL[2], bottom==Board.WALL );
		setAttribute( SokobanUtil.WALL[3], left==Board.WALL );
		setAttribute( SokobanUtil.BLOCK,  (value&Board.BLOCK)==Board.BLOCK);
		setAttribute( SokobanUtil.MARK,  (value&Board.MARK)==Board.MARK);	
	}
	
	public void rotate(){
		Object f = getAttribute(SokobanUtil.WALL[0]);
		for( int i=0; i<3; i++ ){
			setAttribute( SokobanUtil.WALL[i], getAttribute(SokobanUtil.WALL[i+1]));
		}
		setAttribute( SokobanUtil.WALL[3], f );
	}
}
