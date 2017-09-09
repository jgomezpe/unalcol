package unalcol.search.solution;

import java.io.IOException;
import java.io.Writer;

import unalcol.Thing;
import unalcol.io.Write;
import unalcol.Tagged;
import unalcol.search.Goal;
import unalcol.services.Service;

public class SolutionWrite<T> extends Thing implements Write<Tagged<T>> {
	protected Goal<T,Double> goal;
	protected boolean write_object;
	
	public SolutionWrite( Goal<T,Double> goal, boolean write_object ) {
		this.goal = goal;
		this.write_object = write_object;
	}
	
	@Override
	public void write(Writer out) throws IOException {
		Tagged<T> sol = caller();
		out.write(""+goal.apply(sol));
		if( write_object ){
			out.write(' ');
			try{ Service.run(Write.name, sol.unwrap(), out); }
			catch(IOException ie){ throw ie; }
			catch(Exception e){ throw new IOException(e.getMessage()); }
		}
	}
}