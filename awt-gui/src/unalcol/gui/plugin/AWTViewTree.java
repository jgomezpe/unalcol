package unalcol.gui.plugin;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import unalcol.reflect.plugin.PlugInSet;
import unalcol.reflect.xml.XMLElement;
import unalcol.vc.frontend.ViewTree;

public class AWTViewTree extends ViewTree implements AWTView{
	public static String WIDTHS="widths";
	public static String HEIGHTS="heights";
	
	protected MatrixPanel panel;
	
	public AWTViewTree() {
		panel = new MatrixPanel();
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	
	@Override
	public JComponent awt(){ return panel; }
	
	protected int[] values( String str ){
		String[] split = str.split(",");
		int[] v = new int[split.length];
		for( int i=0; i<v.length; i++ ){
			v[i] = Integer.parseInt(split[i]);
		}
		return v;
	}

	protected int complete( int n, int m ){
		int k = n/m;
		if( k*m < n ) k++;
		return k;
	}
	
	protected int[] obtain( int k ){
		double s = 100.0/k;
		int[] v = new int[k];
		double ac=s;
		v[0] = (int)s;
		for( int i=1; i<k; i++ ){
			ac += s;
			v[i] = (int)(ac-v[i-1]);
		}
		return v;
	}
	
	protected int[] obtain( int n, int m ){ return obtain( complete( n, m ) ); }

	public void init(XMLElement element, PlugInSet<?> builder){
		super.init(element, builder);
		int n = children.size();
		if( n>0 ){
			String w = (String)element.getAttribute(WIDTHS);
			String h = (String)element.getAttribute(HEIGHTS);
			int[] ws;
			int[] hs;
			if( w != null ){
				ws = values(w);
				if( h!=null ) hs = values(h);
				else hs = obtain(n, ws.length);
			}else{
				if( h!=null ) hs = values(h);
				else hs = obtain((int)Math.sqrt(n));
				ws = obtain(n,hs.length);
			}
			JComponent[] c = new JComponent[n];
			for( int i=0; i<n; i++ ) c[i] = ((AWTView)children.get(i)).awt();
			panel.init(c, hs, ws);
		}
	}	
}