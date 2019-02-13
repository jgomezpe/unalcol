package unalcol.gui.editor.simple;

import javax.swing.JScrollPane;
import javax.swing.text.JTextComponent;

import unalcol.gui.editor.AWTEditor;
import unalcol.io.Tokenizer;
import unalcol.collection.KeyMap;
import unalcol.collection.keymap.Immutable;

public class SimpleAWTEditor extends AWTEditor{
	public SimpleAWTEditor(String id){ super(id); }
	
	public void setStyle(Immutable<String, SyntaxStyle> styles ){ ((SyntaxEditPanel)editArea).setStyle(styles); }
	
	public JTextComponent editArea(){
		if( editArea==null ) editArea = new SyntaxEditPanel();
		return editArea;	
	}
	
	public JScrollPane scroll(){
		if( scroll==null ) scroll = new JScrollPane();
		return scroll; 
	}
	
	@SuppressWarnings("unchecked")
	public void setTokenizer( Tokenizer tokenizer, KeyMap<Integer, ?> converter ){ ((SyntaxEditPanel)editArea).setTokenizer(tokenizer, (KeyMap<Integer, String>)converter); }
	
}