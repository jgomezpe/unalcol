package unalcol.math.metric;

import unalcol.sort.Order;
import unalcol.types.real.DoubleOrder;

public class DistanceOrder<T> implements Order{
	protected Distance<T> distance;
	protected T point;
	protected DoubleOrder order;
	
	public DistanceOrder( Distance<T> distance, T point ){
		this.distance = distance;
		this.point = point;
		this.order = new DoubleOrder();
	}

	@SuppressWarnings("unchecked")
	@Override
	public int compare(Object x, Object y){ return ((Double)distance.apply((T)x,point)).compareTo(distance.apply((T)y,point)); }
}