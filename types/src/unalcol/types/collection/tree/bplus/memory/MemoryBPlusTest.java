/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.types.collection.tree.bplus.memory;

import unalcol.types.collection.tree.bplus.ImmutableBPlus;
import unalcol.types.integer.IntegerOrder;

/**
 *
 * @author jgomez
 */
public class MemoryBPlusTest {
    public static void main( String[] args ){
       Integer[] v1 = new Integer[]{1,2,3,4,5,6};
       MemoryLeafNode<Integer> node1 = new MemoryLeafNode<>(v1,5);
       Integer[] v2 = new Integer[]{11,12,13,14,15,16};
       MemoryLeafNode<Integer> node2 = new MemoryLeafNode<>(v2,5);
       /*node1.right = node2;
       node2.left = node1;
       BPlusIterator<Integer> iter = new BPlusIterator<>(node1);
       while(iter.hasNext()){
           System.out.println(iter.next());
       }*/
       MemoryInnerNode<Integer> root = new MemoryInnerNode<>(7);
       root.insert(0, node1);
       root.insert(1, node2);
//       root.n=1;
       root.updateLeftKey();
       ImmutableBPlus<Integer> bplus = new ImmutableBPlus<>(
               new IntegerOrder(), root);
       for(Integer x:bplus){
           System.out.println("$$"+x);
       }
       
       System.out.println("Contains:"+bplus.contains(-3));
       System.out.println("Contains:"+bplus.contains(3));
       System.out.println("Contains:"+bplus.contains(5));
       System.out.println("Contains:"+bplus.contains(11));
       System.out.println("Contains:"+bplus.contains(10));

       root.remove(0);
       for(Integer x:bplus){
           System.out.println("##"+x);
       }
       System.out.println("Contains:"+bplus.contains(3));
       System.out.println("Contains:"+bplus.contains(5));
       System.out.println("Contains:"+bplus.contains(11));
       System.out.println("Contains:"+bplus.contains(10));
       
    }
    
}
