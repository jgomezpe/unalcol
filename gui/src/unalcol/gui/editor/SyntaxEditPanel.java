package unalcol.gui.editor;

import java.awt.Color;
import java.util.Vector;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class SyntaxEditPanel extends JTextPane{
	protected SyntaxStyle def;
	protected SyntaxStyle[] styles;
	protected String[] token_style=null;
	protected Tokenizer tokenizer=null;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2456710620251070411L;

	public SyntaxEditPanel(){
		super();
		this.getDocument().addDocumentListener(new SyntaxDocumentListener(this));
	}
	
	public void setTokenizer(Tokenizer tokenizer, String[] token_style){
		this.tokenizer = tokenizer;
		this.token_style = token_style;
	}
	
	public void setStyle( SyntaxStyle style ){
		StyledDocument doc = this.getStyledDocument();
		Style s = doc.getStyle(style.tag());
		if( s==null ){
			Style parent;
			if( style.tag().equals(SyntaxStyle.DEF) ) parent = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
			else parent = doc.getStyle(SyntaxStyle.DEF);
			s = doc.addStyle(style.tag(),parent);
		}	
		if( style.font_family()!=null)  StyleConstants.setFontFamily(s, style.font_family());
		if( style.font_size()>0) StyleConstants.setFontSize(s,style.font_size());
		StyleConstants.setItalic(s, style.italic()); 
		StyleConstants.setBold(s, style.bold()); 
		StyleConstants.setUnderline(s, style.under_line());
		int[] c = style.color();
		if( c!=null ) StyleConstants.setForeground(s, new Color(c[0],c[1],c[2],c[3]));
	}
	
	public void setStyle( SyntaxStyle[] styles ){ for( SyntaxStyle style:styles ) setStyle(style); }

	public void setStyle( String style ){ setStyle(new SyntaxStyle(style)); }
	public void setStyle( String[] styles ){ setStyle(SyntaxStyle.get(styles)); }
}

class SyntaxDocumentListener implements DocumentListener {
	protected SyntaxEditPanel edit;
	public SyntaxDocumentListener( SyntaxEditPanel edit ){
		this.edit = edit;
	}
 
    public void syntax( int pos,int length ){
        StyledDocument doc = edit.getStyledDocument();
        Element root = doc.getDefaultRootElement(); 
        Element paragraph = root.getElement(root.getElementIndex(pos));
		int start = paragraph.getStartOffset();
		int end = length==0?paragraph.getEndOffset():pos+length;
		Vector<Token> changes = new Vector<Token>();
		while(start<end){
			Element p = root.getElement(root.getElementIndex(start));
			length = p.getEndOffset()-start;
			String code = null;
	        try{ code = doc.getText(start, length); } catch (BadLocationException e1) {}
	        if( code != null && code.length()>0 ){
				Token[] token = edit.tokenizer.tokens(code);
				for( Token t:token ){
					t.shift(start);
					changes.add(t);
				}
	        }
			start = p.getEndOffset();
		}
		String[] t_style = edit.token_style;

        Runnable doAssist = new Runnable() {
		@Override
			public void run() {
				for( Token t:changes ){
					doc.setCharacterAttributes(t.offset(),t.endOffset(),doc.getStyle(t_style[t.type()]),true);
    			}
			}
        };
        SwingUtilities.invokeLater(doAssist);
    }
    
    public void insertUpdate(DocumentEvent e) {
    	syntax(e.getOffset(),e.getLength());
    }
    
    public void removeUpdate(DocumentEvent e) {
    	int start = e.getOffset();
    	syntax(start,0);
    }
    
    public void changedUpdate(DocumentEvent e) {
        //Plain text components do not fire these events
    }
}
