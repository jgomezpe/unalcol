package unalcol.gui.paint;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 * <p>Title: Grapichs2DScaler</p>
 *
 * <p>Description: Utility for scaling paint objects to dependent/independent device coordinates</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Graphics2DScaler {
    /**
     * Scale factor in the x axe
     */
    protected double sx;
    /**
     * Scale factor in the y axe
     */
    protected double sy;

    /**
     * Creates a scaling utility (dependent/independent device coordinates) with scale factor of 1.0
     */
    public Graphics2DScaler() {
        sx = 1.0;
        sy = 1.0;
    }

    /**
     * Creates a scaling utility (dependent/independent device coordinates) with the given scale factor
     * @param sx Scale factor in the x axe
     * @param sy Scale factor in the y axe
     */
    public Graphics2DScaler(double sx, double sy) {
        this.sx = sx;
        this.sy = sy;
    }

    /**
     * Scales the given paint object (according to the scale factor) to dependent device coordinates
     * @param g Graphics2D to be scaled
     */
    public void scale(Graphics2D g) {
        g.setTransform(AffineTransform.getScaleInstance(sx, sy));
    }

    /**
     * Scales the given paint object (according to the scale factor) to independent device coordinates
     * @param g Graphics2D to be scaled
     */
    public void scalePI(Graphics2D g) {
        AffineTransform tr = g.getDeviceConfiguration().getNormalizingTransform();
        tr.scale(sx, sy);
        g.setTransform(tr);
    }
}
