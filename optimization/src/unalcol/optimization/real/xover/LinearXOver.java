package unalcol.optimization.real.xover;

import unalcol.clone.*;
import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;

/**
 * <p>Title: LinearXOver</p>
 * <p>Description:Applies a linear crossover. In this case the alpha is unique
 * for each component, it use two alpha, one for the first vector and one
 * for the second vector</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class LinearXOver extends RealArityTwo{
    protected RawGenerator g = new JavaGenerator();
    /**
     * default constructor
     */
    public LinearXOver() {}

    /**
     * Apply the 2-ary genetic operator over the individual genomes
     * @param c1 First Individuals genome to be modified by the genetic operator
     * @param c2 Second Individuals genome to be modified by the genetic operator
     * @return extra information of the genetic operator
     */
    @Override
    public double[][] apply(double[] c1, double[] c2) {
        try {
            double[] x = (double[]) Clone.create(c1);
            double[] y = (double[]) Clone.create(c2);
            int min = Math.min(x.length, y.length);
            double alpha = g.next();
            double alpha_1 = g.next();
            double neg_alpha = 1.0 - alpha;
            double neg_alpha_1 = 1.0 - alpha_1;
            double tx;
            double ty;
            for (int i = 0; i < min; i++) {
                tx = x[i];
                ty = y[i];
                x[i] = alpha * tx + neg_alpha * ty;
                y[i] = alpha_1 * tx + neg_alpha_1 * ty;
            }
            return new double[][]{x, y}; 
        } catch (Exception e) {}
        return null;
    }
}