package unalcol.gui.paint;

import unalcol.util.Instance;

public class ColorInstance implements Instance<Color>{
	public static final String COLOR="color";
	

	@Override
	public Color load(Object[] args) {
		if( args.length!=5 || !COLOR.equals(args[0]) ) return null;
		return new Color( (int)args[1], (int)args[2], (int)args[3], (int)args[4] );
	}

	@Override
	public Object[] store(Color obj) {
		return new Object[]{COLOR, obj.red(), obj.green(), obj.blue(), obj.alpha()};
	}
}