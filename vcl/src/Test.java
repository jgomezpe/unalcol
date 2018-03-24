import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import unalcol.fx.FXView;

public class Test {
	public static void main(String[] args){
		System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
		JComponent panel = new FXView("http://localhost/unalcol/javafxindex.html");
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
