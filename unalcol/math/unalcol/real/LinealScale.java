package unalcol.real;

/**
 *
 * @author jgomez
 */
public class LinealScale extends LinealScale01{
    protected LinealScale01 inverse;

    public LinealScale( double min, double max ){
        super( min, max );
        inverse = null;
    }


    public LinealScale( double originalMin, double originalMax,
                         double targetMin, double targetMax ){
        super( originalMin, originalMax );
        inverse = new LinealScale01(targetMin, targetMax);
    }

    @Override
    public double apply( double x ){
        if( inverse != null )
            return inverse.inverse(super.apply(x));
        else
            return super.apply(x);
    }

    @Override
    public double inverse( double x ){
        if( inverse != null )
            return super.inverse(inverse.apply(x));
        else
            return super.inverse(x);
    }
}