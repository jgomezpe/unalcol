package unalcol.fx;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JPanel;

import com.sun.javafx.application.PlatformImpl;

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
import unalcol.vc.Controller;
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

	public FXView( String index ){
		super();
		this.setMinimumSize(new Dimension());
		this.setPreferredSize(new Dimension());
		this.index = index;
		initComponents();
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
		            }
		        });
		webEngine.load(index); 
	}
	
	public Object execute( String js_command ){
		return webEngine.executeScript(js_command);
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
				stage.setTitle("Java FX Web View Panel");  
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

	@Override
	public boolean register(Controller c) {
		System.out.println("registering...");
		toRegister.add(c);
		if( canRegisterControllers ){
			JSObject win = (JSObject)webEngine.executeScript("window");
			for( int i=registered; i<toRegister.size(); i++ ){
				Controller x = toRegister.get(i);
				c.set(this);
				for( String s:x.id() )	win.setMember(s, x);
			}
			registered = toRegister.size();
		}
		return true;
	}	
}