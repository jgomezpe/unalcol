package unalcol.gui.paint;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

/**
 * <p>Title: PointDrawer</p>
 *
 * <p>Description: Utility for drawing points</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Kunsamu</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class PointDrawer {
    /**
     * Constant (Type of object to draw)
     */
    public static final int SIMPLE_POINT = 0;
    /**
     * Constant (Type of object to draw)
     */
    public static final int X_POINT = 1;
    /**
     * Constant (Type of object to draw)
     */
    public static final int PLUS_POINT = 2;
    /**
     * Constant (Type of object to draw)
     */
    public static final int TRIANGLE_POINT = 3;
    /**
     * Constant (Type of object to draw)
     */
    public static final int OVAL_POINT = 4;
    /**
     * Constant (Type of object to draw)
     */
    public static final int SQUARE_POINT = 5;

    protected static AffineTransform id = new AffineTransform();

    /**
     * Draws a point according to the desired style, color and size
     * @param g Graphics2D
     * @param x int
     * @param y int
     * @param pointType int
     * @param pointSize int
     */
    public static void drawPoint(Graphics2D g, int x, int y, int pointType,
                                 int pointSize) {
        AffineTransform t = g.getTransform();
        g.setTransform(id);
        Point p = new Point(x, y);
        Point fp = new Point(x, y);
        t.transform(p, fp);
        x = fp.x;
        y = fp.y;
        switch (pointType) {
        case SIMPLE_POINT:
            g.drawLine(x, y, x, y);
            break;
        case X_POINT:
            g.drawLine(x - pointSize, y + pointSize, x + pointSize,
                       y - pointSize);
            g.drawLine(x - pointSize, y - pointSize, x + pointSize,
                       y + pointSize);
            break;
        case PLUS_POINT:
            g.drawLine(x, y + pointSize, x, y - pointSize);
            g.drawLine(x - pointSize, y, x + pointSize, y);
            break;
        case TRIANGLE_POINT:
            g.drawLine(x - pointSize, y + pointSize, x, y - pointSize);
            g.drawLine(x, y - pointSize, x + pointSize, y + pointSize);
            g.drawLine(x + pointSize, y + pointSize, x - pointSize,
                       y + pointSize);
            break;
        case OVAL_POINT:
            g.drawOval(x - pointSize, y - pointSize, 2 * pointSize,
                       2 * pointSize);
            break;
        case SQUARE_POINT:
            g.drawLine(x - pointSize, y + pointSize, x - pointSize,
                       y - pointSize);
            g.drawLine(x - pointSize, y + pointSize, x + pointSize,
                       y + pointSize);
            g.drawLine(x + pointSize, y + pointSize, x + pointSize,
                       y - pointSize);
            g.drawLine(x + pointSize, y - pointSize, x - pointSize,
                       y - pointSize);
            break;
        }
        g.setTransform(t);
    }
}
