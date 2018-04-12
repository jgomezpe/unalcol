package unalcol.gui.js.mode.fx;

import unalcol.gui.js.JSView;

public class FXDeamon extends Thread{
	protected JSView view;
	protected boolean done=false;
	protected String command;
	
	public FXDeamon( JSView view, String command ){
		this.view = view;
		this.command = command;
	}
	
	public void run(){
		view.execute(command);
		done = true;
	}
	
	public boolean done(){ return done; }
}
