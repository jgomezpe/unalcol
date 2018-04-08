package unalcol.gui.paint;

public abstract class ScaledCanvas implements Canvas{
	protected int scale=SCALE; // A value of 100 means 1 to 1 scale
	
	public int setScale( int scale ){
		int t = this.scale;
		this.scale = scale;
		return t;
	}
	
	@Override
	public int getScale(){ return scale; }	
}
