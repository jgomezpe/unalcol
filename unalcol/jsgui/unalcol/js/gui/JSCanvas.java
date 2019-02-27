package unalcol.js.gui;

import unalcol.gui.paint.Canvas;
import unalcol.gui.paint.Color;
import unalcol.gui.paint.Command;

public class JSCanvas extends Canvas{
	protected Color color=null;
	protected JSCanvasRender render;
	
	public JSCanvas( JSCanvasRender render ){ this.render = render; }
	
	@Override
	public void command( Command json ){ render.execute("canvas.draw", json.toString()); }

	@Override
	public void beginPath(){ command( Command.beginPath() ); }

	@Override
	public void closePath(){ command( Command.closePath() ); }

	@Override
	public void curveTo(Command c){ command(c); }

	@Override
	public void fill(){ command( Command.fill() ); }

	@Override
	public void fillStyle(Command c){ command(c); }

	@Override
	public void image(Command c){ command(c); }

	@Override
	public void lineTo(Command c){ command(c); }

	@Override
	public void moveTo(Command c){ command(c); }

	@Override
	public void quadTo(Command c){ command(c); }

	@Override
	public void stroke(){ command( Command.stroke()); }

	@Override
	public void strokeStyle(Command c){ command(c); }

	@Override
	public void text(Command c){ command(c); }
}