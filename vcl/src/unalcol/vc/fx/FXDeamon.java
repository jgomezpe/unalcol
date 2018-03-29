package unalcol.vc.fx;

import unalcol.vc.View;

public class FXDeamon extends Thread{
	protected View view;
	protected boolean done=false;
	protected Object data;
	protected String command;
	
	public FXDeamon( View view, String command ){
		this.view = view;
		this.command = command;
	}
	
	public void run(){
		data = view.execute(command);
		done = true;
	}
	
	public Object data(){ return data; }
	
	public boolean done(){ return done; }
}
