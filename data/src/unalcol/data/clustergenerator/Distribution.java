/*
 * <copyright>
 *  Copyright 2004-2005 (Jonatan Gomez Solutions JG-Sol)
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the JML Open Source License as published by
 *  UN-Data Mining Group on the JML Open Source Website
 *  (http://dis.unal.edu.co/profesores/jgomez/projects/jml/index.htm).
 *
 *  THE JML SOFTWARE AND ANY DERIVATIVE SUPPLIED BY LICENSOR IS
 *  PROVIDED "AS IS" WITHOUT WARRANTIES OF ANY KIND, WHETHER EXPRESS OR
 *  IMPLIED, INCLUDING (BUT NOT LIMITED TO) ALL IMPLIED WARRANTIES OF
 *  MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE, AND WITHOUT
 *  ANY WARRANTIES AS TO NON-INFRINGEMENT.  IN NO EVENT SHALL COPYRIGHT
 *  HOLDER BE LIABLE FOR ANY DIRECT, SPECIAL, INDIRECT OR CONSEQUENTIAL
 *  DAMAGES WHATSOEVER RESULTING FROM LOSS OF USE OF DATA OR PROFITS,
 *  TORTIOUS CONDUCT, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 *  PERFORMANCE OF THE JML SOFTWARE.
 *
 * </copyright>
 */
package unalcol.data.clustergenerator;
import unalcol.random.raw.RawGenerator;
import unalcol.random.real.DoubleGenerator;
import unalcol.random.real.GaussianGenerator;
import unalcol.random.real.UniformGenerator;

/**
 * <p>Title: Distribution</p>
 * <p>Description: A two dimensional random distribution</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class Distribution {
  /**
   * Shape determining the distribution. Should be a close wire shape around the origin
   */
  protected Wire shape;

  /**
   * If the points are generated symmetrically around the center in the angle given
   */
  protected boolean symmetric = true;

  /**
   * If the points are generated with a Guassian or uniform distribution
   */
  protected boolean gaussian = true;


  /**
   * Creates a two dimensional distribution
   * @param _shape Shape determining the distribution
   */
  public Distribution (Wire _shape, boolean _symmetric, boolean _gaussian) {
    shape = _shape;
    symmetric = _symmetric;
    gaussian = _gaussian;
  }

  /**
   * Generates a point with the given distribution in the given direction
   * @param center Center of the distribution
   * @param t  Point on the wire shape used for generating the point
   * @return A point following the distribution and the direction given
   */
  public double[] generate( double[] center, double t ){
    double[] p = shape.getPoint(t);
    //double[] zero = new double[]{0.0,0.0};
    //double magnitude = shape.getMagnitude(zero, p);
    //double angle = shape.getAngle(zero, p);
    DoubleGenerator g;
    if (gaussian) {
      g = new GaussianGenerator(0.0, 1.0);
    } else {
      g = new UniformGenerator(0.0, 1.0);
    }
    double[] q = (double[]) center.clone();
    double r = Math.abs(g.next());
    if (symmetric && RawGenerator.bool(this)) {
      r *= -1.0;
    }

    Restriction.space.fastMultiply(p, r);
    Restriction.space.fastPlus(q, p);
//    comp[0] += r*Math.cos(angle);
//    comp[1] += r*Math.sin(angle);
    return q;
  }
}
