package unalcol.js.vc.mode.fx;

import unalcol.js.vc.JSVCManager;

public class FXDeamon extends Thread{
	protected JSVCManager view;
	protected boolean done=false;
	protected String command;
	
	public FXDeamon( JSVCManager view, String command ){
		this.view = view;
		this.command = command;
	}
	
	public void run(){
		view.execute(command);
		done = true;
	}
	
	public boolean done(){ return done; }
}
