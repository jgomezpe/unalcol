package unalcol.data.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

import unalcol.instance.Load;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.array.ArrayCollectionIterator;

public class FileArrayCollection<T> implements ArrayCollection<T>{
	protected RandomAccessFile file = null;
	protected Load<T> load;
	
	public FileArrayCollection( RandomAccessFile file, Load<T> load ){
		this.file = file;
		this.load = load;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		try {
			return (int)this.file.length()/load.size();
		} catch (IOException e) {}
		return -1;
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() <= 0;
	}
	
	@Override
	public Iterator<T> iterator() {
        return new ArrayCollectionIterator<T>(0,this);
	}
	
	protected void locate( int index ){
		try {
			file.seek(index*load.size());
		} catch (IOException e) {}
	}
	
	@Override
	public T get(int index) throws ArrayIndexOutOfBoundsException {
		if( index<0 || index>=size() ) throw new ArrayIndexOutOfBoundsException(index);
		try {
			locate(index);
			byte[] b = new byte[load.size()];
			file.read(b);
			return load.load(b);
		} catch (IOException e) {}
		return null;
	}	
}