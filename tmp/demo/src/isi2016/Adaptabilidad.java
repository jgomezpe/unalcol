package isi2016;

public class Adaptabilidad {
	protected int[] ambiente;
	public Adaptabilidad( int[] ambiente ){
		this.ambiente = ambiente;
	}
	
	public int valor( Glovito g ){
		int a = 0;
		int[] s = g.simula(ambiente);
		for( int i=0; i<s.length-1; i++ ){
			a += (s[i] == ambiente[i+1])?1:0;
		}
		return a;
	}
}
