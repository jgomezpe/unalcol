package unalcol.agents.examples.labyrinth;
import unalcol.agents.Percept;

public class LabyrinthPercept extends Percept{  
  public LabyrinthPercept( int value ) {
      int n = 4;
      for( int i=0; i<n; i++ ){
	  int flag = (1<<i);
	  setAttribute( LabyrinthUtil.WALL[i], (value & flag)==flag );
      }
  }
    
  public void rotate(){
    Object f = getAttribute(LabyrinthUtil.WALL[0]);
    for( int i=0; i<3; i++ ){
      setAttribute( LabyrinthUtil.WALL[i], getAttribute(LabyrinthUtil.WALL[i+1]));
    }
    setAttribute( LabyrinthUtil.WALL[3], f );
  }
}