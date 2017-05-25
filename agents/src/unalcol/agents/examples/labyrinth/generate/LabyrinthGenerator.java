package unalcol.agents.examples.labyrinth.generate;

import unalcol.types.collection.vector.Vector;

public class LabyrinthGenerator {
	
	public static LabyrinthStructure generate(int xsize, int ysize, double probability) {
		boolean[][] marked = new boolean[xsize][ysize];
		marked[(int)(xsize * Math.random())][(int)(ysize * Math.random())] = true;
		LabyrinthStructure structure = new LabyrinthStructure(xsize, ysize);
		fill(structure);
		
		Vector<Coordinate> markedLocations = new Vector<Coordinate>();
		Vector<Coordinate> unmarkedLocations = new Vector<Coordinate>();
		do {
			markedLocations.clear();
			unmarkedLocations.clear();
			for(int x = 0; x < structure.xsize(); x++)
				for(int y = 0; y < structure.ysize(); y++)
					if(marked[x][y])
						markedLocations.add(new Coordinate(x, y));
					else
						unmarkedLocations.add(new Coordinate(x, y));
			if(unmarkedLocations.size() > 0) {
				Coordinate start = null;
				do {
					start = markedLocations.get((int)(markedLocations.size() * Math.random()));
					if(Math.random() < probability)
						start = unmarkedLocations.get((int)(unmarkedLocations.size() * Math.random()));
				} while(!structure.areThereWalls(start.x, start.y));
				if(markedLocations.size() > 1)
					marked[start.x][start.y] = false;
				PathBuilder.build(structure, start, marked);
			}
		} while(unmarkedLocations.size() > 0);
		return structure;
	}

	private static void fill(LabyrinthStructure structure) {
		for(int x = 0; x < structure.xsize(); x++)
			for(int y = 0; y < structure.ysize(); y++)
				for(int d = 0; d < 4; d++)
					structure.addWall(x, y, d);
	}

}
