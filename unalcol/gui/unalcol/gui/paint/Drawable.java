package unalcol.gui.paint;

import unalcol.json.JSON;

public interface Drawable {
	JSON draw();
	default void draw(Canvas canvas){
		canvas.drawJSON(draw());
	}
}