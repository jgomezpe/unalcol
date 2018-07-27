package unalcol.gui.render;

import javax.swing.JPanel;

public class RenderPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1889650141124790453L;
	
	protected AWTRender render = null;
	
	public RenderPanel(AWTRender render){
		this.render = render;
	}
	
	public AWTRender render(){ return render; }
	
	public void render( Object obj ){ render.render(obj); }	
	
}