package unalcol.gui.editor.rsyntax;
import javax.swing.Action;
import javax.swing.text.Segment;

import org.fife.ui.rsyntaxtextarea.OccurrenceMarker;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenImpl;
import org.fife.ui.rsyntaxtextarea.TokenMaker;

import unalcol.iterator.Position2DTrack;
import unalcol.io.Tokenizer;
import unalcol.language.generalized.GeneralizedToken;
import unalcol.collection.Collection;
import unalcol.collection.KeyMap;

public class RSyntaxTokenMaker implements TokenMaker{
	public static RSyntaxTokenMaker lastInstance=null;
	
	protected Tokenizer tokenizer;
	protected KeyMap<Integer, Integer> converter;
	protected TokenImpl firstToken=null;
	protected TokenImpl lastToken=null;
	protected int src;
	
	public RSyntaxTokenMaker(){
		lastInstance = this;
	}
	
	public void setTokenizer( int src, Tokenizer tokenizer, KeyMap<Integer, Integer> converter ){
		this.tokenizer = tokenizer;
		this.converter = converter;
		this.src = src;
	}
	
	protected void addToken( TokenImpl token ){
		if( firstToken == null ){
			firstToken = token;
			lastToken = firstToken;
		}else{
			lastToken.setNextToken(token);
			lastToken = token;
		}
	}
	
	protected void resetTokenList(){
		firstToken = lastToken = null;
	}
	
	@Override
	public void addNullToken(){ addToken( new TokenImpl() ); }

	@Override
	public void addToken(char[] line, int begin, int end, int type, int startOffset) {
		addToken( new TokenImpl(line, begin, end, startOffset, type, 0) ); 
	}

	@Override
	public int getClosestStandardTokenTypeForInternalType(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getCurlyBracesDenoteCodeBlocks(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Action getInsertBreakAction() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLastTokenTypeOnLine(Segment arg0, int arg1) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] getLineCommentStartAndEnd(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getMarkOccurrencesOfTokenType(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OccurrenceMarker getOccurrenceMarker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean getShouldIndentNextLineAfter(Token arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Token getTokenList(Segment text, int startTokenType, int startOffset) {
		char[] array = text.array;
		int offset = text.offset;
		int newStartOffset = startOffset - offset;
		int currentTokenStart = offset;

		resetTokenList();
		String input = text.toString();
		int count = input.length();
		if( input != null && input.length()>0 ){
			Collection<GeneralizedToken<Integer>> token = tokenizer.apply(input, src);
			for( GeneralizedToken<Integer> t:token ){
				int start = ((Position2DTrack)t.pos()).column()-1 + offset;
				if( start>currentTokenStart ) addToken(array, currentTokenStart,start-1, Token.WHITESPACE, newStartOffset+currentTokenStart);
				currentTokenStart = start;
				try{ addToken(array, currentTokenStart, start+t.length()-1, converter.get(t.type()), newStartOffset+currentTokenStart); }catch(Exception e){}
				currentTokenStart += t.length();
			}
		}
		if( currentTokenStart-offset < count ) addToken(array, currentTokenStart,offset+count-1, Token.WHITESPACE, newStartOffset+currentTokenStart);
		addNullToken();
		return firstToken;
	}

	@Override
	public boolean isIdentifierChar(int arg0, char arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isMarkupLanguage() {
		// TODO Auto-generated method stub
		return false;
	}

}
