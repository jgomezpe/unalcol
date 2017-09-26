package unalcol.gui.editor;

import unalcol.language.lexer.Token;

public interface Tokenizer {
	public Token[] tokens( String text );
}
