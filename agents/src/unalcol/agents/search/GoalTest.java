package unalcol.agents.search;

import unalcol.reflect.tag.TaggedObject;
import unalcol.search.Goal;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gómez
 * @version 1.0
 */
public interface GoalTest<T> extends Goal<T,Boolean> {
@Override
public default Boolean apply(TaggedObject<T> state) {
    // TODO Auto-generated method stub
    return apply(state.object());
}

@Override
public default boolean nonStationary() {
    return false;
}
}
