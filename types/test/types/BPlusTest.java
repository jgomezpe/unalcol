/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package types;

import unalcol.random.integer.IntUniform;
import unalcol.types.collection.tree.bplus.BPlus;
import unalcol.types.integer.IntegerOrder;

/**
 *
 * @author Jonatan
 */
public class BPlusTest {
    public static void paint(BPlus<Integer> tree){
        for(Integer j : tree){
            System.out.print(j + "#");
        }
        System.out.println();
    }
    
    public static void main( String[] args ){
        IntUniform g = new IntUniform(100);
        int[] numbers = g.generate(200);
        BPlus<Integer> tree = new BPlus<>(4, new IntegerOrder());
        for( int i=0; i<numbers.length; i++ ){
//            System.out.println( "root" );
//            tree.root.print( 0, true );
            System.out.println( i + " trying .... " + numbers[i]);
            if( tree.add(numbers[i]) ){
                System.out.println( "Done " + numbers[i]);
//                tree.print();
                System.out.println("***************");
            }else{
                System.out.println( "Already there... " + numbers[i]);
            }
            paint(tree);
        }
        
        
        for( int i=0; i<30; i++ ){
            System.out.println( i + " removing.... " + numbers[i]);
            if( tree.del(numbers[i]) ){
                System.out.println( "Done " + numbers[i]);
//                tree.print();
                System.out.println("***************");
            }else{
                System.out.println( "Is not there... " + numbers[i]);
            }
        }
        for(Integer j : tree){
            System.out.print(j + "#");
        }
        System.out.println();
    }
    
}
