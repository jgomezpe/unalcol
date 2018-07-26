package unalcol.gui.editor.simple;

import unalcol.gui.paint.Color;
import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.ImmutableKeyMap;
import unalcol.util.ObjectParser;

public class SyntaxStyle {
	public static final String DEF = "regular";
	public static final String ITALIC = "italic";
	public static final String BOLD = "bold";
	public static final String UNDER_LINE = "under_line";
	
	protected String tag=DEF;
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
	
	public static ImmutableKeyMap<String, SyntaxStyle> get( String styles ){
		SyntaxStyleInstance si = new SyntaxStyleInstance();
		Object[] objs;
		try {
			objs = ObjectParser.parse(styles);
			HTKeyMap<String, SyntaxStyle> km = new HTKeyMap<String,SyntaxStyle>();
			for( Object o:objs ){
				SyntaxStyle style = si.load((Object[])o);
				km.set(style.tag,style);
			}
			return km;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static String color( java.awt.Color c ){
		return ObjectParser.store(new Object[]{"color",c.getRed(),c.getGreen(),c.getBlue(),c.getAlpha()});
	}
	
/*	public static void main( String[] args ){
		SyntaxStyleInstance si = new SyntaxStyleInstance();
		String style =  "[[\"style\",\"regular\",[\"SansSerif\",12]],[\"style\",\"undef\",["+color(java.awt.Color.pink)+"]],[\"style\",\"comment\",[\"italic\","+color(java.awt.Color.gray)+"]],[\"style\",\"symbol\",["+color(java.awt.Color.blue)+"]],[\"style\",\"stitch\",["+color(java.awt.Color.red)+"]],[\"style\",\"reserved\",[\"bold\"]],[\"style\",\"remnant\",["+color(java.awt.Color.orange)+"]]]"; // "[[\"style\",\"normal\",[3,[\"color\",3,4,5,255],\"italic\",\"bold\",\"Sans Serif\"]]]";
		System.out.println(style);
		SyntaxStyle[] styles = get(style);
		for( int i=0; i<styles.length; i++ ) System.out.println( ObjectParser.store(si.store(styles[i])) );
	} */
}