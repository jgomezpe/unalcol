package unalcol.gui.paint;

import unalcol.types.collection.keymap.KeyMap;
import unalcol.types.collection.vector.Vector;

public class CanvasObject{
	public static final int COMPOUND = 1;
	public static final int LINE = 2;
	public static final int POLYGON = 3;
	public static final int POLYLINE = 4;
	public static final int RECT = 5;
	public static final int ARC = 6;
	public static final int FILLRECT = 7;
	public static final int FILLARC = 8;
	public static final int TEXT = 9;
	public static final int IMAGE = 10;

	protected Color color;
	protected int type;
	protected int[] x;
	protected int[] y;
	protected Vector<CanvasObject> inner;
	
	public CanvasObject( KeyMap<String, Object> json ){
		
	}
}
