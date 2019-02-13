/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.unsupervised;

import unalcol.learn.Learner;
import unalcol.types.collection.Collection;

/**
 *
 * @author jgomez
 */
public abstract class UnsupervisedLearner<T> extends 
        Learner<Collection<T>, T, Integer> {    
}

