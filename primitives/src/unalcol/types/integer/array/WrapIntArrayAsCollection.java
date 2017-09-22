package unalcol.types.integer.array;

import java.util.Arrays;

import unalcol.types.collection.Location;
import unalcol.types.collection.array.ArrayLocation;
import unalcol.types.collection.array.Array;

public class WrapIntArrayAsCollection implements Array<Integer>{
	protected IntArray array;
	
	public WrapIntArrayAsCollection() { this(new IntArray()); }
	
	public WrapIntArrayAsCollection( IntArray array ) { this.array = array;	}
	
	@Override
	public Integer get(int index) throws ArrayIndexOutOfBoundsException{ return array.get(index); }

	@Override
	public Object[] toArray(){ return Arrays.stream( array.toArray() ).boxed().toArray( Integer[]::new ); }

	@Override
	public int size(){ return array.size(); }

	@Override
	public boolean add(Integer data){ return array.add(data); }

	@Override
	public void clear() { array.clear(); }

	@Override
	public boolean del(Integer data) {
		int k = findKey(data);
		if( k>=0 ){ 
			array.remove(k); 
			return true;
		} 
		return false;
	}

	@Override
	public boolean del(Location<Integer> locator) {
		if( locator instanceof ArrayLocation ){
			ArrayLocation<Integer> loc = ((ArrayLocation<Integer>)locator);
			return array.remove(loc.getPos());
		}	
		return false;
	}

	@Override
	public boolean add(int index, Integer data) throws ArrayIndexOutOfBoundsException { return array.add(index, data); }

	@Override
	public boolean remove(int index) throws ArrayIndexOutOfBoundsException { return array.remove(index); }

	@Override
	public void resize(int n){ array.resize(n);	}

	@Override
	public boolean set(int index, Integer data) throws ArrayIndexOutOfBoundsException { return array.set(index, data); }
}