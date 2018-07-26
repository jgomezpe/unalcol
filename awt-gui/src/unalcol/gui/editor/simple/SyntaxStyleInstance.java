package unalcol.gui.editor.simple;

import java.util.Vector;

import unalcol.gui.paint.Color;
import unalcol.gui.paint.ColorInstance;
import unalcol.util.Instance;

public class SyntaxStyleInstance implements Instance<SyntaxStyle>{
	public static final String STYLE = "style";
	protected ColorInstance c = new ColorInstance();
	
	@Override
	public SyntaxStyle load(Object[] args){
		if( args.length!=3 || !STYLE.equals(args[0]) ) return null;
		String tag = (String)args[1];
		Color color = null;
		String font_family = null;
		int size = 0;
		boolean bold = false;
		boolean italic = false;
		boolean under_line = false;
		Object[] pars = (Object[])args[2];
		for( int i=0; i<pars.length; i++ ){
			if( pars[i] instanceof String ){
				String str = (String)pars[i];
				bold = str.equals(SyntaxStyle.BOLD);
				italic = str.equals(SyntaxStyle.ITALIC);
				under_line = str.equals(SyntaxStyle.UNDER_LINE);
				if(!(bold || italic || under_line) ) font_family = str;
			}else 
				if( pars[i] instanceof Object[] ) color = c.load((Object[])pars[i]); 
				else size = (int)pars[i];
		}
		return new SyntaxStyle(tag,font_family,size,bold,italic,under_line,color);
	}

	@Override
	public Object[] store(SyntaxStyle style) {
		Vector<Object> v = new Vector<Object>();
		v.add(style.font_size());
		if(style.font_family()!=null) v.add(style.font_family());
		if(style.bold()) v.addElement(SyntaxStyle.BOLD);
		if(style.italic()) v.addElement(SyntaxStyle.ITALIC);
		if(style.under_line()) v.addElement(SyntaxStyle.UNDER_LINE);
		if(style.color()!=null) v.add(c.store(style.color()));
		Object[] pars = new Object[v.size()];
		for( int i=0; i<pars.length; i++ ) pars[i] = v.get(i);
		return new Object[]{STYLE,style.tag(),pars};
	}
}