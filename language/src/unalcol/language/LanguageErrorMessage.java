package unalcol.language;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class LanguageErrorMessage {
    protected int row;
    protected int column;
    protected int consumed;
    protected int symbol;
    public LanguageErrorMessage( int row, int column, int consumed, int symbol ) {
        this.row = row;
        this.column = column;
        this.symbol = symbol;
    }
    public int getRow(){ return row; }

    public int getColumn(){ return column; }

    public int consumed(){ return consumed; }

    public int symbol(){ return symbol; }

}
