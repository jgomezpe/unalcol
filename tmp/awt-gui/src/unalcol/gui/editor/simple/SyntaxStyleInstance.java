package unalcol.gui.editor.simple;

import unalcol.gui.paint.Color;
import unalcol.gui.paint.ColorInstance;
import unalcol.json.JSON;
import unalcol.json.JSON2Instance;

public class SyntaxStyleInstance implements JSON2Instance<SyntaxStyle>{
	public static final String STYLE = "style";
	public static final String DEF = "define";
	public static final String ITALIC = "italic";
	public static final String BOLD = "bold";
	public static final String UNDER_LINE = "under_line";
	public static final String FONT = "font";
	public static final String COLOR = "color";
	public static final String SIZE = "size";

	protected ColorInstance c = new ColorInstance();
	
	protected boolean value( JSON json, String tag ){ return json.getBool(tag);	}
	
	@Override
	public SyntaxStyle load(JSON json){
		int size = json.getInt(SIZE);
		boolean bold = value(json,BOLD);
		boolean italic = value(json,ITALIC);
		boolean under_line = value(json,UNDER_LINE);
		Color color = null;
		try{ color = c.load((JSON)json.get(COLOR));	}catch(Exception e){}

		String font_family = null;
		try{ font_family = (String)json.get(FONT); }catch(Exception e){}
		
		String tag = null; 
		try{ tag = (String)json.get(DEF); }catch(Exception e){}
		return new SyntaxStyle(tag,font_family,size,bold,italic,under_line,color);
	}

	@Override
	public JSON store(SyntaxStyle style) {
		JSON json = new JSON();
		json.set(DEF, style.tag);
		json.set(SIZE, style.font_size());
		if(style.bold()) json.set(BOLD, "true");
		if(style.italic()) json.set(ITALIC, "true");
		if(style.under_line()) json.set(UNDER_LINE, "true");
		if(style.font_family()!=null)json.set(FONT, style.font_family());
		if(style.color()!=null) json.set(COLOR, c.store(style.color()));
		return json;
	}
}