package unalcol.types.collection.tree.bplus.file;

import java.io.IOException;
import java.io.RandomAccessFile;

import unalcol.types.collection.tree.bplus.BPlusInnerNode;
import unalcol.types.collection.tree.bplus.BPlusNode;

public abstract class FileNode<T> implements BPlusNode<T> {
	protected RandomAccessFile index_file = null;
	protected RandomAccessFile data_file = null;
    protected long pos = 0;
    protected int size=0;
    
    public FileNode( RandomAccessFile index_file, int size ){
    	this.index_file = index_file;
    	this.size = size;
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
    		index_file.seek(pos);
    		return index_file.readShort();
    	}catch(IOException e){}
    	return -1; 
    }

    @Override
    public void setn( int n ){
    	try{
    		index_file.seek(pos);
    		index_file.writeShort(n);
    	}catch(IOException e){} 
    }
    
    public long filePos(){
    	return pos*(SIZE_BYTES+(size+3)*REFERENCE_BYTES);
    }
    
    //Siblings
    @Override
    public BPlusNode<T> left(){
    	try{
    		index_file.seek(filePos()+SIZE_BYTES+REFERENCE_BYTES);
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
    		index_file.seek(filePos()+SIZE_BYTES+REFERENCE_BYTES);
    		index_file.writeLong(fnode.pos);
    	}catch(IOException e){} 
    }

    @Override
    public BPlusNode<T> right(){
    	try{
    		index_file.seek(filePos()+SIZE_BYTES+2*REFERENCE_BYTES);
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
    		index_file.seek(filePos()+SIZE_BYTES+2*REFERENCE_BYTES);
    		index_file.writeLong(fnode.pos);
    	}catch(IOException e){}
    }

    //Parent
    @Override
    public BPlusInnerNode<T> parent(){
    	try{
    		index_file.seek(filePos()+SIZE_BYTES);
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
    		index_file.seek(filePos()+SIZE_BYTES);
    		index_file.writeLong(fnode.pos);
    	}catch(IOException e){}
    }        
}

