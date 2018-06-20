package unalcol.math.statistics;

import java.util.Iterator;

import unalcol.sort.Order;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.types.collection.keymap.KeyOrder;
import unalcol.types.collection.vector.SortedVector;

public class Histogram<K> {
	protected SortedVector<KeyValue<K, Integer>> vector;
		
	public Histogram( Order order ){
		vector = new SortedVector<KeyValue<K, Integer>>(new KeyOrder<K,Integer>(order));
	}
	
	public void add( K key, int amount ){
		KeyValue<K, Integer> pair = new KeyValue<K, Integer>(key, amount);
		Integer index = vector.find(pair);
		if( index != null ){
			pair = vector.get(index);
			pair.setValue( pair.value()+amount );
		}else vector.add(pair);
	}
	
	public void inc( K key ){ add( key, 1 ); }
	
	public K mode(){
		if( vector.size() == 0 ) return null;
		int m = 0;
		for( int i=1; i<vector.size(); i++ ){
			if( vector.get(i).value() > vector.get(m).value() ) m=i;
		}
		return vector.get(m).key();
	}
	
	public String toString(){
		return vector.toString();
	}
	
	public void clear(){ vector.clear(); }
	
	public Iterator<K> keys(){ return new HistogramKeys<K>(this); }	
}