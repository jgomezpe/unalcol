package unalcol.agents.examples.labyrinth.generate;

import unalcol.random.util.Shuffle;
import unalcol.types.collection.list.Stack;

public class PathBuilder {
	
	private static Shuffle<Integer> shuffle = new Shuffle<Integer>();
	
	static final int[] dx = { 0, 1, 0, -1};
	static final int[] dy = {-1, 0, 1,  0};
	
	public static class Path {
		int[][] dir;
		Coordinate lastLocation;
		
		public Path(int[][] dir, Coordinate lastLocation) {
			this.dir = dir;
			this.lastLocation = lastLocation;
		}
	}
	
	public static void build(LabyrinthStructure structure, Coordinate start, boolean[][] marked) {
		Path path = findPath(structure, start, marked);
		Coordinate loc = path.lastLocation;
		while(path.dir[loc.x][loc.y] != -1) {
			marked[loc.x][loc.y] = true;
			Coordinate next = moveTo(structure, loc, path.dir[loc.x][loc.y]);
			structure.removeWall(loc.x, loc.y, path.dir[loc.x][loc.y]);
			structure.removeWall(next.x, next.y, (path.dir[loc.x][loc.y] + 2) % 4);
			loc = next;
		}
		marked[loc.x][loc.y] = true;
	}
	
	public static Path findPath(LabyrinthStructure structure, Coordinate start, boolean[][] marked) {
		boolean[][] visited = new boolean[structure.xsize()][structure.ysize()];
		int[][] dir = new int[structure.xsize()][structure.ysize()];
		Stack<Coordinate> S = new Stack<Coordinate>();
		int[] dirs = { 0, 1, 2, 3 };
		Coordinate loc = start;
		S.add(loc);
		dir[loc.x][loc.y] = -1;
		visited[loc.x][loc.y] = true;
		while (!S.isEmpty() && !marked[loc.x][loc.y]) {
			loc = S.pop();
			shuffle.apply(dirs);
			for (int d = 0; d < dirs.length; d++) {
				Coordinate next = moveTo(structure, loc, dirs[d]);
				if (next != null && !visited[next.x][next.y]) {
					dir[next.x][next.y] = (dirs[d] + 2) % 4;
					S.add(next);
					visited[next.x][next.y] = true;
				}
			}
		}
		return new Path(dir, loc);
	}

	public static Coordinate moveTo(LabyrinthStructure structure, Coordinate loc, int dir) {
		Coordinate next = new Coordinate(loc.x + dx[dir], loc.y + dy[dir]);
		return (structure.isValidLocation(next.x, next.y))? next : null;
	}
	
}
