/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain.interactionfunction;

/**
 *
 * @author jgomez
 */
public class GravityLaw extends Polynomial{
    protected double G;
    protected double G_scale = 1.0;
    protected double dmm = 1.0;
    protected double D = 3.0;
    
    public GravityLaw( double G, int D, double dmm, double G_scale ){
        super( D );
        this.G = G;
        this.dmm = dmm;
        this.G_scale = G_scale;
    }

    public GravityLaw( double G, int D, double dmm ){
        super( D );
        this.G = G;
        this.dmm = dmm;
    }

    public GravityLaw( double G ){
        super( 3.0 );
        this.G = G;
    }

    public GravityLaw( double G, int D ){
        super( D );
        this.G = G;
    }

    public GravityLaw( double G, double dmm ){
        super( 3.0 );
        this.G = G;
        this.dmm = dmm;
    }

    @Override
    public double evaluate( double x ){
        return G * super.evaluate(dmm/x);
    }

    public void set_G_scale( double G_scale ){
        this.G_scale = G_scale;
    }
    
    @Override
    public void update(){
        G *= G_scale;
    }
    
    @Override
    public GravityLaw clone(){
        return new GravityLaw(G, (int)pow, dmm, G_scale);
    }
}