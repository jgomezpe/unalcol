package isi2016;

public class Cruce {
	public boolean[][] aplicar( boolean[] uno, boolean[] dos ){
		boolean[] cuno = uno.clone();
		boolean[] cdos = dos.clone();
		int pos = (int)(Math.random() * (uno.length-1)) + 1;
		System.arraycopy(uno, pos, cdos, pos, uno.length-pos);
		System.arraycopy(dos, pos, cuno, pos, uno.length-pos);
		return new boolean[][]{cuno, cdos};
	}
}