package unalcol.constants;

import unalcol.i18n.I18N;

public class InnerCore {
	public static final String ESC_CHAR = "escapecharacter";
	public static final String IO = "inputoutput";
	public static final String EOI = "endofinput";
	public static final String NF = "numberformat";
	public static final String NOUNICODE = "wrongunicode";
	public static final String NOCHAR = "wrongchar";
	public static final String NOSTRING = "wrongstring";
	public static final String NOSUCHELEM = "nosuchelem";
	public static final String NOSEP = "noseparator";
	public static final String UNEXPCHAR = "unexpectedchar";
	public static final String POS2D = "rowcolumn";
	public static final String POS = "position";
	
	public static void init(){ 
		I18N.set(ESC_CHAR, "Invalid escape character \\%c" ); 
		I18N.set(EOI, "Unexpected end of input" ); 
		I18N.set(IO, "Input/Output issue %s" ); 
		I18N.set(NF, "Number format exception %s" ); 
		I18N.set(NOUNICODE, "Unicode format exception %s" ); 
		I18N.set(NOCHAR, "Character format exception %s" ); 
		I18N.set(NOSTRING, "String format exception %s" ); 
		I18N.set(NOSUCHELEM, "No such element %s"); 
		I18N.set(NOSEP, "Unavailable separator character [%c]"); 
		I18N.set(UNEXPCHAR, "Unexpected char [%c]"); 
		I18N.set(POS2D, "at row %d column %d"); 
		I18N.set(POS, "at position %d"); 
	}  
}