/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.space;

/**
 *
 * @author jgomez
 */
public class SpaceMoveInfo<T> {
	protected T start;
	protected T end;
	protected double cost;
    
	public SpaceMoveInfo( T start, T end, double cost ){
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
    
	public T start(){ return start; }
	public T end(){ return end; }
	public double cost(){ return cost; } 
    
	public void setStart( T start ){ this.start = start; }
	public void setEnd( T end ){ this.end = end; }
	public void setCost( double cost ){ this.cost = cost; }   
}