package unalcol.vc;

public class JavaApp implements Controller{
	protected View vc; 
	public JavaApp(){}
	
	public void apply(String id, String txt){
		System.out.println("Comming from javascript " + id + " message " + txt);
	}
	
	public void home_button(){
		vc.execute("vcl_home_button()");
	}
	
	public void trash_button(){
		vc.execute("trace('Trash button pressed')");
		System.out.println("Trash button pressed");
	}

	@Override
	public String[] id(){ return new String[]{"vcl_contentBar","javafx_app"}; }

	@Override
	public void set(View view) {
		this.vc = view;
	}
}