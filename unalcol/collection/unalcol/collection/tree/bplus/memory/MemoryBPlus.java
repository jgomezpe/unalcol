package unalcol.collection.tree.bplus.memory;

import unalcol.collection.tree.bplus.BPlus;
import unalcol.collection.tree.bplus.BPlusInnerNode;
import unalcol.collection.tree.bplus.BPlusLeafNode;
import unalcol.sort.Order;

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
