package unalcol.vc.fx;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JPanel;

import com.sun.javafx.application.PlatformImpl;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import netscape.javascript.JSObject;
import unalcol.reflect.plugin.PlugInManager;
import unalcol.vc.Controller;
import unalcol.vc.PlugInController;
import unalcol.vc.View;

public class FXView extends JPanel implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8961808032259120585L;

	protected Stage stage;  
	protected WebView browser;  
	protected JFXPanel jfxPanel;  
	protected WebEngine webEngine;
	protected String index;
	protected String url;
	protected PlugInManager manager;

	public FXView( String url ){
		super();
		this.setMinimumSize(new Dimension());
		this.setPreferredSize(new Dimension());
		manager = new PlugInManager(url+"plugins/");
		this.index = url+"fx.html";
		initComponents();
		register( new PlugInController(manager) );
	}
	
	protected void initComponents(){  
		jfxPanel = new JFXPanel();  
		setLayout(new BorderLayout());  
		add(jfxPanel, BorderLayout.CENTER);           
		createScene();   
	}     
   
	protected boolean canRegisterControllers = false;
	protected int registered = 0;
	protected Vector<Controller> toRegister = new Vector<Controller>();
	
//	public void load(){ webEngine.loadContent(html_code); }
	public void load(){ 
		// process page loading
		webEngine.getLoadWorker().stateProperty().addListener(
				new ChangeListener<State>() {
		            public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, State oldState, State newState) {
						canRegisterControllers = (newState == State.SUCCEEDED);
						if( canRegisterControllers ){
							execute("function delTimer(id, delay){};");
							register();
						}
		            }
		        });
		webEngine.load(index); 
	}
	
	public Object execute( String js_command ){
		try{ return webEngine.executeScript(js_command); }catch( IllegalStateException e ){}
		FXDeamon deamon = new FXDeamon(this, js_command);
		Platform.runLater( deamon );
		while( !deamon.done() ){ try { Thread.sleep(10); } catch (InterruptedException e) { e.printStackTrace(); }}
		return deamon.data();
	}
	
	/** 
	 * createScene 
	 * 
	 * Note: Key is that Scene needs to be created and run on "FX user thread" 
	 *       NOT on the AWT-EventQueue Thread 
	 * 
	 */  
	private void createScene() {  
		PlatformImpl.startup(new Runnable() {  
			@Override
			public void run() {   
				stage = new Stage();  
				stage.setTitle("Unalcol Interface");  
				stage.setResizable(true);  
   
				Group root = new Group();  
				Scene scene = new Scene(root,80,20);  
				stage.setScene(scene);  
				browser = new WebView();
				browser.autosize();
				webEngine = browser.getEngine();
				load();
	           
				ObservableList<Node> children = root.getChildren();
				children.add(browser);                     
                 
				jfxPanel.setScene(scene);  
                
				scene.widthProperty().addListener(new ChangeListener<Number>() {
					@Override 
					public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {
						browser.setPrefWidth((double)newSceneWidth);
					}
				});
				scene.heightProperty().addListener(new ChangeListener<Number>() {
					@Override 
					public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) {
						browser.setPrefHeight((double)newSceneHeight);
					}
				});
			}
		});  
	}
	
	public void register(){
		JSObject win = (JSObject)webEngine.executeScript("window");
		for( int i=registered; i<toRegister.size(); i++ ){
			Controller x = toRegister.get(i);
			if( x.view() != this ) x.set(this);
			for( String s:x.id() )	win.setMember(s, x);
		}
		registered = toRegister.size();
	}

	@Override
	public boolean register(Controller c) {
		toRegister.add(c);
		if( canRegisterControllers ) register();
		return true;
	}	
}