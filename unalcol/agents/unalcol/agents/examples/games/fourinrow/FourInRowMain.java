/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.games.fourinrow;

/*import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants; */

import unalcol.agents.Agent;

/**
 *
 * @author Jonatan
 */
public class FourInRowMain {
  public static void main( String[] argv ){
	  Agent w_agent = new Agent( new DummyFourInRowAgentProgram("white") );
	  Agent b_agent = new Agent( new DummyFourInRowAgentProgram("black") );
	  FourInRowMainFrame frame = new FourInRowMainFrame( w_agent, b_agent );
	  frame.setVisible(true);
    // Reflection
/*      SwingUtilities.invokeLater(new Runnable() {
          public void run() {
        	  JPanel jPanel1 = new JPanel();
        	  BorderLayout borderLayout2 = new BorderLayout();
        	  JLabel jLabel1 = new JLabel();
        	  JTextField jTextField1 = new JTextField();
        	  JLabel jLabel2 = new JLabel();
        	  JTextField jTextField2 = new JTextField();
        	  JButton jButton1 = new JButton();
        	  JButton jButton2 = new JButton();
        	    jLabel1.setText("Dimension:");
        	    jTextField1.setPreferredSize(new Dimension(37, 20));
        	    jTextField1.setText("8");
        	    jLabel2.setText("Time (sec):");
        	    jTextField2.setPreferredSize(new Dimension(37, 20));
        	    jTextField2.setText("10");
        	    jButton1.setText("Init");
        	    jButton1.addActionListener(new java.awt.event.ActionListener() {
        	      public void actionPerformed(ActionEvent e) {
        	        System.out.println("Hello");
        	      }
        	    });
        	    jPanel1.add(jLabel1, null);
        	    jPanel1.add(jTextField1, null);
        	    jPanel1.add(jLabel2, null);
        	    jPanel1.add(jTextField2, null);
        	    jPanel1.add(jButton1, null);
        	    jPanel1.add(jButton2, null);

        	  JFrame frame = new JFrame("My JFrame Example");
              frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
              frame.setSize(new Dimension(430, 540));
              frame.getContentPane().setLayout(borderLayout2);

              frame.getContentPane().add(jPanel1,  BorderLayout.SOUTH);
              
              frame.setVisible(true);        	  
        	  
          }
      });*/    
  }
    
}
