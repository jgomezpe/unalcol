package unalcol.gui.paint;

public class ImageInstruction  extends RenderInstruction{
	public ImageInstruction(int x, int y, int width, int height, int rot, String txt){ super(RenderInstruction.IMAGE, new int[]{x, width, rot}, new int[]{y, height}, txt); }
}