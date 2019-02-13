package unalcol.gui.log;

import java.io.OutputStream;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

/**
 * <p>Title: LogOutputStream</p>
 *
 * <p>Description: An OutputStream for the Unalcol Library. It can be used for
 * sending error messages and output messages to a log panel instaed of sending them to
 * the console</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class LogOutputStream extends OutputStream {
	/**
	 *  Maximum number of rows being stored in the LogPanel
	 */
	public static int MAX_ROWS = 1000;
	/**
	 * If the LogOutputStream will display the messages or not
	 */
	protected boolean display_messages = true;
	/**
	 * The JTextArea displaying the messages
	 */
	protected JTextPane area;

	/**
	 * Creates a Log OutputStream using the given TextArea
	 * @param area TextArea receiving the output messages
	 */
	public LogOutputStream(JTextPane area){ this.area = area; }

	/**
	 * Creates a Log OutputStream using the given TextArea
	 * @param area TextArea receiving the output messages
	 * @param display defines if the LogOutputStream will display the messages or not
	 */
	public LogOutputStream(JTextPane area, boolean display) {
		this.area = area;
		this.display_messages = display;
	}

	/**
	 * Writes the given symbol in the LogOutputStream
	 * @param b Symbol to be written in the LogOutputStream
	 */
	public void write(int b) {
		if (display_messages) {
			StyledDocument doc = area.getStyledDocument();
			try {
				doc.insertString(doc.getLength(), "" + (char) b, new SimpleAttributeSet());
			} catch (BadLocationException e) {}
			area.setCaretPosition(area.getText().length());
		}
	}
}