package unalcol.types.collection.tree.bplus.file;

import java.io.IOException;
import java.io.RandomAccessFile;

import unalcol.types.collection.tree.bplus.BPlusInnerNode;
import unalcol.types.collection.tree.bplus.BPlusLeafNode;
import unalcol.types.collection.tree.bplus.BPlusNode;

public class FileNode<T> implements BPlusInnerNode<T>, BPlusLeafNode<T> {
	protected RandomAccessFile index_file = null;
	protected RandomAccessFile data_file = null;
    protected long pos = 0;
    protected int size=0;
    protected int length;
    
    public FileNode( RandomAccessFile index_file,
    		RandomAccessFile data_file,
    		int size, int length ){
    	this.index_file = index_file;
    	this.data_file = data_file;
    	this.size = size;
    	this.length = length;
    }
    
    public FileNode(  RandomAccessFile index_file,
    		RandomAccessFile data_file,
    		int size ){
    	this( index_file, data_file, size, 1 + SIZE_BYTES+(size+3)*REFERENCE_BYTES);
    }
    
    public void seekIndex(int shift) throws IOException{
    	index_file.seek(pos*length+shift);
    }
    
    public boolean isLeaf(){
    	try{
    		seekIndex(0);
    		return index_file.readBoolean();
    	}catch(IOException e){}
    	return false;
    }

    public boolean setLeaf( boolean isLeaf ){
    	try{
    		seekIndex(0);
    		index_file.writeBoolean(isLeaf);
    	}catch(IOException e){}
    	return false;
    }

    public void setPos( long pos ){
    	this.pos = pos;
    }
    
    protected static final int SIZE_BYTES = 2;
    protected static final int REFERENCE_BYTES = 8;
    
    // Size    
    @Override
    public int n(){
    	try{
    		seekIndex(1);
    		return index_file.readShort();
    	}catch(IOException e){}
    	return -1; 
    }

    @Override
    public void setn( int n ){
    	try{
    		seekIndex(1);
    		index_file.writeShort(n);
    	}catch(IOException e){} 
    }
    
    //Siblings
    @Override
    public BPlusNode<T> left(){
    	try{
    		seekIndex(1+SIZE_BYTES+REFERENCE_BYTES);
    		long index = index_file.readLong();
    		FileNode<T> node = (FileNode<T>)newInstance(size);
    		node.setPos(index);
    		return node;
    	}catch(IOException e){} 
        return null;
    }

    @Override
    public void setLeft( BPlusNode<T> node ){
    	try{
    		FileNode<T> fnode = (FileNode<T>)node;
    		seekIndex(1+SIZE_BYTES+REFERENCE_BYTES);
    		index_file.writeLong(fnode.pos);
    	}catch(IOException e){} 
    }

    @Override
    public BPlusNode<T> right(){
    	try{
    		seekIndex(1+SIZE_BYTES+(REFERENCE_BYTES>>1));
    		long index = index_file.readLong();
    		FileNode<T> node = (FileNode<T>)newInstance(size);
    		node.setPos(index);
    		return node;
    	}catch(IOException e){} 
        return null;
    }
    
    @Override
    public void setRight( BPlusNode<T> node ){
    	try{
    		FileNode<T> fnode = (FileNode<T>)node;
    		seekIndex(1+SIZE_BYTES+(REFERENCE_BYTES>>1));
    		index_file.writeLong(fnode.pos);
    	}catch(IOException e){}
    }

    //Parent
    @Override
    public BPlusInnerNode<T> parent(){
    	try{
    		index_file.seek(1+SIZE_BYTES);
    		long index = index_file.readLong();
    		FileInnerNode<T> node = new FileInnerNode<T>(index_file,size);
    		node.setPos(index);
    		return node;
    	}catch(IOException e){} 
        return null;
    }

    @Override
    public void setParent( BPlusInnerNode<T> node ){
    	try{
    		@SuppressWarnings("unchecked")
			FileNode<T> fnode = (FileNode<T>)node;
    		index_file.seek(1+SIZE_BYTES);
    		index_file.writeLong(fnode.pos);
    	}catch(IOException e){}
    }  
    
    @Override
    public boolean isFull(){
        return n()==size();
    }
    
    @Override 
    public int underFillSize(){
        return size()/3;
    }
    
    @Override
    public boolean underFill(){
        return n() <= underFillSize();
    }

	@Override
	public BPlusNode<T> newInstance(int SIZE) {
		return new FileNode<T>(index_file, data_file, size, length);
	}

	@Override
	public T leftKey() {
		FileNode<T> mostL = (FileNode<T>)mostLeft();
		return null;
	}

	@Override
	public T updateLeftKey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void merge() {
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
	public void split() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BPlusLeafNode<T> newInstance(T[] keys, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T[] keys() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T key(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insert(int pos, T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean append(T key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set(int i, T key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BPlusInnerNode<T> newInstance(BPlusNode<T>[] nodes, int n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BPlusNode<T>[] next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BPlusNode<T> next(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean append(BPlusNode<T> key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(int pos, BPlusNode<T> key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(int pos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void set(int i, BPlusNode<T> key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BPlusLeafNode<T> mostLeft() {
        if(isLeaf())
            return this;
        else
            return ((FileNode<T>)next(0)).mostLeft();
	}    
}