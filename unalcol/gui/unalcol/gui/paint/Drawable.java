package unalcol.gui.paint;

public interface Drawable {
	Command draw();
	default void draw(Canvas canvas){
		canvas.command(draw());
	}
}