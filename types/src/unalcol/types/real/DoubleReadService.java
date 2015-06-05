/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real;
import unalcol.io.*;
import unalcol.types.integer.IntegerReadService;

import java.io.StringReader;

/**
 *
 * @author jgomez
 */
public class DoubleReadService extends Read<Double>{

    @Override
    public Double read(Double d, ShortTermMemoryReader reader) throws RowColumnReaderException{
        return read(reader);
    }
    public Double read(ShortTermMemoryReader reader) throws RowColumnReaderException{
        try{
            IntegerReadService.removeSpaces(reader);
            char c = (char)reader.read();
            if( Character.isDigit(c) || c=='-' || c=='+' ){
                StringBuilder sb = new StringBuilder();
                sb.append(c);
                IntegerReadService.readDigitStar(reader, sb);
                c = (char)reader.read();
                if( c == '.' ){
                    sb.append(c);
                    IntegerReadService.readDigitStar(reader, sb);
                    c = (char)reader.read();
                }

                if( c=='e' || c=='E' ){
                    sb.append(c);
                    c = (char)reader.read();
                    if( c=='-' || c=='+' ){
                        sb.append(c);
                    }else{
                        IntegerReadService.back(c, reader);
                    }
                    IntegerReadService.readDigitStar(reader, sb);
                }else{
                    IntegerReadService.back(c, reader);
                }
                return Double.parseDouble(sb.toString());
            }
            throw new Exception("Unexpected symbol " + c);
        }catch( Exception e ){
            throw reader.getException("Double Parser Error "+e.getMessage());
        }
    }


    public static void main(String[] args ){
        // Reflection
        StringReader r = new StringReader("    -123.44555e-123");
        ShortTermMemoryReader reader = new ShortTermMemoryReader(r);
        DoubleReadService service = new DoubleReadService();
        try{
           Double x = service.read(reader);
           System.out.println(x);
        }catch(Exception e ){
            e.printStackTrace();
        }
    }
}

