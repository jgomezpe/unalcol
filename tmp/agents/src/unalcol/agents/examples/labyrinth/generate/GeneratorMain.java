package unalcol.agents.examples.labyrinth.generate;

public class GeneratorMain {

	public static void main(String[] args) {
		LabyrinthStructure structure = LabyrinthGenerator.generate(15, 15, 0.8);
		System.out.println(structure.xsize() + " " + structure.ysize());
		for(int x = 0; x < structure.xsize(); x++) {
			for(int y = 0; y < structure.ysize(); y++)
				System.out.print(" " + structure.structure[x][y]);
			System.out.println();
		}
	}

}
