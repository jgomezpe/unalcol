package unalcol.random.raw.rngpack;

import java.util.*;

import unalcol.random.raw.*;

/**
 *
 * Ranecu is an advanced multiplicative linear congruential random number
 * generator with a period of aproximately 10<SUP>18</SUP>.
 * This class is a ported version of the Paul Houle's Ranecu which is a direct 
 * translation from Fortran of the <B>RANECU</B> subroutine published in the paper
 * <BR>
 * F. James, <CITE>Comp. Phys. Comm.</CITE> <STRONG>60</STRONG> (1990) p 329-344
 * <BR>
 * The algorithm was originally described in
 * <BR>
 * P. L'Ecuyer, <CITE>Commun. ACM.</CITE> <STRONG>1988</STRONG> (1988) p 742
 * <BR>
 *
 * <P>
 *
 * <A HREF="https://github.com/jgomezpe/unalcol/blob/master/src/unalcol/random/raw/rngpack.Ranecu.java">
 * Source code </A> is available.<BR> 
 * 
 * This class is a ported version of the Paul Houle's
 * <A HREF="http://www.honeylocust.com/RngPack/"> RngPack 1.1a implementation</A>
 *
 * @author <A HREF="http://www.honeylocust.com/"> Paul Houle </A>
 * (E-mail: <A HREF="mailto:paul@honeylocust.com">paul@honeylocust.com</A>)
 * Porting by <A HREF="http://dis.unal.edu.co/~jgomezpe"> Jonatan Gomez </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */


public class Ranecu extends SeedableGenerator {

    /**
     * Partial seed information
     */
    protected int seed1 = 12345, seed2 = 67890;

    /**
     * Creates a by default Ranecu number generator
     */
    public Ranecu() {
        super();
    }

    /**
     * Creates a Ranecu seedable generator with the given time dependent seed
     * @param seed The time information used for defining the seed
     */
    public Ranecu(Date seed) {
        super(seed);
    }

    /**
     * Creates a Ranecu seedable generator with the given seed
     * @param seed The seed
     */
    public Ranecu(long seed) {
        super(seed);
    }

    public Ranecu(int s1, int s2) {
        this(s1 * (long) Integer.MAX_VALUE + s2);
    }

    /**
     *
     * Returns a seed calculated from given seed
     * @param seed The seed
     * @return a long integer seed
     *
     */
    public long initSeed(long seed) {
        this.seed = seed;
        seed1 = (int) seed / Integer.MAX_VALUE;
        seed2 = (int) seed % Integer.MAX_VALUE;
        return seed;
    }

    /**
     * Returns a random double number
     * @return A random double number
     */
    public double next() {
        int k;

        k = seed1 / 53668;
        seed1 = 40014 * (seed1 - k * 53668) - k * 12211;
        if (seed1 < 0) seed1 = seed1 + 2147483563;

        k = seed2 / 52774;
        seed2 = 40692 * (seed2 - k * 52774) - k * 3791;
        if (seed2 < 0) seed2 = seed2 + 2147483399;

        seed = seed1 * (long) Integer.MAX_VALUE + seed2;
        int lz = seed1 - seed2;
        if (lz < 1) lz = lz + 2147483562;
        return lz * 4.656613e-10;
    }    
}