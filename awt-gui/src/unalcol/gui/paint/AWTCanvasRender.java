package unalcol.gui.paint;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import unalcol.gui.render.AWTRender;
import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.util.Config;
import unalcol.vc.Side;

public class AWTCanvasRender extends CanvasRender implements AWTRender{
	public final static int SCALE=100;
	protected int scale;
	protected Graphics g;
	protected String id;
	protected Side side;
	protected JPanel panel = null;
	
	protected HTKeyMap<String, Image> images = new HTKeyMap<String,Image>();
	
	public AWTCanvasRender( String id ){ this.id = id; }

	public AWTCanvasRender( String id, JPanel panel ){
		this.id = id;
		this.panel = panel;
	}
	
	protected java.awt.Color convert( int[] c ){ return new java.awt.Color(c[0],c[1],c[2],c[3]); }

	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img The Image to be converted
	 * @return The converted BufferedImage
	 */
	public static BufferedImage toBufferedImage(Image img)
	{
	    if (img instanceof BufferedImage) return (BufferedImage) img;

	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();

	    // Return the buffered image
	    return bimage;
	}	
	
	public int scale(){ return scale; }
	
	protected int scale( int value ){	return value*scale/SCALE; }
	
	protected int[] scale( int[] value ){
		if( value == null ) return null;
		int[] svalue = new int[value.length];
		for( int i=0; i<svalue.length; i++ ) svalue[i] = scale(value[i]);
		return svalue;
	}
	
	public void setGraphics( Graphics g ){ this.g = g; }
	
	public void addImage( String id, Image image ){ images.set(id, image); }
	
	protected void drawImage( int x, int y, int width, int height, int rot, String id ){
		Image obj = images.get(id);
		if( obj == null ){
			obj = Config.image(id);
			if( obj == null ) return;
			images.set(id,obj);
		}
		Image img = obj.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		double rotationRequired = Math.toRadians(rot);
		int cx = img.getWidth(null) / 2;
		int cy = img.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, cx, cy);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(op.filter(toBufferedImage(img), null),x, y, null);		
/*
		AffineTransform identity = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.rotate( rotationRequired, cx, cy );
		// Drawing the rotated image at the required drawing locations
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform orTr= g2d.getTransform();
		AffineTransform transX = new AffineTransform();
		transX.setTransform(identity);
		transX.translate(x, y);
		g2d.setTransform(transX);
		g2d.drawImage(img, trans, null);
		g2d.setTransform(orTr); */		
	}
	
	@Override
	public void render(){
		for( RenderInstruction ins : instruction ){
			int[] x = scale(ins.x);
			int[] y = scale(ins.y);
			switch( ins.code ){
				case RenderInstruction.COLOR: g.setColor(convert(ins.x)); break;	
				case RenderInstruction.LINE: g.drawLine(x[0], y[0], x[1], y[1]); break;	
				case RenderInstruction.POLYGON: g.fillPolygon(x,y,x.length); break;	
				case RenderInstruction.ARC: g.drawArc(x[0], y[0], x[1], y[1], ins.x[2], ins.y[2]); break;
				case RenderInstruction.FILLARC: g.fillArc(x[0], y[0], x[1], y[1], ins.x[2], ins.y[2]); break;	
				case RenderInstruction.TEXT: g.drawString(ins.info, x[0], y[0]); break;	
				case RenderInstruction.IMAGE: drawImage(x[0], y[0], x[1], y[1], ins.x[2], ins.info); break;	
			}
		}
	}
	
	public void render( Drawable obj ){
		super.render(obj);
		Dimension d = panel.getSize();
		this.scale = obj.scale(d.width, d.height);
		panel.updateUI();
	}

	@Override
	public void setSide(Side side){ this.side = side; }

	@Override
	public Side side(){ return side; }

	@Override
	public void setId(String id) { this.id = id; }
	
	@Override
	public String id(){ return id; }

	@Override
	public void setPanel(JPanel panel) { this.panel=panel; }

	@Override
	public JPanel panel() { return panel; }
}