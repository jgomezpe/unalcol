package unalcol.math.function.iterative;

import unalcol.math.function.Function;

public abstract class StepFunction<I,O> extends Function<O,O>{
    public abstract O init(I input);
}
