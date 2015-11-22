/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised;

import unalcol.learn.Recognizer;
import unalcol.algorithm.Algorithm;
import unalcol.types.collection.Collection;

/**
 *
 * @author jgomez
 */
public abstract class SupervisedLearning<T> extends 
        Algorithm<Collection<LabeledObject<T>>, Recognizer<T>> {    
}
