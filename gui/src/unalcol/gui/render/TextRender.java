package unalcol.gui.render;

public interface TextRender extends Render{
	public void render( String str );
	default void render( Object obj ){ render(obj.toString()); }
}