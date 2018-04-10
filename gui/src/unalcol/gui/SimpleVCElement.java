package unalcol.gui;

public class SimpleVCElement implements VCElement {
	protected View view=null;
	
	@Override
	public void set(View view) { this.view = view; }

	@Override
	public View view() { return view; }
}