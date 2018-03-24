package unalcol.vc;

public class Response {
	protected int delay;
	protected String msg;
	public Response( int delay ){ this.delay = delay; }
	public Response( String msg ){ this.msg = msg; }
	public boolean async(){ return delay>0; };
	public int delay(){ return delay; }
	public String message(){ return msg; }
	public String toString(){ return delay+"."+msg; }
}