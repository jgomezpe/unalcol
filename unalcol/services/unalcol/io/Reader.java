package unalcol.io;

import java.util.Iterator;

import unalcol.collection.Closable;
import unalcol.iterator.Core;
import unalcol.iterator.Position2DTrack;
import unalcol.iterator.PositionTrack;

public abstract class Reader implements Closable<Integer>{
	/**
	 * Linefeed character
	 */
	public static int LINEFEED = (int) '\n';
	/**
	 * Carriage return character
	 */
	public static int CARRIAGERETURN = (int) '\r';
	
	protected boolean closed = false;
	protected int c;
	protected int src;
	
	public Reader(){ this(0); }
	public Reader(int src){ this.src=src; }
	
	public int src(){ return src; }
	public void setSrc( int src ){ this.src = src; }	
	
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
	
	protected class ReaderIterator extends Core<Integer>{
		public ReaderIterator(Iterator<Integer> iter) {
			super(iter);
			extra[0]=new Position2DTrack(src(),0,0,0);
		}

		@Override
		protected PositionTrack pos(Integer c) {
			Position2DTrack p = (Position2DTrack)extra[(n+pos-1)%n];
			if (c == CARRIAGERETURN || (c == LINEFEED && (int)data [pos] != CARRIAGERETURN)) {
				return new Position2DTrack(src(),0,p.row() + 1, 0);
			}else{
				int row = p.row();
				int col = p.column();
				if (c != LINEFEED) col++;
				return new Position2DTrack(src(),0,row,col);
			}		
		}
	}

	@Override
	public Iterator<Integer> iterator() {
		return new ReaderIterator(iter);
	}
		
	@Override
	public boolean isEmpty() { return closed; }
	
	public abstract int get(); 
}