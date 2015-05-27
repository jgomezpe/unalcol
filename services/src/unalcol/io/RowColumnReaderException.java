/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.io;
import java.io.IOException;

/**
 *
 * @author jgomez
 */
public class RowColumnReaderException extends IOException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected int row = 0;
    protected int column = 0;
    public RowColumnReaderException( int row, int column, String message ){
        super(message);
        this.row = row;
        this.column = column;
    }

    public int getRow(){ return row; }

    public int getColumn(){ return column; }
}
