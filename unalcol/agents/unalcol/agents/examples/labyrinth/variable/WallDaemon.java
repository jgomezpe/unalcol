package unalcol.agents.examples.labyrinth.variable;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.random.UsesRawGenerator;

public class WallDaemon extends UsesRawGenerator implements AgentProgram{
	protected double probability;
	public WallDaemon( double p ){ probability = p;	}
	
	@Override
	public Action compute(Percept p){
		if( raw().bool(probability) ){ return new Action("change_walls"); }
		else return new Action("no_op");
	}

	@Override
	public void init(){}
}