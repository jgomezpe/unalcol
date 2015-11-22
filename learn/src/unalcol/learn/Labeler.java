/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn;

import unalcol.types.collection.array.ArrayCollection;

/**
 *
 * @author jgomez
 */
public interface Labeler<T> {    
    public int[] label( ArrayCollection<T> set );
}
