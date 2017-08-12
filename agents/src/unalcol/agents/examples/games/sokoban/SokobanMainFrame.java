package unalcol.agents.examples.games.sokoban;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

import unalcol.agents.Agent;
import unalcol.agents.simulate.gui.SimpleView;
import unalcol.agents.simulate.gui.WorkingPanel;
import unalcol.agents.simulate.util.InteractiveAgentProgram;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.gui.io.FileFilter;

public class SokobanMainFrame extends JFrame {


    /**
  	 * 
  	 */
  	private static final long serialVersionUID = 7061219818401973108L;

  protected SimpleLanguage language = null;

    protected String fileDir = ".";
    protected String fileName = null;
    protected Thread thread = null;

    protected Agent agent;
    protected SokobanEnvironment environment = null;
    protected SimpleView view;
    protected String title = "Labyrinth";

    // Graphic components
    protected JPanel jPanel2 = new JPanel();
    protected BorderLayout borderLayout1 = new BorderLayout();
    protected FlowLayout flowLayout1 = new FlowLayout();
    protected JMenu jMenu1 = new JMenu();
    protected JMenuItem jMenuItem7 = new JMenuItem();
    protected JMenuItem jMenuItem6 = new JMenuItem();
    protected JMenuItem jMenuItem5 = new JMenuItem();
    protected JMenuItem jMenuItem4 = new JMenuItem();
    protected JMenuItem jMenuItem3 = new JMenuItem();
    protected JMenuItem jMenuItem2 = new JMenuItem();
    protected JMenuBar jMenuBar1 = new JMenuBar();
    protected JMenuItem jMenuItem1 = new JMenuItem();
    protected JMenu jMenuAgentProgram = new JMenu();
    protected JMenuItem jMenuInteractiveAgentProgram = new JMenuItem();
    protected JMenuItem jMenuLoadAgentProgram = new JMenuItem();

    protected GridLayout gridLayout2 = new GridLayout();
    protected WorkingPanel drawArea = new WorkingPanel();
    protected BorderLayout borderLayout2 = new BorderLayout();
    protected JPanel jPanel1 = new JPanel();
    protected JLabel jLabel1 = new JLabel();
    protected JTextField jTextField1 = new JTextField();
    protected JLabel jLabel2 = new JLabel();
    protected JTextField jTextField2 = new JTextField();
    protected JButton jButton1 = new JButton();
    protected JButton jButton2 = new JButton();
  //  MultiChart multiChart1 = new MultiChart();

    public SokobanEnvironment newEnvironmentInstance(){
      environment = new SokobanEnvironment( agent, new SokobanBoard(SokobanEnvironment.DEFAULT_SIZE));
      return environment;
    }

    public void initEnvironment(){
        if( drawArea.getDrawer()==null ) drawArea.setDrawer(new SokobanBoardDrawer());
        environment = this.newEnvironmentInstance();
        environment.setAgentPosition( 0, 0, 0);
        environment.setDelay(100);
        drawArea.getDrawer().setEnvironment( environment );
        environment.registerView(view);
    }

    public SokobanMainFrame( Agent _agent, SimpleLanguage _language ) {
      this( "Sokoban", _agent, _language );
    }

    public SokobanMainFrame( String _title, Agent _agent, SimpleLanguage _language ) {
      title = _title;
      view = new SimpleView( drawArea );
      agent = _agent;
      language = _language;
      environment = this.newEnvironmentInstance();
      this.initEnvironment();
      try {
        jbInit();
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
    
    protected void initMenu(){
      this.setJMenuBar(jMenuBar1);

      jMenu1.setText("Archivo");

      jMenuItem2.setActionCommand("Guardar");
      jMenuItem2.setText("Guardar");
      jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          jMenuItem2_actionPerformed(e);
        }
      });

