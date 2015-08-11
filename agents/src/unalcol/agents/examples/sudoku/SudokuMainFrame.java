package unalcol.agents.examples.sudoku;

import unalcol.agents.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import unalcol.agents.simulate.util.*;
import unalcol.agents.simulate.gui.*;



/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */



public class SudokuMainFrame extends JFrame implements EnvironmentView{


  /**
	 * 
	 */
	private static final long serialVersionUID = 53664339002944745L;

Language language = null;

  Agent agent;
  String fileDir = ".";
  String fileName = null;
  Sudoku sudoku = null;
  Thread thread = null;
  SimpleView view;

  // Graphic components
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  FlowLayout flowLayout1 = new FlowLayout();
  JMenu jMenu1 = new JMenu();
  JMenuItem jMenuItem7 = new JMenuItem();
  JMenuItem jMenuItem6 = new JMenuItem();
  JMenuItem jMenuItem5 = new JMenuItem();
  JMenuItem jMenuItem4 = new JMenuItem();
  JMenuItem jMenuItem3 = new JMenuItem();
  JMenuItem jMenuItem2 = new JMenuItem();
  JMenuBar jMenuBar1 = new JMenuBar();
  JMenuItem jMenuItem1 = new JMenuItem();
  GridLayout gridLayout2 = new GridLayout();
  WorkingPanel drawArea = new WorkingPanel( new SudokuDrawer( ) );
  BorderLayout borderLayout2 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
//  MultiChart multiChart1 = new MultiChart();

  public SudokuMainFrame( Agent _agent, Language _language ) {
    view = new SimpleView( drawArea );
    agent = _agent;
    language = _language;
    sudoku = new Sudoku( agent, language, 20 );
    sudoku.setDelay(100);
    sudoku.registerView(view);
    sudoku.registerView(this);

//    sudoku.addAgent(agent);
    drawArea.getDrawer().setEnvironment( sudoku );
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void envChanged( String command ){
    if (command.indexOf(DONE) == 0) {
      sudoku.stop();
      thread = null;
      jButton2.setText("Simulate");
    }
  }


  private void jbInit() throws Exception {
    this.setJMenuBar(jMenuBar1);
    this.setResizable(false);
    this.setSize(new Dimension(430, 540));
    this.setTitle("sudoku");
    this.getContentPane().setLayout(borderLayout2);
    jPanel2.setLayout(borderLayout1);

    jMenu1.setText("File");

    jMenuItem2.setActionCommand("Save");
    jMenuItem2.setText("Save");

    // open menu item
    jMenuItem1.setText("Open");
    jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        loadButton_actionPerformed(e);
      }
    });

    gridLayout2.setColumns(2);
    gridLayout2.setRows(3);
//    multiChart1.setDataChart(new com.klg.jclass.chart.beans.MultiDataChartWrapper("(data1 SCATTER_PLOT)(data2 SCATTER_PLOT)","(data1 x1)(data2 x1)","(data1 y1)(data2 y1)"));
    jMenuItem4.setText("Save As..");
    jLabel1.setText("Initial Empty Places:");
    jTextField1.setPreferredSize(new Dimension(37, 20));
    jTextField1.setText("20");
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
    jPanel1.add(jButton1, null);
    jPanel1.add(jButton2, null);
//    jTabbedPane1.add(drawArea,    "Data Set");
    this.addWindowListener( new WindowAdapter(){
      public void windowClosing( WindowEvent e ){
        agent.die();
        thread = null;
        System.exit(0);
      } } );
    jMenu1.add(jMenuItem1);
    jMenu1.add(jMenuItem2);
    jMenu1.add(jMenuItem4);
    jMenuBar1.add(jMenu1);
  }


  void loadButton_actionPerformed(ActionEvent e) {
    JFileChooser file = new JFileChooser( fileDir );
    if( file.showOpenDialog(drawArea) == JFileChooser.APPROVE_OPTION ){
      fileDir = file.getSelectedFile().getAbsolutePath();
      fileName = file.getSelectedFile().getAbsolutePath();
//      loadFile();
    }
  }

  void iterButton_actionPerformed(ActionEvent e) {
    // Here some code
  }

  public void view(){
//    drawArea.show_clusters = paramFrame.showClusterCheck.isSelected();
    drawArea.update();
  }


  void jButton1_actionPerformed(ActionEvent e) {
    int emptyPlaces = Integer.parseInt( jTextField1.getText() );
    sudoku.init( emptyPlaces );
    drawArea.update();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    if( thread == null ){
      thread = new Thread( sudoku );
      sudoku.run();
//      thread.start();
      jButton2.setText("Stop");
    }else{
      sudoku.stop();
      thread = null;
      jButton2.setText("Simulate");
    }
  }


}
