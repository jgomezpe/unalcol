package unalcol.io;

import java.util.Iterator;

import unalcol.types.collection.ClosableCollection;
import unalcol.types.collection.ShortTermMemoryIterator;

public abstract class ReaderAsCollection implements ClosableCollection<Integer>{
	protected boolean closed = false;
	protected int c;
	
	public ReaderAsCollection(){}
	
	protected abstract int get(); 
	
	protected Iterator<Integer> iter = new  Iterator<Integer>(){
		@Override
		public boolean hasNext() { return c!=-1; }

		@Override
		public Integer next() {
			int t = c;
			c=get();
			return t;
		}		
	};
	
	/**
	 * Linefeed character
	 */
	protected int LINEFEED = (int) '\n';
	/**
	 * Carriage return character
	 */
	protected int CARRIAGERETURN = (int) '\r';

	@Override
	public Iterator<Integer> iterator() {
		return new ShortTermMemoryIterator<Position2D,Integer>(iter) {
			@Override
			protected Position2D key(Integer c) {
				if (c == CARRIAGERETURN || (c == LINEFEED && data [pos] != CARRIAGERETURN)) {
					return new Position2D(extra[pos].row() + 1, 0);
				}else{
					int row = extra[pos].row();
					int col = extra[pos].column();
					if (col != LINEFEED) col++;
					return new Position2D(row,col);
				}		
			}
		};
	}
		
	@Override
	public boolean isEmpty() { return closed; }	
}