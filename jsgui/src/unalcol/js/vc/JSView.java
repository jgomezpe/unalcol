package unalcol.js.vc;
import unalcol.vc.View;
public interface JSView extends View{
	default void execute( String js_command ){ ((JSFrontEnd)front()).execute(js_command); }
}