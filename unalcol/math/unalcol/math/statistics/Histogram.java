package unalcol.math.statistics;

import java.util.Iterator;

import unalcol.sort.Order;
import unalcol.collection.keymap.KeyValue;
import unalcol.collection.keymap.KeyOrder;
import unalcol.collection.vector.Sorted;

public class Histogram<K> {
	protected Sorted<KeyValue<K, Integer>> vector;
		
	public Histogram( Order order ){
		vector = new Sorted<KeyValue<K, Integer>>(new KeyOrder<K,Integer>(order));
	}
	
	public void add( K key, int amount ){
		KeyValue<K, Integer> pair = new KeyValue<K, Integer>(key, amount);
		try{
			Integer index = vector.find(pair);
			pair = vector.get(index);
			pair.setValue( pair.value()+amount );
		}catch(Exception e){ vector.add(pair); }
	}
	
	public void inc( K key ){ add( key, 1 ); }
	
	public K mode(){
		try{
			int m = 0;
			for( int i=1; i<vector.size(); i++ )
				if( vector.get(i).value() > vector.get(m).value() ) m=i;
			return vector.get(m).key();
		}catch(Exception e){ return null; }
	}
	
	public String toString(){
		return vector.toString();
	}
	
	public void clear(){ vector.clear(); }
	
	public Iterator<K> keys(){ return new HistogramKeys<K>(this); }	
}