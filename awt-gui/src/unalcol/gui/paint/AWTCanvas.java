package unalcol.gui.paint;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

import unalcol.gui.paint.Color;
//
//Quilt Sewer Machine 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/quilt/tree/master/quilt/
//
/**
*
* AWTDrawer
* <P>Simple GUI for drawing quilts.
*
* <P>
* <A HREF="https://github.com/jgomezpe/quilt/tree/master/quilt_computer/src/quilt/computer/AWTDrawer.java" target="_blank">
* Source code </A> is available.
*
* <h3>License</h3>
*
* Copyright (c) 2017 by Jonatan Gomez-Perdomo. <br>
* All rights reserved. <br>
*
* <p>Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
* <ul>
* <li> Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
* <li> Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
* <li> Neither the name of the copyright owners, their employers, nor the
* names of its contributors may be used to endorse or promote products
* derived from this software without specific prior written permission.
* </ul>
* <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
* AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*
*
*
* @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
* (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
* @version 1.0
*/
public class AWTCanvas extends ScaledCanvas{
	
	protected Graphics g;
	
	public AWTCanvas( Graphics g, int scale ){
		this.g = g;
		this.scale = scale;
	}
	
	@Override
	public void drawLine(int start_x, int start_y, int end_x, int end_y){
		g.drawLine(scale(start_x),scale(start_y),scale(end_x),scale(end_y));
	}

	@Override
	public void drawString(int x, int y, String str) {
		g.drawString(str, scale(x), scale(y));
	}
	
	public static java.awt.Color convert( Color color ){ return new java.awt.Color(color.red(),color.green(),color.blue(),color.alpha()); }
	public static Color convert( java.awt.Color color ){ return new Color( color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()); }

	@Override
	public Color setColor(Color color) {
		java.awt.Color awt_color = g.getColor();
		Color quilt_color = convert( awt_color ); 
		g.setColor( convert(color) );
		return quilt_color;
	}

	@Override
	public void drawImage(int x, int y, int width, int height, int rotation, Object obj) {
		Image img = ((Image)obj).getScaledInstance(scale(width), scale(height), Image.SCALE_SMOOTH);
		double rotationRequired = Math.toRadians(rotation);
		int cx = img.getWidth(null) / 2;
		int cy = img.getHeight(null) / 2;
		AffineTransform identity = new AffineTransform();
		AffineTransform trans = new AffineTransform();
		trans.setTransform(identity);
		trans.rotate( rotationRequired, cx, cy );
		// Drawing the rotated image at the required drawing locations
		Graphics2D g2d = (Graphics2D)g;
		AffineTransform orTr= g2d.getTransform();
		AffineTransform transX = new AffineTransform();
		transX.setTransform(identity);
		transX.translate(scale(x), scale(y));
		g2d.setTransform(transX);
		g2d.drawImage(img, trans, null);
		g2d.setTransform(orTr);
	}

	@Override
	public void drawPolygon(int[] x, int[] y) {
		g.fillPolygon(scale(x), scale(y), x.length);
	}
	
	@Override
	public void drawPolyline(int[] x, int[] y) {
		g.drawPolyline(scale(x), scale(y), x.length);
	}

	@Override
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		g.drawArc(scale(x), scale(y), scale(width), scale(height), startAngle, arcAngle);
	}

	@Override
	public void drawFillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
		g.fillArc(scale(x), scale(y), scale(width), scale(height), startAngle, arcAngle);
	}
}