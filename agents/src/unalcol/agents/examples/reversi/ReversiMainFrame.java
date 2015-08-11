/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.agents.examples.reversi;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import unalcol.agents.Agent;
import unalcol.agents.simulate.gui.EnvironmentView;
import unalcol.agents.simulate.gui.SimpleView;
import unalcol.agents.simulate.gui.WorkingPanel;

/**
 *
 * @author Jonatan
 */
public class ReversiMainFrame extends JFrame implements EnvironmentView{
  /**
	 * 
	 */
	private static final long serialVersionUID = -3330280926907525019L;
Agent white_agent;
  Agent black_agent;
  String fileDir = ".";
  String fileName = null;
  Reversi reversi = null;
  Thread thread = null;
  SimpleView view;

  // Graphic components
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout2 = new GridLayout();
  WorkingPanel drawArea = new WorkingPanel( new ReversiDrawer( ) );
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JLabel jLabel2 = new JLabel();
  JTextField jTextField2 = new JTextField();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
//  MultiChart multiChart1 = new MultiChart();
  JPanel jPanel3 = new JPanel();
  GridLayout gridLayout3 = new GridLayout();
  JLabel jLabelw = new JLabel();
  JLabel jLabelb = new JLabel();

  public ReversiMainFrame( Agent w_agent, Agent b_agent ) {
    view = new SimpleView( drawArea );
    white_agent = w_agent;
    black_agent = b_agent;
    reversi = new Reversi( white_agent, black_agent );
    reversi.setDelay(100);
    reversi.registerView(view);
    reversi.registerView(this);

//    sudoku.addAgent(agent);
    drawArea.getDrawer().setEnvironment( reversi );
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void envChanged( String command ){
    if (command.indexOf(DONE) == 0) {
      drawArea.setText(command);
      reversi.stop();
      thread = null;
      jButton2.setText("Simulate");
    }
  }


  private void jbInit() throws Exception {
    this.setSize(new Dimension(430, 540));
    this.setTitle("Reversi");
    this.getContentPane().setLayout(borderLayout2);
    jPanel2.setLayout(borderLayout1);


    gridLayout2.setColumns(2);
    gridLayout2.setRows(3);
//    multiChart1.setDataChart(new com.klg.jclass.chart.beans.MultiDataChartWrapper("(data1 SCATTER_PLOT)(data2 SCATTER_PLOT)","(data1 x1)(data2 x1)","(data1 y1)(data2 y1)"));
    jLabel1.setText("Dimension:");
    jTextField1.setPreferredSize(new Dimension(37, 20));
    jTextField1.setText("8");
    jLabel2.setText("Time (sec):");
    jTextField2.setPreferredSize(new Dimension(37, 20));
    jTextField2.setText("10");
    jButton1.setText("Init");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton1_actionPerformed(e);
      }
    });
    jButton2.setToolTipText("");
    jButton2.setText("Simulate");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jButton2_actionPerformed(e);
      }
    });
    this.getContentPane().add(jPanel2,  BorderLayout.CENTER);
    jPanel2.add(drawArea,  BorderLayout.CENTER);
    this.getContentPane().add(jPanel1,  BorderLayout.SOUTH);
    jPanel1.add(jLabel1, null);
    jPanel1.add(jTextField1, null);
    jPanel1.add(jLabel2, null);
    jPanel1.add(jTextField2, null);
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
    
    //this.getContentPane().add(jPanel3,  BorderLayout.EAST);
    gridLayout3.setColumns(1);
    gridLayout3.setRows(2);
    jPanel3.setLayout(gridLayout3);
    jLabelw.setText("White time:");
    jLabelb.setText("Black time:");
    jPanel3.add(jLabelw);
    jPanel3.add(jLabelb);
    
//    jTabbedPane1.add(drawArea,    "Data Set");
    this.addWindowListener( new WindowAdapter(){
      public void windowClosing( WindowEvent e ){
        white_agent.die();
        black_agent.die();
        thread = null;
        System.exit(0);
      } } );
  }


  void iterButton_actionPerformed(ActionEvent e) {
    // Here some code
  }

  public void view(){
//    drawArea.show_clusters = paramFrame.showClusterCheck.isSelected();
    drawArea.update();
  }


  void jButton1_actionPerformed(ActionEvent e) {
    int dim = Integer.parseInt( jTextField1.getText() );
    long time = 1000*Long.parseLong( jTextField2.getText() );
    Clock clock = new Clock(time, time);
    reversi.init(new Board(dim), clock);
    //reversi.init( emptyPlaces );
    drawArea.update();
    jLabelw.setText("White time:"+clock.white_time_string());
    jLabelb.setText("Black time:"+clock.black_time_string());
  }

  void jButton2_actionPerformed(ActionEvent e) {
    if( thread == null ){
      thread = new Thread( reversi );
      reversi.clock.init(true);
      reversi.run();
//      thread.start();
      jButton2.setText("Stop");
    }else{
      reversi.stop();
      thread = null;
      jButton2.setText("Simulate");
    }
  }
}