/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real;
import unalcol.io.*;
import unalcol.types.integer.IntegerPlainRead;

/**
 *
 * @author jgomez
 */
public class DoublePlainRead extends Read<Double>{

    @Override
    public Double read(ShortTermMemoryReader reader) throws RowColumnReaderException{
        try{
            IntegerPlainRead.removeSpaces(reader);
            char c = (char)reader.read();
            if( Character.isDigit(c) || c=='-' || c=='+' ){
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                IntegerPlainRead.readDigitStar(reader, sb);
                c = (char)reader.read();
                if( c == '.' ){
                    sb.append(c);
                    IntegerPlainRead.readDigitStar(reader, sb);
                    c = (char)reader.read();
                }

                if( c=='e' || c=='E' ){
                    sb.append(c);
                    c = (char)reader.read();
                    if( c=='-' || c=='+' ){
                        sb.append(c);
                    }else{
                        IntegerPlainRead.back(c, reader);
                    }
                    IntegerPlainRead.readDigitStar(reader, sb);
                }else{
                    IntegerPlainRead.back(c, reader);
                }
                return Double.parseDouble(sb.toString());
            }
            throw new Exception("Unexpected symbol " + c);
        }catch( Exception e ){
            throw reader.getException("Double Parser Error "+e.getMessage());
        }
    }
}

