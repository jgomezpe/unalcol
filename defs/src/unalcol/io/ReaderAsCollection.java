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
	
	protected class ReaderIterator extends ShortTermMemoryIterator<Position2D, Integer>{
		public ReaderIterator(Iterator<Integer> iter) {
			super(iter);
			extra[0]=new Position2D(0,0,0,0);
		}

		@Override
		protected Position2D key(Integer c) {
			Position2D p = (Position2D)extra[(n+pos-1)%n];
			if (c == CARRIAGERETURN || (c == LINEFEED && (int)data [pos] != CARRIAGERETURN)) {
				return new Position2D(0,0,p.row() + 1, 0);
			}else{
				int row = p.row();
				int col = p.column();
				if (c != LINEFEED) col++;
				return new Position2D(0,0,row,col);
			}		
		}
		
	}

	@Override
	public Iterator<Integer> iterator() {
		return new ReaderIterator(iter);
	}
		
	@Override
	public boolean isEmpty() { return closed; }	
}