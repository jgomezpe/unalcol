package unalcol.language;

public class TypedValue<T> extends Typed{
	protected T value;
	
	public TypedValue( int type, T value ){
		super(type);
		this.value = value;
	}
	
	public T value(){ return value; }

	public void setValue( T value ){ this.value = value; }
	
	public String toString(){
		return "Type:"+type;
	}
}