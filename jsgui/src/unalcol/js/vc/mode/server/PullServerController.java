package unalcol.js.vc.mode.server;

import unalcol.vc.backend.SimpleController;

public class PullServerController extends SimpleController{
	public static final String SERVER = "unalcol_servlet";

	public PullServerController(){ id=PullServerController.SERVER; }

	public String pull(){ return ((JSServerManager)frontend()).queue(); }
}