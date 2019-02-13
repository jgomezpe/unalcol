package isi2016;

public class Glovito {
  protected int[][] estado;
  protected int[][] salida;
  
  public Glovito(){
	  estado = new int[4][2];
	  salida = new int[4][2];
	  for( int i=0; i<4; i++){
		  for( int j=0; j<2; j++ ){
			  estado[i][j] = (int)(Math.random()*4);
			  salida[i][j] = (Math.random()<0.5)?1:0;
		  }  
	  }
  }
  
  public Glovito( int[][] estado, int[][] salida){
	 this.estado = estado;
	 this.salida = salida;
  }
  
  public int[] simula( int[] entrada ){
	  int[] s = new int[entrada.length];
	  int q = 0;
	  for( int i=0; i<entrada.length; i++ ){
		  s[i] = salida[q][entrada[i]];
		  q = estado[q][entrada[i]];
	  }
	  return s;
  }
  
  public char estado( int q ){
	  switch(q){
	  	case 0: return 'A';
	  	case 1: return 'B';
	  	case 2: return 'C';
	  	case 3: return 'D';
	  }
	  return 'A';
  }
  
  public Glovito( boolean[] chrom ){
	  estado = new int[4][2];
	  salida = new int[4][2];
	  int k=0;
	  for( int i=0; i<4; i++){
		  for( int j=0; j<2; j++ ){
			  estado[i][j] = (chrom[k]?2:0) + (chrom[k+1]?1:0);
			  salida[i][j] = chrom[k+2]?1:0;
			  k +=3;
		  }  
	  }
  }
  
  public boolean[] codifica(){
	  boolean[] chrom = new boolean[3*estado.length*estado[0].length];
	  int k=0;
	  for( int i=0; i<4; i++){
		  for( int j=0; j<2; j++ ){
			  chrom[k] = (estado[i][j]>=2);
			  chrom[k+1] = (estado[i][j]%2==1);			  
			  chrom[k+2] = (salida[i][j]==1);			  
			  k +=3;
		  }  
	  }
	  return chrom;
  }
  
  @Override
  public String toString(){
	  StringBuilder sb = new StringBuilder();
	  for( int i=0; i<estado.length; i++ ){
		  for( int j=0; j<estado[i].length; j++){
			  sb.append(estado(estado[i][j]));
			  sb.append(',');
			  sb.append(salida[i][j]);
			  sb.append(' ');
		  }
		  sb.append('\n');
	  }
	  return sb.toString();
  }
}