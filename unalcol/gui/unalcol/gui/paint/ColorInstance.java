package unalcol.gui.paint;

import unalcol.json.JSON;
import unalcol.json.JSON2Instance;

public class ColorInstance implements JSON2Instance<Color>{
	public static final String COLOR="color";
	public static final String RED="red";
	public static final String GREEN="green";
	public static final String BLUE="blue";
	public static final String ALPHA="alpha";

	protected int value( JSON json, String tag ){ try{ return (Integer)json.get(tag); }catch(Exception e){ return 0; } }

	@Override
	public Color load(JSON json){ return new Color( value(json, RED), value(json,GREEN), value(json,BLUE), value(json,ALPHA));	}

	@Override
	public JSON store(Color color) {
		JSON json = new JSON();
		json.set(RED, color.red());
		json.set(GREEN, color.green());
		json.set(BLUE, color.blue());
		json.set(ALPHA, color.alpha());
		return json;
	}
}