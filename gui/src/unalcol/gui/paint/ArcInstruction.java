package unalcol.gui.paint;

public class ArcInstruction extends  RenderInstruction{
	public ArcInstruction(int x, int y, int width, int height, int startAngle, int arcAngle){
		super(RenderInstruction.ARC, new int[]{x,width,startAngle}, new int[]{y,height,arcAngle}, null);
	}
}