package unalcol.gui.editor;

import javax.swing.JComponent;

import unalcol.gui.AWTSimpleView;
import unalcol.reflect.plugin.PlugInSet;
import unalcol.reflect.xml.XMLElement;

public class AWTEditor extends AWTSimpleView implements EditorView{
	protected SyntaxEditPanel edit_area;
	
	@Override
	public void highlight(int arg0) {}

	@Override
	public void locate(int row, int column) {
		String str = edit_area.getText();
		int caret = 0;
		int i=0;
		while( i<row ){
			while(str.charAt(caret)!='\n') caret++;
			caret++;
			i++;
		}
		edit_area.setCaretPosition(caret+column);
		edit_area.requestFocusInWindow();
	}

	@Override
	public void setText(String txt){ edit_area.setText(txt); }

	@Override
	public JComponent awt(){ return edit_area; }
	
	@Override
	public void init(XMLElement element, PlugInSet<?> builder){
		super.init(element, builder);
	}
	
	public String getText(){ return edit_area.getText(); }
	
	public int[] getPosition(){
		int pos = edit_area.getCaretPosition();
		String str = edit_area.getText();
		int caret = 0;
		int row=0;
		int column=0;
		while( caret<pos ){
			if(str.charAt(caret)!='\n') column++;
			else{
				row++;
				column=0;
			}
			caret++;
		}
		return new int[]{row,column}; 
	}
}