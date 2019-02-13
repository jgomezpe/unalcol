/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised.classification;

import unalcol.learn.Prediction;
import unalcol.math.function.Function;

/**
 *
 * @author jgomez
 */
public interface Classifier<T> extends Function<T,Prediction<Integer>> {}
