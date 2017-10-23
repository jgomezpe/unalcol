package unalcol.data.file;

import java.io.IOException;
import java.io.RandomAccessFile;

import unalcol.instance.Load;
import unalcol.types.collection.array.Array;

public class FileArrayCollection<T> extends FileImmutableArrayCollection<T> implements Array<T> {

	public FileArrayCollection(RandomAccessFile file, Load<T> load) {
		super(file, load);
	}

	@Override
	public boolean add(T obj) {
		locate(size());
		try {
			file.write(load.store(obj));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void clear() {
		try {
			file.setLength(0);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	@Override
	public boolean del(T obj) {
		byte[] b = load.store(obj);
		byte[] bf = new byte[load.size()]; 
		long pos = 0;
		long length;
		try {
			length = file.length();
			boolean flag = false;
			while( pos < length && !flag ){
				file.read(bf);
				int i=0;
				while( i<bf.length && bf[i]==b[i] ){ i++; }
				flag = (i==bf.length);
			}
			if( flag )
				return remove( (int)(pos/load.size()) - 1 );
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	protected int BUFFER_SIZE = 10000000;

	@Override
	public boolean add(Integer index, T obj) throws ArrayIndexOutOfBoundsException {
		locate(index);
		long pos;
		try {
			pos = file.getFilePointer();
			long end = file.length();
			byte[] buffer = new byte[1];
			do{
				long start = Math.max(end-BUFFER_SIZE, pos);
				if( end-start != buffer.length)
					buffer = new byte[(int)(end-start)];
				file.seek(start);
				file.read(buffer);
				file.seek(start+load.size());
				file.write(buffer);
				end = start;
			}while(end>pos);
			file.seek(pos);
			file.write(load.store(obj));
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean remove(Integer index){
		locate(index);
		try {
			long start = file.getFilePointer()+load.size();
			long the_end = file.length();
			byte[] buffer = new byte[1];
			while(start<the_end){
				long end = Math.min(start+BUFFER_SIZE, the_end);
				if( end-start != buffer.length)
					buffer = new byte[(int)(end-start)];
				file.seek(start);
				file.read(buffer);
				file.seek(start-load.size());
				file.write(buffer);
				start = end;
			};
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean set(Integer index, T obj){
		locate(index);
		try {
			file.write(load.store(obj));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void resize(int data) {
		// @TODO: How to resize a file of data uhm???
	}
}