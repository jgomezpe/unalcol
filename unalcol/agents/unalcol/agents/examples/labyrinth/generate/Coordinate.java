package unalcol.agents.examples.labyrinth.generate;

public class Coordinate {
	
	public int x;
	public int y;
	
	public Coordinate() {}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int toInteger(int ysize) {
		return x * ysize + y;
	}
	
	public static Coordinate toCoordinate(int coord, int ysize) {
		return new Coordinate(coord / ysize, coord % ysize);
	}

}
