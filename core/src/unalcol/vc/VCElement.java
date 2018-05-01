package unalcol.vc;

import unalcol.vc.backend.BackEnd;
import unalcol.vc.frontend.FrontEnd;

public interface VCElement {
	public void setBackend( BackEnd backend );
	public void setFrontend( FrontEnd backend );
	public BackEnd backend();
	public FrontEnd frontend();	
}
