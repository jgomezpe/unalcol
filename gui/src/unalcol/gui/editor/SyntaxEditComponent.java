package unalcol.gui.editor;

import unalcol.language.programming.lexer.Lexer;

public interface SyntaxEditComponent {
	public void setTokenizer(Lexer tokenizer, String[] token_style);
	public void setStyle( SyntaxStyle[] styles );
	public String getText();
	public void setText( String txt );
	public void update();
}
