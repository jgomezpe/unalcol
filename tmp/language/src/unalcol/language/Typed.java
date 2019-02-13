package unalcol.language;

public class Typed {
	protected int type;
	
	public Typed( int type ){
		this.type = type;
	}
	
	public int type(){
		return type;
	}
	
	public void setType( int type ){
		this.type = type;
	}
}