package unalcol.js.vc;

import unalcol.vc.VCElement;

public interface JSVCElement  extends VCElement {
	default void execute( String js_command ){ ((JSVCManager)manager()).execute(js_command); }
}