package unalcol.gui.paint;
import unalcol.gui.paint.RenderInstruction;

public class PolygonInstruction extends RenderInstruction{
	public PolygonInstruction(int[] x, int[] y) { super(RenderInstruction.POLYGON, x, y, null); }
}