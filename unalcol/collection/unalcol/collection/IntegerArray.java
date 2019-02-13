package unalcol.collection;
import java.util.Arrays;

import unalcol.integer.Array;

public class IntegerArray implements unalcol.collection.Array<Integer>{
	protected Array array;
	
	public IntegerArray() { this(new Array()); }
	
	public IntegerArray( Array array ) { this.array = array;	}
	
	@Override
	public Integer get(Integer index){ return array.get(index); }

	@Override
	public Object[] toArray(){ return Arrays.stream( array.toArray() ).boxed().toArray( Integer[]::new ); }

	@Override
	public int size(){ return array.size(); }

	@Override
	public boolean add(Integer data){ return array.add(data); }

	@Override
	public void clear() { array.clear(); }

	@Override
	public boolean add(Integer index, Integer data){ return array.add(index, data); }

	@Override
	public boolean remove(Integer index){ return array.remove(index); }

	@Override
	public void resize(int n){ array.resize(n);	}

	@Override
	public boolean set(Integer index, Integer data){ return array.set(index, data); }
}