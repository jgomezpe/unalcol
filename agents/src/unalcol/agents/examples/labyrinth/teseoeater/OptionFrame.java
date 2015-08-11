package unalcol.agents.examples.labyrinth.teseoeater;

import java.awt.*;
import javax.swing.*;

/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class OptionFrame extends JFrame {

  /**
	 * 
	 */
	private static final long serialVersionUID = 5449531048320230518L;
String fileDir = ".";
  FlowLayout flowLayout1 = new FlowLayout();
  GridLayout gridLayout2 = new GridLayout();
  ButtonGroup buttonGroup1 = new ButtonGroup();
  JCheckBox jCheckBox2 = new JCheckBox();
  JCheckBox jCheckBox3 = new JCheckBox();
  JCheckBox jCheckBox4 = new JCheckBox();
  JCheckBox jCheckBox5 = new JCheckBox();
  JCheckBox jCheckBox6 = new JCheckBox();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jPanel2 = new JPanel();

  TeseoEaterMainFrame frame;

  public OptionFrame( TeseoEaterMainFrame _frame ){
    frame = _frame;
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setSize(new Dimension(200, 264));
    this.setTitle("Options");
    this.getContentPane().setLayout(borderLayout1);
    gridLayout2.setColumns(2);
    gridLayout2.setRows(3);

    jCheckBox2.setText("Circular");
    jCheckBox2.setBounds(new Rectangle(30, 50, 150, 35));
    jCheckBox3.setText("White");
    jCheckBox3.setBounds(new Rectangle(30, 80, 150, 35));
    jCheckBox4.setText("Small");
    jCheckBox4.setBounds(new Rectangle(30, 110, 150, 35));
    jCheckBox5.setText("Light");
    jCheckBox5.setBounds(new Rectangle(30, 140, 150, 35));
    jCheckBox6.setText("Healthy");
    jCheckBox6.setBounds(new Rectangle(30, 170, 150, 35));

    jPanel2.setLayout(null);

    this.getContentPane().add(jPanel2, BorderLayout.CENTER);
    jPanel2.add(jCheckBox2, null);
    jPanel2.add(jCheckBox3, null);
    jPanel2.add(jCheckBox4, null);
    jPanel2.add(jCheckBox5, null);
    jPanel2.add(jCheckBox6, null);
  }


}
