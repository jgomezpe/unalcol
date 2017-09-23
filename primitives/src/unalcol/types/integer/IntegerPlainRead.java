/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.integer;
import unalcol.io.*;
import unalcol.services.MicroService;

import java.io.*;

/**
 *
 * @author jgomez
 */
public class IntegerPlainRead<S> extends MicroService<Integer> implements Read<Integer,S>{

    public static <S> void back( char c, ShortTermMemoryReader<S> reader ){
        if( c != (char)-1 ){
            reader.back();
        }
    }

    public static <S> void readDigitStar( ShortTermMemoryReader<S> reader,
                                  StringBuilder sb ) throws IOException{
        char c = (char)reader.read();
        while( Character.isDigit(c)){
            sb.append(c);
            c = (char)reader.read();
        }
        back(c, reader);
    }

    public static <S> void removeSpaces( ShortTermMemoryReader<S> reader ) throws IOException{
        char c = (char)reader.read();
        while( Character.isSpaceChar(c)){
            c = (char)reader.read();
        }
        back(c, reader);
    }

    @Override
    public Integer read(ShortTermMemoryReader<S> reader) throws
            RowColumnReaderException{
        try{
            removeSpaces(reader);
            char c = (char)reader.read();
            if( Character.isDigit(c) || c=='-' || c=='+' ){
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                readDigitStar(reader, sb);
                return Integer.parseInt(sb.toString());
            }
            throw new Exception("Unexpected symbol " + c);
        }catch( Exception e ){
            throw reader.getException("Integer Parser Error "+e.getMessage());
        }

    }
}
