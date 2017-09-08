package unalcol.math.metric;

import unalcol.services.MicroService;
import unalcol.sort.Order;
import unalcol.types.real.DoubleOrder;

public class DistanceOrder<T> extends MicroService<T> implements Order<T> {
	protected Distance<T> distance;
	protected T point;
	protected DoubleOrder order;
	
	public DistanceOrder( Distance<T> distance, T point ){
		this.distance = distance;
		this.point = point;
		this.order = new DoubleOrder();
	}

	@Override
	public int compare(T x, T y) {
		return ((Double)distance.apply(x,point)).compareTo(distance.apply(y,point));
	}
}