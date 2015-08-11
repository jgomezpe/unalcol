package unalcol.agents.examples.labyrinth.teseo.simple;
import unalcol.gui.io.FileFilter;
import unalcol.gui.log.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import unalcol.reflect.util.*;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */

public class SimpleTeseoJavaProgrammingFrame extends JFrame {
  /**
	 * 
	 */
	private static final long serialVersionUID = -4174738342893051854L;
String title = "Ambiente para Teseo";
  String fileName = null;
  String fileDir = ".";
  FlowLayout flowLayout1 = new FlowLayout();
  ButtonGroup buttonGroup1 = new ButtonGroup();
  JCheckBox jCheckBox1 = new JCheckBox();
  JCheckBox jCheckBox2 = new JCheckBox();
  JCheckBox jCheckBox3 = new JCheckBox();
  JCheckBox jCheckBox4 = new JCheckBox();
  BorderLayout borderLayout1 = new BorderLayout();
  JPanel jLogPanel = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JToolBar jToolBar1 = new JToolBar();
  JPanel jPanel2 = new JPanel();
  BorderLayout borderLayout2 = new BorderLayout();
  JButton jOpenButton = new JButton();
  JButton jSaveButton = new JButton();
  JButton jCompileButton = new JButton();
  LogPanel theLogPanel = new LogPanel();
  BorderLayout borderLayout3 = new BorderLayout();

  public LogPanel getLogPanel(){ return theLogPanel; }

