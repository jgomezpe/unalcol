package unalcol.gui.editor.rsyntax;

import javax.swing.JScrollPane;
import javax.swing.text.JTextComponent;

import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;

import unalcol.gui.editor.AWTEditor;
import unalcol.io.Tokenizer;
import unalcol.types.collection.keymap.KeyMap;

public class RSyntaxEditor extends AWTEditor{
	protected AbstractTokenMakerFactory atmf = (AbstractTokenMakerFactory)TokenMakerFactory.getDefaultInstance();
	protected RSyntaxTokenMaker tok;
	protected RTextScrollPane scroll;

    
	public RSyntaxEditor( String id ){
		super(id);
		RSyntaxTextArea textArea = (RSyntaxTextArea)editArea;
		atmf.putMapping("text/"+id, "unalcol.gui.editor.rsyntax.RSyntaxTokenMaker");
		textArea.setSyntaxEditingStyle("text/"+id);
		textArea.setCodeFoldingEnabled(true);
		tok = RSyntaxTokenMaker.lastInstance;
	}
    
	@SuppressWarnings("unchecked")
	public void setTokenizer( Tokenizer tokenizer, KeyMap<Integer, ?> converter ){ this.tok.setTokenizer(tokenizer, (KeyMap<Integer,Integer>)converter); }
	
	public JTextComponent editArea(){
		if( editArea==null ) editArea = new RSyntaxTextArea();
		return editArea;	
	}
	
	public JScrollPane scroll(){
		if( scroll==null ) scroll = new RTextScrollPane((RSyntaxTextArea)editArea);
		return scroll; 
	}
}