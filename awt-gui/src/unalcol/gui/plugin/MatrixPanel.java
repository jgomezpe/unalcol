package unalcol.gui.plugin;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class MatrixPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2621304587181022836L;
	protected double[] widths;
	protected double[] heights;
	protected JPanel inner=null;
	protected JComponent[] items;
	
	public MatrixPanel(){ this.setLayout(new BorderLayout()); }

	protected double[] weight( int[] weight ){
		double[] wc = new double[weight.length];
		wc[0] = 0.01;
		for( int i=1; i<weight.length; i++ ) wc[i] = (wc[0]*weight[i])/weight[0];
		return wc;
	}
	
	public void init( JComponent[] children, int[] rows, int[] columns ){
		widths = weight(rows);
		heights = weight(columns);
		if( inner != null ) for( JComponent c : this.items ) inner.remove(c);
		else{
			inner = new JPanel();
			this.add(inner,BorderLayout.CENTER);
		}
		int cols = widths.length;
		GridBagConstraints gbc;
		inner.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
    	gbc.weightx = gbc.weighty = 0.5;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        for( int i=0; i<items.length; i++){
        	if( items[i] != null ){
	            gbc.gridy = i/cols;
	            gbc.gridx = i%cols;
            	gbc.weightx = widths[gbc.gridx];
            	gbc.weighty = heights[gbc.gridy];
	            gbc.insets = new Insets(2, 2, 2, 2);
	            inner.add(items[i], gbc);
        	}
        }
        inner.invalidate();
	}
}