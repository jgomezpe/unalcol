package unalcol.gui.paint;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LinearGradientPaint;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import unalcol.collection.keymap.HashMap;
import unalcol.util.FileResource;

public class AWTCanvas extends Canvas{
	
	protected Graphics2D g;
	protected HashMap<String, Image> images = new HashMap<String,Image>();

	
	public void setGraphics( Graphics g ){ this.g = (Graphics2D)g; }
	
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
	
	public static Color awt2color( java.awt.Color color ){ return new Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()); } 
	public static java.awt.Color color2awt( Color color ){ return new java.awt.Color(color.red(), color.green(), color.blue(), color.alpha()); }

	GeneralPath path = new GeneralPath();
	
	@Override
	public void moveTo(Command c) {
		double x = scale(c.getReal(Command.X));
		double y = scale(c.getReal(Command.Y));
		path.moveTo(x, y);
	}

	@Override
	public void lineTo(Command c) {
		double x = scale(c.getReal(Command.X));
		double y = scale(c.getReal(Command.Y));
		path.lineTo(x, y);
	}

	@Override
	public void quadTo(Command c) {
		double[] x = scale(c.getRealArray(Command.X));
		double[] y = scale(c.getRealArray(Command.Y));
		path.quadTo(x[0], y[0], x[1], y[1]);
	}

	@Override
	public void curveTo(Command c) {
		double[] x = scale(c.getRealArray(Command.X));
		double[] y = scale(c.getRealArray(Command.Y));
		path.curveTo(x[0], y[0], x[1], y[1], x[2], y[2]);
	}

	@Override
	public void text(Command c) {
		double x = scale(c.getReal(Command.X));
		double y = scale(c.getReal(Command.Y));
		String str = c.getString(Command.MESSAGE);
		g.drawString(str, (int)scale(x), (int)scale(y)); 
	}

	@Override
	public void image(Command c) {
		double[] x = scale(c.getRealArray(Command.X));
		double[] y = scale(c.getRealArray(Command.Y));
		int rot = c.getInt(Command.IMAGE_ROT);
		// boolean reflex = c.getBool(Command.IMAGE_REF);
		String image_path = c.getString(Command.IMAGE_URL); 
		Image obj;
		try{
			obj = images.get(image_path);
		}catch(Exception e){
			obj = FileResource.image(image_path);
			if( obj == null ) return;
			images.set(image_path,obj);
		}
		Image img = obj.getScaledInstance((int)scale(x[1]), (int)scale(y[1]), Image.SCALE_SMOOTH);
		double rotationRequired = Math.toRadians(rot);
		int cx = (img.getWidth(null) / 2);
		int cy = (img.getHeight(null) / 2);
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, cx, cy);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		// Drawing the rotated image at the required drawing locations
		g.drawImage(op.filter(toBufferedImage(img), null),(int)scale(x[0]), (int)scale(y[0]), null);		
	}

	@Override
	public void beginPath(){ path = new GeneralPath(); }

	@Override
	public void closePath(){ path.closePath(); }

	@Override
	public void fill(){
		path.closePath();
		g.fill(path); 
	}

	@Override
	public void stroke(){ g.draw(path); }

	@Override
	public void strokeStyle(Command c) {
		if( c.valid(ColorInstance.COLOR) ) g.setColor(color2awt(color(c))); 
		if( c.valid(Command.LINEWIDTH) ) g.setStroke(new BasicStroke(c.getInt(Command.LINEWIDTH)));
		fillStyle(c);
	}

	@Override
	public void fillStyle(Command c) {
		if( c.valid(ColorInstance.COLOR) ){
			g.setPaint(color2awt(color(c)));
		}else{
			java.awt.Color sc = color2awt(color(c, Command.STARTCOLOR));
			java.awt.Color ec = color2awt(color(c, Command.ENDCOLOR));
			if( c.valid(Command.R) ){
				double x = (Double)c.x();
				double y = (Double)c.y();
				double r = c.getReal(Command.R);
				g.setPaint(new RadialGradientPaint((float)x, (float)y, (float)r, new float[]{0.0f,1.0f}, new java.awt.Color[]{sc,ec}) );
			}else{
				double[] x = (double[])c.x();
				double[] y = (double[])c.y();
				g.setPaint(new LinearGradientPaint((float)x[0], (float)y[0], (float)x[1], (float)y[1], new float[]{0.0f,1.0f}, new java.awt.Color[]{sc,ec}));
			}
		}
	}	
}