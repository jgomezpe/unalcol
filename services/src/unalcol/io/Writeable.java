/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.io;

import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author jgomez
 */
public interface Writeable {
    public void write(Writer writer) throws IOException;
}
