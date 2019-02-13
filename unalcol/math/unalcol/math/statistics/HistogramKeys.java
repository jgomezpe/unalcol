package unalcol.math.statistics;

import java.util.Iterator;

import unalcol.collection.keymap.KeyValue;

public class HistogramKeys<K> implements Iterator<K>{
	protected Iterator<KeyValue<K,Integer>> iterator;

	public HistogramKeys( Histogram<K> histogram) {
		this.iterator = histogram.vector.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public K next() {
		return iterator.next().key();
	}
}