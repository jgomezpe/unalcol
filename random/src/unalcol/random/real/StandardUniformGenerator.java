package unalcol.random.real;

import unalcol.random.raw.RawGenerator;

/**
 * <p>Uniform random number generator.</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */

public class StandardUniformGenerator extends InverseDoubleGenerator {        
    /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public double next( double x ) {
        return x;
    }        
    
    public StandardUniformGenerator(){    
        super();
    }

    public StandardUniformGenerator( RawGenerator _g ){    
        super( _g );
    }
    
    @Override
    public DoubleGenerator new_instance(){
        RawGenerator g = RawGenerator.get(this);
        return new StandardUniformGenerator(g.new_instance());
    }
}
