package unalcol.agents.simulate.gui;


import java.awt.*;
import javax.swing.*;



/**
 * Panel to draw a fuzzy space, and highlight a specific set and value
 */
public class WorkingPanel extends JPanel{

  /**
	 * Serialization purposes 
	 */
  private static final long serialVersionUID = -2539228143965567850L;
  
  DrawPanel drawer = new DrawPanel();
  JPanel jPanel1 = new JPanel();
  BorderLayout borderLayout1 = new BorderLayout();
  JTextPane logPane = new JTextPane();
  BorderLayout borderLayout2 = new BorderLayout();
  JScrollPane jScrollPane1 = new JScrollPane();

  /**
   * Default constructor
   */
  public WorkingPanel() {
      this( null );
  }

  /**
   * Default constructor
   */
  public WorkingPanel( Drawer drawer ) {
   this.drawer.setDrawer(drawer);
   setBackground(new Color(255,255,255));
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public Drawer getDrawer(){
    return drawer.getDrawer();
  }

  public void setDrawer( Drawer drawer ) {
    this.drawer.setDrawer(drawer);
    setBackground(new Color(255,255,255));
  }


  public void update(){
//    System.out.println("trying" );
    repaint();
  }

  public void setText( String text ){
    if( text != null && text != "")  logPane.setText( logPane.getText() + text + "\n" );
  }

  private void jbInit() throws Exception {
    this.setLayout(borderLayout1);
    jPanel1.setLayout(borderLayout2);
    logPane.setToolTipText("");
    logPane.setEditable(false);
    logPane.setText("");
    jPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
    jPanel1.setPreferredSize(new Dimension(11, 60));
    this.add(drawer, BorderLayout.CENTER);
    this.add(jPanel1, BorderLayout.SOUTH);
    jPanel1.setMaximumSize(new Dimension(11, 60));
    jPanel1.setMinimumSize(new Dimension(11, 60));
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    jScrollPane1.getViewport().add(logPane, null);
    jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);
  }
}
