package unalcol.gui.paint;

public class LineInstruction extends RenderInstruction{
	public LineInstruction( int x1, int y1, int x2, int y2 ){ super(RenderInstruction.LINE, new int[]{x1,x2}, new int[]{y1,y2}, null); }
}
