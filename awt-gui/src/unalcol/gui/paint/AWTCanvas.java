package unalcol.gui.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import unalcol.collection.keymap.HashMap;
import unalcol.util.FileResource;

public class AWTCanvas extends Canvas{
	protected Graphics g;
	protected HashMap<String, Image> images = new HashMap<String,Image>();

	
	public void setGraphics( Graphics g ){ this.g = g; }
	
	public void addImage( String id, Image image ){ images.set(id, image); }
	public void delImage( String id ){ images.remove(id); }
	public void clearImages(){ images.clear(); }
	
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
	
	
	@Override
	public void drawImage(int x, int y, int width, int height, int rot, boolean reflex, String image_path) {
		Image obj;
		try{
			obj = images.get(image_path);
		}catch(Exception e){
			obj = FileResource.image(image_path);
			if( obj == null ) return;
			images.set(image_path,obj);
		}
		Image img = obj.getScaledInstance(scale(width), scale(height), Image.SCALE_SMOOTH);
		double rotationRequired = Math.toRadians(rot);
		int cx = (img.getWidth(null) / 2);
		int cy = (img.getHeight(null) / 2);
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, cx, cy);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(op.filter(toBufferedImage(img), null),scale(x), scale(y), null);		
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
	public void drawArc(int x, int y, int width, int height, int startAngle, int endAngle) {
		// @TODO use quadTo (it is a quadratic bezier) in GeneralPath see https://docs.oracle.com/javase/tutorial/2d/geometry/arbitrary.html
	}

	@Override
	public void drawFillArc(int x, int y, int width, int height, int startAngle, int endAngle) {
		// @TODO use quadTo (it is a quadratic bezier) in GeneralPath and closePath().. see https://docs.oracle.com/javase/tutorial/2d/geometry/arbitrary.html		
	}


	@Override
	public void drawLine(int start_x, int start_y, int end_x, int end_y){ g.drawLine(scale(start_x), scale(start_y), scale(end_x), scale(end_y)); }

	@Override
	public void drawPolygon(int[] x, int[] y) { g.fillPolygon(scale(x),scale(y),x.length); }

	@Override
	public void drawString( int x, int y, String str ) { g.drawString(str, scale(x), scale(y)); }

	@Override
	public Color setColor(Color color) {
		java.awt.Color cawt = g.getColor(); 
		Color c = awt2color(cawt);
		cawt = color2awt(color);
		g.setColor(cawt);
		return c;
	}
	
	public static Color awt2color( java.awt.Color color ){ return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()); } 
	public static java.awt.Color color2awt( Color color ){ return new java.awt.Color(color.red(), color.green(), color.blue(), color.alpha()); }
}