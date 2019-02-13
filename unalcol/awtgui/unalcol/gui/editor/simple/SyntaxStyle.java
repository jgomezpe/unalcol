package unalcol.gui.editor.simple;

import unalcol.gui.paint.Color;
import unalcol.json.JSON;
import unalcol.json.JSONParser;
import unalcol.collection.Vector;
import unalcol.collection.keymap.HashMap;
import unalcol.collection.keymap.Immutable;

public class SyntaxStyle {
	public static final String STYLES = "styles";
	public static final String REGULAR = "regular";
	
	protected String tag;
	protected boolean italic=false;
	protected boolean bold=false;
	protected boolean under_line=false;
	protected int size=0;
	protected String font_family=null;
	protected Color color=null;
	
	public SyntaxStyle( String tag, String font_family, int font_size, boolean bold, boolean italic, boolean under_line, Color color ){
		this.tag = tag;
		this.font_family = font_family;
		this.size = font_size;
		this.bold = bold;
		this.italic = italic;
		this.under_line = under_line;
		this.color = color;
	}
	
	public boolean italic(){ return italic; }
	public boolean bold(){ return bold; }
	public boolean under_line(){ return under_line; }
	public String font_family(){ return font_family; }
	public String tag(){ return tag; }
	public int font_size(){ return size; }
	public Color color(){ return color;	}
	
	public static Immutable<String, SyntaxStyle> get( String styles ){
		SyntaxStyleInstance si = new SyntaxStyleInstance();
		try {
			JSONParser parser = new JSONParser();
			JSON json = (JSON)parser.parse(styles);
			HashMap<String, SyntaxStyle> km = new HashMap<String,SyntaxStyle>();
			@SuppressWarnings("unchecked")
			Vector<Object> objs = (Vector<Object>)json.get(STYLES);
			for( Object o:objs ){
				SyntaxStyle style = si.load((JSON)o);
				km.set(style.tag,style);
			}
			return km;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

/*	public static void main( String[] args ){
		SyntaxStyleInstance si = new SyntaxStyleInstance();
		String style =  "[[\"style\",\"regular\",[\"SansSerif\",12]],[\"style\",\"undef\",["+color(java.awt.Color.pink)+"]],[\"style\",\"comment\",[\"italic\","+color(java.awt.Color.gray)+"]],[\"style\",\"symbol\",["+color(java.awt.Color.blue)+"]],[\"style\",\"stitch\",["+color(java.awt.Color.red)+"]],[\"style\",\"reserved\",[\"bold\"]],[\"style\",\"remnant\",["+color(java.awt.Color.orange)+"]]]"; // "[[\"style\",\"normal\",[3,[\"color\",3,4,5,255],\"italic\",\"bold\",\"Sans Serif\"]]]";
		System.out.println(style);
		ImmutableKeyMap<String, SyntaxStyle> styles = get(style);
		for( SyntaxStyle s:styles ) System.out.println( si.store(s) );
	}  */
}