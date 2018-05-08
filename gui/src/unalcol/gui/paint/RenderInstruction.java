package unalcol.gui.paint;

public class RenderInstruction {
	public static final int COLOR = 1;
	public static final int LINE = 2;
	public static final int POLYGON = 3;
	public static final int ARC = 4;
	public static final int FILLARC = 5;
	public static final int TEXT = 6;
	public static final int IMAGE = 7;
	
	protected int code;
	protected int[] x;
	protected int[] y;
	protected String info;
	
	public RenderInstruction( int code, int[] x, int[] y, String info ){
		this.code = code;
		this.x = x;
		this.y = y;
		this.info = info;
	}
}
