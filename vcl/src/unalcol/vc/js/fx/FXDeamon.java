package unalcol.vc.js.fx;

import unalcol.vc.js.JSView;

public class FXDeamon extends Thread{
	protected JSView view;
	protected boolean done=false;
	protected Object data;
	protected String command;
	
	public FXDeamon( JSView view, String command ){
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
