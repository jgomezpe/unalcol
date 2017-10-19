package unalcol.sort;

import unalcol.services.MicroService;

public class DefaultComparator<T>  extends MicroService<T> implements Comparator<T>{
	@Override
	public boolean eq(T one, T two) { return one.equals(two); }
}