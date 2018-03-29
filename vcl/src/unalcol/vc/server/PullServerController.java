package unalcol.vc.server;
import unalcol.vc.SimpleController;

public class PullServerController extends SimpleController{
	public PullServerController(){ super("unalcol_servlet"); }

	public String pull(){ return ((ServerView)view).queue(); }
}