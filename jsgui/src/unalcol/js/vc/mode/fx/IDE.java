package unalcol.js.vc.mode.fx;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import unalcol.js.Util;
import unalcol.js.vc.JSPreModel;
import unalcol.js.vc.mode.fx.FXPanel;
import unalcol.vc.BackEnd;
import unalcol.vc.FrontEnd;
import unalcol.vc.VCModel;

public class IDE{
	public static void main(String[] args){
		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
		// Getting the parameters 
		String url = Util.value(args, "url");
		String pack = Util.value(args,"pack");
		if( pack==null ) pack="";
		String file = Util.value(args,"file");
		if( file==null ) file="main.xml";
		String params = "index.html?mode=fx&pack="+pack+"&file="+file;
		System.out.println(params);
		//String unalcol_url = "http://localhost/unalcol/";
		JSPreModel m = JSPreModel.get(url,pack);
		BackEnd backend = m.backend();
		FXPanel panel = new FXPanel(url+params, m.frontend());
		FrontEnd frontend = panel.frontend();
		new VCModel(backend, frontend);
        // Run this later:
        SwingUtilities.invokeLater(new Runnable() {  
            @Override
            public void run() {  
                final JFrame frame = new JFrame();  
                 
                frame.getContentPane().add(panel);  
        		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        		int width = (int)screenSize.getWidth();
        		int height = (int)screenSize.getHeight();
        		frame.setSize(new Dimension(width*4/5, height*4/5));                 
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
                frame.setVisible(true);  
            }  
        });     
	}
}