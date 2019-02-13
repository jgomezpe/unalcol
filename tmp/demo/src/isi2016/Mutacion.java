package isi2016;

public class Mutacion {
	protected double prob = -1.0;
	public Mutacion(){}
	
	public Mutacion(double prob){
		this.prob = prob;
	}
	
	public boolean[] aplicar( boolean[] chrom ){
		boolean[] copia = chrom.clone();
		double p = prob<0.0?1.0/chrom.length:prob;
		for( int i=0; i<copia.length; i++ ){
			if( Math.random() < p ){
				copia[i] = !copia[i];
			}
		}
		return copia;
	}
}