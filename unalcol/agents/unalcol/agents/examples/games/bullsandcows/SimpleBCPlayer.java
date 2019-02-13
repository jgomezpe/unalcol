package unalcol.agents.examples.games.bullsandcows;
import unalcol.collection.Vector;
import unalcol.random.raw.JavaGenerator;
/**
 *
 * @author Jonatan
 */
public class SimpleBCPlayer {
	protected NumberIndex ni;
	protected Vector<Integer> options = new Vector<Integer>();
	public SimpleBCPlayer( NumberIndex _ni ){
		ni = _ni;
		int s = ni.size();
		for( int i=0; i<s; i++ ) options.add(i);
	}

	public int[] next(){
		JavaGenerator g = new JavaGenerator();
		try{ return ni.getOption( options.get(g.integer(options.size())) ); }catch( Exception e){ return null; }
	}

	public boolean check( int[] option, int[] bc ){
		try{
			int i=0;
			while(  i<options.size() ){
				int[] opt = ni.getOption(options.get(i));            
				int[] bc2 = NumberIndex.compare(option, opt);
				if( bc2[0] != bc[0] || bc2[1] != bc[1] ) options.remove(i);
				else i++;
			}
		}catch(Exception e){}	
		return options.size()>0;
	}
}