package unalcol.gui.editor.simple;

import unalcol.io.Tokenizer;
import unalcol.collection.keymap.Immutable;

public interface SyntaxEditComponent {
	public void setTokenizer(Tokenizer tokenizer, Immutable<Integer,String> token_style);
	public void setStyle( Immutable<String, SyntaxStyle> styles );
	public String getText();
	public void setText( String txt );
	public void update();
}
