/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.data.plaintext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class LabelsFile {
    public static int[] load(String fileName) throws IOException{
        // Hashtable<String,Integer> table = new Hashtable<>();
        Vector<Integer> vals = new Vector<Integer>();
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        while( line != null ){
            vals.add(Integer.parseInt(line));
            line = file.readLine();
        }
        //System.out.println( "Clusters =" + k);
        file.close();
        int[] labels = new int[vals.size()];
        for( int i=0; i<labels.length; i++){
            labels[i] = vals.get(i);
        }
        return labels;
    }

    public static int[] load_relabel(String fileName) throws IOException{
        Hashtable<String,Integer> table = new Hashtable<>();
        Vector<Integer> vals = new Vector<Integer>();
        int k=0;
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        while( line != null ){
            if( !table.containsKey(line) ){
                table.put(line, k);
                vals.add(k);
                k++;
            }else{
                vals.add(table.get(line));
            }
            line = file.readLine();
        }
        //System.out.println( "Clusters =" + k);
        file.close();
        int[] labels = new int[vals.size()];
        for( int i=0; i<labels.length; i++){
            labels[i] = vals.get(i);
        }
        return labels;
    }
    
    public static void main(String[] args){
        try{
            String fileName = "/home/jgomez/Repository/data/misc/datasets/tr11.mat.rclass";        
            int[] v = load(fileName);
            for( int i=0; i<v.length; i++){
                System.out.println(v[i]);
            }
            System.out.println("##"+v.length);
        }catch(Exception e){
            e.printStackTrace();
        }
    }        
}
