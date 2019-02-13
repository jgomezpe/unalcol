/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised.classification;

import unalcol.learn.supervised.InputOutputPair;
import unalcol.types.collection.Collection;
import unalcol.types.collection.vector.SortedVector;
import unalcol.types.integer.IntegerOrder;
import unalcol.types.integer.array.IntArray;

/**
 *
 * @author jgomez
 */
public class LabeledArrayCollectionUtil<T> {
    
    public int[] classes( Collection<InputOutputPair<T,Integer>> set ){
        SortedVector<Integer> v = new SortedVector<Integer>( new IntegerOrder() );
        for( InputOutputPair<T,Integer> k:set ){
            if( !v.contains(k.output()) ){
                v.add(k.output());
            }    
        }
        int[] c = new int[v.size()];
        for( int i=0; i<c.length; i++ ){
            c[i] = v.get(i);
        }
        return c;
    }
    
    
	public int[][] separatedByClass(Collection<InputOutputPair<T,Integer>> set, int classes){
        int[] counter = new int[classes];
        for(  InputOutputPair<T,Integer> k:set ){
            counter[k.output()]++;
        }
        int[][] groups = new int[classes][];
        for( int i=0; i<classes; i++ ){
            groups[i] = new int[counter[i]];
            counter[i] = 0;
        }        
        int i=0;
        for(  InputOutputPair<T,Integer> k:set ){
            groups[k.output()][counter[k.output()]] = i;
            counter[k.output()]++;
            i++;
        }
        return groups;
    }
    
    public void replace_labels( Collection<InputOutputPair<T,Integer>> set,
            int[] classes ){
        for( InputOutputPair<T,Integer> k:set ){
            k.setOutput(IntArray.find(classes, k.output()));
        }        
    }
}
