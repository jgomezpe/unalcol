/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.learn.supervised;

import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.vector.SortedVector;
import unalcol.types.integer.IntegerOrder;
import unalcol.types.integer.array.IntArray;

/**
 *
 * @author jgomez
 */
public class LabeledArrayCollectionUtil {
    
    public static int[] classes( @SuppressWarnings("rawtypes") ArrayCollection<LabeledObject> set ){
        SortedVector<Integer> v = new SortedVector<Integer>( new IntegerOrder() );
        int k;
        for( int i=0; i<set.size(); i++ ){
            k = set.get(i).label();
            if( !v.contains(k) ){
                v.add(k);
            }    
        }
        int[] c = new int[v.size()];
        for( int i=0; i<c.length; i++ ){
            c[i] = v.get(i);
        }
        return c;
    }
    
    
	public static int[][] separatedByClass(@SuppressWarnings("rawtypes") ArrayCollection<LabeledObject> set, int classes){
        int[] counter = new int[classes];
        for( int i=0; i<set.size(); i++ ){
            counter[set.get(i).label()]++;
        }
        int[][] groups = new int[classes][];
        for( int i=0; i<classes; i++ ){
            groups[i] = new int[counter[i]];
            counter[i] = 0;
        }        
        int k;
        for( int i=0; i<set.size(); i++ ){
            k = set.get(i).label();
            groups[k][counter[k]] = i;
            counter[k]++;
        }
        return groups;
    }
    
    public static void replace_labels( @SuppressWarnings("rawtypes") ArrayCollection<LabeledObject> set,
            int[] classes ){
    	@SuppressWarnings("rawtypes")
    	LabeledObject k;
        for( int i=0; i<set.size(); i++ ){
            k = set.get(i);
            k.setLabel(IntArray.find(classes, k.label()));
        }        
    }
}
