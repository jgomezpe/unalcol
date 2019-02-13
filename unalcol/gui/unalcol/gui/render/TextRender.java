package unalcol.gui.render;

public interface TextRender extends Render{
	public void render( String str );
	default void render( Object obj ){ render(obj instanceof String?(String)obj:obj.toString()); }
	public void add( String str );
	default void add( Object obj ){ add(obj instanceof String?(String)obj:obj.toString()); }	
}