  public SimpleTeseoJavaProgrammingFrame(){
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {
    this.setSize(new Dimension(600, 400));
    jTextArea1.setToolTipText("");
    jTextArea1.setVerifyInputWhenFocusTarget(true);
    jTextArea1.setText("");
//    jButton1.addActionListener(new InteractiveAgentFrame_jButton1_actionAdapter(this));
    this.setTitle(title);
    this.getContentPane().setLayout(borderLayout1);
    jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jScrollPane1.setAutoscrolls(true);
    jScrollPane1.setMaximumSize(new Dimension(261, 261));
    jScrollPane1.setMinimumSize(new Dimension(261, 261));
    jPanel2.setLayout(borderLayout2);
    jOpenButton.setToolTipText("Abrir");
    jOpenButton.setSelectedIcon(null);
    jOpenButton.setText("Abrir");
    jOpenButton.addActionListener(new
        SimpleTeseoJavaProgrammingFrame_jOpenButton_actionAdapter(this));
    jSaveButton.setText("Guardar");
    jSaveButton.addActionListener(new
        SimpleTeseoJavaProgrammingFrame_jSaveButton_actionAdapter(this));
    jCompileButton.setToolTipText("Compilar");
    jCompileButton.setText("Compilar");
    jCompileButton.addActionListener(new
        SimpleTeseoJavaProgrammingFrame_jCompileButton_actionAdapter(this));
    jLogPanel.setLayout(borderLayout3);
    jToolBar1.add(jOpenButton);
    jToolBar1.add(jSaveButton);
    jToolBar1.add(jCompileButton);
    jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);
    jScrollPane1.getViewport().add(jTextArea1, null);
    jPanel2.add(jToolBar1, java.awt.BorderLayout.NORTH);
    jPanel2.add(jLogPanel, java.awt.BorderLayout.SOUTH);
    this.getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);
    jLogPanel.add(this.theLogPanel, java.awt.BorderLayout.CENTER);
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
//      agent.setCommands( sb.toString() );
    }catch( Exception x ){ x.printStackTrace(); }
  }

  public static void main( String[] args ){
    SimpleTeseoJavaProgrammingFrame frame = new SimpleTeseoJavaProgrammingFrame();
    frame.setVisible(true);
  }

  public void jOpenButton_actionPerformed(ActionEvent actionEvent) {
    FileFilter filter = new FileFilter( "Archivos Agente (*.agt)" );
    filter.add("agt");
    JFileChooser file = new JFileChooser( fileDir );
    file.setFileFilter(filter);
    if( file.showOpenDialog(this) == JFileChooser.APPROVE_OPTION ){
      try {
        fileDir = file.getSelectedFile().getAbsolutePath();
        fileName = file.getSelectedFile().getName();
        BufferedReader reader = new BufferedReader( new FileReader(fileDir) );
        StringBuffer sb = new StringBuffer();
        String s = reader.readLine();
        while( s != null ){
          sb.append(s);
          sb.append('\n');
          s = reader.readLine();
        }
        jTextArea1.setText(sb.toString());
        reader.close();
        this.setTitle(title + " [" + fileName + "]");
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

  }

  public void jSaveButton_actionPerformed(ActionEvent actionEvent) {
    //    ii..
    FileFilter filter = new FileFilter( "Archivos Agente (*.agt)" );
    filter.add("agt");
    JFileChooser file = new JFileChooser( fileDir );
    file.setFileFilter(filter);
    if( file.showSaveDialog(this) == JFileChooser.APPROVE_OPTION ){
      try{
        fileDir = file.getSelectedFile().getAbsolutePath();
        fileName = file.getSelectedFile().getName();
        int pos = fileName.lastIndexOf(".agt");
        if( pos == -1 || pos != fileName.length()-4 ){
          fileDir += ".agt";
          fileName += ".agt";
        }
        FileWriter writer = new FileWriter(fileDir);
        writer.write(jTextArea1.getText());
        writer.close();
        this.setTitle(title + " [" + fileName + "]");
      }catch( Exception e ){
        e.printStackTrace();
      }
    }

  }

  public void jCompileButton_actionPerformed(ActionEvent actionEvent) {
    String path = "classes";

    StringBuffer sb = new StringBuffer();
    sb.append( "import unalcol.agents.examples.labyrinth.teseo.simple.*;\n" );
    sb.append( "import unalcol.agents.*;\n" );
    sb.append( "import unalcol.agents.examples.labyrinth.teseo.*;\n" );
    sb.append( "public class TheAgentProgram extends SimpleTeseoAgentProgram{\n" );
    sb.append( jTextArea1.getText() );
    sb.append( "\n}\n" );
    String fileName = JavaOS.systemPath("classes/TheAgentProgram");
    File file = new File(path);
    try{
      file.mkdir();
      FileWriter writer = new FileWriter(fileName+".java");
      writer.write( sb.toString() );
      writer.close();
      file = new File(fileName+".class");
      if( file.exists() ){
        file.delete();
      }
    }catch( Exception e ){
      e.printStackTrace();
    }

        unalcol.reflect.compiler.Compiler compiler = new unalcol.reflect.compiler.Compiler();
    String pathName = (new File(".")).getAbsolutePath();
    pathName = pathName.substring(0, pathName.length()-1);
    compiler.addClassPath(pathName+"lib/agents.jar");
    compiler.addClassPath(pathName+"examples.jar");
    try{
      if( compiler.run(fileName + ".java") ){
        JOptionPane.showMessageDialog(this,
            "Felicitaciones!!! No se presentaron errores de compilación");
      }else{
        JOptionPane.showMessageDialog(this, "Problemas!!! Se presentaron errores de compilación.\nLa descripción de los errores esta en el Tab error");
        this.theLogPanel.getErrorArea().setText(compiler.error());
      }
    }catch( Exception e ){
      JOptionPane.showMessageDialog(this, "Problemas!!! Se presentaron problemas al compilar.\nLa descripción de los errores esta en el Tab error");
      this.theLogPanel.getErrorArea().setText(e.getMessage());
    }
  }
}

class SimpleTeseoJavaProgrammingFrame_jCompileButton_actionAdapter
    implements ActionListener {
  private SimpleTeseoJavaProgrammingFrame adaptee;
  SimpleTeseoJavaProgrammingFrame_jCompileButton_actionAdapter(
      SimpleTeseoJavaProgrammingFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent actionEvent) {
    adaptee.jCompileButton_actionPerformed(actionEvent);
  }
}

class SimpleTeseoJavaProgrammingFrame_jSaveButton_actionAdapter
    implements ActionListener {
  private SimpleTeseoJavaProgrammingFrame adaptee;
  SimpleTeseoJavaProgrammingFrame_jSaveButton_actionAdapter(
      SimpleTeseoJavaProgrammingFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent actionEvent) {
    adaptee.jSaveButton_actionPerformed(actionEvent);
  }
}

class SimpleTeseoJavaProgrammingFrame_jOpenButton_actionAdapter
    implements ActionListener {
  private SimpleTeseoJavaProgrammingFrame adaptee;
  SimpleTeseoJavaProgrammingFrame_jOpenButton_actionAdapter(
      SimpleTeseoJavaProgrammingFrame adaptee) {
    this.adaptee = adaptee;
  }

  public void actionPerformed(ActionEvent actionEvent) {
    adaptee.jOpenButton_actionPerformed(actionEvent);
  }
}

/*class SimpleInteractiveAgentFrame_jButton1_actionAdapter implements java.awt.event.ActionListener {
  SimpleTeseoJavaProgrammingFrame adaptee;

  InteractiveAgentFrame_jButton1_actionAdapter(SimpleTeseoJavaProgrammingFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}
*/
