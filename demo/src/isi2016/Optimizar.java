package isi2016;

public class Optimizar {
	
	public static double f(double[] x){
		double y = x[0]*x[0] - x[1] + 3.0*x[0] + 3.0*x[1]*x[1];
		return y;
	} 
	
	public static double[] df(double[] x){
		double[] y = new double[]{2.0*x[0] +3.0, 6.0*x[1] - 1.0};
		return y;
	} 
	
	public static double[] random( int D, double MAX ){
		double LEN = MAX*2.0;
		double[] x = new double[D];
		for( int i=0; i<D; i++ ){
			x[i] = Math.random() * LEN - MAX;
		}
		return x;		
	}
	
	public static String print(double[] x){
		StringBuilder sb = new StringBuilder();
		for( int i=0; i<x.length; i++){
			sb.append(x[i]);
			sb.append(' ');
		}
		return sb.toString();
	}

	public static double[] gradiente( int ITERS, double alpha, double miu ){
		int D = 2;
		double MAX = 10.0;
		double[] x = random(D,MAX);
		double[] da = new double[D];
		double fx = f(x);
		for( int i=0; i<ITERS; i++){
			double[] y = x.clone();
			double[] d = df(x);
			for( int k=0; k<D; k++){
				d[k] = -alpha*d[k]+miu*da[k];
				y[k] += d[k];
			}
			double fy = f(y);
			x = y;
			fx = fy;
			da = d;
			System.out.println( print(x) + ":" + fx);
		}
		return x; 
	}
	
	public static double[] ascenso_colina( int ITERS ){
		int D = 2;
		double MAX = 10.0;
		double[] x = random(D,MAX);
		double fx = f(x);
		for( int i=0; i<ITERS; i++){
			double[] y = x.clone();
			for( int k=0; k<D; k++){
				y[k] += Math.random()*4.0 - 2.0;
			}
			double fy = f(y);
			if( fy <= fx ){
				x = y;
				fx = fy;
			}
			System.out.println( print(x) + ":" + fx );
		}
		return x; 
	}
	
	public static double[] templado_simulado( int ITERS ){
		int D = 2;
		double MAX = 10.0;
		double[] x = random(D,MAX);
		double fx = f(x);
		for( int i=0; i<ITERS; i++){
			double[] y = x.clone();
			for( int k=0; k<D; k++){
				y[k] += Math.random()*4.0 - 2.0;
			}
			double fy = f(y);
			if( fy <= fx || Math.random() <= 5.0/i){
				x = y;
				fx = fy;
			}
			System.out.println( print(x) + ":" + fx );
		}
		return x; 
	}
	
	public static String toString(Glovito[] pop, int[] a){
		StringBuilder sb = new StringBuilder();
		int min = a[0];
		int max = a[0];
		double avg = a[0];
		for( int i=1; i<pop.length; i++ ){
			if( a[i] < min){
				min = a[i];
			}else{
				if( a[i] > max ){
					max = a[i];
				}
			}
			avg += a[i];
		}
		avg /= a.length;
		sb.append(min);
		sb.append(',');
		sb.append(avg);
		sb.append(',');
		sb.append(max);
		
/*
  		for( int i=0; i<pop.length; i++ ){
			sb.append(pop[i]);
			sb.append(a[i]);
			sb.append('\n');
			sb.append("##################");
		}
*/		
		return sb.toString();
	}
	
	public static int selecciona( int[] a ){
		int[] cuatro_fantasticos = new int[4];
		for( int i=0; i<cuatro_fantasticos.length; i++){
			cuatro_fantasticos[i] = (int)(Math.random()*a.length); 
		}
		int k = 0;
		for( int i=1; i<cuatro_fantasticos.length; i++ ){
			if( a[cuatro_fantasticos[i]] > a[cuatro_fantasticos[k]]){
				k = i;
			}
		}
		return cuatro_fantasticos[k];
	}
	
	public static void ga(){
		Mutacion m = new Mutacion();
		Cruce c = new Cruce();
		int[] ambiente= new int[]{1,0,1,1,0,1,1,1,0,1,1};
		Adaptabilidad f = new Adaptabilidad(ambiente);
		int POP_SIZE = 30;
		int[] a = new int[POP_SIZE];
		Glovito[] pop = new Glovito[POP_SIZE];
		for( int i=0; i<POP_SIZE; i++){
			pop[i] = new Glovito();
			a[i] = f.valor(pop[i]);			
		}
		System.out.print("0,");
		System.out.println(toString(pop,a));
		int MAXITERS = 100;
		for( int i=1; i<=MAXITERS; i++ ){
			Glovito[] hijo = new Glovito[POP_SIZE];
			int[] a_hijo = new int[POP_SIZE];
			for( int k=0; k<POP_SIZE; k+=2){
				Glovito padre = pop[selecciona(a)];
				Glovito madre = pop[selecciona(a)];
				boolean[][] cr = c.aplicar(padre.codifica(), madre.codifica());
				cr[0] = m.aplicar(cr[0]);
				cr[1] = m.aplicar(cr[1]);
				hijo[k] = new Glovito(cr[0]);
				hijo[k+1] = new Glovito(cr[1]);
				a_hijo[k] = f.valor(hijo[k]);
				a_hijo[k+1] = f.valor(hijo[k+1]);
			}
			pop = hijo;
			a = a_hijo;
			System.out.print(i + ",");
			System.out.println(toString(pop,a));
		}
	}
	
	public static void main( String[] args ){
		//ascenso_colina(1000);
		//gradiente(1000, 0.5, 1.0);
		//templado_simulado(1000);
		ga();
	}
}
