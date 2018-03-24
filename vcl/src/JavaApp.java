import unalcol.vc.Controller;
import unalcol.vc.View;

public class JavaApp implements Controller{
	protected View vc; 
	public JavaApp(){}
	
	public void apply(String id, String txt){
		System.out.println("Comming from javascript " + id + " message " + txt);
	}
	
	public void home_button(){
		vc.execute("unalcol_home_button()");
	}
	
	public void trash_button(){
		vc.execute("trace('Trash button pressed')");
		System.out.println("Trash button pressed");
	}

	@Override
	public String[] id(){ return new String[]{"unalcol_contentBar","unalcol_xml"}; }

	@Override
	public void set(View view) {
		this.vc = view;
	}
}