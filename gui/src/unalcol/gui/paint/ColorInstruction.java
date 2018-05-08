package unalcol.gui.paint;

public class ColorInstruction extends RenderInstruction{
	public ColorInstruction(Color color) { this(color.r, color.g, color.b, color.a); }
	public ColorInstruction(int r, int g, int b, int alpha) { super(COLOR, new int[]{r, g, b, alpha}, null, null); }
}