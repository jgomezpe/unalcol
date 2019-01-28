package core;

public class DemoC {
	protected DemoA x;
	public DemoC( DemoA x ){
		this.x = x;
	}
	public String toString(){
		return x.toString();
	}
}
