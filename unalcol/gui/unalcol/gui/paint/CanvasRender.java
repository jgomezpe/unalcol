package unalcol.gui.paint;

import unalcol.gui.render.Render;
import unalcol.collection.Grow;
import unalcol.collection.Cleanable;
import unalcol.collection.Collection;

public interface CanvasRender extends Render{
	public Grow<Drawable> objects();
	
	public void setCanvas( Canvas canvas );
	
	public Canvas getCanvas();
	
	default void init(){ ((Cleanable)objects()).clear(); }
	
	default void add( Drawable obj ){ objects().add(obj); }

	@Override
	default void add(Object obj){ add((Drawable)obj); }
	
	default void render(){
		Collection<Drawable> c = objects();
		for( Drawable d:c ) d.draw(getCanvas()); 
	}
	
	default void render( Object obj ){ render((Drawable)obj); }
}