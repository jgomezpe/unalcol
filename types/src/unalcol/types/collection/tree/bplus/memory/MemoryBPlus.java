package unalcol.types.collection.tree.bplus.memory;

import unalcol.sort.Order;
import unalcol.types.collection.tree.bplus.BPlus;
import unalcol.types.collection.tree.bplus.BPlusInnerNode;
import unalcol.types.collection.tree.bplus.BPlusLeafNode;

public class MemoryBPlus<T> extends BPlus<T> {

	public MemoryBPlus(Order order) {
		super(order);
	}

	public MemoryBPlus(int BRANCHING, Order order) {
		super(BRANCHING, order);
	}

	@Override
	public BPlusInnerNode<T> innerNode() {
		return new MemoryInnerNode<T>(BRANCHING);
	}

	@Override
	public BPlusLeafNode<T> leafNode() {
		return new MemoryLeafNode<T>(BRANCHING);
	}

}
