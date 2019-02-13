/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real.mutation;

import unalcol.integer.Uniform;
import unalcol.random.util.Shuffle;

/**
 *
 * @author jgomez
 */
public class PermutationPick implements PickComponents{
    protected int[] set = new int[0];
    protected int GROUP_SIZE = 2;
    protected int MAX_GROUP_SIZE = 20;
    protected int GROUP_COUNT = 0;
    protected int rep = 0;
    protected Uniform rand;

    public PermutationPick( int max_group_size ){        
        MAX_GROUP_SIZE = max_group_size;
        rand = new Uniform(MAX_GROUP_SIZE);
    }
    
    @Override
    public int[] get( int DIMENSION ) {
        if( DIMENSION != set.length ){
            set = new int[DIMENSION];
            for (int i = 0; i < DIMENSION; i++) {
                set[i] = i;
            }
            Shuffle<Integer> shuffle = new Shuffle<Integer>();
            shuffle.apply(set);
        }    
        if( rep == 5 || GROUP_COUNT >= set.length){            
            GROUP_SIZE = 1 + rand.next();
            GROUP_COUNT = 0;
            Shuffle<Integer> shuffle = new Shuffle<Integer>();
            shuffle.apply(set);
            rep = 0;
        }
        int[] indices = new int[Math.min(GROUP_SIZE, set.length-GROUP_COUNT)];
        for( int i=0; i<indices.length; i++){
           indices[i] =  set[GROUP_COUNT+i];
        }        
        GROUP_COUNT += GROUP_SIZE;
        rep++;
        return indices;
    }    
}
