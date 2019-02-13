package unalcol.agents.simulate.util;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class InteractiveAgentFrame extends JFrame {

  /**
	 * 
	 */
	private static final long serialVersionUID = 4224709272829020625L;

InteractiveAgentProgram agent = null;

  String fileDir = ".";
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout2 = new GridLayout();
  ButtonGroup buttonGroup1 = new ButtonGroup();
  JCheckBox jCheckBox1 = new JCheckBox();
  JCheckBox jCheckBox2 = new JCheckBox();
  JCheckBox jCheckBox3 = new JCheckBox();
  JCheckBox jCheckBox4 = new JCheckBox();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel1 = new JPanel();
  JButton jButton1 = new JButton();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();

  public InteractiveAgentFrame( InteractiveAgentProgram _agent ){
    agent = _agent;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    jTextArea1.setToolTipText("");
    jTextArea1.setVerifyInputWhenFocusTarget(true);
    jTextArea1.setText("Prof Jonatan says: Write your actions here, line by line");
    jButton1.setToolTipText("");
    jButton1.setText("Apply");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
          jButton1_actionPerformed(e);
      }
    });  
    this.setSize(new Dimension(200, 264));
    this.setTitle("Program");
    this.getContentPane().setLayout(borderLayout1);
    gridLayout2.setColumns(2);
    gridLayout2.setRows(3);
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPane1.setAutoscrolls(true);
    jScrollPane1.setMaximumSize(new Dimension(261, 261));
    jScrollPane1.setMinimumSize(new Dimension(23, 23));
    this.getContentPane().add(jPanel1, BorderLayout.SOUTH);
    jPanel1.add(jButton1, null);
    this.getContentPane().add(jScrollPane1,  BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTextArea1, null);


  }


  void jButton1_actionPerformed(ActionEvent e) {
    try{
      StringBuffer sb = new StringBuffer();
      int n = jTextArea1.getLineCount();
      for (int i = 0; i < n; i++) {
        int start = jTextArea1.getLineStartOffset(i);
        int end = jTextArea1.getLineEndOffset(i);
        String line;
        if( i == n - 1 ){
          line = jTextArea1.getText(start, end - start);
        }else{
          line = jTextArea1.getText(start, end - start - 1);
        }

        sb.append(line);
        sb.append(",");
      }
      agent.setCommands( sb.toString() );
    }catch( Exception x ){ x.printStackTrace(); }
  }
}

