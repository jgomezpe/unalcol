package unalcol.models.automata.nondeterministic;
import unalcol.services.MicroService;
import unalcol.sort.*;


/**
 * <p>Title: TransitionOrder</p>
 *
 * <p>Description: An ordering mechanism for nfa transitions</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class TransitionOrder extends MicroService<Transition> implements Order<Transition> {
    public String getCanonicalName(){
        return Transition.class.getCanonicalName();
    }

    /**
     * Constructor
     */
    public TransitionOrder() {
    }

    /**
     * Determines if transition <i>one</i> is less than transition <i>two</i> in
     * the lexicographical order:
     * <p><i>one.start < two.start</i> or ( <i>one.start=two.start</i> and <i>one.end < two.end</i> )</p>
     * @param one First Transition
     * @param two Second Transition
     * @return <i>true</i> if transition <i>one</i> is less than transition </i>two</i> in
     * the lexicographical order, <i>false</i> otherwise.
     */
    public int compare(Transition one, Transition two) {
        int dif = one.start - two.start;
        return (dif==0)?one.end-two.end:dif;
    }
}
