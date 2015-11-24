package unalcol.types.collection.tree.bplus.memory;

import unalcol.sort.Order;
import unalcol.types.collection.tree.bplus.BPlus;
import unalcol.types.collection.tree.bplus.BPlusInnerNode;
import unalcol.types.collection.tree.bplus.BPlusLeafNode;

public class MemoryBPlus<T> extends BPlus<T> {

	public MemoryBPlus(int n, Order<T> order) {
		super(n, order);
	}

	@Override
	public BPlusInnerNode<T> innerNode(int n) {
		return new MemoryInnerNode<T>(n);
	}

	@Override
	public BPlusLeafNode<T> leafNode(int n) {
		return new MemoryLeafNode<T>(n);
	}

}
