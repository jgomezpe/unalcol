package unalcol.gui.paint;

import unalcol.gui.render.Render;
import unalcol.types.collection.GrowCollection;
import unalcol.types.collection.CleanableCollection;
import unalcol.types.collection.Collection;

public interface CanvasRender extends Render{
	public GrowCollection<Drawable> objects();
	
	public void setCanvas( Canvas canvas );
	
	public Canvas getCanvas();
	
	default void init(){ ((CleanableCollection)objects()).clear(); }
	
	default void add( Drawable obj ){ objects().add(obj); }

	@Override
	default void add(Object obj){ add((Drawable)obj); }
	
	default void render(){
		Collection<Drawable> c = objects();
		for( Drawable d:c ) d.draw(getCanvas()); 
	}
	
	default void render( Object obj ){ render((Drawable)obj); }
}