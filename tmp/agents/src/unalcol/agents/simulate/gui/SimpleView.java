package unalcol.agents.simulate.gui;

public class SimpleView implements EnvironmentView {
  protected WorkingPanel panel;
  public SimpleView( WorkingPanel _panel ){
    panel = _panel;
  }

  public void envChanged( String command ){
    try{ Thread.sleep(100); }catch (Exception e) { e.printStackTrace(); }
    // There is just one agent
    if( command != null ){
      if (command.indexOf(ERROR) == 0) {
        // Show error
        panel.setText(command);
      }else {
          if (command.indexOf(DONE) == 0) {
            // Show error
            panel.setText("Congratulations!!. You have finished");
          }else {
            panel.update();
          }
      }
    }else{
      panel.update();
    }
  }
}

