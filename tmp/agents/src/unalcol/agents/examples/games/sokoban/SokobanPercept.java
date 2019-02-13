package unalcol.agents.examples.games.sokoban;

import unalcol.agents.Percept;

public class SokobanPercept extends Percept{  
	public SokobanPercept( int left, int top, int right, int bottom, int value ) {
		setAttribute( SokobanUtil.WALL[0], top==SokobanBoard.WALL );
		setAttribute( SokobanUtil.WALL[1], right==SokobanBoard.WALL );
		setAttribute( SokobanUtil.WALL[2], bottom==SokobanBoard.WALL );
		setAttribute( SokobanUtil.WALL[3], left==SokobanBoard.WALL );
		setAttribute( SokobanUtil.BLOCK,  (value&SokobanBoard.BLOCK)==SokobanBoard.BLOCK);
		setAttribute( SokobanUtil.MARK,  (value&SokobanBoard.MARK)==SokobanBoard.MARK);	
	}
	
	public void rotate(){
		Object f = getAttribute(SokobanUtil.WALL[0]);
		for( int i=0; i<3; i++ ){
			setAttribute( SokobanUtil.WALL[i], getAttribute(SokobanUtil.WALL[i+1]));
		}
		setAttribute( SokobanUtil.WALL[3], f );
	}
}
