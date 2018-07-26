package unalcol.gui.editor.simple;

import unalcol.io.Tokenizer;
import unalcol.types.collection.keymap.ImmutableKeyMap;

public interface SyntaxEditComponent {
	public void setTokenizer(Tokenizer tokenizer, ImmutableKeyMap<Integer,String> token_style);
	public void setStyle( ImmutableKeyMap<String, SyntaxStyle> styles );
	public String getText();
	public void setText( String txt );
	public void update();
}
