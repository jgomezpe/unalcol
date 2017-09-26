package unalcol.gui.editor;

import java.io.InputStream;

import unalcol.gui.I18N.I18NManager;
import unalcol.io.Position;

public class ErrorManager extends I18NManager{
	public static final String ERROR = "error";
	public static final String ROW = "row";
	public static final String COLUMN = "column";
	public static final String AT = "at";

	public ErrorManager(){}
	
	public ErrorManager(InputStream is) throws Exception{
		super(is);
	}
	
	public Exception error( Position pos, String c ){
		pos = new Position(pos);
		int row = pos.row();
		int column = pos.column();
		StringBuilder sb = new StringBuilder();
		sb.append(pos.toString());
		sb.append(I18NManager.MSG_SEPARATOR);
		sb.append(table.get(ERROR));
		sb.append(' ');
		sb.append(table.get(AT));
		sb.append(' ');
		sb.append(table.get(ROW));
		sb.append(' ');
		sb.append(""+(row+1));
		sb.append(' ');
		sb.append(table.get(COLUMN));
		sb.append(' ');
		sb.append(""+(column+1));
		sb.append(": ");
		sb.append(c);
		return new Exception(sb.toString());
	}	
}