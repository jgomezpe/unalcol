package unalcol.agents.examples.labyrinth.variable;

import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JMenuItem;

import unalcol.agents.Agent;
import unalcol.agents.examples.labyrinth.Labyrinth;
import unalcol.agents.examples.labyrinth.LabyrinthDrawer;
import unalcol.agents.examples.labyrinth.LabyrinthMainFrame;
import unalcol.agents.examples.labyrinth.teseo.TeseoPerceptDrawer;
import unalcol.agents.examples.labyrinth.teseo.simple.SimpleTeseoJavaProgrammingFrame;
import unalcol.agents.simulate.util.SimpleLanguage;
import unalcol.gui.log.LogOutputStream;

public class TeseoVariableMainFrame extends LabyrinthMainFrame {
	protected double probability;
    /**
	 * 
	 */
	private static final long serialVersionUID = -5820288909843666028L;

	protected SimpleTeseoJavaProgrammingFrame ide = new SimpleTeseoJavaProgrammingFrame();

    protected JMenuItem jMenuEditAgentProgram = new JMenuItem();
    @SuppressWarnings("resource")
	public TeseoVariableMainFrame( Agent _agent, SimpleLanguage _language, double p ) {
        super( "Teseo", _agent, _language );
        this.probability = p;
        this.setSize(new Dimension(660, 740));
        this.jMenuEditAgentProgram.setText("Editar");
        //this.jMenuAgentProgram.add(this.jMenuEditAgentProgram);
        this.jMenuEditAgentProgram.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(ActionEvent e) {
            ide.setVisible(true);
        }
        });

        new LogOutputStream(ide.getLogPanel().getOutArea(), true);
        //System.setOut(new PrintStream(os));
    }
    
  public Labyrinth newLabyrinthInstance(){
    labyrinth = new TeseoVariableLabyrinth( agent, new int[Labyrinth.DEFAULT_SIZE][Labyrinth.DEFAULT_SIZE], probability );
    return labyrinth;
  }

  public void initLabyrinth(){
      if( drawArea.getDrawer()==null ) drawArea.setDrawer(new LabyrinthDrawer(new TeseoPerceptDrawer()));
      super.initLabyrinth();
  }
  
  protected void jMenuLoadAgentProgram_actionPerformed(ActionEvent e) {
	  /**
      Loader ccl = new Loader();
      ccl.set("lib", "classes", "resources", "classes" );
// Load the main class through our CCL
      String progClass = "TheAgentProgram";
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
        JOptionPane.showMessageDialog(this,"Felicitaciones!!! Su programa ha sido cargado");
      }catch( Exception ex ){
        JOptionPane.showMessageDialog(this,"Problemas!!! Se presentaron errores al cargar su programa");
      }
      */
    }
    
}
