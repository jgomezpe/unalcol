/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.bullsandcows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import unalcol.random.raw.JavaGenerator;

/**
 *
 * @author Jonatan
 */
public class BullsCowsGame {
    public static int read( String text ){
	try{
            System.out.println(text);
	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    String s = bufferRead.readLine();
	    return Integer.parseInt(s);
	}catch(IOException e){
		e.printStackTrace();
	}
        return 0;
    }
    
    public static int[] readNumber( String text ){
	try{
            System.out.println(text);
	    BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
	    String s = bufferRead.readLine();
	    int[] opt = new int[s.length()];
            for( int i=0; i<opt.length; i++ ){
                opt[i] = s.charAt(i) - '0';
            }
            return opt;
	}catch(Exception e){
		e.printStackTrace();
	}
        return null;
    }

    public static void main( String[] args ){
        int digits = read("Digits?");
        int positions = read("Positions?");
        NumberIndex ni = new NumberIndex(digits, positions);
        SimpleBCPlayer destructor = new SimpleBCPlayer(ni);
        SimpleBCPlayer player = new SimpleBCPlayer(ni);
        //UNEquipoPlayer player = new UNEquipoPlayer(ni);
        int s = ni.size();
        JavaGenerator g = new JavaGenerator();
        int c_number = g.integer(s);
        int[] opt = ni.getOption(c_number);
        boolean winner = false;
        boolean error ;
        do{
            int[] u_opt = player.next();
            System.out.println("Your robot will try..");
            for( int x : u_opt ){
                System.out.print(x);
            }
            System.out.println();
            int[] u_bc = NumberIndex.compare(opt, u_opt);
            error = !player.check(u_opt, u_bc);
            System.out.println("Bulls (Fijas):"+u_bc[0]+ "Cows (Picas):"+u_bc[1]);
            if(u_bc[0]==positions){
                winner = true;
                System.out.println("Congratulations. You are almost as intelligent as me");
            }
            int[] m_opt = destructor.next();
            System.out.print("Destructor will try:");
            for( int x : m_opt ){
                System.out.print(x);
            }
            System.out.println();
            /*int[] bc = new int[2];
            bc[0] = read("Bulls (Fijas)?");
            bc[1] = read("Cows (Picas)?");*/
            int[] bc = NumberIndex.compare(opt, m_opt);
            System.out.println("Bulls (Fijas):"+bc[0]+ "Cows (Picas):"+bc[1]);
            error = !destructor.check(m_opt, bc);
            if( error ){
                System.out.println("You loser. You can't lie to me");
            }
            if( bc[0] == positions ){
                winner = true;
                System.out.println("As expected, I break your number (and your legs jajajaja)");
            }
        }while(!winner && !error);
    }
}