package unalcol.agents.examples.labyrinth.generate;

public class LabyrinthStructure {
	
	protected int[][] structure = null;
	
	public LabyrinthStructure(int xsize, int ysize) {
		structure = new int[xsize][ysize];
	}

	public int[][] getStructure() {
		return structure;
	}

	public void setStructure(int[][] structure) {
		this.structure = structure;
	}
	
	void addWall(int x, int y, int d) {
		structure[x][y] |= 1<<(d % 4);
	}
	
	void removeWall(int x, int y, int d) {
		structure[x][y] &= Integer.MAX_VALUE ^ 1<<(d % 4);
	}
	
	boolean isValidLocation(int x, int y) {
		return (x >= 0 && x < structure.length) && (y >= 0 && y < structure[0].length);
	}
	
	public int xsize() {
		return structure.length;
	}
	
	public int ysize() {
		return structure[0].length;
	}

	public boolean areThereWalls(int x, int y) {
		return structure[x][y] > 0;
	}

}
