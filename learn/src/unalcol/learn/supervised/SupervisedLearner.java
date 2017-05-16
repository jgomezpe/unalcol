/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised;

import unalcol.learn.Learner;
import unalcol.types.collection.Collection;

/**
 *
 * @author jgomez
 */
public abstract class SupervisedLearner<S,T> extends 
        Learner<Collection<InputOutputPair<S,T>>, S, T> {    
}
