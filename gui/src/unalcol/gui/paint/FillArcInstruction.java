package unalcol.gui.paint;

public class FillArcInstruction extends RenderInstruction{
	public FillArcInstruction(int x, int y, int width, int height, int startAngle, int arcAngle){
		super(RenderInstruction.FILLARC, new int[]{x,width,startAngle}, new int[]{y,height,arcAngle}, null);
	}
}