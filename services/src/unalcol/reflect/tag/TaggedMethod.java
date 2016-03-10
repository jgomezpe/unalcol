package unalcol.reflect.tag;

public interface TaggedMethod<T,R> {
	public R apply( TaggedObject<T> object );
}
