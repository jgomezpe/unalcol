package unalcol.gui.editor;

public interface SyntaxEditComponent {
	public void setTokenizer(Tokenizer tokenizer, String[] token_style);
	public void setStyle( SyntaxStyle[] styles );
	public String getText();
	public void setText( String txt );
	public void update();
}
