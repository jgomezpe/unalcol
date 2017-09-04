package unalcol.agents.examples.labyrinth.variable;
import unalcol.agents.Action;
import unalcol.agents.AgentProgram;
import unalcol.agents.Percept;
import unalcol.random.UsesRawGenerator;
import unalcol.reflect.tag.Tags;

public class WallDaemon extends Tags implements AgentProgram, UsesRawGenerator{
	protected double probability;
	public WallDaemon( double p ){ probability = p;	}
	
	@Override
	public Action compute(Percept p) {
		if( real() < probability ){ return new Action("change_walls"); }
		return new Action("no_op");
	}

	@Override
	public void init(){}
}