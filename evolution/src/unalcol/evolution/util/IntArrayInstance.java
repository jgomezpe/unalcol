/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.util;

import unalcol.instance.InstanceService;

/**
 *
 * @author Jonatan
 */
public class IntArrayInstance  implements InstanceService<int[]> {
    protected int MAX;
    public IntArrayInstance(int MAX){
        this.MAX = MAX;
    }
    
    @Override
    public int[] get(int[] x) {
      int[] a = new int[x.length];
      for( int i=0; i<a.length; i++){
          a[i] = (int)(Math.random() * MAX);
      }
      return a;
    }

    @Override
    public Object owner() {
        return int[].class;
    }    
}

