package unalcol.gui.paint;

public class TextInstruction extends RenderInstruction{
	public TextInstruction(int x, int y, String txt){ super(RenderInstruction.TEXT, new int[]{x}, new int[]{y}, txt); }
}