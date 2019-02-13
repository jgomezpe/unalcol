package unalcol.agents.examples.sudoku;

import unalcol.agents.simulate.util.Language;
import java.io.*;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SudokuLanguage implements Language{
    public static final int DIGITS = 9;
    public static final String NOP = "NOP";
    public static final String SOLVED = "solved";

    public SudokuLanguage() {}

    public int getActionIndex( String action ){
        if( action.equals(NOP) ){
            return (getActionsNumber() - 1);
        }else{
            int[] values = new int[3];
            int k = 0;
            try {
                StreamTokenizer tokenizer = new StreamTokenizer(new
                        StringReader(
                                action));
                while (k < 3 && tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                    if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {

                        values[k] = (int) (tokenizer.nval);
                        k++;
                    }
                }
                return values[0] * DIGITS * DIGITS + values[1] * DIGITS +
                        values[2] - 1;
            } catch (Exception e) {
                return -1;
            }
        }
    }

    public int getPerceptIndex( String percept ){
        if( percept.equals(SOLVED) ){
            return (getPerceptsNumber() - 1);
        }else{
            int[] values = new int[2];
            int k = 0;
            try {
                StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(
                        percept));
                while (k < 2 && tokenizer.nextToken() != StreamTokenizer.TT_EOF) {
                    if (tokenizer.ttype == StreamTokenizer.TT_NUMBER) {

                        values[k] = (int) (tokenizer.nval);
                        k++;
                    }
                }
                return values[0] * DIGITS + values[1];
            } catch (Exception e) {
                return -1;
            }
        }
    }

    public String getAction( int index ){
        if( index < getActionsNumber() - 1 ){
            int row = index / (DIGITS * DIGITS);
            index -= row * DIGITS * DIGITS;
            int column = index / DIGITS;
            index -= column * DIGITS;
            int value = index;
            return "<" + row + "," + column + "," + (value + 1) + ">";
        }else{
            return NOP;
        }
    }

    public String getPercept( int index ){
        if( index < getPerceptsNumber() - 1 ){
            int row = index / DIGITS;
            index -= row * DIGITS;
            int column = index;
            return "<" + row + "," + column + ">";
        }else{
            return SOLVED;
        }
    }

    public int getActionsNumber(){
           //ROW*COLUMN*VALUE + NOP
      return DIGITS*DIGITS*DIGITS + 1;
    }

    public int getPerceptsNumber(){
            //ROW*COLUMN +  SOLVED
       return DIGITS*DIGITS + 1;
    }

    public static void main( String[] args ){

      SudokuLanguage language = new SudokuLanguage();

      int nActions = language.getActionsNumber();
      for( int i=0; i<nActions; i++ ){
        String action = language.getAction(i);
        System.out.println( i + ":" + action + ":" + language.getActionIndex(action));
      }

      int nPerceptions = language.getPerceptsNumber();
      for( int i=0; i<nPerceptions; i++ ){
        String perception = language.getPercept(i);
        System.out.println( i + ":" + perception );
      }

    }
}
