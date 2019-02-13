package unalcol.gui.log;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import unalcol.vc.DefaultComponent;

public class AWTLog extends DefaultComponent implements Log{
	protected LogPanel panel;
	protected Color out_color = Color.gray;
	protected Color error_color = Color.red;
	
	public AWTLog(String id, LogPanel panel){
		super(id);
		this.panel = panel;
	}
	
	public void setOutColor( Color color ){ out_color=color; }
	public void setErrorColor( Color color ){ error_color=color; }

	@Override
	public void display(boolean output){ panel.select(output); }

	 private void appendToPane(JTextPane tp, String msg, Color c){
	        StyleContext sc = StyleContext.getDefaultStyleContext();
	        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

	        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
	        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

	        int len = tp.getDocument().getLength();
	        tp.setCaretPosition(len);
	        tp.setCharacterAttributes(aset, false);
	        tp.replaceSelection(msg);
	    }
	 
	@Override
	public void error(String message){
		panel.getErrorArea().setText("");
		appendToPane(panel.getErrorArea(), message, error_color); 
		display(false);
	}

	@Override
	public void out(String message) {
		panel.getOutArea().setText("");
		appendToPane(panel.getOutArea(), message, out_color); 
		display(true);
	}
}