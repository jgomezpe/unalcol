package unalcol.js.vc;
import unalcol.vc.frontend.View;
public interface JSView extends View{
	default void execute( String js_command ){ ((JSFrontEnd)frontend()).execute(js_command); }
}