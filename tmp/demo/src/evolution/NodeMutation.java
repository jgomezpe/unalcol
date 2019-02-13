package evolution;

import unalcol.search.variation.Variation_1_1;

public class NodeMutation implements Variation_1_1<Node>{
	String[] codes = new String[]{"-9", "-8",};
	  /**
	   * Flips a bit in the given genome
	   * @param gen Genome to be modified
	   * @return Number of mutated bits
	   */
	  @Override
	  public Node apply(Node gen) {
		  gen = gen.clone(null);
		  int n = gen.weight();
		  int m = (int)(Math.random()*n);
		  Node mutate_node = gen.get(m);
		  int x = (int)(Math.random()*codes.length);
		  mutate_node.oper = codes[x];
		  return gen;
	  }
}
