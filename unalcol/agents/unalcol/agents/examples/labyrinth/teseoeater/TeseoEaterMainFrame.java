package unalcol.agents.examples.labyrinth.teseoeater;

import unalcol.agents.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import unalcol.agents.examples.labyrinth.*;
import unalcol.agents.simulate.util.*;



/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */



public class TeseoEaterMainFrame extends LabyrinthMainFrame {

  /**
	 * 
	 */
	private static final long serialVersionUID = 8575444705636357626L;
// Graphic components
  protected JMenu jMenu2;
  protected JMenuItem jMenuItem8;
  protected JMenuItem jMenuItem12;
  protected JMenuItem jMenuItem13;
  protected OptionFrame opt_frame = null;

  public TeseoEaterMainFrame( Agent _agent, SimpleLanguage _language ) {
    super( "Teseo Eater", _agent, _language );
    this.drawArea.setDrawer( new TeseoEaterLabyrinthDrawer( labyrinth ) );
    this.setSize(new Dimension(660, 740));
    opt_frame = new OptionFrame( this );
    opt_frame.setVisible(true);
  }

  public Labyrinth newLabyrinthInstance(){
    labyrinth = new TeseoEaterLabyrinth( agent, new int[Labyrinth.DEFAULT_SIZE][Labyrinth.DEFAULT_SIZE] );
    return labyrinth;
  }
  public void initLabyrinth(){
      if( drawArea.getDrawer()==null ) drawArea.setDrawer(new TeseoEaterLabyrinthDrawer());
      super.initLabyrinth();
  }
 
  protected void initMenu(){
    super.initMenu();
    jMenu2 = new JMenu();
    jMenu2.setText("Edit");
    jMenuItem8 = new JMenuItem();
    jMenuItem8.setText("Resource");
    jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem8_actionPerformed(e);
      }
    });

    jMenuItem12 = new JMenuItem();
    jMenuItem12.setToolTipText("");
    jMenuItem12.setActionCommand("Treasure");
    jMenuItem12.setText("Treasure");
    jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem12_actionPerformed(e);
      }
    });

    jMenuItem13 = new JMenuItem();
    jMenuItem13.setText("Agent");
    jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(ActionEvent e) {
        jMenuItem13_actionPerformed(e);
      }
    });

    jMenuBar1.add(jMenu2);
    jMenu2.add(jMenuItem8);
    jMenu2.add(jMenuItem12);
    jMenu2.add(jMenuItem13);
  }


  protected void drawArea_mouseClicked(MouseEvent e) {
    ((TeseoEaterLabyrinth)labyrinth).setResource( opt_frame.jCheckBox2.isSelected(),
                           opt_frame.jCheckBox3.isSelected(),
                           opt_frame.jCheckBox4.isSelected(),
                           opt_frame.jCheckBox5.isSelected(),
                           opt_frame.jCheckBox6.isSelected() );
      super.drawArea_mouseClicked(e);
  }




  void jMenuItem8_actionPerformed(ActionEvent e) {
    opt_frame.setVisible(true);
    ((TeseoEaterLabyrinth)labyrinth).setOption( TeseoEaterLabyrinth.RESOURCE );
  }

  void jMenuItem12_actionPerformed(ActionEvent e) {
    // set the treasure position
    ((TeseoEaterLabyrinth)labyrinth).setOption( TeseoEaterLabyrinth.TREASURE );
  }

  void jMenuItem13_actionPerformed(ActionEvent e) {
    // set the agent position
    ((TeseoEaterLabyrinth)labyrinth).setOption( TeseoEaterLabyrinth.AGENT );
  }


}
