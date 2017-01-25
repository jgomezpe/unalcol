package unalcol.data.oodb.index;

import unalcol.types.collection.tree.bplus.BPlusNode;
import unalcol.types.collection.tree.bplus.immutable.ImmutableInnerNode;
import unalcol.types.collection.tree.bplus.immutable.ImmutableNode;

public class FileNode<T> implements BPlusNode<T>{

	@Override
	public ImmutableNode<T> left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T leftKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int n() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ImmutableNode<T> newInstance(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableInnerNode<T> parent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImmutableNode<T> right() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String toString(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T updateLeftKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void leftShift() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void merge() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rightShift() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setLeft(ImmutableNode<T> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(ImmutableInnerNode<T> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRight(ImmutableNode<T> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setn(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BPlusNode<T> split() {
		// TODO Auto-generated method stub
		return null;
	}

}
