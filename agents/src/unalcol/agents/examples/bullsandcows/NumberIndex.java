/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.bullsandcows;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author Jonatan
 */
public class NumberIndex {
    protected int[] options;
    protected int digits;
    protected int positions;
    public NumberIndex( int _digits, int _positions ){
        //Here we assume the number of positions is less or equal than the number of digits
        digits = _digits;
        positions = _positions;
        options = new int[positions+1];
        compute(digits, positions, options);
    }
    
    public static int compute(int digits, int positions, int[] options ){        
        if( options[positions] == 0 ){
            if( positions == 1 ) options[positions] = digits;
            else options[positions] = digits * compute(digits-1, positions-1, options);
        }    
        return options[positions];
    }

    public int getIndex( int[] option, int n, Vector<Integer> check ){
        if( n==option.length ) return 0;
        int pos = check.findIndex(option[n]);
        check.remove(pos);        
        n++;
        return pos*options[positions-n] + getIndex(option, n, check);
    }
    
    public int getIndex( int[] option ){
        Vector<Integer> check = new Vector<Integer>();
        for( int i=0; i<digits; i++){
            check.add(i);
        }
        return getIndex(option, 0, check);
    }
    
    public void getOption( int index, int[] option, int n, Vector<Integer> check ){
        n++;
        if( n < option.length ){
            int pos = index / options[positions-n];
            index %= options[positions-n];
            option[n-1] = check.get(pos);
            check.remove(pos);
            getOption(index, option, n, check);
        }else{
            option[n-1] = check.get(index);
        }
    }

    public int[] getOption( int index ){
        Vector<Integer> check = new Vector<Integer>();
        for( int i=0; i<digits; i++){
            check.add(i);
        }
        int[] option = new int[positions];
        getOption(index, option, 0, check);
        return option;
    }
    
    public int size(){
        return options[positions];
    }
    
    public static int[] compare( int[] one, int[] two ){
        int[] bc = new int[2];
        for( int i=0; i<one.length; i++ ){
            int j=0;
            while(j<two.length && one[i]!=two[j]){ j++; }
            if(j<two.length){
                if( i==j ) bc[0]++;
                else bc[1]++;
            }
        }
        return bc;
    }
    
}
