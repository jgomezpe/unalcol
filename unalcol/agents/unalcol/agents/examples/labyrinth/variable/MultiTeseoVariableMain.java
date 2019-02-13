package unalcol.agents.examples.labyrinth.variable;

import unalcol.agents.Agent;
import unalcol.agents.AgentProgram;
import unalcol.agents.examples.labyrinth.Labyrinth;
import unalcol.agents.examples.labyrinth.LabyrinthDrawer;
import unalcol.agents.examples.labyrinth.teseo.simple.RandomReflexTeseo;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.collection.Vector;
/*
<<<<<<< HEAD
import unalcol.random.util.Shuffle;
import unalcol.types.collection.vector.Vector;

public class MultiTeseoVariableMain {
	  private static SimpleLanguage getLanguage(){
		    return  new SimpleLanguage( new String[]{"front", "right", "back", "left", "exit", "fail",
		        "afront", "aright", "aback", "aleft"},
		                                   new String[]{"no_op", "die", "advance", "rotate"}
		                                   );
		  }
	  
	     public static int[] shuffle(int n ){
	    	 int[] a= new int[n];
	    	 for( int i=0; i<a.length; i++){
	    		 a[i] = i;
	    	 }
	    	 for( int i=0; i<2*a.length; i++){
	    		 int k = (int)(Math.random() * n);
	    		 int j = (int)(Math.random() * n);
	    		 int t = a[k];
	    		 a[k] = a[j];
	    		 a[j] = t;
	    	 }
	    	 return a;
	     }

		  public static void main( String[] argv ){
			 / *int[] a = shuffle(7); 
			 for( int x:a ){
				 System.out.println(x);
			 }* /
			 
		     AgentProgram[] teseo = new AgentProgram[7];
		    int index1 = 5;
		    int index2 = 4;
		    / * teseo[2] = new TremauxMovimiento( getLanguage() );
		    teseo[0] = new FatalityAgent( getLanguage() );
		    teseo[3] = new LabyrinthAgent( getLanguage() );
		    teseo[4] = new HardAgent( getLanguage());
		    teseo[5] = new JavanaticosAgent(getLanguage());
		    teseo[1] = new Killer(getLanguage());
		    teseo[6] = new SearchPerfectAgent(getLanguage());* /
		    
		    LabyrinthDrawer.DRAW_AREA_SIZE = 600;
		    LabyrinthDrawer.CELL_SIZE = 40;
		    Labyrinth.DEFAULT_SIZE = 15;
		    
		    Agent agent1 = new Agent(teseo[index1]);    
		    Agent agent2 = new Agent(teseo[index2]);
		    
		    //Agent agent3 = new Agent(p3);
		    Vector<Agent> agent = new Vector<Agent>();
		    agent.add(agent1);
		    agent.add(agent2);
		    MultiTeseoVariableMainFrame frame = new MultiTeseoVariableMainFrame( agent, getLanguage(), 0.1 );
		    frame.setVisible(true);
		     
=======
import unalcol.types.collection.vector.Vector;
*/
public class MultiTeseoVariableMain {
	  private static SimpleLanguage getLanguage(){
		    return  new SimpleLanguage( new String[]{"front", "right", "back", "left", "exit", "fail",
		        "afront", "aright", "aback", "aleft"},
		                                   new String[]{"no_op", "die", "advance", "rotate"}
		                                   );
		  }

		  public static void main( String[] argv ){
		     AgentProgram[] teseo = new AgentProgram[12];
		    int index1 = 10;
		    int index2 = 8;
		    teseo[index1] = new RandomReflexTeseo( getLanguage() );
		    teseo[index2] = new RandomReflexTeseo( getLanguage() );
		    
		    LabyrinthDrawer.DRAW_AREA_SIZE = 600;
		    LabyrinthDrawer.CELL_SIZE = 40;
		    Labyrinth.DEFAULT_SIZE = 15;
		    
		    Agent agent1 = new Agent(teseo[index1]);    
		    Agent agent2 = new Agent(teseo[index2]);
		    
		    //Agent agent3 = new Agent(p3);
		    Vector<Agent> agent = new Vector<Agent>();
		    agent.add(agent1);
		    agent.add(agent2);
		    MultiTeseoVariableMainFrame frame = new MultiTeseoVariableMainFrame( agent, getLanguage(), 0.1 );
		    frame.setVisible(true); 
// >>>>>>> branch 'master' of https://github.com/jgomezpe/unalcol
		  }

}
