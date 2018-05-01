package unalcol.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import unalcol.vc.controller.ControllerInstance;

public class AWTMainPanel  extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8986792404504692555L;

	protected AWTManager manager;

	public AWTMainPanel(String unalcol_url, String url){
		super();
		this.setMinimumSize(new Dimension());
		this.setPreferredSize(new Dimension());
		manager = new AWTManager(new String[]{ url, unalcol_url}, new ControllerInstance());
	}
	
}
