package unalcol.types.collection.tree.bplus.file;

import java.io.RandomAccessFile;

import unalcol.types.collection.tree.bplus.BPlusNode;
import unalcol.types.collection.tree.bplus.immutable.ImmutableInnerNode;
import unalcol.types.collection.tree.bplus.immutable.ImmutableNode;

public class FileBPlusNode<T> implements BPlusNode<T>{
	protected RandomAccessFile index_file = null;
	protected RandomAccessFile data_file = null;
    protected long pos = 0;
    protected int size=0;
    protected int length;

	@Override
	public ImmutableNode<T> newInstance(int SIZE) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T leftKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T updateLeftKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int n() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImmutableInnerNode<T> parent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableNode<T> left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableNode<T> right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString(int level) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setn(int n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(ImmutableInnerNode<T> node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLeft(ImmutableNode<T> node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRight(ImmutableNode<T> node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftShift() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightShift() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void merge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BPlusNode<T> split() {
		// TODO Auto-generated method stub
		return null;
	}

}
