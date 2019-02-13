package unalcol.iterator;

import java.util.Iterator;

public interface Trackable<T> extends Iterator<T>{
	public PositionTrack pos();
}