      // open menu item
      jMenuItem1.setText("Abrir");
      jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          loadButton_actionPerformed(e);
        }
      });

      jMenuItem4.setText("Guardar Como..");
      jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          jMenuItem4_actionPerformed(e);
        }
      });

      jMenu1.add(jMenuItem1);
      jMenu1.add(jMenuItem2);
      jMenu1.add(jMenuItem4);
      jMenuBar1.add(jMenu1);

      this.jMenuAgentProgram.setText("Control Agente");
      this.jMenuInteractiveAgentProgram.setText("Interactivo");
      this.jMenuAgentProgram.add(this.jMenuInteractiveAgentProgram);
      this.jMenuInteractiveAgentProgram.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          jMenuInteractiveAgentProgram_actionPerformed(e);
        }
      });
      this.jMenuLoadAgentProgram.setText("Cargar");
      this.jMenuAgentProgram.add(this.jMenuLoadAgentProgram);
      this.jMenuLoadAgentProgram.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
          jMenuLoadAgentProgram_actionPerformed(e);
        }
      });

      jMenuBar1.add(this.jMenuAgentProgram);

    }

    protected void jbInit() throws Exception {
//      this.setResizable(false);
      this.setSize(new Dimension(430, 730));
      this.setTitle(title);
      this.getContentPane().setLayout(borderLayout2);
      jPanel2.setLayout(borderLayout1);


      gridLayout2.setColumns(2);
      gridLayout2.setRows(3);
//      multiChart1.setDataChart(new com.klg.jclass.chart.beans.MultiDataChartWrapper("(data1 SCATTER_PLOT)(data2 SCATTER_PLOT)","(data1 x1)(data2 x1)","(data1 y1)(data2 y1)"));
      drawArea.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(MouseEvent e) {
          drawArea_mouseClicked(e);
        }
      });
      jLabel1.setText("Agent Position X:");
      jTextField1.setPreferredSize(new Dimension(37, 20));
      jTextField1.setText("0");
      jLabel2.setText("Y");
      jTextField2.setPreferredSize(new Dimension(37, 20));
      jTextField2.setText("0");
      jButton1.setText("Locate");
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
      jPanel2.add(drawArea,  BorderLayout.CENTER);
      this.getContentPane().add(jPanel2,  BorderLayout.CENTER);
      this.getContentPane().add(jPanel1,  BorderLayout.SOUTH);
      jPanel1.add(jLabel1, null);
      jPanel1.add(jTextField1, null);
      jPanel1.add(jLabel2, null);
      jPanel1.add(jTextField2, null);
      jPanel1.add(jButton1, null);
      jPanel1.add(jButton2, null);

      // Closing the window
      this.addWindowListener( new WindowAdapter(){
        public void windowClosing( WindowEvent e ){
          environment.stop();
          thread = null;
          System.exit(0);
        } } );

      this.initMenu();
    }


	protected void loadButton_actionPerformed(ActionEvent e) {
		FileFilter filter = new FileFilter( FILE +" (*"+SKB+")" );
		filter.add(SKB.substring(1));
		JFileChooser file = new JFileChooser( fileDir );
		file.setFileFilter(filter);
		if( file.showOpenDialog(drawArea) == JFileChooser.APPROVE_OPTION ){
			fileDir = file.getSelectedFile().getAbsolutePath();
			fileName = file.getSelectedFile().getAbsolutePath();
			loadFile();
		}
	}

    protected void loadFile(){
      this.setTitle(title + " ["+fileName+"]");
      this.initEnvironment();
      environment.load( fileName );
      view();
    }

    protected void iterButton_actionPerformed(ActionEvent e) {
      // Here some code
    }

    public void view(){
//      drawArea.show_clusters = paramFrame.showClusterCheck.isSelected();
      drawArea.update();
    }

    protected void drawArea_mouseClicked(MouseEvent e) {
      int X = e.getX();
      int Y = e.getY();
      environment.edit(X, Y);
      drawArea.update();
    }

    
    public final static String FILE = "Archivos Sokoban";
    public final static String SKB = ".skb";

	protected void jMenuItem4_actionPerformed(ActionEvent e) {
		FileFilter filter = new FileFilter( FILE +" (*"+SKB+")" );
		filter.add(SKB.substring(1));
		JFileChooser file = new JFileChooser( fileDir );
		file.setFileFilter(filter);
		if( file.showSaveDialog(drawArea) == JFileChooser.APPROVE_OPTION ){
			fileDir = file.getSelectedFile().getAbsolutePath();
			fileName = file.getSelectedFile().getAbsolutePath();
			environment.save( fileName );
			this.setTitle(title + " ["+fileName+"]");
		}
	}

    protected void jMenuItem2_actionPerformed(ActionEvent e) {
      if( fileName == null ){
        jMenuItem4_actionPerformed( e );
      }else{
        environment.save( fileName );
      }
    }

    protected void jMenuInteractiveAgentProgram_actionPerformed(ActionEvent e) {
      Agent agent = environment.getAgent();
      agent.setProgram(new InteractiveAgentProgram(this.language));
      environment.init(agent);
    }

    protected void jMenuLoadAgentProgram_actionPerformed(ActionEvent e) {
  	  /**
//      UnalcolJavaEnv javaEnv = new UnalcolJavaEnv();
      JFileChooser file = new JFileChooser( fileDir );
      if( file.showOpenDialog(drawArea) == JFileChooser.APPROVE_OPTION ){
        fileDir = file.getSelectedFile().getParent();
        fileName = file.getSelectedFile().getName();
        Loader ccl = new Loader();
        ccl.set(fileDir, fileDir, fileDir, fileDir);
  // Load the main class through our CCL
        String progClass = fileName.substring( 0,  fileName.indexOf('.') );
        try{
          Class clas = ccl.loadClass( progClass );

          AgentProgram agent_program = (AgentProgram)clas.newInstance();

          Class mainArgType[] = {(new SimpleLanguage(null,null)).getClass()};
          java.lang.reflect.Method main = clas.getMethod("setLanguage", mainArgType);
          Object argsArray[] = {language};
          main.invoke(agent_program, argsArray);

          Agent agent = labyrinth.getAgent();
          agent.setProgram(agent_program);
          labyrinth.init(agent);
        }catch( Exception ex ){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
      }
      */
    }


    protected void jButton1_actionPerformed(ActionEvent e) {
      int x = Integer.parseInt( jTextField1.getText() );
      int y = Integer.parseInt( jTextField2.getText() );
      environment.setAgentPosition( x, y, 0 );
      drawArea.update();
    }

    protected void jButton2_actionPerformed(ActionEvent e) {
      if( thread == null ){
        thread = new Thread( environment );
        environment.run();
//        thread.start();
        jButton2.setText("Stop");
      }else{
        environment.stop();
        thread = null;
        jButton2.setText("Simulate");
      }
    }
  }
