package unalcol.gui.editor;

import javax.swing.JScrollPane;
import javax.swing.text.JTextComponent;

import unalcol.collection.KeyMap;
import unalcol.io.Tokenizer;
import unalcol.vc.DefaultComponent;

public abstract class AWTEditor extends DefaultComponent implements EditorView{
	protected JTextComponent editArea=null;
	protected JScrollPane scroll=null;
	
	public abstract JTextComponent editArea();
	public abstract JScrollPane scroll();
	
	public AWTEditor(String id){
		super(id); 
		editArea = this.editArea();
		editArea.setVerifyInputWhenFocusTarget(true);
		scroll = this.scroll();
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scroll.setAutoscrolls(true);
		scroll.getViewport().add(editArea, null);
	}
	
	@Override
	public void highlight(int arg0) {}

	@Override
	public void locate(int row, int column){
		String str = editArea.getText();
		int caret = 0;
		int i=0;
		while( i<row ){
			while(str.charAt(caret)!='\n') caret++;
			caret++;
			i++;
		}
		editArea.setCaretPosition(caret+column);
		editArea.requestFocusInWindow();
	}

	@Override
	public void setText(String txt){ editArea.setText(txt); }

	public int[] getPosition(){
		int pos = editArea.getCaretPosition();
		String str = editArea.getText();
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
	
	public abstract void setTokenizer( Tokenizer tokenizer, KeyMap<Integer, ?> converter );
}