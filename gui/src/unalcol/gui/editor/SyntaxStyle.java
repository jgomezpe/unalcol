package unalcol.gui.editor;

import java.util.Vector;

import unalcol.gui.util.ObjectParser;

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
	protected Vector<Integer> color=null;
	
	@SuppressWarnings("unchecked")
	public SyntaxStyle( String style ){
		try {
			Object obj = ObjectParser.parse(style);
			if( obj instanceof Vector ){
				for( Object o:(Vector<Object>)obj ){
					if(o instanceof String){ 
						try{
							size = Integer.parseInt((String)o);
						}catch(NumberFormatException e){
							tag = (String)o;
						}
					}else{
						Vector<Object> v = (Vector<Object>)o;
						for( Object x:v ){
							if(x instanceof Vector){
								Vector<Object> y = (Vector<Object>)x;
								color = new Vector<Integer>();
								for(int i=0; i<y.size(); i++){
									color.add( Integer.parseInt((String)y.get(i)) );
								}
							}else{
								String s = (String)x;
								if( s.equals(ITALIC) ) italic = true;
								else if( s.equals(BOLD) ) bold = true;
								else if( s.equals(UNDER_LINE) ) under_line = true; 
								else{
									try{
										size = Integer.parseInt(s);
									}catch(NumberFormatException e){
										font_family = s;
									}
								} 
							}
						}
					}
				}
			}else{
				tag = style;
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
	
	public boolean italic(){ return italic; }
	public boolean bold(){ return bold; }
	public boolean under_line(){ return under_line; }
	public String font_family(){ return font_family; }
	public String tag(){ return tag; }
	public int font_size(){ return size; }
	public int[] color(){
		if( color == null ) return null;
		int[] c = new int[4];
		for( int i=0; i<c.length; i++) c[i] = color.get(i);
		return c; 
	}
	
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		String first=",[";
		String last="";
		sb.append("["+tag);
		if(size>0){
			sb.append(first+size);
			first=",";
			last="]";
		}
		if(font_family!=null){
			sb.append(first+font_family);
			first=",";
			last="]";
		}
		if(italic){
			sb.append(first+ITALIC);
			first=",";
			last="]";
		}
		if(bold){
			sb.append(first+BOLD);
			first=",";
			last="]";			
		}
		if(under_line){
			sb.append(first+UNDER_LINE);
			first=",";
			last="]";
		}
		if(color!=null){
			sb.append(first+color);
			first=",";
			last="]";
		}
		sb.append(last+"]");
		return sb.toString();
	}
	
	public static SyntaxStyle[] get( String[] styles ){
		SyntaxStyle[] s = new SyntaxStyle[styles.length];
		for( int i=0; i<s.length; i++ ) s[i] = new SyntaxStyle(styles[i]);
		return s;
	}
	
	public static void main( String[] args ){
		String style = "[normal,[3,[3,4,5,255],italic,bold,Sans Serif]]";
		System.out.println(new SyntaxStyle(style));
	}
}
