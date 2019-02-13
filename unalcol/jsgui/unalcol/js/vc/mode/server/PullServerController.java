package unalcol.js.vc.mode.server;

import unalcol.vc.BackEnd;
import unalcol.vc.Controller;
import unalcol.vc.DefaultComponent;
import unalcol.vc.Side;

public class PullServerController extends DefaultComponent implements Controller{
	protected BackEnd backend; 
	public static final String SERVER = "servlet";

	public PullServerController( BackEnd backend ){
		super( SERVER ); 
		this.backend = backend; 
	}

	public String pull(){ return ((JSServerManager)front()).queue(); }
	
	@Override
	public Side side(){ return backend; }
}