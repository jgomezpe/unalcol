package unalcol.types.real.matrix;
import java.io.*;

import unalcol.io.*;


/**
 * <p>Title: DoubleMatrixPersistent </p>
 * <p>Description: A double matrix persistent method</p>
 * <p>Copyright: Copyright (c) 2009</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */

public abstract class DoubleMatrixPersistent extends Write<double[][]>{
    protected double[][] theBase = new double[0][];

    /**
     * Returns the Class that owns the PlugIn
     * @return Class The PlugIns owner class
     */
    public Object owner() {
        return theBase.getClass();
    }

    /**
     * Writes an array to the given writer
     * @param obj array to write
     * @param out The writer object
     * @throws IOException IOException
     */
    public abstract void write(double[][] obj, Writer out) throws IOException;

}
